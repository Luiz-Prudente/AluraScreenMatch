package screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import screenmatch.model.DadosSerie;
import screenmatch.services.ConsumoAPI;
import screenmatch.services.DataConvert;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var consumoAPI  = new ConsumoAPI();
		String json = consumoAPI.getData("gilmore girls");
		System.out.println(json);
		DataConvert converter = new DataConvert();
		DadosSerie data = converter.getData(json,DadosSerie.class);
		System.out.println(data);
	}
}
