import router from '@/router/index.js'
import axios from 'axios'

const account = {
  namespaced: true,
  state: {
    // 사용자 정보
    userId: null,
    email: null,
    nickname: null,
    introduction: null,
    image: '',
    following: [],
    followers: [],
  },
  
  mutations: {
    // [검색]
    SEARCH_ACCOUNTS: function (state, accounts) {
      state.searchedAccounts = accounts
    },
    RESET_SEARCH_ACCOUNTS: function (state) {
      state.searchedAccounts = []
    },
    // 좋아요한 게시물
    LIKE_PLAYROOM: function (state, likePlayroomList) {
      state.likePlayroomList = likePlayroomList
    },
    LIKE_PLAYLIST: function (state, likePlaylistList) {
      state.likePlaylistList = likePlaylistList
    },
    LIKE_BOARD: function (state, likeBoardList) {
      state.likeBoardList = likeBoardList
    },

    // 플레이룸 시청 기록
    GET_HISTORY: function (state, historyList) {
      state.historyList = historyList
    },

    // 유저 정보 얻기
    GET_USER_ACCOUNTS_FOLLOWERS: function (state, followers) {
      console.log('유저 정보 얻기', followers)
      state.userFollowers = followers
    },
    GET_USER_ACCOUNTS_FOLLOWING: function (state, following) {
      console.log('유저 정보 얻기', following)
      state.userFollowing = following
    },


    // 팔로우
    FOLLOW: function (state, following) {
      console.log('팔로우 뮤테이션', following)
      // state.following.push(following)
      state.following = following
      console.log('팔로우 뮤테이션2', state.following)
    },

    UNFOLLOW: function (state, following) {
      console.log('언팔로우 뮤테이션', following)
      state.following = following
      console.log('언팔로우 뮤테이션2', state.following)
    },

  },
  actions: {
    async validateToken() {
      try {
        const { status } = await axiosConnector.get('/account/tokenvalidate');
        return (status == 200);
      } catch(err) {
        return false;
      }
    },

    // 팔로우
    follow: function({ commit, dispatch }, userid) {
      console.log('팔로우 액션')
      axiosConnector.post(`users/${userid}/follow`)
        .then(() => {
          console.log('팔로우 성공적', userSeq)
          // 팔로잉 리스트 업데이트
          // commit('FOLLOW', userSeq)
        })
        .catch((err) => {
          console.log('에러1')
        })
    },

    // 언팔로우
    unfollow: function({ commit }, userid) {
      console.log('언팔로우 액션')
      axiosConnector.delete(`account/${userid}/follow`)
        .then((res) => {
          // console.log('언팔로우 성공적', res.data)
          // commit('UNFOLLOW', userSeq)
        })
        .catch((err) => {
          console.log('에러2')
        })
    },


  },
  modules: {
  }
}
export default account