# How to Install the Email Web Crawler

## Prerequisites
- Node.js and npm (https://nodejs.org/)
- Java 17 or newer (https://adoptopenjdk.net/)
- MongoDB (https://www.mongodb.com/try/download/community)

## Installation Steps

1. **Clone the Repository**
   ```bash
   git clone <repo-url>
   cd Project_2_370_Team_2
   ```

2. **Install Backend Dependencies**
   ```bash
   cd backend
   ./mvnw clean install
   ```

3. **Install Frontend Dependencies**
   ```bash
   cd ../frontend
   npm install
   ```

4. **Set Up MongoDB**
   - Make sure MongoDB is installed and running on `localhost:27017`.
   - Default database: `webcrawler` (configured in `backend/src/main/resources/application.properties`).

5. **Configuration**
   - You can adjust backend settings in `backend/src/main/resources/application.properties` if needed.
