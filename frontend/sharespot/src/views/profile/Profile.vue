<template>
    <div>
        <profile-info :followerCnt="this.followerList.length" :followingCnt="this.followingList.length" :follower="this.followerList"></profile-info>
        <profile-badge></profile-badge>
        <profile-content :postList="this.postList"></profile-content>
        
    </div>
</template>

<script>
import { http } from "@/js/http.js";
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
        
        const following = await http.get(`/users/${this.$route.params.userid}/following`);
        const follower = await http.get(`/users/${this.$route.params.userid}/follower`);
        this.followerList = follower.data
        this.followingList = following.data
        console.log('팔로잉리스트', following)
        console.log('팔로워리스트', follower)
        const post = await http.get(`/main/posts/user/${this.$route.params.userid}`);
        console.log('포스트리스트')
        console.log(post.data)
        this.postList = post.data
        
        //         if 
        //             console.log('체크', res.data)
        //             if (response.data === 'ok') {
        //                 this.follow = true
        //             }
        //             else {
        //                 this.follow = false
        //             }
        //         })
        //     }

        //     catch((err) => {
        //       console.log(err.response)
        //     })
        // },
        
    },

    methods: {
        ...mapActions('getAccounts'),

        // getAccounts: function ($state) {
        //     console.log('getAccounts params')
        //     const params = {
        //         paged: true,
        //         page: this.page,
        //         size: 5,
        //     }
        //     axios.get(`userinfo/${this.$route.params.userId}`, {
        //         params
        //     })
        //         .then((res) => {
        //             if (this.page === 0) {
        //                 this.page++
        //                 console.log('성공적 프로필', res.data)
        //                 this.profile = res.data
        //                 this.activities.push(...res.data.activities)
        //                 this.userId = res.data.userSeq
        //                 this.tastes = res.data.userInfo.tasteInfo
        //                 this.nickname = res.data.nickname
        //                 this.follower_cnt = res.data.from_user.length
        //                 this.followingList = res.data.to_user
        //                 console.log('userId', this.userId)
        //                 $state.loaded()
        //             } else if (res.data.activities.length) {
        //                 this.page++
        //                 this.activities.push(...res.data.activities)
        //                 $state.loaded()
        //             } else {
        //                 $state.complete()
        //             }
        //         })
        //         .catch((err) => {
        //             console.log('에러', err)
        //             $state.complete()
        //         })
        // },


        
    },
};
</script>

<style scoped>

</style>