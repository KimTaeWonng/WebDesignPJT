<template>
    <div>

        <profile-info ></profile-info>
        <profile-badge></profile-badge>
        <profile-content ></profile-content>
        
    </div>
</template>

<script>

import ProfileInfo from '@/components/profile/ProfileInfo.vue';
import ProfileContent from '@/components/profile/ProfileContent.vue';
import ProfileBadge from '@/components/profile/ProfileBadge.vue';


import { mapActions, mapState } from 'vuex'

const userStore = "userStore";
export default {
    name: 'S07P12A505Profile',
    components: { ProfileInfo, ProfileContent, ProfileBadge },
    data() {
        return {
            profile: [],
            follow: false,
            followerList: [], // 팔로잉 하면 팔로워가 늘어남.
            followingList: [],
            postList: [],
            userId: '',
            nickname: '',
        };
    },
    computed: {
        ...mapState(userStore, ["userInfo"]),
    },
    mounted() {

    },
    
    async created() {
        
    },

    methods: {
        ...mapActions(userStore, ['getAccounts']),
        
    },
};
</script>

<style scoped>

</style>