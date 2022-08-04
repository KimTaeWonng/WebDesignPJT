<template>
  <div>
    <v-card class="mt-3">
      <v-tabs color="#289672" centered>
        <v-tab>게시물</v-tab>
        <v-tab>컬렉션</v-tab>
        <v-tab>저장됨</v-tab>

        <v-tab-item v-for="n in 3" :key="n">
          <!-- 게시글 탭 -->
          <!-- 변경: 게시글을 가져와서 띄워줘야 함!(사진 클릭하면 게시글 상세조회로 이동) -->
          <v-container fluid v-if="n == 1">
            <v-row>
              <v-col v-for="n in 12" :key="n" class="d-flex child-flex" cols="4">
                <v-img
                  :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
                  :lazy-src="`https://picsum.photos/10/6?image=${n * 5 + 10}`"
                  aspect-ratio="1"
                  class="grey lighten-2"
                >
                  <template v-slot:placeholder>
                    <v-row class="fill-height ma-0" align="center" justify="center">
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>
                </v-img>
              </v-col>
            </v-row>
          </v-container>

          <!-- 컬렉션 탭 -->
          <!-- 변경: 뱃지들의 소유여부를 가져와서 소유하고 있는 뱃지들을 띄워줘야 함!-->
          <v-container fluid v-if="n == 2">
            <v-row>
              <v-col v-for="n in 12" :key="n" class="d-flex child-flex" cols="4">
                <v-img
                  :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
                  :lazy-src="`https://picsum.photos/10/6?image=${n * 5 + 10}`"
                  aspect-ratio="1"
                  class="grey lighten-2"
                >
                  <template v-slot:placeholder>
                    <v-row class="fill-height ma-0" align="center" justify="center">
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>
                </v-img>
              </v-col>
            </v-row>
          </v-container>

          <!-- 저장됨 탭 -->
          <!-- 변경: 스크랩한 게시글을 가져와서 띄워줘야 함!(사진 클릭하면 게시글 상세조회로 이동)-->
          <v-container fluid v-if="n == 3">
            <v-row>
              <v-col v-for="n in 12" :key="n" class="d-flex child-flex" cols="4">
                <v-img
                  :src="`https://picsum.photos/500/300?image=${n * 5 + 10}`"
                  :lazy-src="`https://picsum.photos/10/6?image=${n * 5 + 10}`"
                  aspect-ratio="1"
                  class="grey lighten-2"
                >
                  <template v-slot:placeholder>
                    <v-row class="fill-height ma-0" align="center" justify="center">
                      <v-progress-circular
                        indeterminate
                        color="grey lighten-5"
                      ></v-progress-circular>
                    </v-row>
                  </template>
                </v-img>
              </v-col>
            </v-row>
          </v-container>
        </v-tab-item>
      </v-tabs>
    </v-card>
  </div>
</template>

<script>
export default {
  name: "SharespotProfileContent",

  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped></style>
