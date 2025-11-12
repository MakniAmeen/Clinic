package com.clinic.appointment.Service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class APIAutoConfigurationService {

    private final HealthAPIIntegrationService integrationService;
    private final GenericHealthAPIConnector apiConnector;

    public APIAutoConfigurationService(HealthAPIIntegrationService integrationService,
                                       GenericHealthAPIConnector apiConnector) {
        this.integrationService = integrationService;
        this.apiConnector = apiConnector;
    }


    public boolean autoConfigureService(String providerId, String apiUrl,
                                        Map<String, String> config) {
        try {
            System.out.println("🔄 Starting auto-configuration for: " + providerId);


            integrationService.configureEndpoint(providerId, apiUrl, config);


            boolean isConnected = simulateConnectionTest(providerId, apiUrl);

            if (isConnected) {
                System.out.println("✅ Auto-configuration successful for: " + providerId);


                markProviderAsActive(providerId);
                return true;
            } else {
                System.out.println("Auto-configuration completed but connection test failed for: " + providerId);
                return false;
            }

        } catch (Exception e) {
            System.out.println("Auto-configuration failed for: " + providerId + " - " + e.getMessage());
            return false;
        }
    }


    private boolean simulateConnectionTest(String providerId, String apiUrl) {
        try {


            // Simulation de délai réseau
            Thread.sleep(100);


            if ("INSURANCE_API".equals(providerId)) {
                System.out.println("🔧 Development: Simulating FAILED connection for " + providerId);
                return false;
            } else {
                System.out.println("🔧 Development: Simulating SUCCESSFUL connection for " + providerId);
                return true;
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private void markProviderAsActive(String providerId) {

        System.out.println("🏷️ Marking " + providerId + " as ACTIVE");
    }


    public List<String> discoverAndConfigure() {
        List<String> results = new ArrayList<>();
        List<HealthServiceProvider> discoveredAPIs = integrationService.discoverAPIs();

        for (HealthServiceProvider provider : discoveredAPIs) {
            Map<String, String> config = new HashMap<>();
            config.put("name", provider.getName());
            config.put("authType", provider.getAuthType());
            config.put("credentials", "demo-credential-" + System.currentTimeMillis());

            boolean success = autoConfigureService(provider.getId(),
                    provider.getBaseUrl(), config);

            String result = provider.getId() + " : " + (success ? "SUCCESS" : "FAILED");
            results.add(result);


            if (success) {
                System.out.println("🎉 " + result + " - Ready for integration");
            } else {
                System.out.println("💡 " + result + " - Check configuration or network");
            }
        }

        return results;
    }
}