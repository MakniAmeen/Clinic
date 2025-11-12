<%@ include file="../layout/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-md-6">
        <h2><i class="fas fa-edit"></i> Edit Client</h2>

        <form action="/clients/update" method="post">
            <input type="hidden" name="id" value="${client.id}">

            <div class="mb-3">
                <label for="name" class="form-label">Full Name *</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="${client.name}" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email *</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="${client.email}" required>
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone *</label>
                <input type="tel" class="form-control" id="phone" name="phone"
                       value="${client.phone}" required>
            </div>

            <div class="mb-3">
                <label for="age" class="form-label">Age *</label>
                <input type="number" class="form-control" id="age" name="age"
                       value="${client.age}" min="1" max="120" required>
            </div>

            <div class="mb-3">
                <label for="gender" class="form-label">Gender *</label>
                <select class="form-control" id="gender" name="gender" required>
                    <option value="Male" ${client.gender == 'Male' ? 'selected' : ''}>Male</option>
                    <option value="Female" ${client.gender == 'Female' ? 'selected' : ''}>Female</option>
                    <option value="Other" ${client.gender == 'Other' ? 'selected' : ''}>Other</option>
                </select>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-warning">
                    <i class="fas fa-sync-alt"></i> Update Client
                </button>
                <a href="/clients" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>