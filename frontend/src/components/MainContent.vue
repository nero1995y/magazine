<template>
  <div>Content Component
    <form>
      <input v-model="username" placeholder="Username"/>
      <input v-model="password" placeholder="Password"/>
      <input v-model="email" placeholder="Email"/>
      <h1>{{username}}</h1>
      <h1></h1>
      <h1></h1>
      <button>회원가입</button>
    </form>
  </div>

</template>

<script>
import axios from 'axios';

export default {
  components: {
  },

  data() {
    return {
      username: "",
      password: undefined,
      email: undefined,
    }
  },

  methods: {
    registerUser() {
      axios.post('/api/v1/user/', {
        username: this.username,
        password: this.password,
        email: this.email
      })
    }
  }
}
</script>
<style scoped>
div {
  flex: 2;
}
</style>