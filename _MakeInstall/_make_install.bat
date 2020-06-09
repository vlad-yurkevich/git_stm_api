docker run --name postgres --network bridge -p 5435:5432 -e POSTGRES_PASSWORD=1234 -d postgres
docker run -d --name hazelcast -ti -p 5701:5701  hazelcast/hazelcast
docker run -d --name zookeeper -p 2181:2181 jplock/zookeeper
docker run -d --name kafka -p 7203:7203 -p 9092:9092 -e KAFKA_ADVERTISED_HOST_NAME=host.docker.internal -e ZOOKEEPER_IP=host.docker.internal ches/kafka
cd scheduler
docker build . -t stm_scheduler
docker run -d --name=stm_scheduler -p 8087:8087 stm_scheduler
cd ..\api\8083
docker build . -t stm_api8083
docker run -d --name=stm_api_8083 -p 8083:8083 stm_api8083
cd ..\8084
docker build . -t stm_api8084
docker run -d --name=stm_api_8084 -p 8084:8084 stm_api8084
cd ..\..\nginx
docker build . -t nginx-image
docker run -d -p 80:80 --name nginx nginx-image