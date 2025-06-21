# demo-xss

A demo project for XSS (Cross-Site Scripting) testing using Spring Boot.

## Docker Image

- [Docker Hub Repository](https://hub.docker.com/repository/docker/andrzejsydor/demo-xss)
- Image: `docker.io/andrzejsydor/demo-xss`

## Running with Docker

```sh
docker pull andrzejsydor/demo-xss
docker run -p 8080:8080 andrzejsydor/demo-xss
```

# Maintenance and Upgrades

`mvn -N io.takari:maven:wrapper -Dmaven=3.9.6`

# Others

[http://<elb>.amazonaws.com:8080/](http://<elb>.amazonaws.com:8080/)

