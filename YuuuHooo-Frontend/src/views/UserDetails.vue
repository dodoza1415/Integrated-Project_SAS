<script setup>
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useAnnouncerStore } from "../stores/userview";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const router = useRouter();
const { params } = useRoute();

const announcer = useAnnouncerStore();

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
    return convertDate.toLocaleDateString("en-GB", options);
  } else {
    return "-";
  }
};
</script>

<template>
  <div class="flex">
    <div class="font-['Acme'] m-10 w-full border rounded-md">
      <table
        class="ann-item border-2 border-gray-400 shadow-lg w-full rounded-md"
      >
        <tr class="border-2 border-gray-400 border-b-gray-200">
<<<<<<< HEAD
          <td class="text-left p-5 flex justify-between">
            <span class="text-2xl ann-title font-bold">
              {{ announcementsDetails.announcementTitle }}<br />
              <span class="text-[18px] ann-category text-gray-400 border-b-gray-200 font-medium">
              {{ announcementsDetails.announcementCategory }}
            </span>
            </span>
            <span class="ml-[45em] ann-close-date" v-if="announcer.mode === 'close'"
=======
          <td class="text-left p-5">
            <span class="text-2xl ann-title font-bold">
              {{ announcementsDetails.announcementTitle }}
            </span>
            <br />
            <span class="text-sm ann-category text-gray-400 border-b-gray-200">
              {{ announcementsDetails.announcementCategory }}
            </span>
            <span class="ml-[75em] ann-close-date" v-if="announcer.mode === 'close'"
>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445
              ><span class="text-red-500">Closed date:</span>
              {{ convertTZ(announcementsDetails.closeDate) }}</span
            >
          </td>
        </tr>
        <tr class="p-50">
          <th class="text-left text-base ann-description font-normal p-5">
            {{ announcementsDetails.announcementDescription }}
          </th>
        </tr>
        <tr class="border-2 border-gray-400 border-t-gray-200 p-5">
          <div class="p-2">
            <button
<<<<<<< HEAD
              @click="router.push({ name: 'Home'})"
              class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:text-gray-400 hover:border-transparent w-[80px] "
=======
              @click="router.push('/announcement')"
              class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:border-transparent"
>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445
            >
              Back
            </button>
          </div>
        </tr>
      </table>
    </div>
  </div>
</template>

<style scoped></style>
