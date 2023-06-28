FROM adoptopenjdk:11-jdk-hotspot

WORKDIR /app

COPY Calculator.java /app

RUN javac Calculator.java

CMD ["java", "Calculator"]
