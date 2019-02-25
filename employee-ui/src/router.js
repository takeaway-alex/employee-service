import Vue from 'vue'
import Router from 'vue-router'
import Departments from './components/Departments'
import Employees from './components/Employees'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Departments',
      component: Departments
    },
    {
      path: '/departments',
      name: 'Departments',
      component: Departments
    },
    {
      path: '/employees',
      name: 'Employees',
      component: Employees
    }
  ]
})
