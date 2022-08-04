import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
// 회원가입
import Login from "../views/users/Login.vue";
import SignUp from "../views/users/SignUp.vue";
import FindPass from "../views/users/FindPass.vue";
import ResetPass from "../views/users/ResetPass.vue";
// 메인피드
import Notice from "../views/main/Notice.vue";
import MainList from "../views/main/MainList.vue";
import UserSearchResult from "../views/main/UserSearchResult.vue";
// 설정
import Settings from "../views/settings/Settings.vue";
import SettingsNotice from "../views/settings/SettingsNotice.vue";
import SettingsInfo from "../views/settings/SettingsInfo.vue";
import SettingsService from "../views/settings/SettingsService.vue";
// 게시글
import PostRegister from "../views/post/PostRegister.vue";
import PostDetail from "../views/post/PostDetail.vue";
import PostModify from "../views/post/PostModify.vue";
// 탐색
import CurationList from "../views/curation/CurationList.vue";
// 그룹
import GroupList from "../views/group/GroupList.vue";
import GroupRegister from "../views/group/GroupRegister.vue";
import GroupDetail from "../views/group/GroupDetail.vue";
import GroupModify from "../views/group/GroupModify.vue";
// 프로필
import Profile from "../views/profile/Profile.vue";
import ProfileModify from "../views/profile/ProfileModify.vue";
import ProfileFollowing from "../views/profile/ProfileFollowing.vue";
import ProfileFollower from "../views/profile/ProfileFollower.vue";
import ProfileTaste from "../views/profile/ProfileTaste.vue";
// 에러
import NotFound from '../views/error/NotFound.vue'


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView ,
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
    // name: "login",
    component: Login,
  },
  {
    path: "/users/signup",
    name: "signUp",
    component: SignUp,
  },
  {
    path: "/users/findpass",
    name: "findPass",
    component: FindPass,
  },
  {
    path: "/users/resetpass",
    name: "resetPass",
    component: ResetPass,
  },
  //알림
  {
    path: "/main/notice",
    name: "notice",
    component: Notice,
  },
  //메인피드 
  {
    path: "/main/mainlist",
    name: "mainList",
    component: MainList,
  },
  //유저검색결과
  {
    path: "/main/usersearchresult/:word",
    name: "userSearchResult",
    component: UserSearchResult,
  },
  //설정
  {
    path: "/settings",
    name: "settings",
    component: Settings,
  },
  //알림설정
  {
    path: "/settings/notice",
    name: "settingsNotice",
    component: SettingsNotice,
  },
  //개인정보설정
  {
    path: "/settings/info",
    name: "settingsInfo",
    component: SettingsInfo,
  },
  //약관정보
  {
    path: "/settings/service",
    name: "settingsService",
    component: SettingsService,
  },
  //게시글
  {
    path: "/post/register",
    name: "postRegister",
    component: PostRegister,
  },
  {
    // /:postno 추가해야 함!
    path: "/post/detail",
    name: "postDetail",
    component: PostDetail,
  },
  {
    path: "/post/modify/:postno",
    name: "postModify",
    component: PostModify,
  },
  {
    path: "/curation",
    name: "curationList",
    component: CurationList,
  },
  {
    path: "/group/list",
    name: "groupList",
    component: GroupList,
  },
  {
    path: "/group/register",
    name: "groupRegister",
    component: GroupRegister,
  },
  {
    // /:groupno 추가해야 함!
    path: "/group/detail",
    name: "groupDetail",
    component: GroupDetail,
  },
  {
    // /:groupno 추가해야 함!
    path: "/group/modify",
    name: "groupModify",
    component: GroupModify,
  },
  {
    // /:userid 추가해야 함!
    path: "/profile",
    name: "profile",
    component: Profile,
  },
  {
    path: "/profile/modify",
    name: "profileModify",
    component: ProfileModify,
  },
  {
    path: "/profile/following",
    name: "profileFollowing",
    component: ProfileFollowing,
  },
  {
    path: "/profile/follower",
    name: "profileFollower",
    component: ProfileFollower,
  },
  {
    path: "/profile/taste",
    name: "profileTaste",
    component: ProfileTaste,
  },
  //에러
  {
    path: "/404",
    name: "NotFound",
    component: NotFound,
  },
  {
    path: "/500",
    name: "Error",
    component: NotFound,
  },
  // {
  //   path: "*",
  //   redirect: "/404",
  // },
];

const router = new VueRouter({
  routes,
});

export default router;
