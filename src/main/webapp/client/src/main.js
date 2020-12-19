// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './components/Main';
import router from './router';
import 'bootstrap/dist/css/bootstrap.css'
import BootstrapVue from 'bootstrap-vue';
import Datepicker from "vuejs-datepicker";
import VueTimepicker from 'vue2-timepicker'
import 'vue2-timepicker/dist/VueTimepicker.css'

Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(Datepicker);
Vue.use(VueTimepicker);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
});
