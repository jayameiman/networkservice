package id.co.bni.qris;

import id.co.bni.qris.utils.RefreshableRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = RefreshableRepositoryImpl.class)
@SpringBootApplication
public class MainController {

	public static void main(String[] args) {
		SpringApplication.run(MainController.class, args);
	}

}
