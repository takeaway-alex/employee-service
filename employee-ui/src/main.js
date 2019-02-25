import Vue from 'vue'
import VueRouter from 'vue-router'
import Vuetify from 'vuetify'
import router from './router'
import App from './App.vue'

Vue.use(Vuetify)
Vue.use(VueRouter)

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
