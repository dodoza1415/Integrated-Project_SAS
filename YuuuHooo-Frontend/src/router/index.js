import { createRouter, createWebHistory } from "vue-router";
import Announcements from "../views/Announcements.vue";
import Details from "../views/Details.vue";
import NotFound from "../views/NotFound.vue";
import Add from "../views/AddAnnouncement.vue";
const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      redirect: { path: "/admin/announcement" },
      name: "Home",
      component: Announcements,
      children: [
        {
          path: "/admin/announcement",
          name: "AnnouncementList",
          component: Announcements,
        }
      ]
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
      component: Add,
    },
  ],
});

export default router;
