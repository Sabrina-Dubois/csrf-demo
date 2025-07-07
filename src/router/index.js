import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/LoginView.vue";

const routes = [
    { path: "/login", component: LoginView },
    { path: "/home", component: HomeView },
    { path: "/", redirect: "/login" },
];

export default createRouter({
    history: createWebHistory(),
    routes,
});
