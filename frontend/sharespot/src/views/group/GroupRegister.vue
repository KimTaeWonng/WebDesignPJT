<template>
  <v-container>
    <v-row class="text-center">
      <v-col cols="12">
        그룹생성
				<group-input></group-input>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import GroupInput from '@/components/group/GroupInput.vue';

export default {
  components: { GroupInput },

    name: "GroupRegister",
    data() {
        return {};
    },
    mounted() {
    },
    methods: {},
}
</script>

<style scoped>

</style>