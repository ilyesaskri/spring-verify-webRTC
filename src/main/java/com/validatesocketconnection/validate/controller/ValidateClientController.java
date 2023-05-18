package com.validatesocketconnection.validate.controller;


import com.validatesocketconnection.validate.model.SocketURI;
import com.validatesocketconnection.validate.model.ValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/validate")
@CrossOrigin(origins = "*")
public class ValidateClientController {

    RestTemplate restTemplate = new RestTemplate();
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{userName}/{roomId}")
    public ValidationResponse validateUser(@PathVariable("userName") String userName, @PathVariable("roomId") String roomId) {
        SocketURI socketURI= getSocketURi();
        System.out.println(socketURI.toString());
        ValidationResponse validationResponseAuthorized = ValidationResponse.builder().isAuth(true)
                .socketURI(getSocketURi()).build();
        ValidationResponse validationResponseNotAuthorized = ValidationResponse.builder()
                .isAuth(false).build();
        if (userName == "" && roomId == "") {
            return ValidationResponse.builder().isAuth(false)
                    .build();
        } else {
            switch (roomId) {
                case ("room1"):
                    return switch (userName) {
                        case "aymen", "ilyes", "salim" -> validationResponseAuthorized;
                        default -> validationResponseNotAuthorized;
                    };
                case ("room2"):
                    return switch (userName) {
                        case "nihed", "yassmine", "salim" -> validationResponseAuthorized;
                        default -> validationResponseNotAuthorized;
                    };
                default:
                    return validationResponseNotAuthorized;

            }

        }
    }

    private SocketURI getSocketURi() {
        String uri = "http://localhost:3000/verify";
        return restTemplate.getForObject(uri,SocketURI.class);
    }
}
