# How to Run the Email Web Crawler

## Prerequisites
- Node.js and npm
- Java 17+
- MongoDB

## Steps

1. **Start MongoDB**
   ```bash
   sudo systemctl start mongod
   ```
   Or, if you do not use systemd:
   ```bash
   mongod --dbpath ~/mongodb-data --bind_ip_all
   ```

2. **Start the Backend**
   ```bash
   cd backend
   ./mvnw spring-boot:run
   ```
   The backend will run on http://localhost:8080

3. **Start the Frontend**
   ```bash
   cd frontend
   npm install
   npm run dev
   ```
   The frontend will run on http://localhost:5173

4. **Login**
   - Username: `user`
   - Password: `password`

5. **Use the Dashboard**
   - Start a crawl by entering a seed URL.
   - View collected emails in the results table.
