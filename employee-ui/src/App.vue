<template>
  <div id="app">
    <Container/>
  </div>
</template>

<script>
import Container from "./components/Container.vue";

import "vuetify/dist/vuetify.min.css";

export default {
  name: "app",
  components: {
    Container
  }
};
</script>

<style>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
