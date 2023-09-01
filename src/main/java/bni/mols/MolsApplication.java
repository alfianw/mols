package bni.mols;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MolsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MolsApplication.class, args);
                System.out.println("Server is Running");
	}

}
