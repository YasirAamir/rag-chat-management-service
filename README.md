#  RAG Chat Management Service

**Backend microservice to store chat histories for a RAG (Retrieval-Augmented Generation) chatbot.**

Supports: chat sessions, chat messages, session management, API key authentication, rate limiting, and centralized logging.

---

##  Features

- Create, rename, mark favorite, and delete chat sessions
- Add messages to sessions with optional context
- Retrieve messages with pagination
- API Key authentication
- Rate limiting using Bucket4j
- Centralized logging (SLF4J + global exception handler)
- Dockerized for local setup
- OpenAPI (Swagger) documentation

---

## ️ Requirements

- Java 21
- Spring Boot 4.0.3
- Maven 3.9+
- MySQL 8+
- Lombok

---


---

##  Setup & Running Locally

1. **Create `.env` file** in the project root:

```
APP_API_KEY =  Api key for security
DB_URL = Database url relevant to the docker network
DB_USERNAME = Database username
DB_PASSWORD = Database password
```

2. Start MySQL on Docker network
```
docker run -d --name rag-mysql --network rag-network -e MYSQL_ROOT_PASSWORD=root123 -e MYSQL_DATABASE=rag-chat-management-service -p 3306:3306 mysql:8
```

3. Build docker image
```
docker build -t rag-chat-management-service .
```

4. Run Application

```
docker run -p 8080:8080 --network rag-network --env-file .env rag-chat-management-service
```

## Swagger

url = /swagger-ui/index.html

# API Endpoints Overview

| Path | Description |
|------|-------------|
| `POST /api/sessions` | Create a new chat session |
| `DELETE /api/sessions/{sessionId}` | Delete a chat session by ID |
| `GET /api/sessions/user/{userId}` | Get all sessions for a user |
| `PATCH /api/sessions/{sessionId}/rename` | Rename a session |
| `PATCH /api/sessions/{sessionId}/toggle` | Toggle favorite status of a session |
| `POST /api/message/session/{sessionId}` | Add a new message to a session |
| `GET /api/message/session/{sessionId}` | Get paginated messages for a session |