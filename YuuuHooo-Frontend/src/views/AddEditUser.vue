<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, onMounted, computed } from "vue";
import sasNav from "../components/sasNav.vue";

const API_ROOT = import.meta.env.VITE_ROOT_API;
const router = useRouter();
const {params} = useRoute();

// console.log(params.id)
const userInfo = ref({})
const type = ref(params.type)
onMounted(async () => {
    if(params.id !== undefined){
        type.value = "edit"
        // console.log(type.value)
        try {
            const res = await fetch(`${API_ROOT}/api/users/${params.id}`);
        if (res.status === 200) {
            userInfo.value = await res.json();
            console.log(userInfo.value)
        } else if (res.status !== 200) {
            alert("The requested page is not available!");
            router.push("/admin/users");
            } else 
                throw new Error("No Announcement");
        } catch (error) {
            console.log(error);
        }
    }else if(params.id === undefined){
        type.value = "add"
        // console.log(type.value)
        userInfo.value = {
            username:"",
            name:"",
            email:"",
            role:""
        }

        console.log(userInfo.value)
    }
})

const cancelAdding = (type) => {
    //  confirm("Do you want to cancel adding a user?") === true ? type === 'user' ? router.push('/admin/user')  : alert("No")
    if(confirm("Do you want to cancel adding a user?") === true) {
        if(type === 'user'){
            router.push('/admin/user')
        }else if(type === 'announcement'){
            router.push('/admin/announcement')
        }
    }
}

const saveAdding = async (information) => {
    if(confirm("Are you sure the user's information is correct?")){
        if(type.value === "add"){

            try {
            const res = await fetch(`${API_ROOT}/api/users`, {
            method: "POST",
            headers: {
             "content-type": "application/json",
            },
            body: JSON.stringify(information),
            });

            if(res.status === 200){
                const users = await res.json();
                console.log(users)
                await router.push({name: 'UserList'});
            }else{
                throw new Error(`There is an error! ,Can't add the user => Status Code : ${res.status}`);
                
            }
        }catch (error){
            console.log(error)
        }

    }else if(type.value === "edit"){

        try {
            const res = await fetch(`${API_ROOT}/api/users/${params.id}`, {
            method: "PUT",
            headers: {
             "content-type": "application/json",
            },
            body: JSON.stringify(information),
            });

            if(res.status === 200){
                const users = await res.json();
                console.log(users)
                await router.push({name: 'UserList'});
            }else{
                throw new Error(`There is an error! ,Can't add the user => Status Code : ${res.status}`);
                
            }
        }catch (error){
            console.log(error)
        }

    }
        
    }
    //router push + fetch
}

// const saveBtn = ref(true)
const saveBtn = computed(() => {
    const saveBtn = ref(true)
    if(Object.values(userInfo.value).includes("")){
        return saveBtn.value = true;
    }else{
        return saveBtn.value = false;
    }
})

const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
// console.log(typeof timezone)

const convertTZ = (date) => {
  if (typeof date === "string") {
    const convertDate = new Date(date);
    const options = {
      day: "numeric",
      month: "short",
      year: "numeric",
      hour: "numeric",
      minute: "numeric",
      timeZone: timezone,
    };
    return convertDate.toLocaleDateString("en-GB", options); //format + convert
  } else {
    return "-";
  }
};

</script>
 
<template>
<div class="flex flex-row">
    <sasNav @cancelAnn="cancelAdding" @cancelUser="cancelAdding"/>
    <div class="m-5 w-full h-[40em]">
        <div
         :class="userInfo.createdOn && userInfo.updatedOn 
         ? 'border-2 border-black p-5 m-5 h-[40em]' 
         : 'border-2 border-black p-5 m-5 h-[36em]'">
            <div class="text-[2em] font-['Acme']">User Detail:</div>
            <div class="flex flex-col mb-[2em] mt-[2em]">
                <div class="text-[1.5em] font-['Acme']">Username</div>
                <input 
                required
                placeholder="username"
                v-model="userInfo.username"
                class="ann-username border-2 border-black rounded-md w-[40em] h-[2em] pl-[6px]"/>
            </div>
            <div class="flex flex-col mb-[2em]">
                <div class="text-[1.5em] font-['Acme']">Name</div>
                <input 
                placeholder="name"
                v-model="userInfo.name"
                class="ann-name border-2 border-black rounded-md w-[40em] h-[2em] pl-[6px]"/>
            </div>
            <div class="flex flex-col mb-[2em]">
                <div class="text-[1.5em] font-['Acme']">Email</div>
                <input 
                placeholder="email"
                v-model="userInfo.email"
                class="ann-email border-2 border-black rounded-md w-[40em] h-[2em] pl-[6px]"/>
            </div>
            <div class="flex flex-col mb-[2em]">
                <div class="text-[1.5em] font-['Acme']">Role</div>
                <select 
                v-model="userInfo.role"
                class="ann-role border-2 border-black rounded-md w-[10em] p-[5px]">
                    <option disabled value="">choose your role</option>
                    <option value="admin">admin</option>
                    <option value="announcer">announcer</option>
                </select>
            </div>
            <div class="flex flex-row gap-40 mb-[2em]" v-if="userInfo.createdOn && userInfo.updatedOn">
                <div class="ann-created-on text-[1em] font-['Acme']">Created On: {{convertTZ(userInfo.createdOn)}}</div>
                <div class="ann-updated-on text-[1em] font-['Acme']">Updated On: {{convertTZ(userInfo.updatedOn)}}</div>
            </div>
            <div class="flex flex-row gap-2">
                <button 
                class="ann-button border-2 btn btn-active text-[1em] font-['Acme'] bg-slate-200 text-black hover:bg-green-300"
                :disabled="saveBtn"
                @click="saveAdding(userInfo)">
                Save
                </button>
                <button 
                class="ann-button border-2 btn btn-active  text-[1em] font-['Acme'] bg-slate-200 text-black hover:bg-red-400"
                @click="cancelAdding('user')"
                >Cancel
            </button>
            </div>
        </div>
    </div>
</div>
</template>
 
<style scoped>

</style>