FROM openjdk:17

WORKDIR /application

COPY /target/BankTransactionManager-0.0.1-SNAPSHOT.jar bank-transaction-manager.jar

ENTRYPOINT ["java", "-jar", "bank-transaction-manager.jar"]
