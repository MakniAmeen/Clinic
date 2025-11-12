<%@ include file="../layout/header.jsp" %>
<div class="row justify-content-center">
    <div class="col-md-6">
        <h2><i class="fas fa-edit"></i> Edit Doctor</h2>

        <form action="/doctors/update" method="post">
            <input type="hidden" name="id" value="${doctor.id}">

            <div class="mb-3">
                <label for="name" class="form-label">Full Name</label>
                <input type="text" class="form-control" id="name" name="name"
                       value="${doctor.name}" required>
            </div>

            <div class="mb-3">
                <label for="specialization" class="form-label">Specialization</label>
                <input type="text" class="form-control" id="specialization" name="specialization"
                       value="${doctor.specialization}" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email"
                       value="${doctor.email}" required>
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="tel" class="form-control" id="phone" name="phone"
                       value="${doctor.phone}" required>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn btn-warning">
                    <i class="fas fa-sync-alt"></i> Update Doctor
                </button>
                <a href="/doctors" class="btn btn-secondary">Cancel</a>
            </div>
        </form>
    </div>
</div>
<%@ include file="../layout/footer.jsp" %>