<script setup>
import { useRouter, RouterLink } from "vue-router";
import { onMounted, ref } from "vue";
import { getUsers } from "../composable/getUser.js";
import sasNav from "../components/sasNav.vue";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const users = ref([]);
onMounted(async () => {
  users.value = await getUsers();
  // console.log(users.value)
})

const router = useRouter();
const deleteUser = async (id, name) => {
  const decision = confirm(`Do you want to delete ${name}'s user data?`)
  
  if(decision === true){
    try {
      const res = await fetch(`${API_ROOT}/api/users/${id}`, {
        method: "DELETE",
      });
      if (res.status === 200) {
        alert(`Delete ${name}'s data completed.'`)
        users.value = users.value.filter((r) => r.id !== id);
      } else if (res.status !== 200) {
        alert(
          `There is an error! ,Can't delete this announcement => announcement id ${id} is not exist!`
        );
      } else
        throw new Error(
          `Can't delete this announcement , Status Code : ${res.status}`
        );
    } catch (error) {
      console.log(error);
    }
  }
}

const changeView = (view) => {
  if(view === "match"){
  router.push(`/admin/user/${view}`)
  }else{
  router.push(`/admin/${view}`)
}
}

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
    <sasNav @toAnn="changeView" @toUser="changeView" @toMatch="changeView"/>
    <div class="w-full h-screen flex flex-col m-5">
        <div class="ann-title font-['Acme'] text-[2em] text-center">User Management</div>
        <div class="grid grid-cols-2 justify-items-start">
            <div class="ann-timezone text-start font-['Acme'] text-[25px]">Date/Time shown in Timezone: {{ timezone }}</div>
            <div class="grid justify-self-end">
                    <button 
                    class="ann-button border-2 btn btn-active bg-slate-200 text-black justify-self-end font-['Acme'] text-[25px] p-2 hover:bg-green-200"
                    @click="router.push({name: 'AddUser'})">
                    Add User
                  </button>
            </div>
        </div>
        <div>
            <div v-if="users.length === 0">
              <h3 class="text-2xl font-['Acme']">No Users</h3>
            </div>
            <table v-else class="table-compact auto w-full mt-7">
                <tr class="bg-green-200 font-['Acme'] text-[15px] border border-black">
                    <th class="text-lg">No.</th>
                    <th class="text-lg">Username</th>
                    <th class="text-lg">Name</th>
                    <th class="text-lg">Email</th>
                    <th class="text-lg">Role</th>
                    <th class="text-lg">Created On</th>
                    <th class="text-lg">Updated On</th>
                    <th class="text-lg">Action</th>
                </tr>
                <tr 
                class="ann-item border border-black"
                v-for="(user, index) in users" :key="user.id">
                    <th>{{ ++index }}</th>
                    <th class="ann-username text-[15px]">{{user.username}}</th>
                    <th class="ann-name text-[15px]">{{ user.name }}</th>
                    <th class="ann-email text-[15px]">{{ user.email }}</th>
                    <th class="ann-role text-[15px]">{{user.role}}</th>
                    <th class="ann-created-on text-[15px]">{{convertTZ(user.createdOn)}}</th>
                    <th class="ann-updated-on text-[15px]">{{convertTZ(user.updatedOn)}}</th>
                    <th class="grid grid-cols-2 gap-2">
                        <button 
                        class="ann-button border border-black rounded-lg text-[15px] p-2 hover:bg-amber-200"
                        @click="router.push({name: 'EditUser', params: {id: user.id}})">
                          edit
                        </button>
                        <button 
                        class="ann-button border border-black rounded-lg text-[15px] p-2 hover:bg-red-300"
                        @click="deleteUser(user.id, user.name)">
                          delete
                        </button>
                    </th>
                </tr>
            </table>
        </div>
    </div>
  </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Acme");
</style>
