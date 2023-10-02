<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const API_ROOT = import.meta.env.VITE_ROOT_API;
const router = useRouter();

let inputDisabled = ref(false)
let messageStyle = ref("")
let showMessage = ref(false)
let messageResponse = ref("")
let userInfo = ref({
    username : "",
    password : ""
})

const checkLogin = async (userInfo) => {
  inputDisabled.value = !inputDisabled.value
  try {
    showMessage.value = !showMessage.value
    const res = await fetch(`${API_ROOT}/api/token`, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(userInfo),
    });
    if(res.ok){
        const response = await res.json();
        localStorage.setItem('token' , response.jwtToken.value)
        messageStyle = "ann-message font-medium border-solid border-2 border-lime-700 rounded-lg p-4 mb-3 bg-lime-100 text-lime-700"
        showMessage.value = !showMessage.value
        messageResponse.value = "Login Sucessful"
        inputDisabled.value = !inputDisabled.value
    }
    else if(res.status === 401 || 404){
        messageStyle = "ann-message font-medium border-solid border-2 border-rose-700 rounded-lg p-4 mb-3 bg-rose-200 text-rose-700"
        showMessage.value = !showMessage.value
        inputDisabled.value = !inputDisabled.value
        if(res.status === 401)
        messageResponse.value = "Password Incorrect"
        if(res.status === 404)
        messageResponse.value = "A user with the specified username DOES NOT exist"
    }

} catch (error) {
    console.log(error);
  }
};
</script>
<template>
    <div class="flex justify-center">
    <div class="flex flex-col mt-20 ml-20">
        <div class="ann-message" :class="messageStyle" :v-show="showMessage" >{{ messageResponse }}</div>
    <div class="flex flex-col border-solid border-2 border-black-150 rounded-lg">
    <div class="text-[2em] font-['Acme'] pt-3 px-5 mb-[-15px] text-center">SAS Login</div>
        <form @submit.prevent="checkLogin(userInfo)" >
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
              Login
            </button>
        </div>
          </form>
        </div>
        </div>
    </div>
</template>
<style></style>
