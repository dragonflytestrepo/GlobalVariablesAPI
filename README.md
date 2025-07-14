# GlobalVariablesAPI

A Spring Boot REST API for storing and managing global variables with in-memory storage and authentication.

## Features

- ✅ **Store Variables**: Store key-value pairs grouped by unique ID
- ✅ **Update Variables**: Update existing keys or add new ones
- ✅ **RESTful API**: POST, GET, DELETE operations
- ✅ **Authentication**: HTTP Basic Authentication required
- ✅ **In-Memory Storage**: Fast HashMap-based storage (no database setup needed)
- ✅ **Thread-Safe**: Uses ConcurrentHashMap for concurrent access

## Technologies

- Java 17+
- Spring Boot 3.2.0
- Spring Security
- Spring Web
- Lombok
- Maven

## Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6+

### Running the Application

1. **Clone and navigate to the project:**
   ```bash
   cd GlobalVariablesAPI
   ```

2. **Build the project:**
   ```bash
   mvn clean install
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080`

### Authentication

The API uses HTTP Basic Authentication:
- **Username**: `admin`
- **Password**: `password`

## Deployment

### Deploying to Render

This project is configured for easy deployment to Render:

1. **Push your code to GitHub:**
   ```bash
   git add .
   git commit -m "Add Render deployment configuration"
   git push origin main
   ```

2. **Connect to Render:**
   - Go to [render.com](https://render.com)
   - Sign up/Login with your GitHub account
   - Click "New +" and select "Web Service"
   - Connect your GitHub repository

3. **Configure the service:**
   - **Name**: `global-variables-api` (or your preferred name)
   - **Environment**: `Java`
   - **Build Command**: `mvn clean package -DskipTests`
   - **Start Command**: `java -jar target/global-variables-api-1.0.0.jar`
   - **Plan**: Free (or choose your preferred plan)

4. **Environment Variables** (optional):
   - `PORT`: Will be automatically set by Render
   - `JAVA_VERSION`: 17 (already configured)

5. **Deploy:**
   - Click "Create Web Service"
   - Render will automatically build and deploy your application

Your API will be available at: `https://your-app-name.onrender.com`

### Deployment Files

The following files are included for Render deployment:
- `render.yaml`: Render configuration
- `Procfile`: Process definition for Render
- Updated `application.properties`: Uses `PORT` environment variable

## API Endpoints

### 1. Store/Update Global Variables

**POST** `/api/global-vars`

**Request Body:**
```json
{
  "GlobalVariables": [
    {
      "id": "1",
      "BASE_URL": "https://your-app.com",
      "USERNAME": "autovendorC01",
      "PASSWORD": "dragonfly2",
      "Transaction_ID": "CNY202507091610081",
      "ENVIRONMENT": "staging",
      "Withdrawl_ID": "W123432",
      "Withdrawl": "W123",
      "ID": "1234"
    }
  ]
}
```

**Response:**
```json
{
  "message": "Global variables stored successfully",
  "count": 1
}
```

### 2. Get Global Variables by ID

**GET** `/api/global-vars/{id}`

**Example:** `GET /api/global-vars/1`

**Response:**
```json
{
  "BASE_URL": "https://your-app.com",
  "USERNAME": "autovendorC01",
  "PASSWORD": "dragonfly2",
  "Transaction_ID": "CNY202507091610081",
  "ENVIRONMENT": "staging",
  "Withdrawl_ID": "W123432",
  "Withdrawl": "W123",
  "ID": "1234"
}
```

### 3. Delete Global Variables by ID

**DELETE** `/api/global-vars/{id}`

**Example:** `DELETE /api/global-vars/1`

**Response:**
```json
{
  "message": "Global variables deleted successfully"
}
```

### 4. Get All Global Variables

**GET** `/api/global-vars`

**Response:**
```json
{
  "1": {
    "BASE_URL": "https://your-app.com",
    "USERNAME": "autovendorC01",
    "PASSWORD": "dragonfly2"
  },
  "2": {
    "ENVIRONMENT": "production",
    "API_KEY": "abc123"
  }
}
```

## Usage Examples

### Using curl

1. **Store variables:**
   ```bash
   curl -X POST http://localhost:8080/api/global-vars \
     -H "Content-Type: application/json" \
     -u admin:password \
     -d '{
       "GlobalVariables": [
         {
           "id": "1",
           "BASE_URL": "https://your-app.com",
           "USERNAME": "autovendorC01",
           "PASSWORD": "dragonfly2"
         }
       ]
     }'
   ```

2. **Get variables:**
   ```bash
   curl -X GET http://localhost:8080/api/global-vars/1 \
     -u admin:password
   ```

3. **Delete variables:**
   ```bash
   curl -X DELETE http://localhost:8080/api/global-vars/1 \
     -u admin:password
   ```

### Using Postman

1. Set the request method and URL
2. Add Basic Auth with username `admin` and password `password`
3. For POST requests, set Content-Type to `application/json`
4. Use the JSON format shown in the examples above

## Project Structure

```
src/
├── main/
│   ├── java/com/example/globalvariablesapi/
│   │   ├── GlobalVariablesApiApplication.java
│   │   ├── controller/
│   │   │   └── GlobalVariablesController.java
│   │   ├── model/
│   │   │   ├── GlobalVariable.java
│   │   │   └── GlobalVariablesRequest.java
│   │   ├── service/
│   │   │   └── GlobalVariablesService.java
│   │   └── config/
│   │       └── SecurityConfig.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/example/globalvariablesapi/
            └── GlobalVariablesApiApplicationTests.java
```

## Key Features Explained

### In-Memory Storage
- Uses `ConcurrentHashMap` for thread-safe operations
- Data persists only during application runtime
- No database setup required

### Authentication
- HTTP Basic Authentication required for all `/api/**` endpoints
- Default credentials: `admin`/`password`
- Can be easily modified in `SecurityConfig.java`

### Variable Management
- Variables are stored as key-value pairs
- Multiple variables can be stored under the same ID
- Existing variables are updated, new ones are added
- ID is required and cannot be empty

## Error Handling

The API provides meaningful error messages:
- 400 Bad Request: Invalid input data
- 401 Unauthorized: Missing or invalid authentication
- 404 Not Found: ID not found for GET/DELETE operations
- 500 Internal Server Error: Server-side errors

## Development

### Running Tests
```bash
mvn test
```

### Building JAR
```bash
mvn clean package
```

### Running JAR
```bash
java -jar target/global-variables-api-1.0.0.jar
```

## Security Notes

- Change default credentials in production
- Consider using environment variables for sensitive data
- The current implementation is suitable for development/testing
- For production, consider adding rate limiting and additional security measures 