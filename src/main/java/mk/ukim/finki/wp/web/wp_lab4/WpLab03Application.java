package mk.ukim.finki.wp.web.wp_lab4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class WpLab03Application {

	public static void main(String[] args) {
		SpringApplication.run(WpLab03Application.class, args);
	}
}
