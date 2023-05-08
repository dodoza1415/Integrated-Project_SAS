const API_ROOT=import.meta.env.VITE_ROOT_API;

export const getAnnouncements = async () => {
  try {
    const res = await fetch(`${API_ROOT}/api/announcements`);
    if (res.status === 200) {
      const announcements = await res.json();
      return announcements;
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
};
