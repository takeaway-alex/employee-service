<template>
  <!-- <div class="departments-list">
    <h2>Departments list</h2>
    <div v-for="department in departments" v-bind:key="department.id">
      <div>{{department.id}}</div>
      <div>{{department.name}}</div>
    </div>
  </div>-->
  <div>
    <v-toolbar flat color="white">
      <v-toolbar-title>Departments list</v-toolbar-title>
      <v-divider class="mx-2" inset vertical></v-divider>
      <v-spacer></v-spacer>
      <v-dialog v-model="dialog" max-width="500px">
        <v-btn slot="activator" color="primary" dark class="mb-2">New Item</v-btn>
        <v-card>
          <v-card-title>
            <span class="headline">New Item</span>
          </v-card-title>

          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.id" label="Id"></v-text-field>
                </v-flex>
                <v-flex xs12 sm6 md4>
                  <v-text-field v-model="editedItem.name" label="Name"></v-text-field>
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
      :items="departments"
      :pagination.sync="pagination"
      :loading="isLoading"
      hide-actions
      class="elevation-1"
    >
      <v-progress-linear slot="progress" color="blue" indeterminate></v-progress-linear>
      <template slot="items" slot-scope="props">
        <td>{{ props.item.id }}</td>
        <td class="text-xs-left">{{ props.item.name }}</td>
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
  name: "DepartmentsList",
  props: ["departments", "isLoading"],
  data() {
    return {
      dialog: false,
      headers: [{ text: "Id", value: "id" }, { text: "Name", value: "name" }],
      editedIndex: -1,
      editedItem: {
        name: ""
      },
      defaultItem: {
        name: ""
      },
      pagination: {
        rowsPerPage: 10
      }
    };
  },
  computed: {
    pages() {
      if (this.pagination.rowsPerPage == null || this.departments == null)
        return 0;

      return Math.ceil(this.departments.length / this.pagination.rowsPerPage);
    }
  },
  watch: {
    dialog(val) {
      val || this.close();
    }
  },
  methods: {
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },

    save() {
      this.$emit("createdepartment", this.editedItem);
      this.close();
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
