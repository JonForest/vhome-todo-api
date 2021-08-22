## Make the Docker container
```bash
./gradlew installDist
docker build -t vhome-todos .
docker run -p 8081:8080 vhome-todos
```
Will be available on `http://localhost:8081`