<%@ include file="../layout/header.jsp" %>
<div class="d-flex justify-content-between align-items-center mb-4">
    <h2><i class="fas fa-calendar-check"></i> Appointments</h2>
    <a href="/appointments/create" class="btn btn-warning">
        <i class="fas fa-plus"></i> Schedule Appointment
    </a>
</div>

<table class="table table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Date & Time</th>
            <th>Doctor</th>
            <th>Client</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="appointment" items="${appointments}">
            <tr>
                <td>${appointment.id}</td>
                <td>${appointment.dateTime}</td>
                <td>${appointment.doctorId.name}</td>
                <td>${appointment.clientId.name}</td>
                <td>
                    <span class="badge bg-${appointment.status == 'Scheduled' ? 'primary' :
                                          appointment.status == 'Completed' ? 'success' :
                                          appointment.status == 'Cancelled' ? 'danger' : 'warning'}">
                        ${appointment.status}
                    </span>
                </td>
                <td>
                    <a href="/appointments/edit/${appointment.id}" class="btn btn-sm btn-warning">
                        <i class="fas fa-edit"></i> Edit
                    </a>
                    <a href="/appointments/delete/${appointment.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Are you sure you want to delete this appointment?')">
                        <i class="fas fa-trash"></i> Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@ include file="../layout/footer.jsp" %>