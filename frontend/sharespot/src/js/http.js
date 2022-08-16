import axios from "axios";

// axios 객체 생성
const http = axios.create({
    baseURL: "https://i7a505.p.ssafy.io/api/",
    headers: {
        "Content-type": "application/json"
    },
});

export { http };