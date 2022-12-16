#FROM ghcr.io/graalvm/jdk:java17-22.0.0
#MAINTAINER Kalathil Ajay Menon
#RUN gu install curl
#WORKDIR /workdir
#COPY build/libs/spring-testcontainers-0.0.1-SNAPSHOT.jar spring-testcontainers.jar
#ENTRYPOINT ["java","-jar","spring-testcontainers.jar"]
FROM springci/graalvm-ce
RUN gu install ruby
WORKDIR /workdir
RUN echo 'puts "Hello from Ruby!\nVersion: #{RUBY_DESCRIPTION}"' > app.rb
CMD ruby app.rb