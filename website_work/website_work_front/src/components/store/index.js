import Vue from "vue";
import Vuex from "vuex"
Vue.use(Vuex)

const state = {
    nickname: '{未知用户}',
    url: 'http://localhost:8080'
}

const mutations = {
    saveNickname(state, nick) {
        state.nickname = nick;
    }
}

const actions = {}

import loginModule from "@/components/store/modules/loginModule";
export default new Vuex.Store({
    actions,
    mutations,
    state,
    modules: {
        loginModule
    }
})
