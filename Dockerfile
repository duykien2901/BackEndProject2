FROM maven:latest
RUN mkdir /project2
WORKDIR /project2
COPY . .
EXPOSE 8000
CMD ["mvn", "spring-boot:run"]