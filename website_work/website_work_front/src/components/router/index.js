import VueRouter from "vue-router";
import LoginDemo from "@/components/LoginDemo.vue";
import MainDemo from "@/components/MainDemo.vue";
import RegisterDemo from "@/components/RegisterDemo.vue";
import FoodPage from "@/components/mainPages/FoodPage.vue";
import FoodModifyPage from "@/components/mainPages/FoodModifyPage.vue";
import FoodAddPage from "@/components/mainPages/FoodAddPage.vue";

const router = new VueRouter({
    routes: [
        {
            path: '/',
            redirect: '/login'
        },
        {
            path: '/login',
            component: LoginDemo,
            name: 'login'
        },
        {
            path: '/main',
            component: MainDemo,
            name: 'main',
            redirect: '/main/selectFood',
            children: [
                {
                    path: 'selectFood',
                    name: 'selectFood',
                    component: FoodPage
                },
                {
                    path: 'modifyFood',
                    name: 'modifyFood',
                    component: FoodModifyPage
                },
                {
                    path: 'addFood',
                    name: 'addFood',
                    component: FoodAddPage
                }
            ]
        },
        {
            path: '/register',
            component: RegisterDemo,
            name: 'register'
        }
    ]
})

export default router