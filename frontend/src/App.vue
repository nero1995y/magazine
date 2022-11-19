<template>
  <div>
    <MainHeader/>

    <div class="wrap">
      <MainMenu/>
      <MainContent/>
      </div>
  </div>
</template>

<script>
import MainHeader from "./components/MainHeader";
import MainMenu from "./components/MainMenu";
import MainContent from "@/components/MainContent";

export default {
  name: 'App',
  components: {
    MainHeader,
    MainMenu,
    MainContent
  }
}
</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
h1 {
  color: #03a9f4;
}
.wrap {
  display: flex;
}
</style>
