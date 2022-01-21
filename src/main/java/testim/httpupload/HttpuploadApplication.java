package testim.httpupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"testim.service.CartService"})
public class HttpuploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpuploadApplication.class, args);
	}

}
