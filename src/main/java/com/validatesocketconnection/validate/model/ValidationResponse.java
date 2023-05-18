package com.validatesocketconnection.validate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationResponse {
    private Boolean isAuth;
    private SocketURI socketURI;
}
