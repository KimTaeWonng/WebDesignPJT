<template>
    <v-container>
        <back-menu
      title="게시물"
      class="mb-1"
    ></back-menu>
    

    <post-comment></post-comment>
    </v-container>
</template>

<script>
import BackMenu from "@/components/layout/BackMenu.vue";
// import PostCard from '@/components/post/PostCard.vue';
import PostComment from '@/components/post/PostComment.vue';

export default {
    components: { BackMenu, PostComment },
    name: 'S07P12A505PostDetail',

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