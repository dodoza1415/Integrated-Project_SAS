<script setup>
import { onMounted, ref , computed } from "vue";
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
      console.log(announcementDetails)
      return announcementDetails;
    }else if(res.status !== 200){
      alert("The requested page is not available!") 
      router.push('/');
    }else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
});

const EditAnnouncement = async (updateAnnouncement) => {
  // const announcementId = params.id;
  try {
    const res = await fetch(
      `http://localhost:8080/api/announcements/${params.id}`,
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
          r.announcementDescription = updateAnnouncement.announcementDescription;
          r.categoryId = updateAnnouncement.categoryId;
          r.publishDate = updateAnnouncement.publishDate;
          r.closeDate = updateAnnouncement.closeDate;
          r.announcementDisplay = updateAnnouncement.announcementDisplay;
        }
        return r;
      },
       router.push(`/admin/announcement/${params.id}`));
    } else if(res.status !== 200){
      alert(`There is an error! , Can't update this announcement => Status Code : ${res.status}`) 
    }else throw new Error("cannot edit");
  } catch (error) {
    console.log(error);
  }
};
const getCategoryId = (cateName) => {
  console.log(cateName)
  switch (cateName) {
    case "ทั่วไป":
    announcementDetails.value.categoryId = 1
      break;
    case "ทุนการศึกษา":
    announcementDetails.value.categoryId = 2
      break;
    case "หางาน":
    announcementDetails.value.categoryId = 3
      break;
    case "ฝึกงาน":
    announcementDetails.value.categoryId = 4
  }
}

const getDisplay = (v) => {
  switch (v) {
    case false:
    announcementDetails.value.announcementDisplay = 'N'
      break;
    case true:
    announcementDetails.value.announcementDisplay = 'Y'
  }
}


const convertUTC = (date, type) => {
  const dateICT = new Date(date)
  const dateUTC = dateICT.toISOString();
  if(type === 'publish'){
    announcementDetails.value.publishDate = dateUTC;
  }else if(type === 'close'){
    announcementDetails.value.closeDate = dateUTC
  }
  
  // console.log(dateUTC)
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
            <select name="category" v-model="announcementDetails.categoryId" class="ann-category pl-[20px] input input-bordered" required @change="getCategoryId(announcementDetails.categoryId)">
            <option disabled value="">Please select one</option>
            <option value="ทั่วไป">ทั่วไป</option>
            <option value="ทุนการศึกษา">ทุนการศึกษา</option>
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
            type="datetime-local"
            class="ann-publish-date ann-publish-time input input-bordered w-max ann-publish-date pl-[20px]" 
            v-model="announcementDetails.publishDate"
            @change="convertUTC(announcementDetails.publishDate, 'publish')"
            />
          </div>
            <!-- <input
            type="time"
            class="ann-publish-time input input-bordered w-max ann-close-time pl-[20px]"
            v-model="announcementDetails.publishTime"
            /> -->
          </tr>
          <tr>
            <th class="text-left text-[30px]">Close Date:</th>
            <div class="m-4">
            <input
            type="datetime-local"
            class="ann-close-date ann-close-time input input-bordered w-max ann-publish-date pl-[20px]" 
            v-model="announcementDetails.closeDate"
            @change="convertUTC(announcementDetails.closeDate, 'close')"
            />
          </div>
            <!-- <input
            type="time"
            class="ann-close-time input input-bordered w-max ann-close-date pl-[20px]"
            v-model="announcementDetails.closeTime"
            /> -->
          </tr>
          <tr>
            <th class="text-left text-[30px]">Display:</th>
          <div class="mt-4 mr-5">
            <input type="checkbox" id="display" name="display" v-model="announcementDetails.announcementDisplay" class="ann-display" @change="getDisplay(announcementDetails.announcementDisplay)">
            <label for="display">Check to show this announcement     {{ announcementDetails.announcementDisplay }}</label> 
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
                @click="EditAnnouncement(announcementDetails) "
              >
              Submit
              <!-- ต้องมีการแก้ไขข้อมูลบางอย่าง ให้ต่างจากเดิมก่อนถึงจะกด submit ได้ / ถ้ามีการแก้แล้วเปลี่ยนกลับเป็นแบบเดิม ต้องกดไม่ได้-->
              </button></span>
            </div>
        <!-- </div> -->
      </div>
    </div>
  </div>

</template>
 
<style scoped>

</style>