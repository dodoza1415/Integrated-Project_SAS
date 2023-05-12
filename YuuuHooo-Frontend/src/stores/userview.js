import { defineStore, acceptHMRUpdate } from "pinia";
import { ref } from "vue";
const API_ROOT=import.meta.env.VITE_ROOT_API;


<<<<<<< HEAD
export const useAnnouncerStore =  defineStore('announcer', () => {
    const mode = ref("active")
    const category = ref("")
    const page = ref(1)
=======
const API_ROOT = import.meta.env.VITE_ROOT_API;
>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445

export const useAnnouncerStore = defineStore("announcer", () => {
  const mode = ref("active");
  const category = ref("");
  const page = ref(1);

  const setMode = (newMode) => {
    mode.value = newMode;
  };

<<<<<<< HEAD
    const setPage = (newPage) => {
        page.value = newPage
    }

    return {mode, category, page, setMode, setCateogry, setPage}
})

if(import.meta.hot){
    import.meta.hot.accept(acceptHMRUpdate(useAnnouncerStore, import.meta.hot))
}
=======
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
>>>>>>> 426e833d011d2cceae368fb8e0919df1ddebe445
