# 🔍 SeekAndFind  

🎥 **Demo Video:** [Watch on YouTube](https://www.youtube.com/watch?v=FT4U7X0Klec)  

SeekAndFind is a **Spring Boot and React-based web application** developed for **uOttawa students** to track lost and found electronic devices. It was built as part of the **Solace Challenge** at **uOttaHack 7** by team **GJW9**.  

## 🚀 How It Works  

1. 📌 **Report a lost item** – If a student loses an electronic device (phone, laptop, tablet, etc.), they submit a form detailing the item.  
2. 📨 **Report a found item** – If a student finds a lost device, they submit a form with their findings.  
3. 🔄 **Event-driven matching** – Both reports trigger events sent to a server via a **Solace message broker** for efficient processing.  
4. ✉️ **Instant email notification** – If a match is found, an email is automatically sent to both students, allowing them to coordinate the item's return.  

---

## 📂 Setup & Running the Project  

### 🖥️ Running the Web App  

```bash
cd webApp
docker compose up
```

### 🔍 Running the Matching System Server
```bach
cd matchingSystem
docker compose up
```

## ⚠️ Configuration Required  

To run the project successfully, update the Solace and Gmail credentials in :

   - `SeekAndFind/webApp/server/src/main/resources/application.properties`  

 
   - `SeekAndFind/matchingSystem/matchingServer/src/main/resources/application.properties` 

---

## 👥 Contributors  

This project was built by **Team GJW9** during **uOttaHack 7**:  
- **Mehdi**  
- **Azzam**  
- **Ilyass**  
- **Jihane**  
