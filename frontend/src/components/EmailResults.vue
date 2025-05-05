<template>
  <div class="email-results">
    <h2>Collected Emails</h2>
    <button @click="fetchEmails">Refresh</button>
    <table v-if="emails.length">
      <thead>
        <tr>
          <th>Email</th>
          <th>URL</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="email in emails" :key="email.id">
          <td>{{ email.email }}</td>
          <td>{{ email.url }}</td>
        </tr>
      </tbody>
    </table>
    <div v-else>No emails found.</div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'EmailResults',
  data() {
    return {
      emails: []
    };
  },
  mounted() {
    this.fetchEmails();
  },
  methods: {
    async fetchEmails() {
      const token = localStorage.getItem('token');
      const res = await axios.get('http://localhost:8080/api/crawler/emails', {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      this.emails = res.data;
    }
  }
};
</script>

<style scoped>
.email-results { max-width: 600px; margin: 2rem auto; }
table { width: 100%; border-collapse: collapse; }
th, td { border: 1px solid #ccc; padding: 0.5rem; }
</style>
