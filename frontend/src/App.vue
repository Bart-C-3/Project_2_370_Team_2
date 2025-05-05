
<script>
import LoginForm from './components/LoginForm.vue';
import CrawlControl from './components/CrawlControl.vue';
import EmailResults from './components/EmailResults.vue';
export default {
  name: 'App',
  components: { LoginForm, CrawlControl, EmailResults },
  data() {
    return {
      isAuthenticated: !!localStorage.getItem('token')
    };
  },
  methods: {
    onLoginSuccess() {
      this.isAuthenticated = true;
    }
  }
};
</script>


<template>
  <div>
    <header>
      <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125" />
      <h1>Email Web Crawler Dashboard</h1>
    </header>
    <main>
      <LoginForm v-if="!isAuthenticated" @login-success="onLoginSuccess" />
      <div v-else>
        <CrawlControl />
        <EmailResults />
      </div>
    </main>
  </div>
</template>

<style scoped>

header {
  line-height: 1.5;
  text-align: center;
}
.logo {
  display: block;
  margin: 0 auto 2rem;
}
</style>
