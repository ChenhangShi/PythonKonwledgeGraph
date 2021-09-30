FROM java:8
#线上在docker里面都是8080，在服务器端口再映射
EXPOSE 8080

VOLUME /tmp
ADD target/*.jar /app.jar
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "-Xms128m", "-Xmx300m", "/app.jar", "--spring.profiles.active=prod"]