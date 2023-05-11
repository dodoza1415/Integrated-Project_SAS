<script setup>
import { getAnnouncements } from "../composable/getAnnouncements.js";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import { useAnnouncerStore } from "../stores/userview";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const router = useRouter();
const announcer = useAnnouncerStore();
const announcements = ref([]);

const closedAnnouncements = ref("Closed Announcements");
const announcementMode = () => {
  if (announcer.mode === "active") {
    closedAnnouncements.value = "Active Announcements";
    announcer.setMode("close");
    fetchAnnouncement();
  } else if (announcer.mode === "close") {
    closedAnnouncements.value = "Closed Announcements";
    announcer.setMode("active");
    fetchAnnouncement();
    // console.log(closedAnnouncements)
  }
};
onMounted(async () => {
  announcements.value = await getAnnouncements();
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
              @click="announcementMode()"
              class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
            >
              {{ closedAnnouncements }}
            </button>
          </div>
        </div>
        <div v-if="announcements.length === 0">
          <h3 class="text-2xl font-['Acme']">No Announcement</h3>
        </div>
        <table v-else class="table table-compact auto w-full mt-7">
          <tr class="font-['Acme'] bg-red-100">
            <th class="text-lg">No.</th>
            <th class="text-lg">Title</th>
            <th v-if="announcer.mode === 'close'">Close date</th>
            <th class="text-lg">Category</th>
          </tr>
          <tr
            v-for="(announcement, index) in announcements"
            :key="announcement.id"
            class="ann-item font-semibold"
          >
            <td>{{ ++index }}</td>
            <td
              class="ann-title cursor-pointer"
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
    </div>
  </div>
</template>

<style scoped></style>
