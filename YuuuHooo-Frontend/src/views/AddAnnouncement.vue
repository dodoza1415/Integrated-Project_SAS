<script setup>
import { ref , computed} from "vue";
import { RouterLink , useRouter} from "vue-router";

defineEmits(["add"]);
const announcement = ref({announcementCategory : 'ทั่วไป' , announcementDisplay : 'N'});
const router = useRouter();

const submitDisabled = ref(false);
const disabledButton = computed(() => {
    if (announcement.announcementCategory === "") return submitDisabled = true
});
</script>

<template>
<div>
    <form> 
  <div class="font-bold text-xl">Announcement Detail: <br/></div>
    <span class="font-bold">Title: </span>
    <input
      type="text"
      id="title"
      placeholder="Your Title"
      class="ann-title input input-bordered w-max ann-title"
      v-model="announcement.announcementTitle"    
      />
      <!-- required  -->

<br />
    <span class="font-bold">Category: </span
    ><br/>
    <select name="category" v-model="announcement.announcementCategory" class="ann-category">
        <option value="ทั่วไป" selected>ทั่วไป</option>
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
      v-model="announcement.announcementDescription"
      required
    ></textarea>
    <span class="font-bold">Publish Date</span>
    <br/>
    <input
      type="date"
      class="ann-title input input-bordered w-max ann-publish-date"
      v-model="announcement.publishDate"
    />
    <input
      type="time"
      placeholder="Your Title"
      class="ann-title input input-bordered w-max ann-publish-time"
      v-model="announcement.publishTime"
    /><br/>
    <span class="font-bold">Close Date</span>
    <br/>
    <input
      type="date"
      class="ann-title input input-bordered w-max ann-close-date"
      v-model="announcement.closeDate"
    />
    <input
      type="time"
      placeholder="Your Title"
      class="ann-title input input-bordered w-max ann-close-time"
      v-model="announcement.closeTime"
    /><br/>
    Display
    <br/>
    <input type="checkbox" id="display" name="display" value="Y" v-model="announcement.announcementDisplay" class="ann-display">
    <label for="display">Check to show this announcement</label><br/>
    <input type="submit" @click="$emit('add', announcement) , router.push('/')" class="ann-button" value="Submit" id="submit" :disabled="submitDisabled">
    <!-- <button @click="$emit('add', announcement) , router.push('/')" class="ann-button" :disabled="submitDisabled">Submit</button> -->
    <RouterLink :to="{ name: 'Home' }" class="ann-button">Cancel</RouterLink>
</form>
</div>
</template>

<style scoped></style>