<script setup>
import { getAnnouncements } from "../composable/getAnnouncements.js";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";
import AddAnnouncement from "./AddAnnouncement.vue";



const announcements = ref([]);
onMounted(async () => {
  announcements.value = await getAnnouncements();
});

const router = useRouter();
router.push('/admin/announcement')
const announcementDetailPage = (announcementId) => {
  router.push({ name: "AnnouncementDetails", params: { id: announcementId } });
};

const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
// console.log(typeof timezone)

const convertTZ = (date) => {
  if(typeof date === "string"){
    const convertDate = new Date(date)
    const options = {  day: 'numeric', month: 'short', year: 'numeric', hour: 'numeric', minute: 'numeric', timeZone: timezone};
    return convertDate.toLocaleDateString('en-GB', options)
  }else{
    return "-"
  }
}

const addAnnouncement = async (announcement) => {
  try {
    const res = await fetch("http://localhost:8080/api/announcements", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(announcement),
    });
    if (res.status === 201) {
      const announcementsAdded = await res.json();
      announcements.value.push(announcementsAdded)
      return announcements;
    } else throw new Error("Can't add the announcement");
  } catch (error) {
    console.log(error);
  }
};

</script>

<template>
  <div>
    <div>
      <div class="text-2xl font-['Acme'] m-10">SIT Announcement System (SAS)</div>
      <div class="overflow-x-auto m-10">
        <div class="text-2xl font-['Acme']">
          Date/Time shown in Timezone: {{ timezone }}
        </div>
        <RouterLink :to="{ name: 'AddAnnouncement' }" class="ann-button">Add Announcement</RouterLink>
        <!-- <AddAnnouncement @add="addAnnouncement"/> -->
        <div v-if="announcements.length === 0">
          <h3 class="text-2xl font-['Acme']">No Announcement</h3>
        </div>
        <table v-else class="table table-compact w-full mt-7">
          <tr class="font-['Acme'] bg-red-100">
            <th class="text-lg ">No.</th>
            <th class="text-lg">Title</th>
            <th class="text-lg">Category</th>
            <th class="text-lg">Publish Date</th>
            <th class="text-lg">Close Date</th>
            <th class="text-lg">Display</th>
            <th class="text-lg">Action</th>
          </tr>
          <tr
            v-for="(announcement, index) in announcements"
            :key="announcement.id"
            class="ann-item font-semibold"
          >
            <td>{{ ++index }}</td>
            <td class="ann-title">{{ announcement.announcementTitle }}</td>
            <td class="ann-category">{{ announcement.announcementCategory }}</td>
            <td class="ann-publish-date">{{ convertTZ(announcement.publishDate) }}</td>
            <td class="ann-close-date">{{ convertTZ(announcement.closeDate) }}</td>
            <td class="ann-display">{{ announcement.announcementDisplay }}</td>
            <td>
              <button
                @click="announcementDetailPage(announcement.id)"
                class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
              >
                view
              </button>
            </td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Acme');
</style>
