<template>
    <v-container>
        <!-- <div class="mx-3">
            <span>Account settings</span>
        </div>
        <hr>
        <v-card
        tile
        >
        <v-row class="align-center mt-1" outlined tile>
        <v-col cols="3" class="text-center">
            <v-avatar color="blue">
                <v-icon color="white" large>
                    mdi-account-circle
                </v-icon>
            </v-avatar>
        </v-col>
        <div class="v-list-item__content">
            <div class="v-list-item__title">프로필 변경</div>
            <div class="v-list-item__subtitle">개인 정보 변경</div>
        </div>
        </v-row>
        </v-card>
        
        <v-card
        tile
        >
        <v-row class="d-flex align-center mt-1">
        <v-col cols="3" class="text-center border-bottom-1px;">
            <v-avatar color="blue">
                <v-icon color="white" large>
                    mdi-account-circle
                </v-icon>
            </v-avatar>
        </v-col>
        <div class="v-list-item__content">
            <div class="v-list-item__title">프로필 변경</div>
            <div class="v-list-item__subtitle">개인 정보 변경</div>
        </div>
        </v-row>
        </v-card> -->

    <v-list>
      <template v-for="(item, index) in accountitems">
        <v-subheader 
          v-if="item.header"
          :key="item.header"
          v-text="item.header"
          class="font-weight-bold"
        ></v-subheader>

        <v-divider
          v-else-if="item.divider"
          :key="index"
        ></v-divider>

        <v-list-item
          v-else
          :key="item.title"
          class="my-1"
        > 
          <v-list-item-avatar :color="item.bgc">
                <v-icon :color="item.color">
                    {{ item.icon }}
                </v-icon>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title v-text="item.title"></v-list-item-title>
            <v-list-item-subtitle  v-text="item.subtitle"></v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
      </template>
    </v-list>

    <v-list>
      <v-subheader>Notifications settings</v-subheader>
      <template>
        <v-list-item>
          <v-list-item-avatar color="pink lighten-3">
                <v-icon color="white">
                  mdi-bell-outline
                </v-icon>
          </v-list-item-avatar>

          <v-list-item-content href="">
            <v-list-item-title>알림 관리</v-list-item-title>
            <v-list-item-subtitle>알림 설정 </v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider
        ></v-divider>
      </template>
    </v-list>

    <v-list>
      <v-subheader>General</v-subheader>
      <template>
        <v-list-item>
          <v-list-item-avatar color="yellow darken-1">
                <v-icon color="white">
                  mdi-alert-circle-outline
                </v-icon>
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>정보</v-list-item-title>
            <v-list-item-subtitle>도움말, 이용약관</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>
        <v-divider
        ></v-divider>
      </template>
    </v-list>

    </v-container>
</template>

<script>
export default {
    name: 'S07P12A505Settings',

      data: () => ({
      accountitems: [
        { header: 'Account Settings' },
               {
          bgc: "blue lighten-3",
          icon: "mdi-account-outline",
          color: "white",
          title: '프로필 변경',
          subtitle: '개인 정보 변경',
        },
        { divider: true},
        {
          bgc: "green lighten-2",
          icon: "mdi-lock-outline",
          color: "white",
          title: '비밀번호 재설정',
          subtitle: '비밀번호 변경',
        },
        { divider: true},
        
      ],
    }),

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style scoped>
    /* hr {
    background-color: rgb(230, 230, 230);
    height: 1px;
    } */
    /* .v-list-item__subtitle {
        font-size: 12px;
        color: #999999;
        font-weight: lighter;
    } */
    .v-list-item-subtitle {
        font-size: 12px;
        color: #999999;
        font-weight: lighter;
    }
</style>