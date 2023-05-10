<script setup>
import { ref, computed, onMounted } from "vue";
import { RouterLink, useRouter, useRoute } from "vue-router";
import { getCategories } from "../composable/getCategories";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const categories = ref([]);

onMounted(async () => {
  categories.value = await getCategories();
  console.log(categories.value);
});
const newAnnouncement = ref({
  announcementTitle: "",
  announcementDescription: "",
  categoryId: "",
  publishDate: null,
  closeDate: null,
  announcementDisplay: false,
});

const publish_Date = ref(null);
const publish_Time = ref(null);
const close_Date = ref(null);
const close_Time = ref(null);
const convertUTC = (date, time, type) => {
  const dateString = date + " , " + time;
  // console.log(dateString)
  if (dateString.includes(null)) {
    return "-";
  } else {
    const dateICT = new Date(dateString);
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

// const show = (ann) => {
//   console.log(ann);
// };

const addAnnouncement = async (announcement) => {
  // ต้องรวม publishdate-time , closedate-time เข้าด้วยกันก่อน
  try {
    const res = await fetch(`${API_ROOT}/api/announcements`, {
      method: "POST",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(announcement),
    });
    if (res.status === 200) {
      const announcementAdded = await res.json();
      await router.push("/");
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
          class="ann-category pl-[20px] border border-black rounded-md h-[2.5em]"
          required
        >
          <option disabled value="">Please select one</option>
          <option
            :value="category.categoryId"
            v-for="(category, index) in categories"
          >
            {{ category.categoryName }}
          </option>
        </select>
        <!-- {{ newAnnouncement.categoryId }} -->
      </div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Description </span><br />
        <textarea
          class="textarea textarea-bordered ann-description pl-[20px] w-[30em] h-[10em]"
          v-model="newAnnouncement.announcementDescription"
          required
        ></textarea>
      </div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Publish Date</span><br />
        <input
          type="date"
          class="ann-publish-date input input-bordered w-max mr-1"
          v-model="publish_Date"
        />
        <input
          type="time"
          class="ann-publish-time input input-bordered w-max"
          v-model="publish_Time"
        />
      </div>
      <div class="mt-3">
        <span class="text-left text-[20px]">Close Date</span><br />
        <input
          type="date"
          class="ann-close-date input input-bordered w-max mr-1"
          v-model="close_Date"
        />
        <input
          type="time"
          class="ann-close-time input input-bordered w-max"
          v-model="close_Time"
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
          class="ann-display checkbox checkbox-sm"
        />
        <span class="ml-2 text-[18px]"
          ><label for="display">Check to show this announcement</label></span
        ><br />
        <!-- {{ newAnnouncement.announcementDisplay }} -->
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
            getDisplay(newAnnouncement.announcementDisplay);
            convertUTC(publish_Date, publish_Time, 'publish');
            convertUTC(close_Date, close_Time, 'close');
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
