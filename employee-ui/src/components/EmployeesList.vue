<template>
  <div>
    <v-toolbar flat color="white">
      <v-toolbar-title>Employees list</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
      <v-dialog v-model="dialog" max-width="500px">
        <v-btn slot="activator" color="primary" dark class="mb-2">New Item</v-btn>
        <v-card>
          <v-card-title>
            <span class="headline">{{ formTitle }}</span>
          </v-card-title>

          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.fullName" label="Name"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.birthday" label="Birthday"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.email" label="Email"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-combobox
                    v-model="department"
                    :items="departments"
                    item-text="name"
                    item-value="id"
                  ></v-combobox>
                </v-flex>
              </v-layout>
            </v-container>
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click="close">Cancel</v-btn>
            <v-btn color="blue darken-1" flat @click="save">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-toolbar>
    <v-data-table
      :headers="headers"
      :items="employees"
      :pagination.sync="pagination"
      :loading="isLoading"
      hide-actions
      class="elevation-1"
    >
      <v-progress-linear slot="progress" color="blue" indeterminate></v-progress-linear>
      <template slot="items" slot-scope="props">
        <td>{{ props.item.fullName }}</td>
        <td class="text-xs-right">{{ props.item.birthday }}</td>
        <td class="text-xs-right">{{ props.item.email }}</td>
        <td class="text-xs-right">{{ props.item.id }}</td>
        <td class="text-xs-right">{{ getDepartmentName(props.item.departmentId) }}</td>
        <td class="justify-center layout px-0">
          <v-icon small class="mr-2" @click="editItem(props.item)">edit</v-icon>
          <v-icon small @click="deleteItem(props.item)">delete</v-icon>
        </td>
      </template>
      <template slot="no-data"></template>
    </v-data-table>
    <div class="text-xs-center pt-2">
      <v-pagination v-model="pagination.page" :length="pages"></v-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "EmployeesList",
  props: ["employees", "departments", "isLoading"],
  data: () => ({
    dialog: false,
    headers: [
      { text: "Name", value: "fullName" },
      { text: "Birthday", value: "birthday" },
      { text: "email", value: "email" },
      { text: "Id", value: "id" },
      { text: "Department", value: "departmentId" },
      { text: "Actions", value: "name", sortable: false }
    ],
    editedIndex: -1,
    editedItem: {
      fullName: "",
      birthday: "",
      email: "",
      Id: 0,
      departmentId: 0
    },
    defaultItem: {
      fullName: "",
      birthday: "",
      email: "",
      Id: 0,
      departmentId: 0
    },
    pagination: {
      rowsPerPage: 10
    },
    department: { id: undefined, name: undefined }
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "New Item" : "Edit Item";
    },
    pages() {
      if (this.pagination.rowsPerPage == null || this.employees == null)
        return 0;

      return Math.ceil(this.employees.length / this.pagination.rowsPerPage);
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
    department(val) {
      this.editedItem.departmentId = val.id;
    }
  },

  methods: {
    editItem(item) {
      this.editedIndex = this.employees.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.department = this.departments.find(d => d.id === item.departmentId);
      this.dialog = true;
    },

    deleteItem(item) {
      this.$emit("deleteemployee", item.id);
    },

    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },

    save() {
      if (this.editedIndex > -1) {
        this.$emit("updateemployee", {
          item: this.editedItem,
          index: this.editedIndex
        });
      } else {
        this.$emit("createemployee", this.editedItem);
      }
      this.close();
    },
    getDepartmentName(departmentId) {
      const dep = this.departments.find(
        department => department.id === departmentId
      );
      return dep ? dep.name : "";
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
