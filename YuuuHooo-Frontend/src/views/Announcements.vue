<script setup>
import { getAnnouncements } from "../composable/getAnnouncements.js";
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";


const announcements = ref([]);
onMounted(async () => {

  announcements.value = await getAnnouncements();
});

const router = useRouter();
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



</script>

<template>
  <div>
    <div>
      <div class="text-2xl font-['Acme'] m-10">SIT Announcement System (SAS)</div>
      <div class="overflow-x-auto m-10">
        <div class="text-2xl font-['Acme']">
          Date/Time shown in Timezone: {{ timezone }}
        </div>
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
            class="font-semibold"
          >
            <td>{{ ++index }}</td>
            <td>{{ announcement.announcementTitle }}</td>
            <td>{{ announcement.announcementCategory }}</td>
            <td>{{ convertTZ(announcement.publishDate) }}</td>
            <td>{{ convertTZ(announcement.closeDate) }}</td>
            <td>{{ announcement.announcementDisplay }}</td>
            <td>
              <button
                @click="announcementDetailPage(announcement.id)"
                class="btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
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
