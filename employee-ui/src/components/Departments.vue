<template>
  <DepartmentsList
    :departments="departments"
    :isLoading="isLoading"
    @createdepartment="createDepartment"
  />
</template>

<script>
import axios from "axios";
import DepartmentsList from "./DepartmentsList";
import { base_url } from "@/config";
export default {
  name: "Departments",
  components: {
    DepartmentsList
  },
  methods: {
    createDepartment: function({ name }) {
      axios
        .post(`${base_url}/department`, { name })
        .then(res => {
          this.departments = [...this.departments, { id: res.data, name }];
        })
        .catch();
    }
  },
  data() {
    return {
      sLoading: false,
      departments: []
    };
  },
  created() {
    this.isLoading = true;
    axios
      .get(`${base_url}/departments`)
      .then(res => {
        this.isLoading = false;
        this.departments = res.data;
      })
      .catch(err => (this.isLoading = true));
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
