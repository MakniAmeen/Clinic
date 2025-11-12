<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>API Management - Clinic System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <div class="container">
        <a class="navbar-brand" href="/">
            <i class="fas fa-hospital-alt"></i> Clinic API Management
        </a>
    </div>
</nav>

<div class="container mt-4">
    <h1><i class="fas fa-plug"></i> API Integration Management</h1>

    <div class="row mt-4">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-success text-white">
                    <h5><i class="fas fa-satellite-dish"></i> Discover APIs</h5>
                </div>
                <div class="card-body">
                    <p>Automatically discover available health service APIs</p>
                    <button class="btn btn-success" onclick="discoverAPIs()">
                        <i class="fas fa-search"></i> Discover APIs
                    </button>
                    <div id="discoveryResults" class="mt-3"></div>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="card">
                <div class="card-header bg-warning text-dark">
                    <h5><i class="fas fa-robot"></i> Auto-Configure</h5>
                </div>
                <div class="card-body">
                    <p>Automatically configure all discovered APIs</p>
                    <button class="btn btn-warning" onclick="autoConfigure()">
                        <i class="fas fa-magic"></i> Auto-Configure All
                    </button>
                    <div id="autoConfigResults" class="mt-3"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-12">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <h5><i class="fas fa-heartbeat"></i> API Health Status</h5>
                </div>
                <div class="card-body">
                    <button class="btn btn-info" onclick="checkHealth()">
                        <i class="fas fa-stethoscope"></i> Check API Health
                    </button>
                    <div id="healthStatus" class="mt-3"></div>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-12">
            <a href="/" class="btn btn-secondary">
                <i class="fas fa-arrow-left"></i> Back to Dashboard
            </a>
        </div>
    </div>
</div>

<script>
    function discoverAPIs() {
        fetch('/api/admin/discover')
            .then(response => response.json())
            .then(apis => {
                let html = '<div class="alert alert-info"><h6>Discovered APIs:</h6><ul>';
                apis.forEach(api => {
                    html += `<li><strong>${api.name}</strong> (${api.id})<br>
                                <small>URL: ${api.baseUrl}<br>
                                Auth: ${api.authType}</small></li>`;
                });
                html += '</ul></div>';
                document.getElementById('discoveryResults').innerHTML = html;
            });
    }

    function autoConfigure() {
        fetch('/api/admin/auto-configure', { method: 'POST' })
            .then(response => response.json())
            .then(results => {
                let html = '<div class="alert alert-warning"><h6>Auto-Configuration Results:</h6><ul>';
                results.forEach(result => {
                    html += `<li>${result}</li>`;
                });
                html += '</ul></div>';
                document.getElementById('autoConfigResults').innerHTML = html;
            });
    }

    function checkHealth() {
        fetch('/api/monitoring/health')
            .then(response => response.json())
            .then(health => {
                let html = `<div class="alert alert-info">
                                <h6>API Health Status:</h6>
                                <p>Total APIs: ${health.totalAPIs} | Healthy: ${health.healthyAPIs}</p>`;

                if (health.apiStatuses.length > 0) {
                    html += '<ul>';
                    health.apiStatuses.forEach(api => {
                        const badgeClass = api.status === 'HEALTHY' ? 'bg-success' :
                            api.status === 'UNHEALTHY' ? 'bg-warning' : 'bg-danger';
                        html += `<li><span class="badge ${badgeClass}">${api.status}</span>
                                    ${api.providerName} - ${api.responseTime || 'N/A'}</li>`;
                    });
                    html += '</ul>';
                } else {
                    html += '<p>No APIs configured. Use discovery and auto-configure first.</p>';
                }
                html += '</div>';
                document.getElementById('healthStatus').innerHTML = html;
            });
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>