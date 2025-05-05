<template>
  <div class="login-form">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <input v-model="username" placeholder="Username" required />
      <input v-model="password" type="password" placeholder="Password" required />
      <button type="submit">Login</button>
      <div v-if="error" class="error">{{ error }}</div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'LoginForm',
  data() {
    return {
      username: '',
      password: '',
      error: ''
    };
  },
  methods: {
    async login() {
      this.error = '';
      try {
        const res = await axios.post('http://localhost:8080/api/auth/login', {
          username: this.username,
          password: this.password
        });
        localStorage.setItem('token', res.data.token);
        this.$emit('login-success');
      } catch (e) {
        this.error = 'Invalid credentials';
      }
    }
  }
};
</script>

<style scoped>
.login-form { max-width: 300px; margin: 2rem auto; }
.error { color: red; margin-top: 1rem; }
</style>
