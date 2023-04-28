<script setup>
import { onMounted, ref } from "vue";
import { useRoute } from "vue-router";

const { params } = useRoute();

const announcementsDetails = ref([]);
onMounted(async () => {
  const announcementId = params.id;
  try {
    const res = await fetch(
      `http://localhost:8080/api/announcements/${announcementId}`
    );
    if (res.status === 200) {
      announcementsDetails.value = await res.json();
      console.log(announcementsDetails.value)
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
});

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
    <div class="font-['Chicle'] m-10">
      <div class="text-[45px]">
        Announcement Details:
      </div>
      <div>
        <table>
          <tr>
            <th class="text-left text-[30px]">Title:</th>
            <td class="pl-[20px] text-[20px]">{{ announcementsDetails.title }}</td>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Category:</th>
            <td class="pl-[20px] text-[20px]">{{ announcementsDetails.categoryName }}</td>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Description:</th>
            <td class="pl-[20px] text-[20px]">{{ announcementsDetails.description }}</td>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Publish Date:</th>
            <td class="pl-[20px] text-[20px]">{{ convertTZ(announcementsDetails.publishDate) }}</td>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Close Date:</th>
            <td class="pl-[20px] text-[20px]">{{ convertTZ(announcementsDetails.closeDate) }}</td>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Display:</th>
            <td class="pl-[20px] text-[20px]">{{ announcementsDetails.display }}</td>
          </tr>
        </table>
        <div class="text-blue-400 hover:text-red-500 mt-5 text-[20px]">
          <RouterLink :to="{ name: 'Home' }">Back</RouterLink>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Chicle');
</style>
