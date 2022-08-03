<template>
<div>
<v-item-group>
    <v-container>
      <p class="font-weight-black">🔥이달의 HotSpot🔥</p>
    <v-row no-gutters>
      <!-- 맛집 -->
      <v-col align="center">
        <v-item v-slot="{ active, toggle }">
        <div>
        <v-btn class="hotspot-btn" v-model="menus" value="맛집" id="restaurant" :color="active ? '#289672' : '#e4e4e4'" fab dark  @click="toggle(), clicked('맛집')">
          <v-icon size="30"> mdi-silverware-fork-knife </v-icon>
        </v-btn>
        <p class="hotspotText">맛집</p>
        </div>
        </v-item>
        </v-col
      >
      <!-- 카페 -->
      <v-col align="center">
      <v-item v-slot="{ active, toggle }">
      <div>
        <v-btn class="hotspot-btn" v-model="menus" value="카페" id="cafe" :color="active ? '#289672' : '#e4e4e4'" fab dark  @click="toggle(), clicked('카페')">
          <v-icon size="30"> mdi-coffee </v-icon>
        </v-btn>
        <p class="hotspotText">카페</p>
        </div>
        </v-item>
        </v-col
      >
      <!-- 문화 -->
      <v-col align="center">
      <v-item v-slot="{ active, toggle }">
      <div>
        <v-btn class="hotspot-btn" v-model="menus" value="문화" id="culture" :color="active ? '#289672' : '#e4e4e4'" fab dark  @click="toggle(), clicked('문화')">
          <v-icon size="30"> mdi-filmstrip </v-icon>
        </v-btn>
        <p class="hotspotText">문화</p>
        </div>
        </v-item>
        </v-col
      >
      <!-- 여행 -->
      <v-col align="center">
      <v-item v-slot="{ active, toggle }">
      <div>
        <v-btn class="hotspot-btn" v-model="menus" value="여행" id="trip" fab dark :color="active ? '#289672' : '#e4e4e4'" @click="toggle(), clicked('여행')">
          <v-icon size="30"> mdi-wallet-travel </v-icon>
        </v-btn>
        <p class="hotspotText">여행</p>
        </div>
        </v-item>
      </v-col>
      <!-- 생활 -->
      <v-col align="center">
        <v-item v-slot="{ active, toggle }">
        <div>
        <v-btn class="hotspot-btn" v-model="menus" value="생활" id="life" fab dark :color="active ? '#289672' : '#e4e4e4'" @click="toggle(), clicked('생활')">
          <v-icon size="30"> people </v-icon>
        </v-btn>
        <p class="hotspotText">생활</p>
        </div>
      </v-item>
      </v-col>
    </v-row>
    </v-container>
    </v-item-group>

    <v-dialog
      id="hotspotDialog"
      v-model="dialog"
      scrollable
      max-width="300px"
    >
      <v-card>
        <v-card-title class="text-weight-900">이달의 {{ menus }} HotSpot🔥</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-list three-line>
      <v-list-item
        v-for="item in items"
        :key="item.place"
      >
        <v-row> 
          <v-col cols="3"> 
            <v-list-item-avatar>
              <v-img :src="item.photo"></v-img>
            </v-list-item-avatar>
          </v-col> 
          <v-col cols="9">
            <v-row no-gutters>
              <h4 class="text-weight-bold">{{item.place}}</h4>
            </v-row> 
            <v-row no-gutters>
              <div style="font-size: 13px">작성자: {{item.author}}</div>
            </v-row>
            <v-row no-gutters>
              <div style="font-size: 13px; color:#289672">자세히 보기</div>
            </v-row>
          
          </v-col> 
        </v-row>
        </v-list-item>
  </v-list>
        </v-card-text>
        <!-- <v-divider></v-divider>
        <v-card-actions>
          <v-btn
            class=""
            color="#289672"
            text
            @click="dialog = false"
          >
            확인
          </v-btn>
        </v-card-actions> -->
      </v-card>
    </v-dialog>
    </div>
</template>

<script>
export default {
  name: "S07P12A505HotSpotMenu",

  data() {
    return {
      items: [
        {
          photo: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          place: '더 브라운 선릉점',
          author: '카페조아',
        },
        {
          photo: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          place: '바나프레소 역삼대로점',
          author: '카페짱',
        },
        {
          photo: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          place: '더 브라운 선릉점',
          author: '카페조아',
        },
        {
          photo: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          place: '바나프레소 역삼대로점',
          author: '카페조아',
        },
        {
          photo: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
          place: '더 브라운 선릉점',
          author: '카페조아',
        },
      ],
      dialog: false,
      menus: '맛집',
    };
  },

  mounted() {},

  methods: {
    clicked(menu) {
      this.menus = menu;
      console.log(this.menus)

      this.dialog = true;

    }
  },
};
</script>

<style scoped>
.hotspotText{
  font-size: 13px;
  font-weight: 500;
  margin-top: 5px;
}


</style>
