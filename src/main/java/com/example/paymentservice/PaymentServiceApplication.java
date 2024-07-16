package com.example.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.PriorityQueue;

@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		PriorityQueue<Integer> integerQueue = new PriorityQueue<>();
		integerQueue.add(5);
		integerQueue.add(-2);

		System.out.println(integerQueue.peek());
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
