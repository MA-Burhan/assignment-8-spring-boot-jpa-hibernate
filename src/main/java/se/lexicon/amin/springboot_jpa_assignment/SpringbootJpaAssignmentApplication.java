package se.lexicon.amin.springboot_jpa_assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.lexicon.amin.springboot_jpa_assignment.data.ProductRepositoryImpl;
import se.lexicon.amin.springboot_jpa_assignment.entity.Product;

@SpringBootApplication
public class SpringbootJpaAssignmentApplication implements CommandLineRunner {

	@Autowired
	private ProductRepositoryImpl repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaAssignmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
