package com.reskilling.CRUDOperations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // This will load all the application context and start the server on a random port
class CrudOperationsApplicationTests {

	@LocalServerPort // This will inject the random port number
	private int port;

	@Autowired
	private TestRestTemplate restTemplate; // This is used to test the RESTful web services

	@Test
	public void getStudentByIdTest() {
		String url = "http://localhost:" + port + "/getStudentById/{id}"; // This is the URL of the RESTful web service (created url with string)
		Map<String, String> pathVariable = Map.of("id", "1"); // This is the parameter to be passed to the RESTful web service (created path variable with map)

		HttpEntity<String> entity = new HttpEntity<>(null,null); // This is the entity to be passed to the RESTful web service (Created entity with body and headers)
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url); // This is used to build the URL (Created builder to resolve the pathVariable)
		System.out.println("uri is "+builder.buildAndExpand(pathVariable).toUri()); // This will print the URL
		ResponseEntity<String> response = restTemplate.exchange(builder.buildAndExpand(pathVariable).toUri(), HttpMethod.GET, entity, String.class); // This is used to test the RESTful web service
		System.out.println(response.getBody()); // This will print the response

		assertEquals(HttpStatus.OK, response.getStatusCode()); // This will check if the status code is OK

	}

}
