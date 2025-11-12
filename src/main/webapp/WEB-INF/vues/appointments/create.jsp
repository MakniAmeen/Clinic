<%@ include file="../layout/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-md-6">
        <h2><i class="fas fa-calendar-plus"></i> Schedule Appointment</h2>

        <form action="/appointments" method="post">
            <div class="mb-3">
                <label for="dateTime" class="form-label">Date & Time *</label>
                <input type="datetime-local" class="form-control" id="dateTime" name="dateTime" required>
            </div>

            <div class="mb-3">
                <label for="doctorId" class="form-label">Doctor *</label>
                <select class="form-control" id="doctorId" name="doctorId" required>
                    <option value="">Select Doctor</option>
                    <c:forEach var="doctor" items="${doctors}">
                        <option value="${doctor.id}">Dr. ${doctor.name} - ${doctor.specialization}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="clientId" class="form-label">Client *</label>
                <select class="form-control" id="clientId" name="clientId" required>
                    <option value="">Select Client</option>
                    <c:forEach var="client" items="${clients}">
                        <option value="${client.id}">${client.name} - ${client.email}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="status" class="form-label">Status *</label>
                <select class="form-control" id="status" name="status" required>
                    <option value="Scheduled">Scheduled</option>
                    <option value="Confirmed">Confirmed</option>
                    <option value="Completed">Completed</option>
                    <option value="Cancelled">Cancelled</option>
                </select>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-warning">
                    <i class="fas fa-calendar-check"></i> Schedule Appointment
                </button>
                <a href="/appointments" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>