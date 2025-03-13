document.addEventListener("DOMContentLoaded", function () {
    let addJobButton = document.querySelector(".btn_add.add_job");

    if (addJobButton) {
        addJobButton.addEventListener("click", function () {
            createAddJobModal();
        });
    }
    attachDeleteEvent();
    attachEditEvent();
});

function createAddJobModal(job = null) {
    let colRight = document.getElementById("col_right");
    if (!colRight) {
        console.error("Không tìm thấy phần tử #col_right");
        return;
    }

    let existingModal = document.getElementById("addJobModal");
    if (existingModal) {
        existingModal.remove();
    }

    let isEdit = job !== null;

    let modal = document.createElement("div");
    modal.id = "addJobModal";
    modal.className = "modal fade show";
    modal.style.display = "block";
    modal.innerHTML = `
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">${isEdit ? "Chỉnh sửa Công Việc" : "Thêm Công Việc"}</h5>
                    <button type="button" class="btn-close" id="closeModalBtn"></button>
                </div>
                <div class="modal-body">
                    <form id="addJobForm">
                        <input type="hidden" id="jobId" value="${isEdit ? job.id : ''}">
                        <div class="mb-3">
                            <label class="form-label">Tên Công Việc</label>
                            <input type="text" class="form-control" id="tenCongViec" value="${isEdit ? job.tenCongViec : ''}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mô Tả</label>
                            <textarea class="form-control" id="moTa">${isEdit ? job.moTa : ''}</textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Ngày Giao</label>
                            <input type="date" class="form-control" id="ngayGiao" value="${isEdit ? job.ngayGiao : ''}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Hạn Hoàn Thành</label>
                            <input type="date" class="form-control" id="hanHoanThanh" value="${isEdit ? job.hanHoanThanh : ''}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Trạng Thái</label>
                            <select class="form-control" id="trangThai">
                                <option value="Chưa bắt đầu" ${isEdit && job.trangThai === "Chưa bắt đầu" ? "selected" : ""}>Chưa bắt đầu</option>
                                <option value="Đang thực hiện" ${isEdit && job.trangThai === "Đang thực hiện" ? "selected" : ""}>Đang thực hiện</option>
                                <option value="Hoàn thành" ${isEdit && job.trangThai === "Hoàn thành" ? "selected" : ""}>Hoàn thành</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" id="submitJobBtn" style="display: block">
                            ${isEdit ? "Lưu thay đổi" : "Thêm"}
                        </button>
                    </form>
                </div>
            </div>
        </div>
    `;

    colRight.appendChild(modal);

    document.getElementById("closeModalBtn").addEventListener("click", closeModal);

    document.getElementById("submitJobBtn").addEventListener("click", function () {
        isEdit ? submitEditJob() : submitAddJob();
    });
}

function closeModal() {
    let modal = document.getElementById("addJobModal");
    if (modal) modal.remove();
}

function submitAddJob() {
    let tenCongViec = document.getElementById("tenCongViec").value.trim();
    let moTa = document.getElementById("moTa").value.trim();
    let ngayGiao = document.getElementById("ngayGiao").value;
    let hanHoanThanh = document.getElementById("hanHoanThanh").value;
    let trangThai = document.getElementById("trangThai").value;

    if (!tenCongViec || !moTa || !ngayGiao || !hanHoanThanh || !trangThai) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    let formData = new URLSearchParams();
    formData.append("action", "add");
    formData.append("tenCongViec", tenCongViec);
    formData.append("moTa", moTa);
    formData.append("ngayGiao", ngayGiao);
    formData.append("hanHoanThanh", hanHoanThanh);
    formData.append("trangThai", trangThai);

    fetch("/CongViecServlet", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: formData.toString()
    }).then(response => {
        if (response.ok) {
            closeModal();
            location.reload();
        } else {
            alert("Lỗi khi thêm công việc!");
        }
    }).catch(error => {
        console.error("Error:", error);
        alert("Lỗi kết nối đến server!");
    });
}

function attachDeleteEvent() {
    document.querySelectorAll(".btn_delete").forEach(button => {
        button.addEventListener("click", function () {
            let jobId = this.getAttribute("data-id");
            confirmDelete(jobId);
        });
    });
}

function confirmDelete(jobId) {
    if (confirm("Bạn có chắc muốn xóa công việc này không?")) {
        deleteJob(jobId);
    }
}

function deleteJob(jobId) {
    fetch("/CongViecServlet", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: new URLSearchParams({ action: "delete", id: jobId })
    })
        .then(response => {
            if (response.ok) {
                alert("Xóa công việc thành công!");
                location.reload();
            } else {
                alert("Lỗi khi xóa công việc!");
            }
        })
        .catch(error => {
            console.error("Error:", error);
            alert("Lỗi kết nối đến server!");
        });
}

function attachEditEvent() {
    document.querySelectorAll(".btn_edit").forEach(button => {
        button.addEventListener("click", function () {
            let job = {
                id: this.getAttribute("data-id"),
                tenCongViec: this.getAttribute("data-ten"),
                moTa: this.getAttribute("data-mota"),
                ngayGiao: this.getAttribute("data-ngaygiao"),
                hanHoanThanh: this.getAttribute("data-hanhoanthanh"),
                trangThai: this.getAttribute("data-trangthai")
            };
            createAddJobModal(job);
        });
    });
}

function submitEditJob() {
    let jobId = document.getElementById("jobId").value;
    let tenCongViec = document.getElementById("tenCongViec").value.trim();
    let moTa = document.getElementById("moTa").value.trim();
    let ngayGiao = document.getElementById("ngayGiao").value;
    let hanHoanThanh = document.getElementById("hanHoanThanh").value;
    let trangThai = document.getElementById("trangThai").value;

    if (!tenCongViec || !moTa || !ngayGiao || !hanHoanThanh || !trangThai) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    let formData = new URLSearchParams();
    formData.append("action", "edit");
    formData.append("id", jobId);
    formData.append("tenCongViec", tenCongViec);
    formData.append("moTa", moTa);
    formData.append("ngayGiao", ngayGiao);
    formData.append("hanHoanThanh", hanHoanThanh);
    formData.append("trangThai", trangThai);

    fetch("/CongViecServlet", {
        method: "POST",
        headers: { "Content-Type": "application/x-www-form-urlencoded" },
        body: formData.toString()
    }).then(response => {
        if (response.ok) {
            closeModal();
            location.reload();
        } else {
            alert("Lỗi khi cập nhật công việc!");
        }
    }).catch(error => {
        console.error("Error:", error);
        alert("Lỗi kết nối đến server!");
    });
}


