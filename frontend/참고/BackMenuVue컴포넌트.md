# BackMenu.vue 컴포넌트
## 필요 속성(props)
title, isRight

## 속성 설명
### title: String
- < 버튼 옆에 중앙에 들어올 문구를 나타냄
### isRight
- true/false 값.
- 중앙 문구 옆에 오른쪽 버튼이 필요한 경우 true 넣기, 필요 없는 경우 비워두면 된다.

## 사용예시

    <template>
    <v-app>
        <v-main>
        <back-menu :title="mainTitle"></back-menu>
        <router-view />
        <nav-bar></nav-bar>
        </v-main>
    </v-app>
    </template>

    <script>
    import NavBar from "./components/layout/NavBar.vue";
    import BackMenu from "./components/layout/BackMenu.vue";

    export default {
    name: "App",
    components: { NavBar, BackMenu },
    data() {
        return {
        mainTitle: "안녕",
        };
    },
    };
</script>
