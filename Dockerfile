FROM maven:3-adoptopenjdk-11 as builder
WORKDIR /sample-project
COPY ./pom.xml /sample-project
RUN mvn -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true dependency:go-offline
COPY . /sample-project
RUN mvn -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true package -B
ARG JAR_FILE=/sample-project/target/*.jar
RUN cp ${JAR_FILE} sample-project.jar
RUN java -Djarmode=layertools -jar sample-project.jar extract


FROM maven:3-adoptopenjdk-11
WORKDIR sample-project
RUN apt-get update
RUN apt-get install telnet
RUN true
COPY --from=builder sample-project/dependencies/ ./
RUN true
COPY --from=builder sample-project/spring-boot-loader/ ./
RUN true
COPY --from=builder sample-project/snapshot-dependencies/ ./
RUN true
COPY --from=builder sample-project/application/ ./
RUN true
ENTRYPOINT ["java", "-Duser.timezone=Asia/Colombo","org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080

