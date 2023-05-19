<script setup>
import { getAnnouncements } from "../composable/getAnnouncements.js";
import { getCategories } from "../composable/getCategories.js";
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import { useAnnouncerStore } from "../stores/userview";

const API_ROOT = import.meta.env.VITE_ROOT_API;

const { params } = useRoute();
const router = useRouter();
const announcer = useAnnouncerStore();
const categories = ref([]);
const announcements = ref([]);

const closedAnnouncements = ref("Closed Announcements");
const announcementModeTitle = () => {
  if (announcer.mode === "active") {
    closedAnnouncements.value = "Closed Announcements";
    changeModeButton.value =
      "ann-button btn btn-info bg-gray-200 border-transparent hover:bg-red-300 hover:border-transparent";
  } else if (announcer.mode === "close") {
    closedAnnouncements.value = "Active Announcements";
    changeModeButton.value =
      "ann-button btn btn-info bg-gray-200 border-transparent hover:bg-green-300 hover:border-transparent";
  }
};

const changeModeButton = ref(
  "ann-button btn btn-info bg-gray-200 border-transparent hover:bg-red-300 hover:border-transparent"
);
const announcementMode = () => {
  if (announcer.mode === "active") {
    announcer.setMode("close");
    announcer.setPage(0);
    fetchAnnouncement();
    console.log(announcements.value);
  } else if (announcer.mode === "close") {
    announcer.setMode("active");
    announcer.setPage(0);
    fetchAnnouncement();
    console.log(announcements.value);
  }
};
onMounted(async () => {
  categories.value = await getCategories();
  announcer.setCateogry(0);

  try {
    const announcer = useAnnouncerStore();
    const res = await fetch(
      `${API_ROOT}/api/announcements/pages` +
        "?" +
        new URLSearchParams({
          mode: announcer.mode,
          category: announcer.category,
        })
    );
    if (res.status === 200) {
      // console.log(await res.json())
      announcements.value = await res.json();
      console.log(announcements.value);
      // return announcements;
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }

  announcementModeTitle();
  // console.log(announcements.value)
  // console.log(announcer.mode)
});

const fetchAnnouncement = async () => {
  try {
    const announcer = useAnnouncerStore();
    const res = await fetch(
      `${API_ROOT}/api/announcements/pages` +
        "?" +
        new URLSearchParams({
          mode: announcer.mode,
          category: announcer.category,
          page: announcer.page,
        })
    );
    if (res.status === 200) {
      // console.log(await res.json())
      announcements.value = await res.json();
      // console.log(announcements.value.last)
      // return announcements;
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
};

const timezone = Intl.DateTimeFormat().resolvedOptions().timeZone;
// console.log(typeof timezone)

const convertTZ = (date) => {
  if (typeof date === "string") {
    const convertDate = new Date(date);
    const options = {
      day: "numeric",
      month: "short",
      year: "numeric",
      hour: "numeric",
      minute: "numeric",
      timeZone: timezone,
    };
    return convertDate.toLocaleDateString("en-GB", options);
  } else {
    return "-";
  }
};

const userDetailPage = (announcementId) => {
  router.push({ name: "UserDetails", params: { id: announcementId } });
};

const prevStatus = computed(() => {
  if (announcements.value.first === true) {
    return true;
  }
});
const nextStatus = computed(() => {
  if (announcements.value.last === true) {
    return true;
  }
});

const pageList = computed(() => {
  const range = 10;
  const page = announcer.page + 1;
  const pageList = [];

  if (announcements.value.totalPages > 10) {
    if (page < range) {
      for (let i = 1; i <= 10; i++) {
        pageList.push(i);
      }
    } else if (page >= range) {
      for (let i = 1; i <= 10; i++) {
        pageList.push(i + (page - range));
      }
    }
  } else {
    for (let i = 1; i <= announcements.value.totalPages; i++) {
      pageList.push(i);
    }
  }

  return pageList;
});

const changePage = (type, event) => {
  if (type === "click") {
    announcer.setPage(event.target.textContent - 1);
    fetchAnnouncement();
  } else if (type === "next") {
    if (announcer.page < announcements.value.totalPages - 1) {
      announcer.setPage(announcer.page + 1);
      // console.log(announcer.page)
      // console.log(pageList.value)
      // console.log(announcements.value.totalPages - 1)
      fetchAnnouncement();
    }
  } else if (type === "prev") {
    // console.log(announcer.page)
    if (announcer.page >= 1) {
      announcer.setPage(announcer.page - 1);
      fetchAnnouncement();
    }
  }
};
</script>

<template>
  <div>
    <div class="text-2xl font-['Acme'] m-10 text-center font-bold">
      SIT Announcement System (SAS)
    </div>
    <div class="overflow-auto m-10">
      <div class="text-lg font-['Acme'] flex justify-between">
        <span class="font-bold"
          >Date/Time shown in Timezone &nbsp:
          <span class="font-thin">&nbsp{{ timezone }}</span>
        </span>
      </div>
      <div class="mb-[-10px] flex justify-between">
        <span class="text-lg font-['Acme'] font-bold"
          >Choose Category &nbsp:
          <select
            class="ann-category-filter ml-3 mt-3 border border-gray rounded-md h-[2.0em] w-[7.5em] font-thin text-sm"
            v-model="announcer.category"
            @change="
              announcer.setCateogry(announcer.category);
              announcer.setPage(0);
              fetchAnnouncement();
            "
          >
            <option value="0" selected>ทั้งหมด</option>
            <option
              v-for="(category, index) in categories"
              :value="category.categoryId"
              :key="category.categoryId"
            >
              {{ category.categoryName }}
            </option>
          </select>
        </span>
        <span class="mb-[-20px] text-lg font-['Acme']">
          <button
            @click="announcementMode(), announcementModeTitle()"
            class="ann-button"
            :class="changeModeButton"
          >
            {{ closedAnnouncements }}
          </button>
        </span>
      </div>
      <div v-if="announcements.totalElements === 0">
        <h3 class="text-2xl font-['Acme'] mt-5">No Announcement</h3>
      </div>
      <table
        v-else
        class="table table-compact auto w-full mt-7 border shadow-lg border-gray-300 rounded-md"
      >
        <tr class="font-['Acme'] bg-red-100">
          <th class="text-lg text-center">No.</th>
          <th class="text-lg">Title</th>
          <th v-if="announcer.mode === 'close'">Close date</th>
          <th class="text-lg">Category</th>
        </tr>
        <tr
          v-for="(announcement, index) in announcements.content"
          :key="index"
          class="ann-item font-semibold border border-gray-300"
        >
          <td class="text-center">{{ ++index + 5 * announcer.page }}</td>
          <td
            class="ann-title cursor-pointer hover:text-gray-500"
            @click="userDetailPage(announcement.id)"
          >
            {{ announcement.announcementTitle }}
          </td>
          <td v-if="announcer.mode === 'close'" class="ann-close-date">
            {{ convertTZ(announcement.closeDate) }}
          </td>
          <td class="ann-category">
            {{ announcement.announcementCategory }}
          </td>
        </tr>
      </table>
      <div
        class="text-2xl font-['Acme'] mt-5"
        v-if="announcements.totalElements > 5"
      >
        <!-- v-if="announcements.length >= 5" -->
        <button
          class="ann-page-prev btn btn-info bg-gray-200 border-transparent hover:bg-green-600 hover:text-white hover:border-transparent w-[80px]"
          :disabled="prevStatus"
          @click="changePage('prev', $event)"
        >
          Prev
        </button>
        <button
          class="btn bg-gray-200 border-transparent text-black hover:border-transparent hover:bg-green-400 m-1"
          v-for="(page, index) in pageList"
          :key="index"
          @click="changePage('click', $event)"
          :class="
            pageList[index] - 1 === announcer.page
              ? `ann-page-${index} bg-green-400 text-black`
              : `ann-page-${index}`
          "
        >
          {{ page }}
        </button>
        <button
          class="ann-page-next btn btn-info bg-gray-200 border-transparent hover:bg-green-500 hover:border-transparent hover:text-white w-[80px]"
          :disabled="nextStatus"
          @click="changePage('next', $event)"
        >
          Next
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
