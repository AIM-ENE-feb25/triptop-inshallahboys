import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws UnirestException {
        // Klant
//        User user = new User("edevries", "3g2Rw9sT1x");

        // Medewerker
//        User user = new User("fvleeuwen", "0qW3rE5t7y");

        // Beheerder
        User user = new User("mvdlinden", "1xZ3cV5b7n");

        final String APPLICATION = "triptop";

        String token = Login(user);
        String accessMessage = checkAcces(user, APPLICATION, token).equals("allowed") ? "does have access" : "does not have access";
        System.out.println(accessMessage);
    }

    public static String Login(User user) throws UnirestException {
        JSONObject json = new JSONObject();
        json.put("username", user.getUsername());
        json.put("password", user.getPassword());

        HttpResponse<JsonNode> response = Unirest.post("https://triptop-identity.wiremockapi.cloud/login")
                .header("Content-Type", "application/json")
                .body(json)
                .asJson();

        String tokenValue = response.getBody().getObject().getJSONObject("token").getString("value");
        System.out.println(tokenValue);

        return tokenValue;
    }

    public static String checkAcces(User user, String application, String token) throws UnirestException {

        JSONObject json = new JSONObject();
        json.put("username", user.getUsername());
        json.put("application", application);

        HttpResponse<JsonNode> response = Unirest.post("https://triptop-identity.wiremockapi.cloud/checkAppAccess?token=" + token)
                .header("Content-Type", "application/json")
                .body(json)
                .asJson();

        String hasAccess = response.getBody().getObject().getString("access");
        return hasAccess;
    }
}
