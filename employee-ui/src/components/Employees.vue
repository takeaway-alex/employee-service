<template>
  <EmployeesList
    :employees="employees"
    :departments="departments"
    :isLoading="isLoading"
    @createemployee="createEmployee"
    @updateemployee="updateEmployee"
    @deleteemployee="deleteEmployee"
  />
</template>

<script>
import axios from "axios";
import EmployeesList from "./EmployeesList";
import { base_url } from "@/config";
export default {
  name: "Employees",
  components: {
    EmployeesList
  },
  methods: {
    createEmployee: function({ email, fullName, birthday, departmentId }) {
      axios
        .post(`${base_url}/employee`, {
          email,
          fullName,
          birthday,
          departmentId
        })
        .then(res => {
          this.employees = [
            ...this.employees,
            { id: res.data, email, fullName, birthday, departmentId }
          ];
        })
        .catch();
    },
    updateEmployee: function({
      item: { id, email, fullName, birthday, departmentId },
      index
    }) {
      axios
        .put(`${base_url}/employee`, {
          id,
          email,
          fullName,
          birthday,
          departmentId
        })
        .then(() => {
          Object.assign(this.employees[index], {
            id,
            email,
            fullName,
            birthday,
            departmentId
          });
        })
        .catch();
    },
    deleteEmployee: function(employeeId) {
      axios
        .delete(`${base_url}/employee?uuid=${employeeId}`)
        .then(() => {
          this.employees = this.employees.filter(
            employee => employee.id !== employeeId
          );
        })
        .catch();
    }
  },
  data() {
    return {
      isLoading: false,
      employees: [],
      departments: []
    };
  },
  created() {
    this.isLoading = true;

    axios
      .get(`${base_url}/employees`)
      .then(res => {
        this.isLoading = false;
        this.employees = res.data;
      })
      .catch(() => (this.isLoading = false));

    axios
      .get(`${base_url}/departments`)
      .then(res => {
        this.departments = res.data;
      })
      .catch();
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
