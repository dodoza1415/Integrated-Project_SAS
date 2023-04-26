import { createRouter, createWebHashHistory } from "vue-router";
import Announcements  from "../views/Announcements.vue";
import Announcement from "../views/Details.vue";
import NotFound from "../views/NotFound.vue";
const router = createRouter({
    history : createWebHashHistory(),
    routes : [
        {
            path:"/",
            name:"Home",
            component: Announcements
        },
        {
            path:"/details/:idAnnouncement",
            name:"Announcement",
            component: Announcement
        }, 
        {
            path:'/:catchNotMatchPath(.*)',
            name:'NotFound',
            component:NotFound
        }
    ]
});

export default router;