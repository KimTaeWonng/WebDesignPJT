<template>
  <v-container>
    <back-menu title="비밀번호 재설정" class="mb-1"></back-menu>

    <v-row class="text-center">
      <v-col cols="12">
        <div style="margin-top: 10%">
          <span
            class="material-icons"
            style="color: rgb(40, 150, 114); font-size: 700%"
          >
            lock_reset
          </span>
        </div>
        <validation-observer ref="observer" v-slot="{ invalid }">
          <form @submit.prevent="submit" class="mr-6 ml-6 mt-5">
            <!-- 비밀번호 입력 -->
            <validation-provider
              v-slot="{ errors }"
              name="비밀번호"
              :rules="{
                required: true,
                minmax: [8, 20],
              }"
            >
              <v-text-field
                class="mt-3"
                id="password"
                v-model="user.password"
                :counter="20"
                type="password"
                :error-messages="errors"
                label="비밀번호"
                required
                color="#289672"
              ></v-text-field>
            </validation-provider>

            <!-- 비밀번호 확인 입력 -->
            <validation-provider
              v-slot="{ errors }"
              name="비밀번호확인"
              rules="required|confirmed:비밀번호"
            >
              <v-text-field
                class="mt-5"
                :counter="20"
                type="password"
                :error-messages="errors"
                label="비밀번호 확인"
                v-model="confirmPwd"
                required
                color="#289672"
              ></v-text-field>

              <v-row class="mt-2" justify="center">
                <v-col cols="8">
                  <div>
                    <v-btn
                      type="submit"
                      :disabled="invalid"
                      style="background-color: #289672; color: white"
                      width="100%"
                      @click="changePass()"
                    >
                      비밀번호 재설정
                    </v-btn>

                    <v-dialog v-model="dialogSuccess" max-width="290">
                      <v-card>
                        <div class=""><br /><br /></div>
                        <div
                          class="text-center"
                          style="color: rgb(40, 150, 114)"
                        >
                          <span class="material-icons" style="font-size: 80px">
                            task_alt
                          </span>
                        </div>
                        <div class="text-center mt-5">
                          <div style="font-weight: bold; font-size: 6vw">
                            {{ userInfo.nickname }} 님의
                          </div>
                          <div style="font-size: 5vw">
                            비밀번호 재설정이 완료되었습니다!
                          </div>
                        </div>

                        <div class="text-center mt-10">
                          <v-btn
                            color="rgb(40,150,114)"
                            dark
                            @click="goSetting()"
                          >
                            확인
                          </v-btn>
                        </div>

                        <div>
                          <br />
                          <br />
                        </div>
                      </v-card>
                    </v-dialog>
                  </div>
                </v-col>
              </v-row>
            </validation-provider>
          </form>
        </validation-observer>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
import { required, max, regex, confirmed } from "vee-validate/dist/rules";
import {
  extend,
  ValidationObserver,
  ValidationProvider,
  setInteractionMode,
} from "vee-validate";
import { mapState } from "vuex";
import { http } from "@/js/http.js";

const userStore = "userStore";
setInteractionMode("eager");

extend("confirmed", {
  ...confirmed,
  message: "비밀번호와 비밀번호 확인이 일치하지 않습니다.",
});

extend("required", {
  ...required,
  message: "{_field_}(을)를 입력해주세요.",
});

extend("minmax", {
  validate(value, { min, max }) {
    return value.length >= min && value.length <= max;
  },
  params: ["min", "max"],
  message: "{_field_}(은)는 {min}~{max}자로 입력해주세요.",
});

extend("max", {
  ...max,
  message: "{_field_}은 {length}자 이내로 입력해주세요.",
});

extend("regex", {
  ...regex,
  message: "{_field_} 형식이 올바르지 않습니다.",
});

export default {
  components: { BackMenu, ValidationProvider, ValidationObserver },
  name: "ResetPass",

  data() {
    return {
      dialogSuccess: false,
      user: {
        password: "",
      },
      confirmPwd: "",
    };
  },

  computed: {
    ...mapState(userStore, ["userInfo"]),
  },

  methods: {
    async changePass() {
      const response = await http.post(
        `/users/reset/password/${this.userInfo.user_id}`,
        this.user.password
      );
      console.log(response);
      this.dialogSuccess = true;
    },
    submit() {
      this.$refs.observer.validate();
    },
    goSetting() {
      this.dialogSuccess = false;
      this.$router.push({
        name: "settings"
      });
    },
  },
};
</script>

<style scoped></style>
