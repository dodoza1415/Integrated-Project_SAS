<script setup>
import { useRouter } from 'vue-router';



const router = useRouter();

// const cancelAdding = (type) => {
//     //  confirm("Do you want to cancel adding a user?") === true ? type === 'user' ? router.push('/admin/user')  : alert("No")
//     if(confirm("Do you want to cancel adding a user?") === true) {
//         if(type === 'user'){
//             router.push('/admin/user')
//         }else if(type === 'announcement'){
//             router.push('/admin/announcement')
//         }
//     }
// }

defineEmits(['cancelAnn', 'cancelUser', 'toAnn', 'toUser'])
</script>
 
<template>
 <div class="w-[20em] flex flex-col mt-5 ml-5 pr-5 gap-1 border-r-4 border-r-black ">
      <div class="ann-app-title h-[2em] text-[3em] font-['Acme'] text-center">
        SAS
      </div>
      <div
      @click="$emit('cancelAnn', 'announcement'); $emit('toAnn', 'announcement')"
      class="ann-menu h-[3em] text-[1em] font-['Acme'] border-2 border-slate-300 rounded-md border-solid text-center p-2 hover:bg-rose-500 hover:border-transparent hover:text-white cursor-pointer">
        Announcement
      </div>
      <div 
      @click="$emit('cancelUser', 'user'); $emit('toUser', 'user')"
      class="ann-menu h-[3em] text-[1em] font-['Acme'] border-2 border-slate-300 rounded-md border-solid text-center p-2 hover:bg-emerald-500 hover:border-transparent hover:text-white cursor-pointer">
        User
      </div>
    </div>
</template>
 
<style scoped>

</style>