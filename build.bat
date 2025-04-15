call mvn clean package -DskipTests
call docker build -t bank-transaction-manager:0.0.1 .