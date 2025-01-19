# SeekAndFind
SeekAndFind is a Spring Boot and React-based web application designed for uOttawa students to track lost and found electronics items. When a student loses an electronic device, such as a phone, laptop, or tablet, they can submit a form detailing the item. Similarly, when another student finds a lost device, they can also submit a form with their findings. Both actions trigger events sent to a server through a message broker for efficient processing. If a match is found between a lost and found item, the system sends an email notification to both students, enabling them to reconnect and return the item.

#### Run the web app
``` bach
cd webApp/server
./gradlew bootJar
cd ..
docker compose up
```

#### Run the matchingSystem server
```bach
cd matchingSystem/matchingServer
./gradlew bootRun
```
