<%@ include file="layout/header.jsp" %>
<div class="row">
    <div class="col-md-12 text-center">
        <h1 class="display-4 mb-4">Welcome to Clinic Appointment System</h1>
        <p class="lead">Manage doctors, clients, and appointments efficiently</p>
    </div>
</div>

<div class="row mt-5">
    <div class="col-md-4">
        <div class="card text-center">
            <div class="card-body">
                <i class="fas fa-user-md fa-3x text-primary mb-3"></i>
                <h5 class="card-title">Doctors</h5>
                <p class="card-text">Manage medical professionals</p>
                <a href="/doctors" class="btn btn-primary">Manage Doctors</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card text-center">
            <div class="card-body">
                <i class="fas fa-users fa-3x text-success mb-3"></i>
                <h5 class="card-title">Clients</h5>
                <p class="card-text">Manage patient records</p>
                <a href="/clients" class="btn btn-success">Manage Clients</a>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="card text-center">
            <div class="card-body">
                <i class="fas fa-calendar-check fa-3x text-warning mb-3"></i>
                <h5 class="card-title">Appointments</h5>
                <p class="card-text">Schedule and manage appointments</p>
                <a href="/appointments" class="btn btn-warning">Manage Appointments</a>
            </div>
        </div>
    </div>
</div>
<%@ include file="layout/footer.jsp" %>