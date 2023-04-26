<script setup>
import { useRoute } from "vue-router";

const { params } = useRoute();
const announcementId = params.idAnnouncement;
const announcement = params.announcement;
</script>
 
<template>
<div>
    <div>
        Announcement Detail:
        <div>
            <table>
                <tr>
                    <th>Title</th>
                    <td>{{ announcement.announcementTitle }}</td>
                </tr>
                <tr>
                    <th>Category</th>
                    <td>{{ announcement.announcementCategory}}</td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td>{{ announcement.announcementDescription }}</td>
                </tr>
                <tr>
                    <th>Publish Date</th>
                    <td>{{ announcement.publishDate }}</td>
                </tr>
                <tr>
                    <th>Close Date</th>
                    <td>{{ announcement.closeDate }}</td>
                </tr>
                <tr>
                    <th>Display</th>
                    <td>{{ announcement.announcementDisplay }}</td>
                </tr>
            </table>
            <div>
                <RouterLink :to="{name:'Announcements'}">|<button>Back</button></RouterLink> 
            </div>
        </div>
    </div>
</div>
</template>
 
<style scoped>

</style>