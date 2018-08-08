package com.seoulbike.demo.web;

import com.fasterxml.jackson.databind.JsonNode;
import com.seoulbike.demo.domain.User;
import com.seoulbike.demo.domain.UserRepository;
import com.seoulbike.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.net.HttpURLConnection;
import java.util.Arrays;

@Controller
@RequestMapping("/api/kakao")
public class KaKaoLoginController {
    private static final Logger log = LoggerFactory.getLogger(KaKaoLoginController.class);

    @Resource(name = "userService")
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    private RestTemplate restTemplate = new RestTemplate();

    private static final String CLIENT_ID = "ff16e8e6cd2f7b78da632c2d47250fd3";

    @GetMapping("/oauth")
    public String kakaoLogin(String code, HttpSession session) {
        log.info("code: {}", code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", "http://localhost:8080/api/kakao/oauth");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);


        JsonNode response = restTemplate.postForObject("https://kauth.kakao.com/oauth/token", request, JsonNode.class);
        String accessToken = response.get("access_token").asText();
        log.info("response: {}", response.get("access_token"));


        JsonNode userInfo = getUserInfo(accessToken);
        String userId = userInfo.get("id").asText();
        String nickName = userInfo.get("properties").get("nickname").asText();

        session.setAttribute("sessionedUser", userId);
        session.setAttribute("nickName",nickName);
        session.setAttribute("accessToken",accessToken);

        return "redirect:/";
    }

    public JsonNode getUserInfo(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Bearer "+accessToken);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(params, headers);
        System.out.println("request "+request);
        JsonNode response  = restTemplate.postForObject("https://kapi.kakao.com/v2/user/me",request,JsonNode.class);

        return response;
    }

}
