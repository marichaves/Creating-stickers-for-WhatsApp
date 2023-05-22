package imdb;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.net.URL;
import java.io.InputStream;
public class App {


    public static void main(String[] args) throws Exception{
         // fazer uma conexâo HTTP (protocolo usado para se comunicar na web) e buscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/Top250Movies/k_ok1icqdq";
        URI endereco = URI.create(url);
        var client =  HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        
        String body = response.body();
   

        // pegar só os dados que interessam (ranking, titulo e poster)
       var parser =	new JsonParser();
       List<Map<String, String>> listaDeFilmes = parser.parse(body);
      

      
    // exibir e manipular os dados da forma que quisermos
       for (Map<String, String> filme : listaDeFilmes) {
    	   
    	   String urlImagem = filme.get("image");
    	   String titulo = filme.get("title");
    	   InputStream inputStream = new URL(urlImagem).openStream();
    	   
    	   String nomeArquivo = titulo + ".png";
    	   
    	   var geradora = new GeradoraDeFigurinhas();
    	   geradora.cria(inputStream, nomeArquivo);
    	   
    	   System.out.println(titulo);
         
           System.out.println();
           
       }
       
      
    }
    }
