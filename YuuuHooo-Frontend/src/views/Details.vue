<script setup>
import { onMounted, ref } from "vue";
import { useRoute , useRouter } from "vue-router";

const API_ROOT = import.meta.env.VITE_ROOT_API 

const router = useRouter();
const { params } = useRoute();


const announcementsDetails = ref({});
const announcementId = params.id;
const announcementEditPage = (announcementId) => {
  router.push({ name: "EditAnnouncement", params: { id : announcementId} });
}
onMounted(async () => {
  const announcementId = params.id;
  try {
    const res = await fetch(
      `${API_ROOT}/api/announcements/${announcementId}`
    );
    if (res.status === 200) {
      announcementsDetails.value = await res.json();
      console.log(announcementsDetails.value)
    }else if(res.status !== 200){
      alert("The requested page is not available!") 
      router.push('/');
    }else throw new Error("No Announcement");
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
    <div class="font-['Acme'] m-10">
      <div class="text-[45px] ">
        Announcement Details:
      </div>
      <div>
        <table class="ann-item">
          <tr>
            <th class="text-left text-[30px]">Title:</th>
            <div class="m-4">
            <td class="ann-title pl-[20px] text-[20px]">{{ announcementsDetails.announcementTitle }}</td>
          </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Category:</th>
            <div class="m-4">
            <td class="ann-category pl-[20px] text-[20px]">{{ announcementsDetails.announcementCategory }}</td>
          </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Description:</th>
            <div class="m-4">
            <td class="ann-description pl-[20px] text-[20px]">{{ announcementsDetails.announcementDescription }}</td>
          </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Publish Date:</th>
            <div class="m-4">
            <td class="ann-publish-date pl-[20px] text-[20px]">{{ convertTZ(announcementsDetails.publishDate) }}</td>
          </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Close Date:</th>
            <div class="m-4">
            <td class="ann-close-date pl-[20px] text-[20px]">{{ convertTZ(announcementsDetails.closeDate) }}</td>
          </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Display:</th>
            <div class="m-4">
            <td class="ann-display pl-[20px] text-[20px]">{{ announcementsDetails.announcementDisplay }}</td>
          </div>
          </tr>
        </table>
        <!-- <div class="text-blue-400 hover:text-gray-500 mt-5 text-[20px]"> -->
          <div class="mt-5">
          <button
                @click="router.push('/')"
                class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:border-transparent"
              >
              Back
        </button>
        <!-- </div> -->
        <!-- <div class="text-blue-400 hover:text-red-500 mt-5 text-[20px]"> -->
          <span class="ml-2"><button
                @click="announcementEditPage(announcementId)"
                class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
              >
              Edit
              </button></span>
        <!-- </div> -->
      </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Acme');
</style>
