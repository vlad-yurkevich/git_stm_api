FROM adoptopenjdk/openjdk11
RUN mkdir -p /mnt/app
COPY /target/stm_scheduler.jar /mnt/app
RUN chmod -R 777 /mnt/app/stm_scheduler.jar
CMD ["java", \
"-jar", \
"-Xms1g", \
"-Xmx2g", \
 "/mnt/app/stm_scheduler.jar"]