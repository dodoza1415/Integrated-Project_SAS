<script setup>
import { onMounted, ref } from "vue";
import { useRoute , useRouter} from "vue-router";

const { params } = useRoute();
const router = useRouter();
const announcementDetails = ref({})

onMounted(async () => {
  const announcementId = params.id;
  try {
    const res = await fetch(
      `http://localhost:8080/api/announcements/${announcementId}`
    );
    if (res.status === 200) {
        announcementDetails.value = await res.json();
      console.log(announcementDetails.value)
    }else if(res.status !== 200){
      alert("The requested page is not available!") 
      router.push('/');
    }else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
});

const EditAnnouncement = async (updateAnnouncement) => {
  try {
    const res = await fetch(
      `http://localhost:8080/api/announcements/${announcementId}`,
      {
        method: "PUT",
        headers: {
          "content-type": "application/json",
        },
        body: JSON.stringify(updateAnnouncement),
      }
    );
    if (res.status === 200) {
      console.log("edit sucessfully");
      announcementDetails.value = announcementDetails.value.map((r) => {
        if (r.id === updateAnnouncement.id) {
          r.announcementTitle = updateAnnouncement.announcementTitle;
          r.announcementCategory = updateAnnouncement.announcementCategory;
          r.announcementDescription = updateAnnouncement.announcementDescription;
          r.publishDate = updateAnnouncement.publishDate;
          r.closeDate = updateAnnouncement.closeDate;
          r.announcementDisplay = updateAnnouncement.announcementDisplay;
        }
        return r;
      },
       router.push(`/admin/announcement/${params.id}`));
    } else if(res.status !== 200){
      alert(`Can't update this announcement , ...`) 
    }else throw new Error("cannot edit");
  } catch (error) {
    console.log(error);
  }
};
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
            <input
            type="text"
            id="title"
            placeholder="Your Title"
            class="ann-title input input-bordered w-max ann-title pl-[20px]"
            v-model="announcementDetails.announcementTitle"    
            required/>
          </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Category:</th>
            <div class="m-4">
            <select name="category" v-model="announcementDetails.announcementCategory" class="ann-category pl-[20px]" required>
            <option value="ทั่วไป">ทั่วไป</option>
            <option value="ทนการศึกษา">ทุนการศึกษา</option>
            <option value="หางาน">หางาน</option>
            <option value="ฝึกงาน">ฝึกงาน</option>
            </select>
          </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Description:</th>
            <div class="m-4">
            <textarea
            placeholder="write description..."
            class=" textarea textarea-bordered w-full ann-description pl-[20px]"
            v-model="announcementDetails.announcementDescription"
            required
            ></textarea>
          </div>
          </tr>
          <tr>
            
            <th class="text-left text-[30px]">Publish Date:</th>
            <div class="m-4">
            <input
            type="text"
            class="ann-title input input-bordered w-max ann-publish-date pl-[20px]" 
            v-model="announcementDetails.publishDate"
            />
          </div>
            <!-- <input
            type="time"
            class="ann-title input input-bordered w-max ann-close-time pl-[20px]"
            v-model="announcementDetails.publishTime"
            /> -->
          </tr>
          <tr>
            <th class="text-left text-[30px]">Close Date:</th>
            <div class="m-4">
            <input
            type="text"
            class="ann-title input input-bordered w-max ann-publish-date pl-[20px]" 
            v-model="announcementDetails.closeDate"
            />
          </div>
            <!-- <input
            type="time"
            class="ann-title input input-bordered w-max ann-close-date pl-[20px]"
            v-model="announcementDetails.closeTime"
            /> -->
          </tr>
          <tr>
            <th class="text-left text-[30px]">Display:</th>
          <div class="mt-4 mr-5">
            <input type="checkbox" id="display" name="display" value="Y" v-model="announcementDetails.announcementDisplay" class="ann-display" checked>
            <label for="display">Check to show this announcement</label> 
          </div>
          </tr>
        </table>
        <div class="mt-5">
        <!-- <div class="text-blue-400 hover:text-gray-500 mt-5 text-[20px]"> -->
          <RouterLink :to="{name : 'AnnouncementDetails'}"  class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:border-transparent">
               Back
        </RouterLink>
        <!-- </div> -->
        <!-- <div class="text-blue-400 hover:text-red-500 mt-5 text-[20px]"> -->
            <span class="ml-2"><button
                class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
                @click="EditAnnouncement(announcementDetails)"
              >
              Submit
              </button></span>
            </div>
        <!-- </div> -->
      </div>
    </div>
  </div>

</template>
 
<style scoped>

</style>