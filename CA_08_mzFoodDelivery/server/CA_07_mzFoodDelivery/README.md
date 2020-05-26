### Deployment

you can run the project on docker containers with these commands:

    // build the docker image
    docker image build -t java-server .
    
    // run the app
    docker container run -p 8080:8080 java-server
