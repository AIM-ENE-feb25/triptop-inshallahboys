import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class BookingApi {

    public static void main(String[] args) throws UnirestException {

        HttpResponse<String> response = Unirest.get("https://booking-com.p.rapidapi.com/v1/metadata/exchange-rates?locale=en-gb&currency=AED")
                .header("x-rapidapi-host", "booking-com.p.rapidapi.com")
                .header("x-rapidapi-key", "94970d1393msh92fa12afc4a0d74p1ee854jsn513682af9321")
                .asString();

        System.out.println(response.getBody());
    }
}
