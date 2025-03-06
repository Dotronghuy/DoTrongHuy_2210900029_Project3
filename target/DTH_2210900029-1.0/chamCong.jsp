<%@ page import="com.example.DTH_2210900029.entity.ChamCong" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/main.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <title>Danh sách chấm công</title>
</head>
<body>
<div id="col_left">
    <div id="header_left">
        <div class="header-top">
            <div class="ul-header">
                <a href="#" class="li-header_item">
                    <i class="fa-solid fa-house"></i>
                    <h4 class="text_header">Trang chủ</h4>
                </a>
            </div>
        </div>
        <div class="header-bottom">
            <div class="listmusic">
                <div class="find_listmusic">
                    <div class="srcoll">
                        <ul class="ul_listmusic">
                            <li class="li_list center"><a href="/NhanVienServlet">Nhân viên</a></li>
                            <li class="li_list center"><a href="/CongViecServlet">Công việc</a></li>
                            <li class="li_list center"><a href="/ChamCongServlet">Chấm công</a></li>
                            <li class="li_list center"><a href="/LuongServlet">Lương</a></li>
                            <li class="li_list center"><a href="/HopDongServlet">Hợp đồng</a></li>
                            <li class="li_list center"><a href="/PhanCongServlet">Phân công</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="col_right">
    <div class="content_right">
        <div class="header_right">
            <h2>Quản lý Chấm Công</h2>
            <button class="btn_add" onclick="window.location.href='ChamCongServlet?action=add'">
                <i class="fa-solid fa-plus"></i> Thêm Chấm Công
            </button>
        </div>

        <div class="search_bar">
            <input type="text" id="search" placeholder="Tìm kiếm chấm công..." autocomplete="off">
            <i class="fa-solid fa-magnifying-glass"></i>
        </div>

        <div class="table_container">
            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>ID Nhân Viên</th>
                    <th>Ngày Làm Việc</th>
                    <th>Tổng Giờ Làm</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<ChamCong> chamCongs = (List<ChamCong>) request.getAttribute("chamCongs");
                    if (chamCongs != null && !chamCongs.isEmpty()) {
                        for (ChamCong chamCong : chamCongs) {
                %>
                <tr>
                    <td><%= chamCong.getId() %></td>
                    <td><%= chamCong.getIdNhanVien() %></td>
                    <td><%= chamCong.getNgayLam() %></td>
                    <td><%= chamCong.getSoGioLam() %> giờ</td>
                    <td>
                        <a href="ChamCongServlet?action=edit&id=<%= chamCong.getId() %>" class="btn_edit">
                            <i class="fa-solid fa-pen"></i>
                        </a>
                        <a href="ChamCongServlet?action=delete&id=<%= chamCong.getId() %>" class="btn_delete"
                           onclick="return confirm('Bạn có chắc chắn muốn xóa?');">
                            <i class="fa-solid fa-trash"></i>
                        </a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="5" style="text-align: center;">Không có dữ liệu chấm công.</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
