<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        그룹생성
				<GroupInput></GroupInput>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GroupInput from './GroupInput.vue';
export default {
    name: "GroupRegister",
    data() {
        return {};
    },
    mounted() {
    },
    methods: {},
    components: { GroupInput }
};
</script>

<style scoped>

</style>