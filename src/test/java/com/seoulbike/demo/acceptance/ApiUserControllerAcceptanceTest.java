package com.seoulbike.demo.acceptance;

import com.seoulbike.demo.domain.UserRepository;
import com.seoulbike.demo.dto.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiUserControllerAcceptanceTest {

    private static final Logger log = LoggerFactory.getLogger(ApiUserControllerAcceptanceTest.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    public void create() {
        HttpHeaders headers = new HttpHeaders();
        //reponse가 어떤 타입으로 받을지 지정해주는것
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserDto userDto = new UserDto("userId", "password", "joseph", "joe@naver.com");
        log.debug("UserDto {}", userDto);
        System.out.println("~~~~~~~~~" + userDto);
        ResponseEntity<String> response = testRestTemplate.postForEntity("/api/users", userDto, String.class);

        log.debug("response {}", response);
        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));

    }
}
