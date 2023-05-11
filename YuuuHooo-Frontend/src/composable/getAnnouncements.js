const API_ROOT=import.meta.env.VITE_ROOT_API;
const mode = "active"
export const getAnnouncements = async () => {
  try {
    const res = await fetch(`${API_ROOT}/api/announcements`+ JSON.stringify(mode));
    if (res.status === 200) {
      const announcements = await res.json();
      return announcements;
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
};
