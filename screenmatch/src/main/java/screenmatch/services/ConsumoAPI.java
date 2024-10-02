package screenmatch.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.Normalizer;

public class ConsumoAPI {
    private final String API_KEY = "e35ebea";



    private String buildUrl(String content){
        return "http://www.omdbapi.com/?t="+ treatment(content)+ "&Season=" +"&apikey="+ API_KEY;
    }

    private String treatment(String text){
        String normalizadtext = Normalizer.normalize(text, Normalizer.Form.NFD);
        String treatedText = normalizadtext.replaceAll("\\p{M}", "");
        return treatedText.replaceAll(" ", "+").toLowerCase();
    }


    public String getData(String content){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(buildUrl(content)))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e){
            throw new RuntimeException(e);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        String json = response.body();
        return json;
    }
}
