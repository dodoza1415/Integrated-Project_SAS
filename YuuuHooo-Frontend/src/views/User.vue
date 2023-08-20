<script setup>
import { useRouter, RouterLink } from "vue-router";



const router = useRouter();

const deleteUser = () => {
  const decision = confirm(`Do you want to delete "...'s" user data?`)
  
  if(decision === true){
    alert("Yes")
  }
}
</script>

<template>
  <div class="flex flex-row">
    <div class="w-[20em] flex flex-col m-5 pr-5 gap-1 border-r-4 border-r-black">
      <div class="ann-app-title h-[2em] text-[3em] font-['Acme'] text-center">
        SAS
      </div>
      <div
        @click="router.push('/admin/announcement')"
        class="ann-menu h-[3em] text-[1em] font-['Acme'] border-2 border-slate-300 rounded-md border-solid text-center p-2 hover:bg-rose-500 hover:border-transparent hover:text-white cursor-pointer"
      >
        Announcement
      </div>
      <div
        @click="router.push('/admin/user')"
        class="ann-menu h-[3em] text-[1em] font-['Acme'] border-2 border-slate-300 rounded-md border-solid text-center p-2 hover:bg-emerald-500 hover:border-transparent hover:text-white cursor-pointer"
      >
        User
      </div>
    </div>
    <div class="w-full h-screen flex flex-col m-5">
        <div class="ann-title font-['Acme'] text-[2em] text-center">User Management</div>
        <div class="grid grid-cols-2 justify-items-start">
            <div class="ann-timezone text-start font-['Acme'] text-[25px]">Date/Time shown in Timezone: ???</div>
            <div class="grid justify-self-end">
                    <button 
                    class="ann-button border-2 btn btn-active bg-slate-200 text-black justify-self-end font-['Acme'] text-[25px] p-2 hover:bg-green-200"
                    @click="router.push('/admin/user/add')">
                    Add User
                  </button>
            </div>
        </div>
        <div>
            <table class="table-compact auto w-full mt-7">
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
                class="ann-item border border-black">
                    <th>1.</th>
                    <th class="ann-username text-[15px]">Hello123</th>
                    <th class="ann-name text-[15px]">Hello Eiei</th>
                    <th class="ann-email text-[15px]">helloyuuuhooo123@gmail.com</th>
                    <th class="ann-role text-[15px]">admin</th>
                    <th class="ann-created-on text-[15px]">Today</th>
                    <th class="ann-updated-on text-[15px]">Today</th>
                    <th class="grid grid-cols-2 gap-2">
                        <button 
                        class="ann-button border border-black rounded-lg text-[15px] p-2 hover:bg-amber-200"
                        @click="router.push('/admin/user/id/edit')">
                          edit
                        </button>
                        <button 
                        class="ann-button border border-black rounded-lg text-[15px] p-2 hover:bg-red-300"
                        @click="deleteUser()">
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
