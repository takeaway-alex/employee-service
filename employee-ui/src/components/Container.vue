<template>
  <!-- <div class="container">
    <Navigation/>
    <router-view/>
  </div>-->
  <v-app>
    <v-navigation-drawer app>
      <Navigation/>
    </v-navigation-drawer>
    <v-content>
      <v-container fluid>
        <router-view/>
      </v-container>
    </v-content>
    <v-footer app></v-footer>
  </v-app>
</template>

<script>
// import DepartmentForm from "./DepartmentForm";
// import DepartmentsList from "./DepartmentsList";
// import EmployeeForm from "./EmployeeForm";
// import EmployeesList from "./EmployeesList";
import Navigation from "./Navigation";
export default {
  name: "Container",
  components: {
    // DepartmentForm,
    // DepartmentsList,
    // EmployeeForm,
    // EmployeesList,
    Navigation
  }
};
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
