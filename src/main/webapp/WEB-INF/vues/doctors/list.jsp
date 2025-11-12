<%@ include file="../layout/header.jsp" %>
<div class="d-flex justify-content-between align-items-center mb-4">
    <h2><i class="fas fa-user-md"></i> Doctors</h2>
    <a href="/doctors/create" class="btn btn-primary">
        <i class="fas fa-plus"></i> Add New Doctor
    </a>
</div>

<table class="table table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Specialization</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="doctor" items="${doctors}">
            <tr>
                <td>${doctor.id}</td>
                <td>${doctor.name}</td>
                <td>${doctor.specialization}</td>
                <td>${doctor.email}</td>
                <td>${doctor.phone}</td>
                <td>
                    <a href="/doctors/edit/${doctor.id}" class="btn btn-sm btn-warning">
                        <i class="fas fa-edit"></i> Edit
                    </a>
                    <a href="/doctors/delete/${doctor.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Are you sure you want to delete this doctor?')">
                        <i class="fas fa-trash"></i> Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@ include file="../layout/footer.jsp" %>