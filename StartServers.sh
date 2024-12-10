#!/bin/bash
# backend server
cd backend || { 
    echo "Failed to change directory to backend"; 
    exit 1; 
}
mvn clean package -DskipTests || { 
    echo "Failed to build backend"; 
    exit 1; 
}

java -jar target/*.jar &  # RUN backend server in background
if [ $? -ne 0 ]; then
    echo "Failed to start backend server"
    exit 1
fi
echo "Backend server is running"

# WebGL server
cd ../webgl-server || { 
    echo "Failed to change directory to webgl-server"; 
    exit 1; }
javac LocalServer.java || { 
    echo "Failed to compile LocalServer.java"; 
    exit 1; }

java LocalServer &  # RUN WebGL server in background
if [ $? -ne 0 ]; then
    echo "Failed to start WebGL server"
    exit 1
fi
echo "WebGL server is running"

# รอทุก Process complete
wait

# chmod +x StartServers.sh && ./StartServers.sh