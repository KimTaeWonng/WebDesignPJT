<template>
    <v-container>
        <back-menu title="이용약관"></back-menu>
        <FollowItem></FollowItem>
    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
import FollowItem from "@/components/profile/FollowItem.vue";

export default {
    name: 'S07P12A505SettingsService',
    components: { BackMenu, FollowItem },
    data() {
        return {
        };
    },

    mounted() {
        
    },

    methods: {
        
    },
};
</script>

<style scoped>

</style>