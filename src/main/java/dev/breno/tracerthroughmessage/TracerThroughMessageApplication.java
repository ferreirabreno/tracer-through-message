package dev.breno.tracerthroughmessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TracerThroughMessageApplication {

	private static final Logger logger = LoggerFactory.getLogger(TracerThroughMessageApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TracerThroughMessageApplication.class, args);
	}

}
