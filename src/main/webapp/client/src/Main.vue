<template>
  <p>{{ name }}</p>
</template>

<script>
import axios from "axios";

export default {
  name: 'Main',
  data() {
    return {
      name: '',
    }
  },
  created() {
    const path = 'http://localhost:8090/';

    axios.get(path).then((resp) => {
      this.name = resp.data.name
    }).catch((error) => {
      console.log(error)
    })
  }
};
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
