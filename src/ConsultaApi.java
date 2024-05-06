import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    // Creando MMetodo para la Consulta
    public Divisa buscaDivisa(String divisaOrigen, String divisaDestino, double cantidad){
        // Creando URL
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/dab40049ed605b8f4b28d2f8/pair/"
                +divisaOrigen+"/"+divisaDestino+"/"+cantidad);

        // Realizando Petición de Conexión
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        // Obteninedo Respuesta Json de la Consulta y Enviando a la Clase Divisa
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Divisa.class);

        } catch (Exception e) {
            throw new RuntimeException("Consulta No Valida");
        }

    }
}
