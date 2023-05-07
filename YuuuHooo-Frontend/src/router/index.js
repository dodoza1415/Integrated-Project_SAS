import { createRouter, createWebHistory } from "vue-router";
import Announcements from "../views/Announcements.vue";
import Details from "../views/Details.vue";
import NotFound from "../views/NotFound.vue";
import AddAnnouncement from "../views/AddAnnouncement.vue";
import EditAnnouncement from "../views/EditAnnouncement.vue"
const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "Home",
      component: Announcements,
    },
    {
      path: "/admin/announcement",
      name: "AnnouncementList",
      component: Announcements,
    },
    {
      path: "/admin/announcement/:id",
      name: "AnnouncementDetails",
      component: Details,
    },
    {
      path: "/:catchNotMatchPath(.*)",
      name: "NotFound",
      component: NotFound,
    },
    {
      path: "/admin/announcement/add",
      name: "AddAnnouncement",
      component: AddAnnouncement,
    },
    {
      path: "/admin/announcement/:id/edit",
      name: "EditAnnouncement",
      component: EditAnnouncement,
    },
  ],
});

export default router;
