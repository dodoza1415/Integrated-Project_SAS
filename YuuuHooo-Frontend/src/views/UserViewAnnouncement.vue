<script setup>
import { getAnnouncements } from "../composable/getAnnouncements.js";
import { onMounted, ref } from "vue";
<<<<<<< HEAD
import { useRoute, useRouter } from "vue-router";
import { useAnnouncerStore } from "../stores/userview";

const API_ROOT = import.meta.env.VITE_ROOT_API;
const {params} = useRoute()
const router = useRouter();
const announcer = useAnnouncerStore();
const announcements = ref([]);
const closedAnnouncements = ref('Closed Announcements');
const announcementModeTitle = () => {
  if (announcer.mode === "active") {
    closedAnnouncements.value = "Closed Announcements";
    changeModeButton.value = "ann-button btn btn-info bg-gray-200 border-transparent hover:bg-red-300 hover:border-transparent"
}else if (announcer.mode === "close") {
  closedAnnouncements.value = "Active Announcements";
    changeModeButton.value = "ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
}
}
const changeModeButton = ref("ann-button btn btn-info bg-gray-200 border-transparent hover:bg-red-300 hover:border-transparent")
const announcementMode = () => {
  if (announcer.mode === "active") {
    announcer.setMode("close");
    fetchAnnouncement();
  } else if (announcer.mode === "close") {
    announcer.setMode("active");
    fetchAnnouncement();
=======
import { useRouter } from "vue-router";
import { useAnnouncerStore } from "../stores/userview";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const router = useRouter();
const announcer = useAnnouncerStore();
const announcements = ref([]);

const announcementsModeBtn = ref("Closed Announcements");
const changeAnnouncementMode = () => {
  if (announcer.mode === "active") {
    announcementsModeBtn.value = "Active Announcements";
    announcer.setMode("close");
    fetchAnnouncement();
  } else if (announcer.mode === "close") {
    announcementsModeBtn.value = "Closed Announcements";
    announcer.setMode("active");
    fetchAnnouncement();

>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445
    // console.log(closedAnnouncements)
  }
};
onMounted(async () => {
  announcements.value = await getAnnouncements();
<<<<<<< HEAD
  announcementModeTitle();
=======

  if (announcer.mode === "active") {
    announcementsModeBtn.value = "Closed Announcements";
  } else if (announcer.mode === "close") {
    announcementsModeBtn.value = "Active Announcements";
  }

>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445
  // console.log(announcements.value)
  // console.log(announcer.mode)
});

const fetchAnnouncement = async () => {
  try {
    const announcer = useAnnouncerStore();
    const res = await fetch(
      `${API_ROOT}/api/announcements` +
        "?" +
        new URLSearchParams({
          mode: announcer.mode,
        })
    );
    if (res.status === 200) {
      announcements.value = await res.json();
      return announcements;
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
};

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

const userDetailPage = (announcementId) => {
  router.push({ name: "UserDetails", params: { id: announcementId } });
};
</script>

<template>
  <div>
<<<<<<< HEAD
      <div class="text-2xl font-['Acme'] m-10 text-center font-bold">
        SIT Announcement System (SAS)
      </div>
      <div class="overflow-auto m-10">
        <div class="text-lg font-['Acme'] flex justify-between">
          <span class="font-bold">Date/Time shown in Timezone : <span class="font-thin">{{ timezone }}</span> </span> 
          <span class="mb-[-20px]">
          <button
            @click="announcementMode() , announcementModeTitle()"
            :class="changeModeButton"
              >{{closedAnnouncements}}
       </button>
          </span>
=======
    <div>
      <div class="text-2xl font-['Acme'] m-10">
        SIT Announcement System (SAS)
      </div>
      <div class="overflow-auto m-10">
        <div class="grid justify-items-start">
          <div class="text-2xl font-['Acme']">
            Date/Time shown in Timezone: {{ timezone }}
          </div>
          <div class="text-2xl font-['Acme'] grid justify-self-end">
            <button
              @click="changeAnnouncementMode()"
              class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
            >
              {{ announcementsModeBtn }}
            </button>
          </div>
>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445
        </div>
        <div v-if="announcements.length === 0">
          <h3 class="text-2xl font-['Acme']">No Announcement</h3>
        </div>
        <table v-else class="table table-compact auto w-full mt-7 border shadow-lg border-gray-300  rounded-md">
          <tr class="font-['Acme'] bg-red-100">
            <th class="text-lg text-center">No.</th>
            <th class="text-lg">Title</th>
            <th v-if="announcer.mode === 'close'">Close date</th>
            <th class="text-lg">Category</th>
          </tr>
          <tr
            v-for="(announcement, index) in announcements"
            :key="announcement.id"
<<<<<<< HEAD
            class="ann-item font-semibold border border-gray-300"
          >
            <td class=" text-center">{{ ++index }}</td>
            <td
              class="ann-title cursor-pointer hover:text-gray-500 "
=======
            class="ann-item font-semibold"
          >
            <td>{{ ++index }}</td>
            <td
              class="ann-title cursor-pointer hover:text-purple-500"
>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445
              @click="userDetailPage(announcement.id)"
            >
              {{ announcement.announcementTitle }}
            </td>
            <td v-if="announcer.mode === 'close'" class="ann-close-date">
              {{ convertTZ(announcement.closeDate) }}
            </td>
            <td class="ann-category">
              {{ announcement.announcementCategory }}
            </td>
          </tr>
        </table>
      </div>
<<<<<<< HEAD
=======
    </div>
>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445
  </div>
</template>

<style scoped></style>
