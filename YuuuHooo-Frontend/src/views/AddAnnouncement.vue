<script setup>
import { ref , computed, onMounted} from "vue";
import { RouterLink , useRouter} from "vue-router";



const router = useRouter();

const submitDisabled = ref(false);
const disabledButton = computed(() => {
    if (announcement.announcementCategory === "") return submitDisabled = true
});

const newAnnouncement = ref({});
onMounted(() => {
  newAnnouncement.value = {
    announcementTitle: "",
    announcementCategory: "",
    announcementDescription: "",
    publishDate: "",
    publishTime: "",
    closeDate: "",
    closeTime: "",
    announcementDisplay: "",
    
  }
})

const show = (ann) => {
  console.log(ann)
}

defineEmits(["add"]);
</script>

<template>
<div>
  <div class="font-bold text-xl">Announcement Detail: <br/></div>
    <span class="font-bold">Title: </span>
    <input
      type="text"
      id="title"
      placeholder="Your Title"
      class="ann-title input input-bordered w-max ann-title"
      v-model="newAnnouncement.announcementTitle"    
      />
      <!-- required  -->

<br />
    <span class="font-bold">Category: </span
    ><br/>
    <select name="category" v-model="newAnnouncement.announcementCategory" class="ann-category">
        <option value="ทั่วไป" se>ทั่วไป</option>
        <option value="ทนการศึกษา">ทนการศึกษา</option>
        <option value="หางาน">หางาน</option>
        <option value="ฝึกงาน">ฝึกงาน</option>
    </select>
    <br/>
    <span class="font-bold">Description: </span
    ><br/>
    <textarea
      placeholder="write description..."
      class="textarea textarea-bordered w-full ann-description"
      v-model="newAnnouncement.announcementDescription"
      required
    ></textarea>
    <span class="font-bold">Publish Date</span>
    <br/>
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
    /><br/>
    <span class="font-bold">Close Date</span>
    <br/>
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
    /><br/>
    Display
    <br/>
    <input type="checkbox" id="display" name="display" value="Y" v-model="newAnnouncement.announcementDisplay" class="ann-display">
    <label for="display">Check to show this announcement</label><br/>
    <RouterLink :to="{name: 'Home'}"><input type="submit" @click="$emit('add', newAnnouncement); show(newAnnouncement);" class="ann-button" value="Submit" id="submit" :disabled="submitDisabled"></RouterLink>
    <RouterLink :to="{ name: 'Home' }" class="ann-button">Cancel</RouterLink>
</div>
</template>

<style scoped></style>