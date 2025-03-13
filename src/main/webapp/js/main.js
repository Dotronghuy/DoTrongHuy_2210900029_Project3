document.addEventListener("DOMContentLoaded", function () {
    const addButton = document.querySelector(".btn_add");
    const editButtons = document.querySelectorAll(".btn_edit");

    if (addButton) {
        addButton.addEventListener("click", function () {
            showAddForm();
        });
    }

    editButtons.forEach(button => {
        button.addEventListener("click", function (event) {
            event.preventDefault();
            const id = this.getAttribute("data-id");
            const hoTen = this.getAttribute("data-hoTen");
            const ngaySinh = this.getAttribute("data-ngaySinh");
            const gioiTinh = this.getAttribute("data-gioiTinh");
            const sdt = this.getAttribute("data-sdt");
            const email = this.getAttribute("data-email");
            const chucVu = this.getAttribute("data-chucVu");
            showEditForm(id, hoTen, ngaySinh, gioiTinh, sdt, email, chucVu);
        });
    });

    function showAddForm() {
        createForm();
    }

    function showEditForm(id, hoTen, ngaySinh, gioiTinh, sdt, email, chucVu) {
        createForm(id, hoTen, ngaySinh, gioiTinh, sdt, email, chucVu);
    }

    function createForm(id = "", hoTen = "", ngaySinh = "", gioiTinh = "Nam", sdt = "", email = "", chucVu = "Nhân viên") {
        if (document.getElementById("addForm")) return;

        let formContainer = document.createElement("div");
        formContainer.id = "addForm";
        formContainer.className = "position-fixed top-50 start-50 translate-middle bg-white p-4 shadow-lg rounded";
        formContainer.style.width = "800px";

        formContainer.innerHTML = `
        <div class="d-flex justify-content-between align-items-center">
            <h3 class="mb-3">${id ? "Sửa" : "Thêm"} nhân viên</h3>
            <i class="fa-solid fa-xmark fs-4 text-danger cursor-pointer" id="closeFormIcon"></i>
        </div>

        <form action="NhanVienServlet?action=${id ? "update" : "add"}" method="post">
            ${id ? `<input type="hidden" name="id" value="${id}">` : ""}

            <div class="mb-3">
                <label for="hoTen" class="form-label">Họ Tên:</label>
                <input type="text" id="hoTen" name="hoTen" class="form-control" required autocomplete="off" value="${hoTen}">
            </div>

            <div class="mb-3">
                <label for="ngaySinh" class="form-label">Ngày Sinh:</label>
                <input type="date" id="ngaySinh" name="ngaySinh" class="form-control" required value="${ngaySinh}">
            </div>

            <div class="mb-3">
                <label for="gioiTinh" class="form-label">Giới Tính:</label>
                <select id="gioiTinh" name="gioiTinh" class="form-select">
                    <option value="Nam" ${gioiTinh === "Nam" ? "selected" : ""}>Nam</option>
                    <option value="Nữ" ${gioiTinh === "Nữ" ? "selected" : ""}>Nữ</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="sdt" class="form-label">Số Điện Thoại:</label>
                <input type="text" id="sdt" name="sdt" class="form-control" autocomplete="off" value="${sdt}">
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="text" id="email" name="email" class="form-control" autocomplete="off" value="${email}">
            </div>

            <div class="mb-3">
                <label for="chucVu" class="form-label">Chức vụ:</label>
                <select id="chucVu" name="chucVu" class="form-select">
                    <option value="Nhân viên" ${chucVu === "Nhân viên" ? "selected" : ""}>Nhân viên</option>
                    <option value="Quản lý" ${chucVu === "Quản lý" ? "selected" : ""}>Quản lý</option>
                </select>
            </div>

            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary" style="display: block">Lưu</button>
            </div>
        </form>
        `;

        document.getElementById("col_right").appendChild(formContainer);

        document.getElementById("closeFormIcon").addEventListener("click", function () {
            formContainer.remove();
        });
    }
});
