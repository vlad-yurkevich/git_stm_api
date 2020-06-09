FROM adoptopenjdk/openjdk11
RUN mkdir -p /mnt/app
COPY /target/stm_api8084.jar /mnt/app
RUN chmod -R 777 /mnt/app/stm_api8084.jar
CMD ["java", \
"-jar", \
"-Xms1g", \
"-Xmx2g", \
 "/mnt/app/stm_api8084.jar"]