<script setup>
import { useRouter , RouterLink } from 'vue-router';
const router = useRouter();
</script>
<template>
  <div>
  <div class="font-['Acme']  text-[45px] text-center mt-[300px]">
  <h2>Page Not Found! </h2>
</div>
<div class="font-['Acme']  text-[20px] text-center mb-[50px]">
  <h5>Please check your path ( URL ).</h5>
</div>
<div class="btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent flex justify-center">
  <p @click="router.push('/')">Home</p>
</div>
</div>

</template>

<style></style>
