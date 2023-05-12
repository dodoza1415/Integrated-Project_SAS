import { useAnnouncerStore } from "../stores/userview";
const API_ROOT=import.meta.env.VITE_ROOT_API;


export const getAnnouncements = async () => {
  try {
    const announcer = useAnnouncerStore()
    const res = await fetch(`${API_ROOT}/api/announcements` + '?' + new URLSearchParams({
      mode : announcer.mode
  }));
    if (res.status === 200) {
      const announcements = await res.json();
      return announcements;
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
};
