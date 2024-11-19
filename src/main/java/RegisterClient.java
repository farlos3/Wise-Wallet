import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class RegisterClient {
    public static void main(String[] args) {
        // URL ของ PHP script ที่ทำการสมัครสมาชิก
        String urlString = "http://localhost/Wise-Wallet/php/register.php"; 

        // ข้อมูลที่จะส่ง (email, username, password, created_at)
        String email = "test@example.com";
        String username = "testuser";
        String password = "password123";
        String createdAt = "2024-11-19"; // วันที่และเวลาเมื่อทำการสมัคร

        // สร้าง JSON Object สำหรับส่งข้อมูล
        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("username", username);
        json.put("password", password);
        json.put("created_at", createdAt);

        HttpURLConnection connection = null;

        try {
            // สร้าง URI และแปลงเป็น URL
            URI uri = new URI(urlString);
            URL url = uri.toURL();

            // เปิดการเชื่อมต่อ
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // ส่งข้อมูล JSON
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // ตรวจสอบรหัสสถานะการตอบกลับ
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) { // 200 OK
                System.out.println("Request successful.");
                readResponse(connection);
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) { // 400 Bad Request
                System.err.println("Client error: Bad Request. Check the parameters sent.");
                readError(connection);
            } else if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) { // 500 Internal Server Error
                System.err.println("Server error: Something went wrong on the server.");
                readError(connection);
            } else {
                System.err.println("Unexpected response code: " + responseCode);
                readError(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    // Method to read the response from the server
    private static void readResponse(HttpURLConnection connection) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            System.out.println("Response: " + response.toString());
        }
    }

    // Method to read the error response from the server
    private static void readError(HttpURLConnection connection) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            System.err.println("Error Response: " + response.toString());
        }
    }
}
