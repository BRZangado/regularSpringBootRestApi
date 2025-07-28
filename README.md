# Puppies API 🐕

A web-based API for sharing pictures of dogs, similar to Instagram. Built with a clean multi-module architecture to ensure scalability, maintainability, and code reusability.

## 🏗️ Architecture

This project follows a **multi-module architecture** that promotes separation of concerns and loose coupling:

```
puppies-api/
├── rest-api/     # Entry and exit point for data (Controllers)
├── core/         # Business logic modules (Services)
├── model/        # Database access layer (Entities and Repositories)
└── flyway/       # Database structure and table creation
```

### Module Responsibilities

- **rest-api**: Handles HTTP requests and responses, input validation, and API routing
- **core**: Contains business logic, service implementations, and application rules
- **model**: Manages data access, entity definitions, and repository patterns
- **flyway**: Database migration and schema management

This decoupled approach allows each module to function as a service dependency, with single responsibility principles. It enables easy integration of new modules, services, and reuse of existing components.

## 🚀 Features

The Puppies API currently implements the following capabilities:

- ✅ **User Management**: Create users with name and email
- ✅ **Authentication**: User sign-in functionality
- ✅ **Post Creation**: Share images with text content and timestamps
- ✅ **Post Interactions**: Like posts from other users
- ✅ **Feed Management**: Fetch user feeds with posts ordered by date (newest first)
- ✅ **Post Details**: Retrieve individual post information
- ✅ **User Profiles**: Access user profile data
- ✅ **User Activity**: Fetch lists of liked posts and user's own posts

## 📋 Prerequisites

- Docker
- Docker Compose

*Alternative: You can run without Docker, but you'll need to install MySQL locally and run the Flyway and REST API services separately.*

## 🚀 Quick Start

### Using Docker Compose (Recommended)

```bash
docker compose up --build
```

This command will:
- Build all modules
- Start MySQL database
- Run Flyway migrations
- Launch the REST API server

## Testing in postman
You can use puppiesCollection.postman_collection located at the root of this project to import all endpoints into postman and test the application