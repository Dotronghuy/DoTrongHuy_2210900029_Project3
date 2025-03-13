document.addEventListener("DOMContentLoaded", function () {
    // Lấy giá trị vai trò từ thuộc tính data-vai-tro
    const vaiTro = document.querySelector(".ul_listmusic").getAttribute("data-vai-tro");

    const btnChamCong = document.querySelector("#btnChamCong");
    if (btnChamCong) {
        btnChamCong.style.display = "inline-block";
    }

    if (vaiTro === "Admin") {
        document.querySelectorAll(".btn_add").forEach(button => {
            button.style.display = "inline-block";
        });

        document.querySelectorAll(".btn_edit").forEach(button => {
            button.style.display = "inline-block";
        });

        document.querySelectorAll(".btn_delete").forEach(button => {
            button.style.display = "inline-block";
        });
    } else if (vaiTro === "Nhân viên") {
        document.querySelectorAll(".btn_add").forEach(button => {
            button.style.display = "none";
        });

        document.querySelectorAll(".btn_edit").forEach(button => {
            button.style.display = "none";
        });

        document.querySelectorAll(".btn_delete").forEach(button => {
            button.style.display = "none";
        });
    }

    const logoutLink = document.getElementById("logoutLink");

    if (logoutLink) {
        logoutLink.addEventListener("click", function (event) {
            event.preventDefault();

            window.location.href = "LogoutServlet";
        });
    }
});