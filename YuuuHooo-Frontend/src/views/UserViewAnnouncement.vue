<script setup>
import { getAnnouncements } from "../composable/getAnnouncements.js";
import { onMounted, ref} from "vue";
import { useRouter } from "vue-router";

const announcements = ref([]);
const router = useRouter();
const closedAnnouncements = ref("Closed Announcements")

const announcementMode = () => {
  closedAnnouncements.value = "Active Announcements"
}
onMounted(async () => {
  announcements.value = await getAnnouncements();
  // console.log(announcements.value)
});
const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
// console.log(typeof timezone)


const userDetailPage = (announcementId) => {
    router.push({ name: "UserDetails", params: { id: announcementId } });
} 
</script>
 
<template>
 <div>
    <div>
      <div class="text-2xl font-['Acme'] m-10">SIT Announcement System (SAS)</div>
      <div class=" overflow-auto m-10">
        <div class="grid justify-items-start">
        <div class="text-2xl font-['Acme']">
          Date/Time shown in Timezone: {{ timezone }}
        </div>
          <div class="text-2xl font-['Acme'] grid justify-self-end">
            <button
            @click="announcementMode()"
                class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
              >{{closedAnnouncements}}
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
          </tr>
          <tr
            v-for="(announcement, index) in announcements"
            :key="announcement.id"
            class="ann-item font-semibold"
            >
            <td>{{ ++index }}</td>
            <td class="ann-title cursor-pointer" @click="userDetailPage(announcement.id)">{{ announcement.announcementTitle }}</td>
            <td class="ann-category">{{ announcement.announcementCategory }}</td>
          </tr>
        </table>
</div>
</div>
</div>
</template>
 
<style scoped>

</style>