package com.carlosSerafimm.delivery.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@RestController
public class SwaggerAggregatorController {

    private final DiscoveryClient discoveryClient;
    private final WebClient webClient;

    @Autowired
    public SwaggerAggregatorController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
        this.webClient = WebClient.builder().build();
    }

    @GetMapping(value = "/v3/api-docs-aggregated", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> aggregateDocs() {
        List<String> services = List.of("DELIVERY-TRACKING", "COURIER-MANAGEMENT");
        List<Mono<Map>> calls = new ArrayList<>();

        for (String service : services) {
            // pega a primeira instância do serviço pelo Eureka
            var instances = discoveryClient.getInstances(service);
            if (!instances.isEmpty()) {
                String url = instances.get(0).getUri() + "/v3/api-docs";
                calls.add(webClient.get()
                        .uri(url)
                        .retrieve()
                        .bodyToMono(Map.class)
                        .onErrorReturn(Collections.emptyMap()));
            }
        }

        return Mono.zip(calls, results -> {
            Map<String, Object> merged = new LinkedHashMap<>();
            Map<String, Object> paths = new LinkedHashMap<>();
            Map<String, Object> components = new LinkedHashMap<>();

            for (Object result : results) {
                if (result instanceof Map map) {
                    // merge paths
                    Map<String, Object> p = (Map<String, Object>) map.get("paths");
                    if (p != null) paths.putAll(p);

                    // merge components (schemas, responses, etc)
                    Map<String, Object> c = (Map<String, Object>) map.get("components");
                    if (c != null) components.putAll(c);
                }
            }

            merged.put("openapi", "3.0.1");
            merged.put("info", Map.of("title", "API Gateway Aggregated Docs", "version", "1.0"));
            merged.put("paths", paths);
            merged.put("components", components);

            return merged;
        });
    }
}
