<template>
    <v-container>
    <back-menu title="알림"></back-menu>
    <v-list>
      <div v-for="item in accountitems" :key="item.id"> 
        <v-list-item class="my-3">
          <v-list-item-avatar>
                <v-icon :color="item.color">
                    {{ item.icon }}
                </v-icon>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-html="item.title"></v-list-item-title>
            <v-list-item-subtitle  v-html="item.subtitle"></v-list-item-subtitle>
          </v-list-item-content>
          <!-- 스위치 건들이면 여기가 바뀜
             <input aria-checked="true" id="input-378" role="switch" type="checkbox" aria-disabled="false" value=""> -->
        <v-switch 
        inset
        color="success"
        ></v-switch>
        </v-list-item>
        <v-divider></v-divider>
      </div>

    </v-list>

    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";

export default {
    name: 'SettingsInfo',
    components: { BackMenu },
    data: () => ({
    accountitems: [
    {
        id: 1,
        icon: "mdi-bell-outline",
        color: "black",
        title: '알림 관리',
            subtitle: '알림 설정',
        return: {
            switch1: true,
            switch2: false,
        }
    },
    {
        id: 2,
        icon: "mdi-cards-heart-outline",
        color: "black",
        title: '좋아요',
        // subtitle: '비밀번호 변경',
    },
    {
        id: 3,
        icon: "mdi-chat-outline",
        color: "black",
        title: '댓글',
        // subtitle: '개인 정보 변경',
    },  
    {
        id: 4,
        icon: "mdi-account-plus-outline",
        color: "black",
        title: '팔로우',
        // subtitle: '개인 정보 변경',
    },  
    {
        id: 5,
        icon: "mdi-account-multiple-outline",
        color: "black",
        title: '모임',
        // subtitle: '개인 정보 변경',
    },  
    {
        id: 6,
        icon: "mdi-file-plus-outline",
        color: "black",
        title: '피드',
        subtitle: '개인 정보 변경',
    },  
    {
        id: 7,
        icon: "mdi-message-outline",
        color: "black",
        title: '채팅',
        // subtitle: '개인 정보 변경',
    },  
    ],
    }),


    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style scoped>

</style>