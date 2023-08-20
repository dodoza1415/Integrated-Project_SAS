<script setup>
import { useRouter, RouterLink } from "vue-router";

const router = useRouter();

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

const saveAdding = () => {
    confirm("Are you sure the user's information is correct?") === true ? alert("Yes") : alert("No")
    //router push + fetch
}
</script>
 
<template>
<div class="flex flex-row">
    <div class="w-[20em] flex flex-col mt-5 ml-5 pr-5 gap-1 border-r-4 border-r-black ">
      <div class="ann-app-title h-[2em] text-[3em] font-['Acme'] text-center">
        SAS
      </div>
      <div
      @click="cancelAdding('announcement')"
      class="ann-menu h-[3em] text-[1em] font-['Acme'] border-2 border-slate-300 rounded-md border-solid text-center p-2 hover:bg-rose-500 hover:border-transparent hover:text-white cursor-pointer">
        Announcement
      </div>
      <div 
      @click="cancelAdding('user')"
      class="ann-menu h-[3em] text-[1em] font-['Acme'] border-2 border-slate-300 rounded-md border-solid text-center p-2 hover:bg-emerald-500 hover:border-transparent hover:text-white cursor-pointer">
        User
      </div>
    </div>
    <div class="m-5 w-full h-[40em]">
        <div class="border-2 border-black p-5 m-5 h-[36em]">
            <div class="text-[2em] font-['Acme']">User Detail:</div>
            <div class="flex flex-col mb-[2em] mt-[2em]">
                <div class="text-[1.5em] font-['Acme']">Username</div>
                <input class="ann-username border-2 border-black rounded-md w-[40em] h-[2em] pl-[6px]"/>
            </div>
            <div class="flex flex-col mb-[2em]">
                <div class="text-[1.5em] font-['Acme']">Name</div>
                <input class="ann-name border-2 border-black rounded-md w-[40em] h-[2em] pl-[6px]"/>
            </div>
            <div class="flex flex-col mb-[2em]">
                <div class="text-[1.5em] font-['Acme']">Email</div>
                <input class="ann-email border-2 border-black rounded-md w-[40em] h-[2em] pl-[6px]"/>
            </div>
            <div class="flex flex-col mb-[2em]">
                <div class="text-[1.5em] font-['Acme']">Role</div>
                <select class="ann-role border-2 border-black rounded-md w-[10em] p-[5px]">
                    <option>roles</option>
                </select>
            </div>
            <div class="flex flex-row gap-2">
                <button 
                class="ann-button border-2 btn btn-active text-[1em] font-['Acme'] bg-slate-200 text-black hover:bg-green-300"
                @click="saveAdding()">
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