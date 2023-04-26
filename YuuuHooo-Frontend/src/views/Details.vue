<script setup>
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

const { params } = useRoute();

const announcementsDetails = ref([])

onMounted(async () => {
    const announcementId = params.idAnnouncement;
    try{
        const res = await fetch(`http://localhost:8080/api/announcements/${announcementId}`)
        if(res.status === 200){
            announcementsDetails.value = await res.json();
            console.log(announcementsDetails.value)
        }else throw new Error("No Announcement")
    }catch (error){
        console.log(error);
    }
})
</script>
 
<template>
<div>
    <div>
        Announcement Detail:
        <div>
            <table>
                <tr>
                    <th>Title</th>
                    <td>{{ announcementsDetails.announcementTitle }}</td>
                </tr>
                <tr>
                    <th>Category</th>
                    <td>{{ announcementsDetails.announcementCategory }}</td>
                </tr>
                <tr>
                    <th>Description</th>
                    <td>{{ announcementsDetails.announcementDescription }}</td>
                </tr>
                <tr>
                    <th>Publish Date</th>
                    <td>{{ announcementsDetails.publishDate }}</td>
                </tr>
                <tr>
                    <th>Close Date</th>
                    <td>{{ announcementsDetails.closeDate }}</td>
                </tr>
                <tr>
                    <th>Display</th>
                    <td>{{ announcementsDetails.announcementDisplay }}</td>
                </tr>
            </table>
            <div>
                <RouterLink :to="{name:'Home'}">Back</RouterLink> 
            </div>
        </div>
    </div>
</div>
</template>
 
<style scoped>

</style>