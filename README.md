# Notification Service

This project is a notification service that polls messages from a Kafka topic (simulated with a mock Kafka) and sends email notifications to subscribers based on their package usage.


## How to Run

1. Clone the repository.
2. Configure your email settings in `src/resources/email.properties`.
3. Compile and run the `NotificationService` class.

## Email Configuration

Update the `email.properties` file with your SMTP server details:

```properties
mail.smtp.host=smtp.example.com
mail.smtp.port=587
mail.smtp.auth=true
mail.smtp.starttls.enable=true
mail.smtp.user=your-email@example.com
mail.smtp.password=your-password

## Project Structure
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
