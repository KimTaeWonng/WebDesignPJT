# BackMenu.vue 컴포넌트
## 필요 속성(props)
title, isRight, type

## 속성 설명
### title: String
- < 버튼 옆에 중앙에 들어올 문구를 나타냄
### isRight: Boolean
- true/false 값.
- 중앙 문구 옆에 오른쪽 버튼이 필요한 경우 true 넣기, 필요 없는 경우 비워두면 된다.
- 참고: isRight 속성은 boolean 타입인데 v-model 바인딩없이 true/false 하는 법을 몰라서... data()에 추가해서 사용하세요...! (알면알려주기)
### type: String
- 오른쪽 버튼 종류를 나타낸다
- groupDetail.vue 에서 사용하는 경우 : groupDetail<br>
profileModify.vue 에서 사용하는 경우 : profileModify 로 설정한다.

## 사용예시

    <template>
    <v-app>
        <v-main>
        <back-menu title="중앙하단메뉴이름으로설정하세요" :isRight="isRight" type="groupDetail"></back-menu>
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
        isRight: true,
        };
    },
    };
</script>
