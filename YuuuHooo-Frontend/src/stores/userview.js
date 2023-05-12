import { defineStore, acceptHMRUpdate } from "pinia";
import { ref } from "vue";
const API_ROOT=import.meta.env.VITE_ROOT_API;



export const useAnnouncerStore = defineStore("announcer", () => {
  const mode = ref("active");
  const category = ref("");
  const page = ref(1);

  const setMode = (newMode) => {
    mode.value = newMode;
  };

  const setCateogry = (newCategory) => {
    category.value = newCategory;
  };

  const setPage = (newPage) => {
    page.value = newPage;
  };

  return { mode, category, page, setMode, setCateogry, setPage };
});

if (import.meta.hot) {
  import.meta.hot.accept(acceptHMRUpdate(useAnnouncerStore, import.meta.hot));
}
