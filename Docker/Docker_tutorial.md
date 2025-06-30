# 🖼️ Image Service - Spring Boot + Docker

A Spring Boot application packaged with Maven and containerized using Docker.

---

## 🚀 Getting Started

### 1. Build the JAR

```bash
mvn clean package
```

### 2. Build the Docker Image

```bash
docker build -t image-service:latest .

docker build -t docker-demo:latest .
```

### 3. Run the Container

```bash
docker run -d --name image-backend -p 8080:8080 image-service:latest
```

---

## 🧱 Container Management

| Action                    | Command                                 | Notes                               |
|---------------------------|-----------------------------------------|-------------------------------------|
| ✅ List running containers | `docker ps`                             | Shows currently active containers   |
| 🔄 List all containers    | `docker ps -a`                          | Includes stopped/created containers |
| 🧠 See container logs     | `docker logs <container-name>`          | Useful for debugging                |
| 🔍 Inspect container info | `docker inspect <container-name>`       | Full config and metadata            |
| ⛔ Stop a container        | `docker stop <container-name>`          | Graceful shutdown                   |
| ▶️ Start a container      | `docker start <container-name>`         | Start a stopped one                 |
| 🔁 Restart a container    | `docker restart <container-name>`       | Stops and starts again              |
| 🚪 Enter container shell  | `docker exec -it <container-name> bash` | Access container like a terminal    |
| 🗑️ Remove container      | `docker rm <container-name>`            | Must stop first                     |
| 🧹 Remove all stopped     | `docker container prune`                | Frees up space                      |

---

## 📦 🛠️ Image Management

| Action                 | Command                   | Notes                             |
|------------------------|---------------------------|-----------------------------------|
| 🖼️ List all images    | `docker images`           | Shows all downloaded/built images |
| 🧼 Remove image        | `docker rmi <image-name>` | Only if not in use                |
| 🧹 Clean unused images | `docker image prune`      | Removes dangling `<none>` images  |

---

## 🌐 Networking / Ports

| Action                | Command                                        | Notes                           |
|-----------------------|------------------------------------------------|---------------------------------|
| 🌍 Port mapping       | `docker run -p 8080:8080 ...`                  | Maps container to host port     |
| 🔗 List networks      | `docker network ls`                            | Useful for multi-container apps |
| 🌐 Connect to network | `docker network connect <network> <container>` | For custom communication        |

---

## 🛠️ Common Use Cases

### Check logs when app crashes

```bash
docker logs <container-name>
```

### Enter container to debug

```bash
docker exec -it <container-name> bash
```

### Test your image locally

```bash
docker run -d --name test-backend -p 8080:8080 image-service:latest
```

### Clean up

```bash
docker container prune      # removes stopped containers
docker image prune          # removes unused/dangling images
```

docker run -d \
--name postgres_container \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=DEMO_DB \
-p 5431:5432 \
-v postgres_data:/var/lib/postgresql/data \
postgres:latest

