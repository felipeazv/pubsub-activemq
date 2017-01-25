package com.example;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static com.example.JMSConfiguration.MAILBOX_TOPIC;
import static com.example.JMSConfiguration.MAILBOX_QUEUE;

@SpringBootApplication
public class PublisherApplication {
	public static void main(String[] args) {
		// Launch the application
		// ConfigurableApplicationContext context =
		// SpringApplication.run(PublisherApplication.class, args);
		SpringApplication.run(PublisherApplication.class, args);
		// Send a message with a POJO - the template reuse the message converter
		// System.out.println("Sending an email message.");
		// jmsTemplate.convertAndSend(MAILBOX_TOPIC, new
		// Email("info@example.com", "Hello"));
		// jmsTemplate.convertAndSend(new ActiveMQTopic(MAILBOX_TOPIC), new
		// Email("info@example.com", "Hello"));
	}

}

@Component
class Subscriber1 {

	@JmsListener(destination = MAILBOX_TOPIC)
	public void receiveMessage(Email email) {
		System.out.println("Subscriber1: <" + email + ">");
	}

}

@Component
class Subscriber2 {

	@JmsListener(destination = MAILBOX_TOPIC)
	public void receiveMessage(Email email) {
		System.out.println("Subscriber2: <" + email + ">");
	}

}

@Component
class Subscriber3 {

	@JmsListener(destination = MAILBOX_TOPIC)
	public void receiveMessage(Email email) {
		System.out.println("Subscriber3: <" + email + ">");
	}

}

@RestController
class Rest {
	@Autowired
	JMSConfiguration jms;
	@Autowired
	JmsTemplate jmsTemplate;

	@RequestMapping(value = "/send2topic", method = RequestMethod.GET)
	public void sendMessage2Topic(@RequestParam String message) {
//		jmsTemplate.convertAndSend(new ActiveMQTopic(MAILBOX_TOPIC), message);
		jmsTemplate.convertAndSend(new ActiveMQTopic(MAILBOX_TOPIC), new Email("info@example.com", "Hello"));
	}

	// @RequestMapping(value = "/send2queue", method = RequestMethod.GET)
	// public void sendMessage2Queue(@RequestParam String message) {
	// jms.send2Queue(MAILBOX_QUEUE, message);
	// }
}
