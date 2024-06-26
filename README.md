# Notification Service

This Java application demonstrates a notification service that polls messages from a Kafka topic and sends notifications via email.

## How to Run

Clone the repository:

git clone https://github.com/yourusername/notification-service.git
cd notification-service

Build the project:

mvn clean package

Run the application:

java -jar target/notification-service.jar

## Project Structure

```plaintext
notification-service/
├── src/
│   ├── main/
│   │   ├── MockKafka.java
│   │   ├── NotificationService.java
│   │   ├── NotificationRecord.java
│   ├── resources/
│   │   ├── kafka.properties
│   │   └── email.properties
├── .gitignore
└── README.md
```

### Files and Directories

- **`MockKafka.java`**: Mock implementation of Kafka operations to simulate message polling.
- **`NotificationService.java`**: Main logic of the notification service, which processes Kafka messages and sends notifications.
- **`NotificationRecord.java`**: Data structure representing a notification record.
- **`resources/`**: Directory containing configuration files.
  - **`kafka.properties`**: Configuration settings for Kafka operations.
  - **`email.properties`**: Configuration settings for the email service.
- **`.gitignore`**: Specifies files and directories to be ignored by Git.
- **`README.md`**: Documentation and instructions for the project.

## Prerequisites

- Java 8 or higher
- Maven

## Email Configuration

Configure email settings in src/resources/email.properties
Example: 
```
mail.smtp.host=smtp.example.com
mail.smtp.port=587
mail.smtp.auth=true
mail.smtp.starttls.enable=true
mail.smtp.username=myemail@example.com
mail.smtp.password=mysecretpassword
```

## Configuration

### Kafka Configuration

Configure Kafka settings in `src/resources/kafka.properties`.

Example:

```properties
bootstrap.servers=localhost:9092
group.id=notification-group
topic.name=NotificationTopic
```
