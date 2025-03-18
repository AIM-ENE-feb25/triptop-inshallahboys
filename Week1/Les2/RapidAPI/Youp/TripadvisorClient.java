import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TripadvisorClient {
    public static void main(String[] args) throws UnirestException {
        HttpResponse<String> response = Unirest.get("https://tripadvisor16.p.rapidapi.com/api/v1/restaurant/searchRestaurants?locationId=304554")
                .header("x-rapidapi-key", "452f88916emsh10e84a2a6c17d1dp1bf48bjsn694c71d67a8e")
                .header("x-rapidapi-host", "tripadvisor16.p.rapidapi.com")
                .asString();

        System.out.println(response.getBody());
    }
}
