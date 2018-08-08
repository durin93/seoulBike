package com.seoulbike.demo.web;


import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequestMapping("/pay")
public class PayController {
    private static final Logger log = LoggerFactory.getLogger(PayController.class);


    @GetMapping("/success")
    public String success(String pg_token, HttpSession session) {
        log.info("success");
        log.info("pgtoken"+pg_token);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK 88b102d5940b30748955db127f6c1f90");
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", session.getAttribute("tid").toString());
        params.add("partner_user_id", "partner_user_id");
        params.add("partner_order_id", "partner_order_id");
        params.add("pg_token", pg_token);


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        JsonNode response = new RestTemplate().postForObject("https://kapi.kakao.com/v1/payment/approve", request, JsonNode.class);
        log.info("response: {}" + response);


        return "redirect:/";
    }

    @PostMapping("/fail")
    public String fail() {
        log.info("fail");
        return "redirect:";
    }

    @PostMapping("/cancel")
    public String cancel() {
        log.info("cancel");
        return "redirect:";
    }

}
