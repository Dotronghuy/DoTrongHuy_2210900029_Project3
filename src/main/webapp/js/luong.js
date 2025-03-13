document.addEventListener("DOMContentLoaded", function () {
    document.querySelectorAll("tr.luong-row").forEach(row => {
        let luongCoBan = parseFloat(row.querySelector(".luong-co-ban").innerText.replace(/[^0-9.-]+/g, "")) || 0;
        let phuCap = parseFloat(row.querySelector(".phu-cap").innerText.replace(/[^0-9.-]+/g, "")) || 0;
        let khauTru = parseFloat(row.querySelector(".khau-tru").innerText.replace(/[^0-9.-]+/g, "")) || 0;

        let tongLuong = luongCoBan + phuCap - khauTru;

        row.querySelector(".tong-luong").innerText = tongLuong.toLocaleString("vi-VN") + " VND";
    });
    attachEditEvent();
});

function attachEditEvent() {
    document.querySelectorAll(".btn_edit").forEach(button => {
        button.addEventListener("click", function () {
            console.log("Edit button clicked!");
            let luong = {
                id: this.getAttribute("data-id"),
                idNhanVien: this.getAttribute("data-id-nhan-vien"),
                thang: this.getAttribute("data-thang"),
                nam: this.getAttribute("data-nam"),
                tongGio: this.getAttribute("data-tong-gio"),
                luongCoBan: this.getAttribute("data-luong-co-ban"),
                phuCap: this.getAttribute("data-phu-cap"),
                khauTru: this.getAttribute("data-khau-tru")
            };
            createEditLuongModal(luong);
        });
    });
}

function createEditLuongModal(luong) {
    let colRight = document.getElementById("col_right");
    if (!colRight) {
        console.error("Không tìm thấy phần tử #col_right");
        return;
    }

    let existingModal = document.getElementById("editLuongModal");
    if (existingModal) {
        existingModal.remove();
    }

    let modal = document.createElement("div");
    modal.id = "editLuongModal";
    modal.className = "position-fixed top-50 start-50 translate-middle bg-white p-4 shadow-lg rounded";
    modal.style.width = "800px";
    modal.innerHTML = `
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Chỉnh sửa Lương</h5>
                    <button type="button" class="btn-close" id="closeModalBtn"></button>
                </div>
                <div class="modal-body">
                    <form id="editLuongForm">
                        <input type="hidden" id="luongId" value="${luong.id}">
                        <input type="hidden" id="idNhanVien" value="${luong.idNhanVien}">
                        <div class="mb-3">
                            <label class="form-label">ID Nhân Viên</label>
                            <input type="text" class="form-control" value="${luong.idNhanVien}" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Tháng</label>
                            <input type="number" class="form-control" id="thang" value="${luong.thang}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Năm</label>
                            <input type="number" class="form-control" id="nam" value="${luong.nam}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Tổng giờ làm</label>
                            <input type="number" class="form-control" id="tongGio" value="${luong.tongGio}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Lương cơ bản</label>
                            <input type="number" class="form-control" id="luongCoBan" value="${luong.luongCoBan}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Phụ cấp</label>
                            <input type="number" class="form-control" id="phuCap" value="${luong.phuCap}">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Khấu trừ</label>
                            <input type="number" class="form-control" id="khauTru" value="${luong.khauTru}">
                        </div>
                        <button type="button" class="btn btn-primary" id="submitLuongBtn" style="display: block">Lưu thay đổi</button>
                    </form>
                </div>
            </div>
        </div>
    `;
    colRight.appendChild(modal);

    document.getElementById("closeModalBtn").addEventListener("click", closeModal);
    document.getElementById("submitLuongBtn").addEventListener("click", submitEditLuong);
}

function closeModal() {
    let modal = document.getElementById("editLuongModal");
    if (modal) modal.remove();
}

function submitEditLuong() {
    let luongId = document.getElementById("luongId").value;
    let idNhanVien = document.getElementById("idNhanVien").value;
    let thang = document.getElementById("thang").value.trim();
    let nam = document.getElementById("nam").value.trim();
    let tongGio = document.getElementById("tongGio").value.trim();
    let luongCoBan = document.getElementById("luongCoBan").value.trim();
    let phuCap = document.getElementById("phuCap").value.trim();
    let khauTru = document.getElementById("khauTru").value.trim();

    console.log("luongId:", luongId);
    console.log("idNhanVien:", idNhanVien);
    console.log("thang:", thang);
    console.log("nam:", nam);
    console.log("tongGio:", tongGio);
    console.log("luongCoBan:", luongCoBan);
    console.log("phuCap:", phuCap);
    console.log("khauTru:", khauTru);

    if (!thang || !nam || !tongGio || !luongCoBan || !phuCap || !khauTru) {
        alert("Vui lòng nhập đầy đủ thông tin!");
        return;
    }

    if (isNaN(thang) || isNaN(nam) || isNaN(tongGio) || isNaN(luongCoBan) || isNaN(phuCap) || isNaN(khauTru)) {
        alert("Vui lòng nhập số hợp lệ!");
        return;
    }

    let formData = new URLSearchParams();
    formData.append("action", "edit");
    formData.append("id", luongId);
    formData.append("idNhanVien", idNhanVien);
    formData.append("thang", thang);
    formData.append("nam", nam);
    formData.append("tongGioLam", tongGio);
    formData.append("luongCoBan", luongCoBan);
    formData.append("phuCap", phuCap);
    formData.append("khauTru", khauTru);

    fetch("/LuongServlet", {
        method: "POST",
        headers: {"Content-Type": "application/x-www-form-urlencoded"},
        body: formData.toString()
    }).then(response => {
        if (response.ok) {
            closeModal();
            location.reload();
        } else {
            alert("Lỗi khi cập nhật lương!");
        }
    }).catch(error => {
        console.error("Error:", error);
        alert("Lỗi kết nối đến server!");
    });
}


