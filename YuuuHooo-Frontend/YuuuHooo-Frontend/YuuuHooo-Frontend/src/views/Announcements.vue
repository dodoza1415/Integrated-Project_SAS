<script setup>
import {getAnnouncements} from "../composable/getAnnouncements.js"
import { ref } from "vue";
import { useRouter } from "vue-router";

const announcements = ref(getAnnouncements());
const router = useRouter();
const announcementDetail = (id , announcement) => {
  router.push({ name: "Announcement", params: { idAnnouncement : id , announcement : announcement} });
};
</script>
 
<template>
<div>
    <div>
    SIT Announcement System(SAS)
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
                    <th>{{announcements.idAnnouncement}}</th>
                </tr>
                <tr v-for="announcement in announcements"
                :key="announcement.idAnnouncement"> 
                    <td>{{announcement.idAnnouncement}}</td>
                    <td>{{announcement.announcementTitle}}</td>
                    <td>{{announcement.publishDate}}</td>
                    <td>{{announcement.closeDate}}</td>
                    <td>{{announcement.announcementDisplay}}</td>
                    <td><button @click="announcementDetail(announcement.idAnnouncement , announcement)">view</button></td>
                </tr>   
            </table>
        </div>
    </div>
</div>
</template>
 
<style scoped>

</style>