import { createRouter, createWebHistory } from "vue-router";
import AddAnnouncement from "../views/AddAnnouncement.vue";
import AddEditUser from "../views/AddEditUser.vue";
import Announcements from "../views/Announcements.vue";
import Details from "../views/Details.vue";
import EditAnnouncement from "../views/EditAnnouncement.vue";
import Login from "../views/Login.vue";
import MatchPassword from "../views/MatchPassword.vue";
import NotFound from "../views/NotFound.vue";
import User from "../views/User.vue";
import UserViewAnnouncement from "../views/UserViewAnnouncement.vue";
import UserViewDetails from "../views/UserViewDetails.vue";
// import EditUser from "../views/EditUser.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
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
    {
      path: "/announcement",
      name: "UserViewAnnouncement",
      component: UserViewAnnouncement,
    },
    {
      path: "/announcement/:id",
      name: "UserViewDetails",
      component: UserViewDetails,
    },
    {
      path: "/admin/user",
      name: "UserList",
      component: User,
    },
    {
      path: "/admin/user/add",
      name: "AddUser",
      component: AddEditUser,
    },
    {
      path: "/admin/user/:id/edit",
      name: "EditUser",
      component: AddEditUser,
    },
    {
      path: "/admin/user/match",
      name: "MatchPassword",
      component: MatchPassword,
    },
    {
      path: "/login",
      name: "Login",
      component: Login,
    }
  ],
});

export default router;
