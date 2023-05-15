<script setup>
import { getAnnouncements } from "../composable/getAnnouncements.js";
import { getCategories } from "../composable/getCategories.js";
import { onMounted, ref } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useAnnouncerStore } from "../stores/userview";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const { params } = useRoute();
const router = useRouter();
const announcer = useAnnouncerStore();
const categories = ref([]);
const announcements = ref([]);

const closedAnnouncements = ref("Closed Announcements");
const announcementModeTitle = () => {
  if (announcer.mode === "active") {
    closedAnnouncements.value = "Closed Announcements";
    changeModeButton.value =
      "ann-button btn btn-info bg-gray-200 border-transparent hover:bg-red-300 hover:border-transparent";
  } else if (announcer.mode === "close") {
    closedAnnouncements.value = "Active Announcements";
    changeModeButton.value =
      "ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent";
  }
};

const changeModeButton = ref(
  "ann-button btn btn-info bg-gray-200 border-transparent hover:bg-red-300 hover:border-transparent"
);
const announcementMode = () => {
  if (announcer.mode === "active") {
    announcer.setMode("close");
    fetchAnnouncement();
  } else if (announcer.mode === "close") {
    announcer.setMode("active");
    fetchAnnouncement();
    // console.log(closedAnnouncements)
  }
};
onMounted(async () => {
  announcements.value = await getAnnouncements();
  categories.value = await getCategories();

  announcementModeTitle();
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

const prevStatus = ref(false);
const nextStatus = ref(false);
// const ButtonDisabled = () => {

// }
const show = () => {
  console.log(announcements.value);
};
</script>

<template>
  <div>
    <div class="text-2xl font-['Acme'] m-10 text-center font-bold">
      SIT Announcement System (SAS)
    </div>
    <div class="overflow-auto m-10">
      <div class="text-lg font-['Acme'] flex justify-between">
        <span class="font-bold"
          >Date/Time shown in Timezone &nbsp:
          <span class="font-thin">&nbsp{{ timezone }}</span>
        </span>
      </div>

      <!-- <div class="mb-[-10px] flex justify-between">
        <span class="text-lg font-['Acme'] font-bold"
          >Choose Category &nbsp:
          <select
            class="ann-category-filter ml-3 mt-3 border border-gray rounded-md h-[2.0em] w-[7.5em] font-thin text-sm"
            v-model="announcements.categoryId"
          >
            <option value="0">ทั้งหมด</option>
            <option
              :value="category.categoryId"
              v-for="(category, index) in categories"
              :key="category.categoryId"
            >
              {{ category.categoryName }}
            </option>
          </select>
        </span>
      </div> -->

      <span class="mb-[-20px] text-lg font-['Acme'] ml-[70em]">
        <button
          @click="announcementMode(), announcementModeTitle()"
          :class="changeModeButton"
        >
          {{ closedAnnouncements }}
        </button>
      </span>
      <div v-if="announcements.length === 0">
        <h3 class="text-2xl font-['Acme']">No Announcement</h3>
      </div>
      <table
        v-else
        class="table table-compact auto w-full mt-7 border shadow-lg border-gray-300 rounded-md"
      >
        <tr class="font-['Acme'] bg-red-100">
          <th class="text-lg text-center">No.</th>
          <th class="text-lg">Title</th>
          <th v-if="announcer.mode === 'close'">Close date</th>
          <th class="text-lg">Category</th>
        </tr>
        <tr
          v-for="(announcement, index) in announcements"
          :key="announcement.id"
          class="ann-item font-semibold border border-gray-300"
        >
          <td class="text-center">{{ ++index }}</td>
          <td
            class="ann-title cursor-pointer hover:text-gray-500"
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
      <!-- <div class="text-2xl font-['Acme'] mt-5">
        <button class="ann-page-prev btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:text-slate-400 hover:border-transparent w-[80px]" :disabled="prevStatus">Prev</button>
        <button class="ann-page-0 btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:text-slate-400 hover:border-transparent active:bg-green-500 active:text-white w-[50px]">1</button>
        <button class="ann-page-next btn btn-info bg-gray-200 border-transparent hover:bg-green-500 hover:border-transparent hover:text-white w-[80px]" :disabled="nextStatus">Next</button>
      </div> -->
    </div>
  </div>
</template>

<style scoped></style>
