<script setup>
import { ref , computed , onMounted} from "vue";
import { RouterLink , useRouter, useRoute} from "vue-router"


const newAnnouncement = ref({});
const router = useRouter();
const submitDisabled = ref(false);
// const disabledButton = computed(() => {
//     if (announcement.announcementCategory === "") return submitDisabled = true
// });
onMounted(() => {
  newAnnouncement.value={
  announcementTitle:"",
  announcementCategory:"",
  announcementDescription:"",
  publishDate:"",
  publishTime:"",
  closeDate:"",
  closeTime:"",
  announcementDisplay:""
}})

const show = (ann) =>{
  console.log(ann)
}

const addAnnouncement = async (newAnnouncement) => {
  // ต้องรวม publishdate-time , closedate-time เข้าด้วยกันก่อน
  try {
    const res = await fetch("http://localhost:8080/api/announcements", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(newAnnouncement),
    });
    if (res.status === 201) {
      const announcementAdded = await res.json();
      console.log(announcementAdded)
    } else throw new Error("Can't add the announcement");
  } catch (error) {
    console.log(error);
  }
};
</script>

<template>
<div>
    <div class="font-['Acme'] m-10">
      <div class="text-[45px]">
      Announcement Detail: <br/>
    </div>
    <div class="mt-3">
    <span class="text-left text-[20px]">Title </span><br/>
    <input
      type="text"
      id="title"
      class="ann-title input input-bordered w-max ann-title pl-[20px]"
      v-model="newAnnouncement.announcementTitle"    
      required/>
    </div>
    <div class="mt-3">
    <span class="text-left text-[20px]">Category </span><br/>
    <select name="category" v-model="newAnnouncement.announcementCategory" class="ann-category pl-[20px]" required>
        <option value="ทั่วไป">ทั่วไป</option>
        <option value="ทนการศึกษา">ทุนการศึกษา</option>
        <option value="หางาน">หางาน</option>
        <option value="ฝึกงาน">ฝึกงาน</option>
    </select>
  </div>
  <div class="mt-3">
    <span class="text-left text-[20px]">Description </span
    ><br/>
    <textarea
      class="textarea textarea-bordered w-full ann-description pl-[20px]"
      v-model="newAnnouncement.announcementDescription"
      required
    ></textarea>
  </div>
  <div class="mt-3">
    <span class="text-left text-[20px]">Publish Date</span><br/>
    <input
      type="date"
      class="ann-title input input-bordered w-max ann-publish-date"
      v-model="newAnnouncement.publishDate"
    />
    <input
      type="time"
      placeholder="Your Title"
      class="ann-title input input-bordered w-max ann-publish-time"
      v-model="newAnnouncement.publishTime"
    />
  </div>
  <div class="mt-3">
    <span class="text-left text-[20px]">Close Date</span><br/>
    <input
      type="date"
      class="ann-title input input-bordered w-max ann-close-date"
      v-model="newAnnouncement.closeDate"
    />
    <input
      type="time"
      placeholder="Your Title"
      class="ann-title input input-bordered w-max ann-close-time"
      v-model="newAnnouncement.closeTime"
    />
  </div>
  <div class="mt-3">
    <span class="text-left text-[20px]">Display</span>
    <br/>
    <input type="checkbox" id="display" name="display" value="Y" v-model="newAnnouncement.announcementDisplay" class="ann-display">
    <span class="ml-2"><label for="display">Check to show this announcement</label></span><br/>
  </div>
    <!-- <input type="submit" @click="$emit('add', newAnnouncement) ; show(newAnnouncement) ; router.push('/')" class="ann-button" value="Submit" id="submit"> -->
    <div class="mt-7">
    <button @click="router.push('/'); addAnnouncement(newAnnouncement)" class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent">Submit</button>
    <!-- ปุ่ม Submit => user ต้องมีการ input ค่า title , category , description เข้ามาก่อนถึงจะกดได้ ไม่งั้นต้อง disabled (publishdate , closedate = optional , display default = N) -->
    <span class="ml-2"><button @click="router.push('/')"
            class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:border-transparent"
            >Cancel
       </button></span>
      </div>
</div>
</div>
</template>

<style scoped></style>