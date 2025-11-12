<%@ include file="../layout/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-md-6">
        <h2><i class="fas fa-edit"></i> Edit Appointment</h2>

        <form action="/appointments/update" method="post">
            <input type="hidden" name="id" value="${appointment.id}">

            <div class="mb-3">
                <label for="dateTime" class="form-label">Date & Time *</label>
                <input type="datetime-local" class="form-control" id="dateTime" name="dateTime"
                       value="${appointment.dateTime}" required>
            </div>

            <div class="mb-3">
                <label for="doctorId" class="form-label">Doctor *</label>
                <select class="form-control" id="doctorId" name="doctorId" required>
                    <c:forEach var="doctor" items="${doctors}">
                        <option value="${doctor.id}" ${appointment.doctorId.id == doctor.id ? 'selected' : ''}>
                            Dr. ${doctor.name} - ${doctor.specialization}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="clientId" class="form-label">Client *</label>
                <select class="form-control" id="clientId" name="clientId" required>
                    <c:forEach var="client" items="${clients}">
                        <option value="${client.id}" ${appointment.clientId.id == client.id ? 'selected' : ''}>
                            ${client.name} - ${client.email}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="mb-3">
                <label for="status" class="form-label">Status *</label>
                <select class="form-control" id="status" name="status" required>
                    <option value="Scheduled" ${appointment.status == 'Scheduled' ? 'selected' : ''}>Scheduled</option>
                    <option value="Confirmed" ${appointment.status == 'Confirmed' ? 'selected' : ''}>Confirmed</option>
                    <option value="Completed" ${appointment.status == 'Completed' ? 'selected' : ''}>Completed</option>
                    <option value="Cancelled" ${appointment.status == 'Cancelled' ? 'selected' : ''}>Cancelled</option>
                </select>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-warning">
                    <i class="fas fa-sync-alt"></i> Update Appointment
                </button>
                <a href="/appointments" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>