<?php
$db_servername = "localhost";
$db_database = "wise_wallet";
$db_username = "root";
$db_password = "";

function getDbConnection() {
    global $db_servername, $db_username, $db_password, $db_database;
    $conn = new mysqli($db_servername, $db_username, $db_password, $db_database);
    if ($conn->connect_error) {
        throw new Exception("Database connection failed: " . $conn->connect_error);
    }
    return $conn;
}
?>
