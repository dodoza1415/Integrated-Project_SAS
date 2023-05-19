<script setup>
import { getAnnouncements } from "../composable/getAnnouncements.js";
import { onMounted, ref, onBeforeMount } from "vue";
import { useRouter, RouterLink } from "vue-router";
import { useAnnouncerStore } from "../stores/userview";

const API_ROOT = import.meta.env.VITE_ROOT_API;
const announcer = useAnnouncerStore();
const announcements = ref([]);

onMounted(async () => {
  announcer.setMode('admin')
  announcements.value = await getAnnouncements();
  // console.log(announcements.value)
});

const router = useRouter();
const announcementDetailPage = (announcementId) => {
  router.push({ name: "AnnouncementDetails", params: { id: announcementId } });
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

const deleteAnnouncement = async (id) => {
  if (confirm("Do you want to delete this announcement?") === true) {
    try {
      const res = await fetch(`${API_ROOT}/api/announcements/${id}`, {
        method: "DELETE",
      });
      if (res.status === 200) {
        console.log("delete sucessfully");
        announcements.value = announcements.value.filter((r) => r.id !== id);
      } else if (res.status !== 200) {
        alert(
          `There is an error! ,Can't delete this announcement => announcement id ${id} is not exist!`
        );
      } else
        throw new Error(
          `Can't delete this announcement , Status Code : ${res.status}`
        );
    } catch (error) {
      console.log(error);
    }
  }
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
              @click="router.push('/admin/announcement/add')"
              class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
            >
              Add Announcement
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
            <th class="text-lg">Category</th>
            <th class="text-lg">Publish Date</th>
            <th class="text-lg">Close Date</th>
            <th class="text-lg">Display</th>
            <th class="text-lg">#Views</th>
            <th class="text-lg text-center" colspan="2">Action</th>
          </tr>
          <tr
            v-for="(announcement, index) in announcements"
            :key="announcement.id"
            class="ann-item font-semibold"
          >
            <td>{{ ++index }}</td>
            <td class="ann-title">{{ announcement.announcementTitle }}</td>
            <td class="ann-category">
              {{ announcement.announcementCategory }}
            </td>
            <td class="ann-publish-date">
              {{ convertTZ(announcement.publishDate) }}
            </td>
            <td class="ann-close-date">
              {{ convertTZ(announcement.closeDate) }}
            </td>
            <td class="ann-display">{{ announcement.announcementDisplay }}</td>
            <td class="ann-counter">{{ announcement.viewCount }}</td>
            <div class="flex mr-[-5em]">
              <div>
                <td>
                  <button
                    @click="announcementDetailPage(announcement.id)"
                    class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-sky-400 hover:border-transparent"
                  >
                    view
                  </button>
                </td>
              </div>
              <div>
                <td>
                  <button
                    @click="deleteAnnouncement(announcement.id)"
                    class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-red-300 hover:border-transparent"
                  >
                    delete
                  </button>
                </td>
              </div>
            </div>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Acme");
</style>
