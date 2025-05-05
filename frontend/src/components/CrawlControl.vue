<template>
  <div class="crawl-control">
    <h2>Start Email Crawl</h2>
    <form @submit.prevent="startCrawl">
      <input v-model="seedUrl" placeholder="Seed URL" required />
      <button type="submit">Start Crawl</button>
      <div v-if="message" class="message">{{ message }}</div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'CrawlControl',
  data() {
    return {
      seedUrl: '',
      message: ''
    };
  },
  methods: {
    async startCrawl() {
      this.message = '';
      try {
        const token = localStorage.getItem('token');
        await axios.post('http://localhost:8080/api/crawler/start', this.seedUrl, {
          headers: { 'Authorization': `Bearer ${token}`, 'Content-Type': 'application/json' }
        });
        this.message = 'Crawl started!';
      } catch (e) {
        this.message = 'Failed to start crawl.';
      }
    }
  }
};
</script>

<style scoped>
.crawl-control { max-width: 400px; margin: 2rem auto; }
.message { color: green; margin-top: 1rem; }
</style>
