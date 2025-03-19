
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws UnirestException {
        // Klant
        User user = new User("dvandijk", "7hY4uP0w6e");

        // Medewerker
        // User user = new User("avdvelden", "2kL4jH6u8i");

        // Beheerder
        // User user = new User("ldgr", "1xZ3cV5b7n");



        String application = "triptop";

        System.out.println("--------------------------------");
        System.out.println("Logging in...");
        System.out.println("--------------------------------");

        String token = login(user);

        System.out.println("--------------------------------");
        System.out.println("Checking for access and role...");
        System.out.println("--------------------------------");


        checkForAcces(user.getUsername(), application, token);
    }

    public static String login(User user)
            throws UnirestException {
        String url = "https://triptop-identity.wiremockapi.cloud/login";

        JSONObject userJson = new JSONObject();
        userJson.put("username", user.getUsername());
        userJson.put("password", user.getPassword());

        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(userJson)
                .asJson();

        JsonNode responseBody = response.getBody(); // pakt de hele response
        JSONObject jsonObject = responseBody.getObject(); // pakt daarvan het object
        JSONObject tokenObject = jsonObject.getJSONObject("token"); // pakt daarvan 't token object
        String tokenValue = tokenObject.getString("value"); // pakt daarvan de daadwerkelijke token
        String expirationTime = tokenObject.getString("expirationTime"); // en de expiration time

        System.out.println("Token: " + tokenValue);
        System.out.println("Expiration time: " + expirationTime);

        return tokenValue;
    }

    public static void checkForAcces(String username, String application, String token) throws UnirestException {
        JSONObject accesJson = new JSONObject();
        accesJson.put("username", username);
        accesJson.put("application", application);

        String url = "https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=" + token;

        HttpResponse<JsonNode> response = Unirest.post(url)
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(accesJson)
                .asJson();

        JsonNode responseBody = response.getBody();
        JSONObject jsonObject = responseBody.getObject();
        String hasAccess = jsonObject.getString("access");
        String role = jsonObject.getString("role");

        System.out.println("Has access: " + hasAccess);
        System.out.println("Role: " + role);
    }
}