import Vue from "vue";
import Vuex from "vuex"
Vue.use(Vuex)

const state = {
    nickname: 'lalala',
}

const mutations = {
    saveNickname(state, nick) {
        state.nickname = nick;
    }
}

const actions = {}


export default new Vuex.Store({
    actions,
    mutations,
    state,
    namespaced: true
})
