package com.seoulbike.demo.web;

import com.seoulbike.demo.domain.User;
import com.seoulbike.demo.security.LoginUser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Controller
@RequestMapping("/bikes")
public class BikeController {


    @GetMapping("")
    public String a(@LoginUser User loginUser){

        return "/bike/list";
    }

}
