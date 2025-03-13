document.addEventListener("DOMContentLoaded", function () {
    document.querySelector(".btn_add").addEventListener("click", function () {
        createAddPhanCongModal();
    });
});

function createAddPhanCongModal() {
    let colRight = document.getElementById("col_right");
    if (!colRight) {
        console.error("Không tìm thấy phần tử #col_right");
        return;
    }

    let existingModal = document.getElementById("addPhanCongModal");
    if (existingModal) {
        existingModal.remove();
    }

    let modal = document.createElement("div");
    modal.id = "addPhanCongModal";
    modal.className = "position-fixed top-50 start-50 translate-middle bg-white p-4 shadow-lg rounded";
    modal.style.width = "800px";
    modal.innerHTML = `
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Thêm Phân Công Mới</h5>
                    <button type="button" class="btn-close" id="closeModalBtn"></button>
                </div>
                <div class="modal-body">
                    <form id="addPhanCongForm">
                        <div class="mb-3">
                            <label class="form-label">ID Nhân Viên</label>
                            <input type="text" class="form-control" id="idNhanVien" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Tên Nhân Viên</label>
                            <input type="text" class="form-control" id="tenNhanVien" readonly required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">ID Công Việc</label>
                            <input type="text" class="form-control" id="idCongViec" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Tên Công Việc</label>
                            <input type="text" class="form-control" id="tenCongViec" readonly required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Ngày Bắt Đầu</label>
                            <input type="date" class="form-control" id="ngayBatDau" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Ngày Kết Thúc</label>
                            <input type="date" class="form-control" id="ngayKetThuc" required>
                        </div>
                        <button type="button" class="btn btn-primary" id="submitPhanCongBtn" style="display: block">Lưu Thêm</button>
                    </form>
                </div>
            </div>
        </div>
    `;
    colRight.appendChild(modal);
    document.getElementById("closeModalBtn").addEventListener("click", closeModal);

    document.getElementById("submitPhanCongBtn").addEventListener("click", submitAddPhanCong);

    setTimeout(function () {
        let idNhanVienElement = document.getElementById("idNhanVien");
        if (idNhanVienElement) {
            idNhanVienElement.addEventListener("blur", function () {
                let idNhanVien = this.value.trim();
                if (idNhanVien) {
                    fetchEmployeeName(idNhanVien);
                }
            });
        }

        let idCongViecElement = document.getElementById("idCongViec");
        if (idCongViecElement) {
            idCongViecElement.addEventListener("blur", function () {
                let idCongViec = this.value.trim();
                if (idCongViec) {
                    fetchJobName(idCongViec);
                }
            });
        }
    }, 0);
}

function closeModal() {
    let modal = document.getElementById("addPhanCongModal");
    if (modal) modal.remove();
}

function fetchEmployeeName(idNhanVien) {
    fetch(`/PhanCongServlet?action=fetchEmployeeName&idNhanVien=${idNhanVien}`)
        .then(response => response.json())
        .then(data => {
            if (data.tenNhanVien) {
                let tenNhanVienElement = document.getElementById("tenNhanVien");
                if (tenNhanVienElement) {
                    tenNhanVienElement.value = data.tenNhanVien;
                }
            } else {
                alert("Không tìm thấy nhân viên với ID này!");
            }
        })
        .catch(error => {
            console.error("Lỗi:", error);
            alert("Lỗi khi lấy tên nhân viên!");
        });
}

function fetchJobName(idCongViec) {
    fetch(`/PhanCongServlet?action=fetchJobName&idCongViec=${idCongViec}`)
        .then(response => response.json())
        .then(data => {
            if (data.tenCongViec) {
                let tenCongViecElement = document.getElementById("tenCongViec");
                if (tenCongViecElement) {
                    tenCongViecElement.value = data.tenCongViec;
                }
            } else {
                alert("Không tìm thấy công việc với ID này!");
            }
        })
        .catch(error => {
            console.error("Lỗi:", error);
            alert("Lỗi khi lấy tên công việc!");
        });
}

function submitAddPhanCong() {
    let idNhanVien = document.getElementById("idNhanVien").value.trim();
    let tenNhanVien = document.getElementById("tenNhanVien").value.trim();
    let idCongViec = document.getElementById("idCongViec").value.trim();
    let tenCongViec = document.getElementById("tenCongViec").value.trim();
    let ngayBatDau = document.getElementById("ngayBatDau").value.trim();
    let ngayKetThuc = document.getElementById("ngayKetThuc").value.trim();

    if (!idNhanVien || !tenNhanVien || !idCongViec || !tenCongViec || !ngayBatDau || !ngayKetThuc) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    console.log("Dữ liệu gửi đi: ", {
        idNhanVien,
        tenNhanVien,
        idCongViec,
        tenCongViec,
        ngayBatDau,
        ngayKetThuc
    });

    let formData = new URLSearchParams();
    formData.append("action", "add");
    formData.append("idNhanVien", idNhanVien);
    formData.append("tenNhanVien", tenNhanVien);
    formData.append("idCongViec", idCongViec);
    formData.append("tenCongViec", tenCongViec);
    formData.append("ngayBatDau", ngayBatDau);
    formData.append("ngayKetThuc", ngayKetThuc);

    fetch("/PhanCongServlet", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: formData.toString()
    })
        .then(response => {
            console.log("Phản hồi từ server:", response);
            if (response.ok) {
                closeModal();
                location.reload();
            } else {
                alert("Lỗi khi thêm phân công!");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Lỗi kết nối đến server!");
        });
}

