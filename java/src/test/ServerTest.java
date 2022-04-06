import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.net.HttpURLConnection;
import org.junit.Test;

public class ServerTest {
    private static HttpURLConnection connection;

    @Test
    public void someTest() throws UnirestException {
        //Check status
        HttpResponse<String> jsonNodeHttpResponse = Unirest.get("http://localhost:8080/number=2").header("accept", "application/json").asString();
        int response = jsonNodeHttpResponse.getStatus();
        System.out.println("Response is " + response);
        assert(response == 200);
    }
}
