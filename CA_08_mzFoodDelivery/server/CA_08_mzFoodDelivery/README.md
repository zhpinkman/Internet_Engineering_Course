### Deployment

you can run the project on docker containers with these commands:
    
    // download mysql:
    docker pull mysql
    
    // Starting a MySQL instance:
    docker run -p 3307:3307 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql:latest
    docker exec -it mysql mysql -uroot -p
    123456
    CREATE DATABASE IF NOT EXISTS mzFoodDelivery
    
    // build the docker image:
    docker build -t java-server .
    
    // run the app:
    docker container run -p 8080:8080 java-server
