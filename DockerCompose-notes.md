## Docker compose notes

```shell

docker-compose --version
 
 docker-compose -f docker-compose.yml build admin-service

docker-compose -f docker-compose.yml up -d postgres admin-service

```

https://docs.docker.com/reference/cli/docker/system/prune/

# To remove all stopped containers, unused networks, dangling images, and build cache

docker system prune

# To remove all unused images (not just dangling ones)

## how to run

docker-compose -f docker-compose.yml run -d postgres

docker-compose -f docker-compose.yml build admin-service


