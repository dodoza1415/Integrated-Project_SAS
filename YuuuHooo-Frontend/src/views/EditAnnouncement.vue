<script setup>
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const { params } = useRoute();
const router = useRouter();
const announcementDetails = ref({});
const categories = ref([]);
onMounted(async () => {
  categories.value = await getCategories();
  console.log(categories.value);
  const announcementId = params.id;
  try {
    const res = await fetch(`${API_ROOT}/api/announcements/${announcementId}`);
    if (res.status === 200) {
      announcementDetails.value = await res.json();
      convertTime(
        announcementDetails.value.publishDate,
        null,
        "publish",
        "UTC"
      );
      convertTime(announcementDetails.value.closeDate, null, "close", "UTC");
      // console.log(typeof announcementDetails.value.closeDate === "object");
      // console.log(announcementDetails)
      // return announcementDetails;
    } else if (res.status !== 200) {
      alert("The requested page is not available!");
      router.push("/");
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
});

const EditAnnouncement = async (updateAnnouncement) => {
  // const announcementId = params.id;
  try {
    const res = await fetch(`${API_ROOT}/api/announcements/${params.id}`, {
      method: "PUT",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(updateAnnouncement),
    });
    if (res.status === 200) {
      console.log("edit sucessfully");
      // announcementDetails.value = announcementDetails.value.map((r) => {
      //   if (r.id === updateAnnouncement.id) {
      //     r.announcementTitle = updateAnnouncement.announcementTitle;
      //     r.announcementDescription =
      //       updateAnnouncement.announcementDescription;
      //     r.categoryId = updateAnnouncement.categoryId;
      //     r.publishDate = updateAnnouncement.publishDate;
      //     r.closeDate = updateAnnouncement.closeDate;
      //     r.announcementDisplay = updateAnnouncement.announcementDisplay;
      //   }
      //   return r;
      // });
      router.push(`/admin/announcement/${params.id}`);
    } else if (res.status !== 200) {
      alert(
        `There is an error! , Can't update this announcement => Status Code : ${res.status}`
      );
    } else throw new Error("cannot edit");
  } catch (error) {
    console.log(error);
  }
};

const publish_Date = ref(null);
const publish_Time = ref(null);
const close_Date = ref(null);
const close_Time = ref(null);
const convertTime = (date = null, time = null, type, tz) => {
  if (tz === "UTC") {
    if (typeof date === "object") {
      if (type === "publish") {
        publish_Date.value = new String();
      } else if (type === "close") {
        close_Date.value = new String();
      }
    } else {
      const optionsDate = { year: "numeric", month: "2-digit", day: "2-digit" };
      const dateICT = new Date(date).toLocaleDateString("fr-CA", optionsDate);

      const optionsTime = { hour: "2-digit", minute: "2-digit" };
      const timeICT = new Date(date).toLocaleTimeString("en-GB", optionsTime);
      if (type === "publish") {
        publish_Date.value = dateICT;
        publish_Time.value = timeICT;
      } else if (type === "close") {
        close_Date.value = dateICT;
        close_Time.value = timeICT;
      }
    }
  }

  if (tz === "ICT") {
    const dateString = date + " , " + time;
    // console.log(dateString)
    if (dateString.includes(null) || date.length === 0) {
      if (type === "publish") {
        announcementDetails.value.publishDate = null;
      } else if (type === "close") {
        announcementDetails.value.closeDate = null;
      }
    } else {
      const dateICT = new Date(dateString);
      const dateUTC = dateICT.toISOString();
      if (type === "publish") {
        announcementDetails.value.publishDate = dateUTC;
      } else if (type === "close") {
        announcementDetails.value.closeDate = dateUTC;
      }
    }
  }
};

const show = (updateAnn) => {
  console.log(close_Date.value);
  console.log(updateAnn);
};

const userModifying = ref(true);
const change = () => {
  userModifying.value = !userModifying.value;
};
</script>

<template>
  <div>
    <div class="font-['Acme'] m-10">
      <div class="text-[45px]">Announcement Details:</div>
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
                required
                @input="userModifying = false"
              />
            </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Category:</th>
            <div class="m-4">
              <select
                name="category"
                v-model="announcementDetails.categoryId"
                class="ann-category pl-[20px] input input-bordered"
                required
                @input="userModifying = false"
              >
                <option disabled value="">Please select one</option>
                <option
                  :value="category.categoryId"
                  v-for="(category, index) in categories"
                >
                  {{ category.categoryName }}
                </option>
              </select>
              <!-- {{ announcementDetails.categoryId }} -->
            </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Description:</th>
            <div class="m-4">
              <textarea
                placeholder="write description..."
                class="textarea textarea-bordered w-full ann-description pl-[20px]"
                v-model="announcementDetails.announcementDescription"
                required
                @input="userModifying = false"
              ></textarea>
            </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Publish Date:</th>
            <div class="m-4">
              <input
                type="date"
                class="ann-publish-date input input-bordered w-max pl-[20px] mr-2"
                v-model="publish_Date"
                @input="userModifying = false"
              />
              <input
                type="time"
                class="ann-publish-time input input-bordered w-max pl-[20px]"
                v-model="publish_Time"
                @input="userModifying = false"
              />
            </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Close Date:</th>
            <div class="m-4">
              <input
                type="date"
                class="ann-close-date input input-bordered w-max pl-[20px] mr-2"
                v-model="close_Date"
                @input="userModifying = false"
              />
              <input
                type="time"
                class="ann-close-time input input-bordered w-max pl-[20px]"
                v-model="close_Time"
                @input="userModifying = false"
              />
            </div>
          </tr>
          <tr>
            <th class="text-left text-[30px]">Display:</th>
            <div class="mt-4 mr-5">
              <input
                type="checkbox"
                id="display"
                name="display"
                true-value="Y"
                false-value="N"
                v-model="announcementDetails.announcementDisplay"
                class="ann-display checkbox checkbox-sm"
                @input="userModifying = false"
              />
              <label for="display" class="ml-2 text-[18px]"
                >Check to show this announcement
                <!-- {{ announcementDetails.announcementDisplay }} -->
              </label>
            </div>
          </tr>
        </table>
        <div class="mt-5">
          <!-- <div class="text-blue-400 hover:text-gray-500 mt-5 text-[20px]"> -->
          <RouterLink
            :to="{ name: 'AnnouncementDetails' }"
            class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-gray-300 hover:border-transparent"
          >
            Back
          </RouterLink>
          <!-- </div> -->
          <!-- <div class="text-blue-400 hover:text-red-500 mt-5 text-[20px]"> -->
          <span class="ml-2"
            ><button
              class="ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent"
              @click="
                convertTime(publish_Date, publish_Time, 'publish', 'ICT');
                convertTime(close_Date, close_Time, 'close', 'ICT');
                EditAnnouncement(announcementDetails);
              "
              :disabled="userModifying"
            >
              Submit
              <!-- ต้องมีการแก้ไขข้อมูลบางอย่าง ให้ต่างจากเดิมก่อนถึงจะกด submit ได้ / ถ้ามีการแก้แล้วเปลี่ยนกลับเป็นแบบเดิม ต้องกดไม่ได้-->
            </button></span
          >
        </div>
        <!-- </div> -->
      </div>
    </div>
  </div>
</template>

<style scoped></style>
