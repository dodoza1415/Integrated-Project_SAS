export const getAnnouncements = async () => {
  try {
    const res = await fetch("http://localhost:8080/api/announcements");
    if (res.status === 200) {
      const announcements = await res.json();
      return announcements;
    } else throw new Error("No Announcement");
  } catch (error) {
    console.log(error);
  }
};
