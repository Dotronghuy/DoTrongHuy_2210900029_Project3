document.addEventListener("DOMContentLoaded", function () {
    const chamCongBtn = document.getElementById("btnChamCong");
    const idNhanVien = document.getElementById("idNhanVien").value;

    if (!idNhanVien) {
        alert("Không tìm thấy ID nhân viên. Vui lòng đăng nhập lại.");
        return;
    }

    function kiemTraTrangThaiChamCong() {
        fetch(`ChamCongServlet?idNhanVien=${idNhanVien}`)
            .then(response => response.json())
            .then(data => {
                if (data.gioVao) {
                    document.getElementById("gioVaoText").textContent = data.gioVao;
                }
                if (data.gioRa) {
                    document.getElementById("gioRaText").textContent = data.gioRa;
                }

                if (!data.gioVao || (data.gioVao && !data.gioRa)) {
                    chamCongBtn.textContent = data.gioVao ? "Chấm công ra" : "Chấm công vào";
                    chamCongBtn.disabled = false;
                } else {
                    chamCongBtn.textContent = "Bạn đã chấm công xong";
                    chamCongBtn.disabled = true;
                }
            })
            .catch(error => console.error("Lỗi khi kiểm tra trạng thái:", error));
    }

    chamCongBtn.addEventListener("click", function () {
        const now = new Date();
        const ngayLam = now.toISOString().split("T")[0];
        const gioHienTai = now.toTimeString().split(" ")[0];

        fetch("ChamCongServlet", {
            method: "POST",
            headers: { "Content-Type": "application/x-www-form-urlencoded" },
            body: `idNhanVien=${idNhanVien}&ngayLam=${ngayLam}&gioHienTai=${gioHienTai}`
        })
            .then(response => response.json())
            .then(data => {
                if (data.message) {
                    alert(data.message);
                }
                location.reload();
            })
            .catch(error => console.error("Lỗi:", error));
    });

    kiemTraTrangThaiChamCong();
});
