package dev.breno.tracerthroughmessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TracerThroughMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(TracerThroughMessageApplication.class, args);
	}

}
