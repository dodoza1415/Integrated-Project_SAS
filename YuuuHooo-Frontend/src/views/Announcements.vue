<script setup>
import {getAnnouncements} from "../composable/getAnnouncements.js"
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

const announcements = ref([])
onMounted(async () => {
  announcements.value = await getAnnouncements()
})
console.log(typeof announcements)
const router = useRouter();
const announcementDetailPage = (id) => {
  router.push({ name: "Announcement", params: { idAnnouncement : id} });
};


</script>
 
<template>
<p v-if="announcements.length === 0" class="text-3xl font-bold text-center">No Announcement</p>
<div v-else>
    <div>
    <div class="text-3xl font-bold">
    SIT Announcement System(SAS)
    </div>
        <div>
            Date/Time shown in Timezone:<span>Asia/Bangkok</span>
            <table>
                <tr>
                    <th>No.</th>
                    <th>Title</th>
                    <th>Category</th>
                    <th>Publish Date</th>
                    <th>Close Date</th>
                    <th>Display</th>
                    <th>Action</th>
                </tr>
                <tr v-for="announcement in announcements"
                :key="announcement.idAnnouncement"> 
                    <td>{{announcement.idAnnouncement}}</td>
                    <td>{{announcement.announcementTitle}}</td>
                    <td>{{ announcement.announcementCategory }}</td>
                    <td>{{announcement.publishDate}}</td>
                    <td>{{announcement.closeDate}}</td>
                    <td>{{announcement.announcementDisplay}}</td>
                    <td><button @click="announcementDetailPage(announcement.idAnnouncement)">view</button></td>
                </tr>   
            </table>
        </div>
    </div>
</div>
</template>
 
<style scoped>

</style>