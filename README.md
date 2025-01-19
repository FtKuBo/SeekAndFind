

Run the web app

cd webApp
cd server 
./gradlew bootJar
cd ..
docker compose up


Run the matchingSystem

cd matchingSystem
cd matchingServer
./gradlew bootRun