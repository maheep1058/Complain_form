package ComplaintApp.main;

import ComplaintApp.main.complaint.ComplaintService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ComplaintService complaintService) {
		return args -> {
			complaintService.addDummyComplaint();
		};
	}
}
