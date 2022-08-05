<template>
    <v-container>
    <back-menu title="보드게임조아조아" :isRight="true" type="groupDetail" class="mb-1"></back-menu>
    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";

export default {
    components: { BackMenu },
    name: 'S07P12A505GroupDetail',

export default {
  components: { GroupInfo },
    name: "GroupDetail",
    data() {
        return {
            isRight: true,
        };
    },
    mounted() {
    },
    methods: {},

};
</script>

<style scoped>

</style>