<template>
    <v-container>
    <back-menu title="그룹 수정" class="mb-1"></back-menu>
    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";

export default {
    components: { BackMenu },
    name: 'S07P12A505GroupModify',

export default {
    name: "GroupModify",
    data() {
        return {
          isRight: true,
        };
    },
    mounted() {
    },
    methods: {},
    components: { BackMenu, GroupInput }
};
</script>

<style scoped>

</style>