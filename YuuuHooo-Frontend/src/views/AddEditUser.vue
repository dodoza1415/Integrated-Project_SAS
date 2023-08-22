<script setup>
import { useRouter, useRoute } from "vue-router";
import { ref, onMounted, computed } from "vue";
import sasNav from "../components/sasNav.vue";

const API_ROOT = import.meta.env.VITE_ROOT_API;
const router = useRouter();
const {params} = useRoute();

// console.log(params.details)
const userInfo = ref({})
onMounted(() => {
    if(params.details === undefined){
        userInfo.value = {
            username: "",
            name: "",
            email: "",
            role: ""
        }
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
        try {
            const res = await fetch(`${API_ROOT}/api/users`, {
            method: "POST",
            headers: {
             "content-type": "application/json",
            },
            body: JSON.stringify(information),
            });

            if(res.status === 200){
                const addedUser = await res.json();
                console.log(addedUser)
                await router.push({name: 'UserList'});
            }else{
                throw new Error(
                `There is an error! ,Can't add the user => Status Code : ${res.status}`
      );
            }
        }catch (error){
            console.log(error)
        }
    }
    //router push + fetch
}

// const saveBtn = ref(true)
// const check = computed(() => {
//     for (const [key, value] of Object.entries(userInfo.value)) {
        
// }
// })

</script>
 
<template>
    {{ check }}
<div class="flex flex-row">
    <sasNav @cancelAnn="cancelAdding" @cancelUser="cancelAdding"/>
    <div class="m-5 w-full h-[40em]">
        <div class="border-2 border-black p-5 m-5 h-[36em]">
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
            <div class="flex flex-row gap-2">
                <button 
                :disabled="saveBtn"
                class="ann-button border-2 btn btn-active text-[1em] font-['Acme'] bg-slate-200 text-black hover:bg-green-300"
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