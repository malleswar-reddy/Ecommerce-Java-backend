## ✅ To-Do Tasks – Microservices Setup Plan

### ✅ 1. Build Maven Projects

* ✅ Create build file for all Maven projects

## build all maven project

```shell

./build.sh # or

./build.sh all # to build all projects

./build.sh admin-service # to build specific project

```

* ⬜ Create Dockerfile for each project
* ⬜ Create Docker Compose for all projects
* ⬜ Create Docker Compose with database
* ⬜ Create Docker Compose with database and API Gateway

### ⬜ 2. Build Docker Images

### Docker compose notes

```shell

docker-compose --version

# build admin-service
docker-compose -f docker-compose.yml build admin-service

```

2 ways to build Docker images:
build docker command with a specific service or

```shell
   docker build -t admin-service  ./adminservice 
```

build docker command with a specific service or set memory limit

```shell
   docker build -t admin-service --memory=512m ./adminservice 
```

build all services defined in `docker-compose.yml` or build a specific service.

* ⬜ Build Docker images for all services

```shell

# build admin-service
docker-compose -f docker-compose.yml build 

# it created docker image for all services defined in docker-compose.yml

```

* ⬜ Build Docker images for a specific service memory limit

```shell
docker-compose -f docker-compose.yml build --memory=512m admin-service
```

### ⬜ 3. Run Docker Compose (Target: June 27)

* ⬜ Run Docker Compose with database

```shell
# Run Docker Compose with database
docker-compose -f docker-compose.yml up -d postgres

```

* ⬜ Run Docker Compose with database and API Gateway

### ⬜ 4. Liquibase Integration (Target: June 29, Sunday 10 AM)

* ⬜ Create Liquibase file for each project
* ⬜ Create Liquibase integration for all projects
* ⬜ Create Liquibase integration for all projects with database

### ⬜ 5. Configure Server (Target: June 30, Monday 10 AM)

* ⬜ Create server config for each project
* ⬜ Create server config for all projects
* ⬜ Add server config for projects with DB
* ⬜ Add server config for projects with DB & API Gateway

### ⬜ 6. API Gateway Setup

* ⬜ Configure routing and load balancing
* ⬜ Add service registry (e.g., Eureka or Consul) if needed

### ⬜ 7. Service-to-Service Connectivity

* ⬜ Add OpenFeign/WebClient for inter-service calls
* ⬜ Secure internal calls with shared secrets or JWT

### ⬜ 8. Test Cases (Mockito)

* ⬜ Write test cases for each project
* ⬜ Write test cases for all projects combined
* ⬜ Write test cases for projects with DB
* ⬜ Write test cases with DB & API Gateway
 