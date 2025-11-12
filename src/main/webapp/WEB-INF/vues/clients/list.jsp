<%@ include file="../layout/header.jsp" %>
<div class="d-flex justify-content-between align-items-center mb-4">
    <h2><i class="fas fa-users"></i> Clients</h2>
    <a href="/clients/create" class="btn btn-success">
        <i class="fas fa-plus"></i> Add New Client
    </a>
</div>

<table class="table table-striped table-hover">
    <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td>${client.id}</td>
                <td>${client.name}</td>
                <td>${client.email}</td>
                <td>${client.phone}</td>
                <td>${client.age}</td>
                <td>${client.gender}</td>
                <td>
                    <a href="/clients/edit/${client.id}" class="btn btn-sm btn-warning">
                        <i class="fas fa-edit"></i> Edit
                    </a>
                    <a href="/clients/delete/${client.id}" class="btn btn-sm btn-danger"
                       onclick="return confirm('Are you sure you want to delete this client?')">
                        <i class="fas fa-trash"></i> Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<%@ include file="../layout/footer.jsp" %>