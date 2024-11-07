package com.wisecrm.employeeservice.client;

import org.springframework.beans.factory.annotation.*;
import org.springframework.cloud.client.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.*;

@Component
public class ApiKeyServiceClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${apikey-service}")
    private String serviceName;

    @Value("${apikey-service-api}")
    private String api;

    public String getApiPath(String companyId) {
        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);

        if (instances.size()==0) return null;
        String serviceUri = String.format("%s"+api+"%s",instances.get(0).getUri().toString(), companyId);

        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class, companyId);

        return restExchange.getBody();
    }

}
