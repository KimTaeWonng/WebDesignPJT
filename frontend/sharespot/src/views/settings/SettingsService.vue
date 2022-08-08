<template>
    <v-container>
        <back-menu title="이용약관"></back-menu>
        <post-input-item></post-input-item>
    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
import PostInputItem from '../../components/post/PostInputItem.vue';

export default {
    name: 'S07P12A505SettingsService',
    components: { PostInputItem, BackMenu },
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