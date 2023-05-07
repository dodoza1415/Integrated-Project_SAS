<script setup>
import { ref, computed, onMounted } from "vue";
import { RouterLink, useRouter, useRoute } from "vue-router";

// const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
// console.log(typeof timezone)

// const convertTZ = (date) => {
//   if(typeof date === "string"){
//     const convertDate = new Date.UTC(date)
//     const options = {  day: 'numeric', month: 'short', year: 'numeric', hour: 'numeric', minute: 'numeric'};
//     return convertDate.toLocaleDateString('en-GB', options)
//   }else{
//     return "-"
//   }
// }

const newAnnouncement = ref({
  announcementTitle: "",
  announcementDescription: "",
  categoryId: "",
  publishDate: null,
  closeDate: null,
  announcementDisplay: false,
});

// const getCategoryId = (cateName) => {
//   // console.log(cateName);
//   if (cateName !== "") {
//     switch (cateName) {
//       case "ทั่วไป":
//         newAnnouncement.value.categoryId = 1;
//         break;
//       case "ทุนการศึกษา":
//         newAnnouncement.value.categoryId = 2;
//         break;
//       case "หางาน":
//         newAnnouncement.value.categoryId = 3;
//         break;
//       case "ฝึกงาน":
//         newAnnouncement.value.categoryId = 4;
//     }
//   }
// };

const convertUTC = (date, type) => {
  if (date === null) {
    return "-";
  } else {
    const dateICT = new Date(date);
    const dateUTC = dateICT.toISOString();
    if (type === "publish") {
      newAnnouncement.value.publishDate = dateUTC;
    } else if (type === "close") {
      newAnnouncement.value.closeDate = dateUTC;
    }
  }

  // console.log(dateUTC)
};

const getDisplay = (v) => {
  if (v === true) {
    newAnnouncement.value.announcementDisplay = "Y";
  } else if (v === false) {
    newAnnouncement.value.announcementDisplay = "N";
  }
};

const router = useRouter();
const submitDisabled = ref(false);
// const disabledButton = computed(() => {
//     if (announcement.announcementCategory === "") return submitDisabled = true
// });

const show = (ann) => {
  console.log(ann);
};

const addAnnouncement = async (announcement) => {
  // ต้องรวม publishdate-time , closedate-time เข้าด้วยกันก่อน
  try {
    const res = await fetch("http://localhost:8080/api/announcements", {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(announcement),
    });
    if (res.status === 200) {
      const announcementAdded = await res.json();
      await router.push('/')
      // console.log(announcementAdded);
    } else {
      throw new Error(
        `There is an error! ,Can't add the announcement => Status Code : ${res.status}`
      );
    }
  } catch (error) {
    console.log(error);
  }
};
</script>

<template>
  <div>
    <div class="font-['Acme'] m-10">
      <div class="text-[45px]">Announcement Detail: <br /></div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Title </span><br />
        <input
          type="text"
          id="title"
          class="ann-title input input-bordered w-max ann-title pl-[20px]"
          v-model="newAnnouncement.announcementTitle"
          required
        />
      </div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Category </span><br />
        <select
          name="category"
          v-model="newAnnouncement.categoryId"
          class="ann-category pl-[20px]"
          required
        >
          <option disabled value="">Please select one</option>
          <option value="1">ทั่วไป</option>
          <option value="2">ทุนการศึกษา</option>
          <option value="3">หางาน</option>
          <option value="4">ฝึกงาน</option>
        </select>
        {{ newAnnouncement.categoryId }}
      </div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Description </span><br />
        <textarea
          class="textarea textarea-bordered ann-description pl-[20px]"
          v-model="newAnnouncement.announcementDescription"
          required
        ></textarea>
      </div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Publish Date</span><br />
        <input
          type="datetime-local"
          class="ann-publish-date ann-publish-time input input-bordered w-max"
          v-model="newAnnouncement.publishDate"
        />
      </div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Close Date</span><br />
        <input
          type="datetime-local"
          class="ann-close-date ann-close-time input input-bordered w-max"
          v-model="newAnnouncement.closeDate"
        />
      </div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Display</span>
        <br />
        <input
          type="checkbox"
          id="display"
          name="display"
          v-model="newAnnouncement.announcementDisplay"
          class="ann-display"
        />
        <span class="ml-2"
          ><label for="display">Check to show this announcement</label></span
        ><br />
        {{ newAnnouncement.announcementDisplay }}
      </div>
      <!-- <input type="submit" @click="$emit('add', newAnnouncement) ; show(newAnnouncement) ; router.push('/')" class="ann-button" value="Submit" id="submit"> -->
      <div class="mt-7">
        <button
        :disabled="
          newAnnouncement.announcementTitle.length === 0 ||
          newAnnouncement.announcementDescription.length === 0 || 
          newAnnouncement.categoryId.length === 0
          "
          @click="
            show(newAnnouncement);
            convertUTC(newAnnouncement.publishDate, 'publish');
            convertUTC(newAnnouncement.closeDate, 'close');
            getDisplay(newAnnouncement.announcementDisplay);
            addAnnouncement(newAnnouncement);
          "
          class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
        >
          Submit
        </button>
        <!-- ปุ่ม Submit => user ต้องมีการ input ค่า title , category , description เข้ามาก่อนถึงจะกดได้ ไม่งั้นต้อง disabled (publishdate , closedate = optional , display default = N) -->
        <span class="ml-2"
          ><button
            @click="router.push('/')"
            class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:border-transparent"
          >
            Cancel
          </button></span
        >
      </div>
    </div>
  </div>
</template>

<style scoped></style>
