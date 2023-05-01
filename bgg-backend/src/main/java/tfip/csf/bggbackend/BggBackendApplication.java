package tfip.csf.bggbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tfip.csf.bggbackend.Repository.bggRepo;

@SpringBootApplication
public class BggBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BggBackendApplication.class, args);
	}


}
