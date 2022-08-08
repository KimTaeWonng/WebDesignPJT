import { http } from "./http.js";

// 로그인 통신함수
async function login(user, success, fail) {
    console.log(user);
    await http
        .post(`/users/login`, JSON.stringify(user))
        .then(success)
        .catch(fail);
}

// id를 기준으로 토큰 확인 통신함수
async function findById(userid, success, fail) { 
    http.defaults.headers["Authorization"] =
        sessionStorage.getItem("Authorization");
    await http.get(`/users/info/${userid}`).then(success).catch(fail);
}

export { login, findById };