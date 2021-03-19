package com.holidu.interview.assignment.restclient;

import com.holidu.interview.assignment.dto.TreeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

/**
 * *     Using RestTemplate to consume RESTful Web Services.
 * RestTemplate is used to make HTTP Rest Calls (REST Client).
 **/

@Component
public class TreeRestClient {

   @Value("${tree.rest.client.url}")
    private String endpoint;


    public List<TreeResponse> findAllTreesPagination(int rows, int offset) {
        RestTemplate restTemp = new RestTemplate();

        final UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("$limit", rows)
                .queryParam("$offset", offset)
                .queryParam("$order", "tree_id")
                .build(false);

        ResponseEntity<TreeResponse[]> response = restTemp.getForEntity(uriComponents.toUriString(), TreeResponse[].class);

        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(response.getBody())));

    }

}
