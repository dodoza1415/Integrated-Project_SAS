import { createRouter, createWebHashHistory } from "vue-router";
import Announcements from "../views/Announcements.vue";
import Details from "../views/Details.vue";
import NotFound from "../views/NotFound.vue";
const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    // {
    //   path: "/",
    //   name: "Home",
    //   component: Announcements,
    // },
    {
      path: "/admin/announcement",
      name: "Home",
      component: Announcements,
    },
    {
      path: "/:id",
      name: "AnnouncementDetails",
      component: Details,
    },
    {
      path: "/:catchNotMatchPath(.*)",
      name: "NotFound",
      component: NotFound,
    },
  ],
});

export default router;
