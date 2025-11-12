package com.clinic.appointment.Service;

public class HealthServiceProvider {
    private String id;
    private String name;
    private String baseUrl;
    private String authType;
    private boolean isActive = true;
    private String status = "DISCONNECTED";

    public HealthServiceProvider() {}

    public HealthServiceProvider(String id, String name, String baseUrl, String authType) {
        this.id = id;
        this.name = name;
        this.baseUrl = baseUrl;
        this.authType = authType;
    }


    public String getId() { return id; }
    public String getName() { return name; }
    public String getBaseUrl() { return baseUrl; }
    public String getAuthType() { return authType; }
    public boolean isActive() { return isActive; }
    public String getStatus() { return status; }


    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }
    public void setAuthType(String authType) { this.authType = authType; }
    public void setActive(boolean active) { isActive = active; }
    public void setStatus(String status) { this.status = status; }
}