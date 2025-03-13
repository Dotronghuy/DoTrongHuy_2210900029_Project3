document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll(".btn_edit").forEach(button => {
        button.addEventListener("click", function () {
            console.log("Chỉnh sửa hợp đồng!");
            let hopdong = {
                id: this.getAttribute("data-id"),
                idNhanVien: this.getAttribute("data-id-nhan-vien"),
                ngayBatDau: this.getAttribute("data-ngay-bat-dau"),
                ngayKetThuc: this.getAttribute("data-ngay-ket-thuc"),
            };
            createEditHopDongModal(hopdong);
        });
    });
});

function createEditHopDongModal(hopdong) {
    let colRight = document.getElementById("col_right");
    if (!colRight) {
        console.error("Không tìm thấy phần tử #col_right");
        return;
    }

    let existingModal = document.getElementById("editHopDongModal");
    if (existingModal) {
        existingModal.remove();
    }

    let modal = document.createElement("div");
    modal.id = "editHopDongModal";
    modal.className = "position-fixed top-50 start-50 translate-middle bg-white p-4 shadow-lg rounded";
    modal.style.width = "800px";
    modal.innerHTML = `
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Chỉnh sửa Hợp Đồng</h5>
                    <button type="button" class="btn-close" id="closeModalBtn"></button>
                </div>
                <div class="modal-body">
                    <form id="editHopDongForm">
                        <input type="hidden" id="hopDongId" value="${hopdong.id}">
                        <input type="hidden" id="idNhanVien" value="${hopdong.idNhanVien}">
                        <div class="mb-3">
                            <label class="form-label">ID Nhân Viên</label>
                            <input type="text" class="form-control" value="${hopdong.idNhanVien}" readonly>
                        </div>
                      
                        <div class="mb-3">
                            <label class="form-label">Ngày bắt đầu hợp đồng</label>
                            <input type="date" class="form-control" id="ngayBatDau" value="${hopdong.ngayBatDau}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Ngày kết thúc hợp đồng</label>
                            <input type="date" class="form-control" id="ngayKetThuc" value="${hopdong.ngayKetThuc}">
                        </div>
                       
                        <button type="button" class="btn btn-primary" id="submitHopDongBtn" style="display:block;">Lưu thay đổi</button>
                    </form>
                </div>
            </div>
        </div>
    `;
    colRight.appendChild(modal);

    document.getElementById("closeModalBtn").addEventListener("click", closeModal);

    document.getElementById("submitHopDongBtn").addEventListener("click", submitEditHopDong);
}

function closeModal() {
    let modal = document.getElementById("editHopDongModal");
    if (modal) modal.remove();
}

function submitEditHopDong() {
    let hopDongId = document.getElementById("hopDongId").value;
    let idNhanVien = document.getElementById("idNhanVien").value;
    let ngayBatDau = document.getElementById("ngayBatDau").value.trim();
    let ngayKetThuc = document.getElementById("ngayKetThuc").value.trim();

    console.log("Dữ liệu gửi đi:", { hopDongId, idNhanVien, ngayBatDau, ngayKetThuc }); // Kiểm tra dữ liệu

    if (!ngayBatDau || !ngayKetThuc) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    let formData = new URLSearchParams();
    formData.append("action", "edit");
    formData.append("id", hopDongId);
    formData.append("idNhanVien", idNhanVien);
    formData.append("ngayBatDau", ngayBatDau);
    formData.append("ngayKetThuc", ngayKetThuc);

    fetch("/HopDongServlet", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: formData.toString()
    }).then(response => {
        if (response.ok) {
            closeModal();
            location.reload();
        } else {
            alert("Lỗi khi cập nhật hợp đồng!");
        }
    }).catch(error => {
        console.error("Error:", error);
        alert("Lỗi kết nối đến server!");
    });
}