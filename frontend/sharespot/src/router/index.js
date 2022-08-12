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
import NotFound from '../views/error/NotFound.vue';

// import store from "@/store/index.js";

Vue.use(VueRouter);

// const onlyAuthUser = async (to, from, next) => {
//   const checkUserInfo = store.getters["userStore/checkUserInfo"];
//   const getUserInfo = store._actions["userStore/getUserInfo"];
//   let token = sessionStorage.getItem("Authorization");

//   if (checkUserInfo == null && token) {
//     await getUserInfo(token);
//   }

//   if (checkUserInfo === null) {
//     alert("로그인이 필요한 페이지입니다.");
//     next({ name: "login" });
//   } else {
//     next();
//   }
// };

// const onlyNoAuthUser = async (to, from, next) => {
//   const checkUserInfo = store.getters["userStore/checkUserInfo"];
//   const getUserInfo = store._actions["userStore/getUserInfo"];
//   let token = sessionStorage.getItem("Authorization");
//   if (checkUserInfo == null && token) {
//     await getUserInfo(token);
//   }
//   if (checkUserInfo === null) {
//     next();
//   } else {
//     next({ name: "mainList" });
//   }
// };

const routes = [
  {
    path: "/",
    name: "home",
    // beforeEnter: onlyNoAuthUser,
    component: HomeView ,
  },
  {
    path: "/users/login",
    name: "login",
    // beforeEnter: onlyNoAuthUser,
    component: Login,
  },
  {
    path: "/users/signup",
    name: "signUp",
    // beforeEnter: onlyNoAuthUser,
    component: SignUp,
  },
  {
    path: "/users/findpass",
    name: "findPass",
    // beforeEnter: onlyNoAuthUser,
    component: FindPass,
  },
  {
    path: "/users/resetpass",
    name: "resetPass",
    // beforeEnter: onlyNoAuthUser,
    component: ResetPass,
  },
  //알림
  {
    path: "/main/notice",
    name: "notice",
    // beforeEnter: onlyAuthUser,
    component: Notice,
  },
  //메인피드 
  {
    path: "/main/mainlist",
    name: "mainList",
    // beforeEnter: onlyAuthUser,
    component: MainList,
  },
  //유저검색결과
  {
    path: "/main/usersearchresult/:word",
    name: "userSearchResult",
    // beforeEnter: onlyAuthUser,
    component: UserSearchResult,
  },
  //설정
  {
    path: "/settings",
    name: "settings",
    // beforeEnter: onlyAuthUser,
    component: Settings,
  },
  //알림설정
  {
    path: "/settings/notice",
    name: "settingsNotice",
    // beforeEnter: onlyAuthUser,
    component: SettingsNotice,
  },
  //개인정보설정
  {
    path: "/settings/info",
    name: "settingsInfo",
    // beforeEnter: onlyAuthUser,
    component: SettingsInfo,
  },
  //약관정보
  {
    path: "/settings/service",
    name: "settingsService",
    // beforeEnter: onlyAuthUser,
    component: SettingsService,
  },
  //게시글
  {
    path: "/post/register",
    name: "postRegister",
    // beforeEnter: onlyAuthUser,
    component: PostRegister,
  },
  {
    path: "/post/detail/:postno",
    name: "postDetail",
    // beforeEnter: onlyAuthUser,
    component: PostDetail,
  },
  {
    path: "/post/modify/:postno",
    name: "postModify",
    // beforeEnter: onlyAuthUser,
    component: PostModify,
  },
  {
    path: "/curation",
    name: "curationList",
    // beforeEnter: onlyAuthUser,
    component: CurationList,
  },
  {
    path: "/group/list",
    name: "groupList",
    // beforeEnter: onlyAuthUser,
    component: GroupList,
  },
  {
    path: "/group/register",
    name: "groupRegister",
    // beforeEnter: onlyAuthUser,
    component: GroupRegister,
  },
  {
    // /:groupno 추가해야 함!
    path: "/group/detail",
    name: "groupDetail",
    // beforeEnter: onlyAuthUser,
    component: GroupDetail,
  },
  {
    // /:groupno 추가해야 함!
    path: "/group/modify",
    name: "groupModify",
    // beforeEnter: onlyAuthUser,
    component: GroupModify,
  },
  {
    // /:userid 추가해야 함!
    path: "/profile/:userid",
    name: "profile",
    // beforeEnter: onlyAuthUser,
    component: Profile,
  },
  {
    path: "/profile/modify",
    name: "profileModify",
    // beforeEnter: onlyAuthUser,
    component: ProfileModify,
  },
  {
    path: "/profile/following/:userid",
    name: "profileFollowing",
    // beforeEnter: onlyAuthUser,
    component: ProfileFollowing,
  },
  {
    path: "/profile/follower/:userid",
    name: "profileFollower",
    // beforeEnter: onlyAuthUser,
    component: ProfileFollower,
  },
  {
    path: "/profile/taste/:userid",
    name: "profileTaste",
    // beforeEnter: onlyAuthUser,
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
