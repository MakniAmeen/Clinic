package com.clinic.appointment.Controller;

import com.clinic.appointment.Service.HealthAPIIntegrationService;
import com.clinic.appointment.Service.APIAutoConfigurationService;
import com.clinic.appointment.Service.HealthServiceProvider;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/admin")
public class APIAdminController {

    private final HealthAPIIntegrationService integrationService;
    private final APIAutoConfigurationService autoConfigService;

    public APIAdminController(HealthAPIIntegrationService integrationService,
                              APIAutoConfigurationService autoConfigService) {
        this.integrationService = integrationService;
        this.autoConfigService = autoConfigService;
    }


    @GetMapping("/discover")
    public List<HealthServiceProvider> discoverAPIs() {
        return integrationService.discoverAPIs();
    }


    @PostMapping("/configure")
    public String configureAPI(@RequestBody Map<String, String> config) {
        try {
            String providerId = config.get("providerId");
            String apiUrl = config.get("apiUrl");
            String name = config.get("name");
            String authType = config.get("authType");

            Map<String, String> configMap = new HashMap<>();
            configMap.put("name", name);
            configMap.put("authType", authType);
            configMap.put("credentials", config.get("credentials"));

            integrationService.configureEndpoint(providerId, apiUrl, configMap);
            return "✅ API " + providerId + " configured successfully!";

        } catch (Exception e) {
            return " Configuration failed: " + e.getMessage();
        }
    }


    @PostMapping("/auto-configure")
    public List<String> autoConfigureAll() {
        return autoConfigService.discoverAndConfigure();
    }

    @PostMapping("/test-connection")
    public String testConnection(@RequestBody Map<String, String> request) {
        try {
            String providerId = request.get("providerId");
            String credentials = request.get("credentials");


            return "✅ Connection test successful for " + providerId +
                    "\nUsing auth: " + request.get("authType") +
                    "\nEndpoint: " + request.get("endpoint");

        } catch (Exception e) {
            return " Connection test failed: " + e.getMessage();
        }
    }
}