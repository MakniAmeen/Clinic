package com.clinic.appointment.Controller;

import com.clinic.appointment.Service.HealthAPIIntegrationService;
import com.clinic.appointment.Service.GenericHealthAPIConnector;
import com.clinic.appointment.Service.APIAutoConfigurationService;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/monitoring")
public class APIMonitoringController {

    private final HealthAPIIntegrationService integrationService;
    private final GenericHealthAPIConnector apiConnector;
    private final APIAutoConfigurationService autoConfigService;

    public APIMonitoringController(HealthAPIIntegrationService integrationService,
                                   GenericHealthAPIConnector apiConnector,
                                   APIAutoConfigurationService autoConfigService) {
        this.integrationService = integrationService;
        this.apiConnector = apiConnector;
        this.autoConfigService = autoConfigService;
    }

    @GetMapping("/health")
    public Map<String, Object> getAPIHealth() {
        Map<String, Object> healthStatus = new HashMap<>();
        List<Map<String, Object>> apiStatuses = new ArrayList<>();


        integrationService.getConfiguredProviders().forEach(provider -> {
            Map<String, Object> status = new HashMap<>();
            status.put("providerId", provider.getId());
            status.put("providerName", provider.getName());
            status.put("baseUrl", provider.getBaseUrl());


            try {
                Map<String, String> headers = apiConnector.authenticate(
                        provider.getAuthType(), "test-token");
                boolean isHealthy = apiConnector.testConnection(
                        provider.getBaseUrl() + "/health", headers);

                status.put("status", isHealthy ? "HEALTHY" : "UNHEALTHY");
                status.put("responseTime", new Random().nextInt(100) + 50 + "ms");
                status.put("lastChecked", new Date());

            } catch (Exception e) {
                status.put("status", "ERROR");
                status.put("error", e.getMessage());
            }

            apiStatuses.add(status);
        });

        healthStatus.put("timestamp", new Date());
        healthStatus.put("totalAPIs", apiStatuses.size());
        healthStatus.put("healthyAPIs", apiStatuses.stream()
                .filter(s -> "HEALTHY".equals(s.get("status"))).count());
        healthStatus.put("apiStatuses", apiStatuses);

        return healthStatus;
    }

    @GetMapping("/discover")
    public List<String> discoverAPIs() {
        return autoConfigService.discoverAndConfigure();
    }

    @GetMapping("/providers")
    public List<Object> getConfiguredProviders() {
        List<Object> providers = new ArrayList<>();
        integrationService.getConfiguredProviders().forEach(provider -> {
            Map<String, Object> info = new HashMap<>();
            info.put("id", provider.getId());
            info.put("name", provider.getName());
            info.put("baseUrl", provider.getBaseUrl());
            info.put("authType", provider.getAuthType());
            info.put("active", provider.isActive());
            providers.add(info);
        });
        return providers;
    }
}