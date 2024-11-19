import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class loginClient {
    public static void main(String[] args) {
        String urlString = "http://localhost/Wise-Wallet/php/check.php";

        // ข้อมูลที่ต้องส่ง
        String email = "test@example.com";
        String password = "password123";

        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);

        HttpURLConnection connection = null;

        try {
            URI uri = new URI(urlString); // ใช้ URI แทน String
            URL url = uri.toURL(); // แปลง URI เป็น URL

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            connection.setDoOutput(true);

            // แสดง JSON ที่ส่ง (ใช้สำหรับ debug)
            System.out.println("Sending JSON: " + json.toString());

            // ส่งข้อมูล JSON
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = json.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // อ่าน response
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Login successful.");
                readResponse(connection);
            } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
                System.err.println("Unauthorized: Invalid credentials.");
                readResponse(connection);
            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
                System.err.println("User not found.");
                readResponse(connection);
            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
                System.err.println("Bad Request: Missing or invalid parameters.");
                readResponse(connection);
            } else if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
                System.err.println("Server error occurred.");
                readResponse(connection);
            } else {
                System.err.println("Unexpected response code: " + responseCode);
                readResponse(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

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
}
