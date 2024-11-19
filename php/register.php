<?php
include 'connect.php';  // Include the database connection file

try {
    $conn = new mysqli($db_servername, $db_username, $db_password, $db_database);

    // รับข้อมูล JSON จาก body
    $data = json_decode(file_get_contents("php://input"), true);

    if (isset($data['email'], $data['username'], $data['password'], $data['created_at'])) {
        $email = $data['email'];
        $username = $data['username'];
        $password = $data['password'];
        $created_at = $data['created_at'];

        // Hash the password
        $hashed_password = password_hash($password, PASSWORD_DEFAULT);

        // Prepare the SQL statement
        $stmt = $conn->prepare("INSERT INTO `users` (`email`, `username`, `password`, `created_at`) VALUES (?, ?, ?, ?)");
        $stmt->bind_param("ssss", $email, $username, $hashed_password, $created_at);

        if ($stmt->execute()) {
            http_response_code(201);  // HTTP 201 Created
            echo json_encode(["code" => 201, "message" => "User created successfully"]);
        } else {
            http_response_code(500);  // HTTP 500 Internal Server Error
            echo json_encode(["code" => 500, "message" => "Error creating user"]);
        }

        $stmt->close();
    } else {
        http_response_code(400);  // HTTP 400 Bad Request
        echo json_encode(["code" => 400, "message" => "Missing parameters"]);
    }
} catch (Exception $e) {
    http_response_code(500);
    echo json_encode(["code" => 500, "message" => $e->getMessage()]);
} finally {
    if (isset($conn)) {
        $conn->close();
    }
}

?>