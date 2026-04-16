# KT Spring

A simple Spring Boot application demonstrating the use of Kotlin with Spring. It includes a basic REST controller and a service layer.

## Getting Started

### Prerequisites

- [Bun](https://bun.sh/) (for frontend tooling)
- [Docker](https://www.docker.com/) (for database)
- [Java 17+](https://adoptopenjdk.net/) (for running the backend)
- [Gradle](https://gradle.org/) (wrapper included)

### Build Tailwind CSS

```bash
bun ci
bun run build:css
```

### Start the Database

```bash
docker compose up db -d
```

### Run the Application

Start the Spring Boot application in development mode:

```bash
./gradlew bootRun
```

### Build the Application

To create a production-ready JAR:

```bash
./gradlew build
```

### Run the Built JAR

```bash
java -jar ./app/build/libs/app.jar
```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
