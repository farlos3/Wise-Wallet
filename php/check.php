<?php
include 'connect.php'; // Include the database connection file

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

try {
    $conn = new mysqli($db_servername, $db_username, $db_password, $db_database);

    // รับข้อมูล JSON ที่ส่งมา
    $data = json_decode(file_get_contents("php://input"), true);

    // แสดงข้อมูล JSON ที่ได้รับ (ใช้สำหรับ debug)
    error_log("Received JSON: " . json_encode($data));

    // ตรวจสอบว่ามี email และ password
    if (!empty($data['email']) && !empty($data['password'])) {
        $email = $data['email'];
        $password = $data['password'];

        // ค้นหาผู้ใช้จาก email
        $stmt = $conn->prepare("SELECT id, email, password, username FROM users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        $stmt->bind_result($id, $db_email, $db_password, $username);

        if ($stmt->fetch()) {
            // ตรวจสอบรหัสผ่าน
            if (password_verify($password, $db_password)) {
                http_response_code(200); // HTTP 200 OK
                echo json_encode([
                    "code" => 200,
                    "message" => "Login successful",
                    "response" => [
                        "id" => $id,
                        "email" => $db_email,
                        "username" => $username
                    ]
                ]);
            } else {
                http_response_code(401); // HTTP 401 Unauthorized
                echo json_encode([
                    "code" => 401,
                    "message" => "Invalid credentials",
                    "response" => ""
                ]);
            }
        } else {
            http_response_code(404); // HTTP 404 Not Found
            echo json_encode([
                "code" => 404,
                "message" => "User not found",
                "response" => ""
            ]);
        }
        $stmt->close();
    } else {
        http_response_code(400); // HTTP 400 Bad Request
        echo json_encode([
            "code" => 400,
            "message" => "Missing email or password",
            "response" => ""
        ]);
    }
} catch (Exception $e) {
    http_response_code(500); // HTTP 500 Internal Server Error
    echo json_encode([
        "code" => 500,
        "message" => $e->getMessage(),
        "response" => ""
    ]);
} finally {
    if (isset($conn) && $conn->ping()) {
        $conn->close();
    }
}
?>