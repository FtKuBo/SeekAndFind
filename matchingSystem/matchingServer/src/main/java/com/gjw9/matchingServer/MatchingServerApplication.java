package com.gjw9.matchingServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import com.gjw9.matchingServer.services.EmailService;
import com.gjw9.matchingServer.services.ObjService;
import java.util.function.Consumer;

@SpringBootApplication
public class MatchingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchingServerApplication.class, args);
	}

	@Bean
	public Consumer<Message<String>> myConsumer(StreamBridge sb) {
		return v -> {
			String header = String.valueOf(v.getHeaders().get("solace_destination"));
			ObjService service = new ObjService();
			EmailService eService = new EmailService();

			System.out.println(header.equals("object/lost"));

			if (header.equals("object/lost")){
				String requestBody = v.getPayload();
				// check if the object is in the objectfound tab
				// 		remove all the matches and send them an email
				// else add it to the objectlost tab 
				

			}

			else if (header.equals("object/found")){
				String requestBody = v.getPayload();
				// check if the object is in the objectlost tab
				// 		remove all the matches and send them an email
				// else add it to the objectlost tab 
			
			}

			System.out.println(v.getPayload());

			System.out.println("Received myConsumer: " + v.getPayload());
			System.out.println(header);
		};
	}

}
