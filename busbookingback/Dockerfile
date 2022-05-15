FROM openjdk:18
RUN apt-get update -qq && apt-get install -y mysql-server
RUN mysql -u root -p
RUN 
COPY ./target/busbookingback-1.0.0 /
WORKDIR /
EXPOSE 80
CMD ["java", "-jar", "/busbookingback-1.0.0"]