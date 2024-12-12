# Wise-Wallet

## Project Architecture
**Backend:** <br>
    - Uses Spring Boot to run the server (PORT: 8081) <br>
    - Handles Web application Login and Registration system <br>
**WebGL-Server:**  <br>
    - Developed in Java <br>
    - Runs on PORT: 8080 <br> 
    - Publishes WebGL content from Unity <br>
**StartServers.sh:** <br>
    - Shell script to simultaneously run both servers (PORT: 8081, PORT: 8080) <br>

## Local Setup and Cloning Instructions
### Step-by-Step Installation
1. Install, Start XAMPP and Prepare MySQL Server 
    - Open Command Prompt as Administrator 
    - **Check** MySQL Server state, using ```sc query mysql80``` 
    - If MySQL is **RUNNING**, **STOP** the service using ```net stop mysql80``` 
    - (Optional) **Start** MySQL service using ```net start mysql80``` 
2. Clone the Repository <br>
    - ```git clone https://github.com/farlos3/Wise-Wallet.git``` 
    - ```cd Wise-Wallet``` 
3. Start Servers <br>
    - ```chmod +x StartServers.sh && ./StartServers.sh``` 

## Database Access (phpMyAdmin)
    - Local URL: http://localhost/phpmyadmin/
    - Default Username: ```root```
    - Default Password: (typically blank)

## Database Configuration
1. Navigate to the configuration file <br>
    - ```/backend/src/main/resources/application.properties```
2. Update database connection settings <br>
    - ```spring.datasource.url=jdbc:mysql://localhost:3306/your_database``` 
    - ```spring.datasource.password=your_password``` 
## Notes
    - Ensure XAMPP is running and phpMyAdmin is accessible 
    - Replace your_database with your actual database name
    - Replace your_password with your MySQL password
