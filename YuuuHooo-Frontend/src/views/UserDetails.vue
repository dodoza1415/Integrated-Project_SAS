<script setup>
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const router = useRouter();
const { params } = useRoute();

const announcementsDetails = ref({});


onMounted(async () => {
  const announcementId = params.id;
  try {
    const res = await fetch(`${API_ROOT}/api/announcements/${announcementId}`);
    if (res.status === 200) {
      announcementsDetails.value = await res.json();
      // console.log(announcementsDetails.value)
    } else if (res.status !== 200) {
      alert("The requested page is not available!");
      router.push("/announcement");
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
});
</script>

<template>
  <div>
    <div class="font-['Acme'] m-10 ">
        <div class="mb-5">
      <table class="ann-item border-2 border-gray-500  ">
        <tr class="border-2  border-gray-500">
          <td class="text-left p-5">
        <span class="text-2xl ann-title font-bold">
            {{ announcementsDetails.announcementTitle }}
        </span>
          <br/>
          <span class="text-sm ann-category text-gray-400">
            {{ announcementsDetails.announcementCategory }}
          </span>
        </td>
        </tr>
        <tr class="p-50">
          <th class="text-left text-base ann-description font-normal p-5">
            {{ announcementsDetails.announcementDescription }}
          </th>
        </tr >
      </table>
    </div>
      <div>
          <button
            @click="router.push('/announcement')"
            class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:border-transparent"
          >
            Back
          </button>
        </div>
    </div>
  </div>
</template>

<style scoped></style>
