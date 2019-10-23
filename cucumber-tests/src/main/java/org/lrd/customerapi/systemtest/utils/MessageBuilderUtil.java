package org.lrd.customerapi.systemtest.utils;


import java.io.IOException;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;


public class MessageBuilderUtil {

    public static String getPayload(String firstName, String lastName) {
        return String.format("{\"firstName\":\"%s\", \"lastName\":\"%s\"}", firstName, lastName);
    }

    public static HttpHeaders getHeaders(String headers) throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(new ObjectMapper().readValue(headers, Map.class));
        return httpHeaders;
    }


}
