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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RestController
@RequestMapping("/api/pay")
public class ApiPayController {
    private static final Logger log = LoggerFactory.getLogger(ApiPayController.class);


    @PostMapping("")
    public ResponseEntity<String> home(HttpSession session) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "KakaoAK 88b102d5940b30748955db127f6c1f90");
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML, MediaType.APPLICATION_JSON));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "partner_order_id");
        params.add("partner_user_id", "partner_user_id");
        params.add("item_name", "서울바이크");
        params.add("quantity", "1");
        params.add("total_amount", "2200");
        params.add("vat_amount", "200");
        params.add("tax_free_amount", "0");
        params.add("approval_url", "http://localhost:8080/pay/success");
        params.add("fail_url", "http://localhost:8080/pay/fail");
        params.add("cancel_url", "http://localhost:8080/pay/cancel");



        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        JsonNode response = new RestTemplate().postForObject("https://kapi.kakao.com/v1/payment/ready", request, JsonNode.class);
        log.info("response: {}" + response);

        session.setAttribute("tid",response.get("tid").asText());
        return new ResponseEntity<String>(response.get("next_redirect_pc_url").asText(), headers, HttpStatus.OK);
    }




}