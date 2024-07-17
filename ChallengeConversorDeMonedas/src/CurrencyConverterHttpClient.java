import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class CurrencyConverterHttpClient {
    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/";

    private final HttpClient httpClient;

    public CurrencyConverterHttpClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getExchangeRates(String baseCurrency) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + baseCurrency))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to fetch exchange rates: " + response.statusCode());
        }

        return response.body();
    }

    public static void main(String[] args) {
        CurrencyConverterHttpClient client = new CurrencyConverterHttpClient();
        try {
            String response = client.getExchangeRates("USD");
            System.out.println("Exchange rates: " + response);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}


