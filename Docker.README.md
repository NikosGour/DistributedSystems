# Docker Instructions

## Building image

`docker build -t distributed_systems_backend --build-arg DOCKER_HOST=$winhost .`

the `$winhost` is the IP of the docker host machine. I use windows with WSL and thats why I have to input that exported variable.If you are using linux you need to input the IP of docker0 interface (maybe leaving it empty will work as well).

## Running database

`docker run --name distributed_systems_database -p 3306:3306 -e MYSQL_ROOT_PASSWORD="nikos2002" -e MYSQL_DATABASE="SpringBootDB" -d mysql:latest
`

## Running backend

`docker run -it --rm --name distributed_systems_backend -p 7979:7979 distributed_systems_backend:latest
`

