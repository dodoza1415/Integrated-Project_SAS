import { defineStore, acceptHMRUpdate } from "pinia";
import { ref } from "vue";

export const useAnnouncerStore =  defineStore('announcer', () => {
    const mode = ref("admin")
    const category = ref("")
    const page = ref(1)

    const setMode = (newMode) => {
        mode.value = newMode
    }

    const setCateogry = (newCategory) => {
        category.value = newCategory
    }

    const setPage = (newPage) => {
        page.value = newPage
    }
})