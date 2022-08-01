import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import LoginView from "../views/users/LoginView.vue";
import SignUpView from "../views/users/SignUpView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/about",
    name: "about",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/AboutView.vue"),
  },
  {
    path: "/users/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/users/signup",
    name: "signup",
    component: SignUpView,
  },
];

const router = new VueRouter({
  routes,
});

export default router;
