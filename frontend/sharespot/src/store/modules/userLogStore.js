import { http } from "@/js/http.js";

const userLogStore = {
    namespaced: true,
    // state: data. 수정은 store에서만 하는게 좋다.
    state: {
        searchWordList: [], //최근 검색 단어

        searchWord: "",
        scrapPostList: [], //스크랩한 게시글 아이디
        likePostList: [], //좋아요한 게시글 아이디
        followingUserList: [], // 내가 팔로우하는 유저 정보들
        followUserList: [], //나를 팔로우하는 유저 정보들,
        myGroupList: [],  //내가 가입한 그룹 리스트
        myMeetingList: [], //내가 참여중인 모임 리스트

    },
    // getters: vue의 computed와 같은 역할. State를 기반으로 계산
    getters: {
        getSearchWordList(state) { 
            return state.searchWordList;
        },
        getfollowingUserList(state) { 
            return state.followingUserList;
        },
        getfollowUserList(state) { 
            return state.followUserListt;
        },
        getmyMeetingList(state) { 
            return state.myMeetingList;
        },
        getmyGroupList(state) { 
            return state.myGroupList;
        },

    },
    // mutations: state를 변경할 때 사용하는 유일한 방법. commit으로 변경.(동기 methods)
    mutations: {
        // 유저 정보 검색기록
        SET_SEARCH_WORD_LIST(state, searchWord) { 
            if (state.searchWordList.length > 5) {
                state.searchWordList.splice(0, 1);
            }
            state.searchWordList.push(searchWord);
        },

        // 유저 정보 검색 초기화
        RESET_SEARCH_WORD_LIST(state) { 
            state.searchWordList = [];
        }
        ,

        SAVE_SEARCH_WORD(state,searchWord ) { 
            state.searchWord = searchWord;
        }
        ,

        // 게시글 스크랩
        SCRAP_POST_LIST(state, postId) { 
            state.scrapPostList.push(postId);
        },

        // 게시글 스크랩 취소
        UNSCRAP_POST_LIST(state, postId) { 
            const i = state.scrapPostList.indexOf(postId);
            state.scrapPostList.splice(i, 1);
        },

        // 게시글 좋아요
        LIKE_POST_LIST(state, postId) {
            state.likePostList.push(postId);
        },

        // 게시글 좋아요취소
        UNLIKE_POST_LIST(state, postId) { 
            const i = state.likePostList.indexOf(postId);
            state.likePostList.splice(i, 1);
        },
        
        // 팔로우 버튼을 눌렀을 때
        FOLLOW(state, user) { 
            state.followingUserList.push(user);
        },

        // 언팔로우 버튼을 눌렀을 때
        UNFOLLOW(state, user) {

            console.log(state.followingUserList)
            for (var i = 0; i < state.followingUserList.length; i++) {
                console.log('언팔로우 인덱싱중')

                if (state.followingUserList[i].user_id == user) {
                    break
              }
            }
            state.followingUserList.splice(i, 1);
        },

        // 나를 팔로우하는 팔로워 유저 리스트
        SET_FOLLOWING_USERLIST(state, users) { 
            state.followingUserList = users;
            //console.log(state.followingUserList);
        },
        // 내가 참여중인 모임 리스트
        SET_MEETING_LIST(state, users) { 
            state.myMeetingList = users;
            //console.log(state.followingUserList);
        },
        // 내가 참여중인 그룹 리스트
        SET_GROUP_LIST(state, users) { 
            state.myGroupList = users;
            //console.log(state.followingUserList);
        },
    },
    
    // actions: 상태를 변이시키는 대신 액션으로 변이에 대한 커밋 처리(비동기 methods)
    actions: {
        // 유저 정보 검색기록
        setSearchWordList({ commit }, searchWord) { 
            commit('SET_SEARCH_WORD_LIST', searchWord)
        },
        // 미팅 리스트 불러오기
        // setMeetingList({ commit }, searchWord) { 
        //     commit('SET_SEARCH_WORD_LIST', searchWord)
        //     console.log("userLogStore: action" + searchWord);
        // },
        // 그룹 리스트 불러오기
        // setGroupList({ commit }, searchWord) { 
        //     commit('SET_SEARCH_WORD_LIST', searchWord)
        //     console.log("userLogStore: action" + searchWord);
        // },

        // 유저 정보 검색 초기화
        resetSearchWordList({ commit }) { 
            commit('RESET_SEARCH_WORD_LIST');
        }

        ,
        // 게시글 스크랩
        scrapPostList({ commit }, postId) { 
            commit('SCRAP_POST_LIST', postId);
        },

        // 게시글 스크랩 취소
        unscrapPostList({commit}, postId) { 
            commit('UNSCRAP_POST_LIST', postId);
        },

        // 게시글 좋아요
        likePostList({ commit}, postId) {
            commit('LIKE_POST_LIST', postId);
        },

        // 게시글 좋아요취소
        unlikePostList({ commit}, postId) { 
            commit('UNLIKE_POST_LIST', postId);
        },

        saveSearchWord({ commit }, searchWord) {
            commit('SAVE_SEARCH_WORD', searchWord)
        },
        
        // 팔로우 버튼을 눌렀을 때
        async follow(store, followInfo){
            try { 
                const response = await http.post(`/users/${followInfo.res.followerId}/follow`, followInfo.res);

                if (response.data === 1) {
                    console.log('팔로우 성공')
                }
                

                store.commit("FOLLOW", followInfo.user);

            } catch (error) {
                alert("팔로우에 실패했습니다.");
            }

            // commit('FOLLOW', user);

            // console.log("userLogStore: action" );
        },

        // 언팔로우 버튼을 눌렀을 때
        async unfollow(store, followInfo){
            try { 

                const response = await http.delete(`/users/${followInfo.userId}/${followInfo.followerId}/`);

                if (response.data == 1) {
                    console.log('언팔로우 성공')
                }

                store.commit("UNFOLLOW", followInfo.userId);

            } catch (error) {
                alert("언팔로우에 실패했습니다.");
            }
            // commit('FOLLOW', user);
            //         http.delete(`/users/${this.$route.params.userid}/${this.userInfo.user_id}`);
            //         console.log('팔로우취소')
            //       }

            // console.log("userLogStore: action" );
        },


        // 팔로잉 유저 리스트
        async setFollowingUserList(store, userid) { 
            try { 
                const response = await http.get(`/users/${userid}/following`);
                console.log('리스폰스.data', response.data);
                store.commit("SET_FOLLOWING_USERLIST", response.data);

                
            } catch (error) {
                alert("팔로잉 유저리스트 조회를 실패하였습니다.");
             }
        },

        // 그룹 리스트
        async setGroupList(store, userid) { 
            try { 
                const groups = await http.get(`/group/userGroup/${userid}`);
                console.log('그룹.data', groups.data);
                console.log(store)
                store.commit("SET_GROUP_LIST", groups.data);

                
            } catch (error) {
                alert("당신의 모임리스트 조회를 실패하였습니다.");
             }
        },
        
        // async follow(store, followInfo){
        //     try { 

        //         const response = await http.post(`/users/${followInfo.res.followerId}/follow`, followInfo.res);

        //         if (response.data === 1) {
        //             console.log('팔로우 성공')
        //         }
                

        //         store.commit("FOLLOW", followInfo.user);

        //     } catch (error) {
        //         alert("팔로우에 실패했습니다.");
        //     }
            // commit('FOLLOW', user);

            // console.log("userLogStore: action" );
        // },

        // // 그룹 참가 버튼을 눌렀을 때
        // async joinGroup(store, followInfo){
        //     try { 

        //         const response = await http.delete(`/users/${followInfo.userId}/${followInfo.followerId}/`);

        //         if (response.data == 1) {
        //             console.log('언팔로우 성공')
        //         }

        //         store.commit("UNFOLLOW", followInfo.userId);

        //     } catch (error) {
        //         alert("언팔로우에 실패했습니다.");
        //     }
        //     // commit('FOLLOW', user);
        //     //         http.delete(`/users/${this.$route.params.userid}/${this.userInfo.user_id}`);
        //     //         console.log('팔로우취소')
        //     //       }

        //     // console.log("userLogStore: action" );
        // },



        // // 그룹 그룹 탈퇴 버튼을 눌렀을 때
        // async exitGroup(store, userid) { 
        //     try { 
        //         const response = await http.get(`/users/${userid}/following`);
        //         console.log('리스폰스.data', response.data);
        //         store.commit("SET_GROUP_LIST", response.data);

                
        //     } catch (error) {
        //         alert("팔로잉 유저리스트 조회를 실패하였습니다.");
        //      }
        // },
    },
};

export default userLogStore;

