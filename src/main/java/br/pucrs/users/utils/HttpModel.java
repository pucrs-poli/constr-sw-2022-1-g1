package br.pucrs.users.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;

public class HttpModel {

    public static HttpEntity entity(Object object, MediaType midiaType)
    { return entity(object, midiaType,""); }
    public static HttpEntity entity(Object object, MediaType midiaType, String Authorization) {
        HttpHeaders headers = new HttpHeaders();
        if(!Authorization.isEmpty())
            headers.setBearerAuth(Authorization.split(" ")[1]);
        headers.setContentType(midiaType);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        return new HttpEntity (object, headers);
    }

}
