# SocialPlat

A modern social platform built with Spring Boot backend and ReactJS frontend for connecting and sharing content.

## 📋 Overview

SocialPlat is a personal project demonstrating full-stack development capabilities, combining enterprise-level backend architecture with a responsive frontend interface. The platform provides core social networking features including user authentication, profile management, and content sharing.

## 🚀 Technologies

### Backend
- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 4.0.1** - Framework for building production-ready applications
- **Spring Data JPA** - Data persistence and database operations
- **Spring Security** - Authentication and authorization
- **JWT (JSON Web Tokens)** - Secure token-based authentication
- **MySQL** - Relational database management
- **Lombok** - Reduce boilerplate code
- **Gradle** - Build automation and dependency management

### Frontend
- **ReactJS** - Modern UI library for building interactive interfaces
- **JavaScript/JSX** - Dynamic frontend logic

## ✨ Features

- 🔐 **Secure Authentication** - JWT-based authentication system
- 👤 **User Management** - Registration, login, and profile management
- 🔒 **Spring Security** - Role-based access control and authorization
- 📊 **RESTful API** - Well-structured API endpoints
- 💾 **Database Integration** - MySQL for persistent data storage
- 🔄 **Hot Reload** - Development tools for faster iteration
- 🧪 **Testing Support** - JUnit platform for unit and integration tests

## 🛠️ Prerequisites

Before running this project, ensure you have:

- Java 21 or higher
- MySQL 8.0+
- Node.js and npm (for ReactJS frontend)
- Gradle (or use the included Gradle wrapper)

## 📦 Installation

### 1. Clone the Repository
```bash
git clone https://github.com/TrungKien2714/SocialPlat.git
cd SocialPlat
```

### 2. Database Setup
Create a MySQL database:
```sql
CREATE DATABASE socialplat;
```

Configure database connection in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/socialplat
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Build and Run Backend
Using Gradle wrapper:
```bash
# On Unix/Linux/Mac
./gradlew bootRun

# On Windows
gradlew.bat bootRun
```

Or build and run the JAR:
```bash
./gradlew build
java -jar build/libs/SocialPlat-0.0.1-SNAPSHOT.jar
```

### 4. Setup Frontend
```bash
# Navigate to frontend directory (if separated)
cd frontend
npm install
npm start
```

## 🏗️ Project Structure

```
SocialPlat/
├── src/
│   ├── main/
│   │   ├── java/           # Java source files
│   │   │   └── com/SocialPlat/
│   │   │       ├── controller/   # REST controllers
│   │   │       ├── service/      # Business logic
│   │   │       ├── repository/   # Data access layer
│   │   │       ├── model/        # Entity classes
│   │   │       ├── config/       # Configuration classes
│   │   │       └── security/     # Security configurations
│   │   └── resources/      # Application properties and static files
│   └── test/               # Test files
├── build.gradle            # Gradle build configuration
└── settings.gradle         # Gradle settings
```

## 🔧 Configuration

### JWT Configuration
Configure JWT settings in your application properties:
```properties
jwt.secret=your_secret_key
jwt.expiration=86400000
```

### Application Port
Default port is 8080. To change:
```properties
server.port=8080
```

## 🧪 Testing

Run tests using Gradle:
```bash
./gradlew test
```

## 📝 API Endpoints

*Documentation coming soon - Add your specific endpoints here*

Example structure:
```
POST   /api/auth/register    - Register new user
POST   /api/auth/login       - User login
GET    /api/users/{id}       - Get user profile
PUT    /api/users/{id}       - Update user profile
```

## 🤝 Contributing

This is a personal learning project, but suggestions and feedback are welcome!

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is open source and available for educational purposes.

## 👨‍💻 Author

**TrungKien2714**
- GitHub: [@TrungKien2714](https://github.com/TrungKien2714)

## 🙏 Acknowledgments

- Spring Boot documentation
- React community
- JWT authentication best practices

## 📞 Contact

For questions or feedback, feel free to open an issue in the repository.

---

⭐ Star this repo if you find it helpful!
