<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import sasNav from "../components/sasNav.vue";

const API_ROOT = import.meta.env.VITE_ROOT_API;
const router = useRouter();

const changeView = (view) => {
  if(view !== 'match')
  if(userInfo.value.username || userInfo.value.password !== ""){
    if (confirm("Do you want to cancel check matching password?")) {
        if (true) {
        router.push(`/admin/${view}`);
        }
    }
  }
  else if(view === "match"){
    router.push(`/admin/user/${view}`)
  }else{
  router.push(`/admin/${view}`)
}
}
let inputDisabled = ref(false)
let messageStyle = ref("")
let showMessage = ref(false)
let messageResponse = ref("")
let userInfo = ref({
    username : "",
    password : ""
})

const checkMatch = async (userInfo) => {
  inputDisabled.value = !inputDisabled.value
  try {
    showMessage.value = !showMessage.value
    const res = await fetch(`${API_ROOT}/api/users/match`, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(userInfo),
    });
    if(res.ok){
        const response = await res.text();
        messageStyle = "ann-message font-medium border-solid border-2 border-lime-700 rounded-lg p-4 mb-3 bg-lime-100 text-lime-700"
        showMessage.value = !showMessage.value
        messageResponse.value = response
        inputDisabled.value = !inputDisabled.value
    }
    else if(res.status === 401 || 404){
        const response = await res.json();
        messageStyle = "ann-message font-medium border-solid border-2 border-rose-700 rounded-lg p-4 mb-3 bg-rose-200 text-rose-700"
        showMessage.value = !showMessage.value
        messageResponse.value = response.message
        inputDisabled.value = !inputDisabled.value
    }

} catch (error) {
    console.log(error);
  }
};
</script>
<template>
    <div class="flex flex-row ">
    <sasNav @toAnn="changeView" @toUser="changeView" @toMatch="changeView"/>
    <div class="flex flex-col mt-20 ml-20">
        <div class="ann-message" :class="messageStyle" :v-show="showMessage" >{{ messageResponse }}</div>
    <div class="flex flex-col border-solid border-2 border-black-150 rounded-lg">
    <div class="text-[2em] font-['Acme'] pt-3 px-5 mb-[-15px]">Match Password</div>
        <form @submit.prevent="checkMatch(userInfo)" >
          <div class="flex flex-col mb-[2em] mt-[2em] px-5">
            <div class="text-[1.2em] font-['Acme'] pb-2">Username</div>
            <input
              type="text"
              required
              maxlength="45"
              placeholder="username"
              v-model.trim="userInfo.username"
              class="ann-username border-2 border-black-150 rounded-md w-[25em] h-[2em] pl-[6px] mt-[-5px] mb-[-5px]" :disabled="inputDisabled"
            />
          </div>
          <div class="flex flex-col mb-[2em]  px-5">
            <div class="text-[1.2em] font-['Acme'] pb-2 mt-[-10px]">Password</div>
            <input
              type="password"
              required
              minlength="8"
              maxlength="14"
              v-model.trim="userInfo.password"
              class="ann-password border-2 border-black-150 rounded-md w-[25em] h-[2em] pl-[6px] mb-[-7px] mt-[-5px]" :disabled="inputDisabled"
            />
          </div>
          <div class="px-5 pb-3">
          <button
              type="submit"
              class="ann-button border-2 btn btn-active text-sm font-['Acme'] bg-slate-200 text-slate-600  hover:bg-green-300 hover:border-white border-white px-5 mt-[-10px]"
              :disabled="inputDisabled">
              Match or not
            </button>
        </div>
          </form>
        </div>
        </div>
    </div>
</template>
<style></style>
