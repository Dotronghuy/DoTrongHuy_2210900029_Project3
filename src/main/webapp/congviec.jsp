<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.DTH_2210900029.entity.CongViec" %>

<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Công Việc</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>
<div id="appspotify">
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
                        <div class="scroll">
                            <ul class="ul_listmusic">
                                <a href="/NhanVienServlet" class="relative">
                                    <li class="li_list"><div class="desc_li"><p class="text_list">Nhân viên</p></div></li>
                                </a>
                                <a href="/CongViecServlet" class="relative">
                                    <li class="li_list"><div class="desc_li"><p class="text_list">Công việc</p></div></li>
                                </a>
                                <a href="/ChamCongServlet" class="relative">
                                    <li class="li_list"><div class="desc_li"><p class="text_list">Chấm công</p></div></li>
                                </a>
                                <a href="/LuongServlet" class="relative">
                                    <li class="li_list"><div class="desc_li"><p class="text_list">Lương</p></div></li>
                                </a>
                                <a href="/HopDongServlet" class="relative">
                                    <li class="li_list"><div class="desc_li"><p class="text_list">Hợp đồng</p></div></li>
                                </a>
                                <a href="/PhanCongServlet" class="relative">
                                    <li class="li_list"><div class="desc_li"><p class="text_list">Phân công</p></div></li>
                                </a>
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
                <h2>Danh sách Công Việc</h2>
                <button class="btn_add" onclick="window.location.href='/addJob.jsp'">
                    <i class="fa-solid fa-plus"></i> Thêm công việc
                </button>
            </div>

            <div class="search_bar">
                <input type="text" id="search" placeholder="Tìm kiếm công việc..." autocomplete="off">
                <i class="fa-solid fa-magnifying-glass"></i>
            </div>

            <div class="table_container">
                <table border="1">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Tên Công Việc</th>
                        <th>Mô Tả</th>
                        <th>Ngày giao</th>
                        <th>Hạn hoàn thành</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<CongViec> congViecs = (List<CongViec>) request.getAttribute("congViecs");
                        for (CongViec cv : congViecs) { %>
                    <tr>
                        <td><%= cv.getId() %></td>
                        <td><%= cv.getTenCongViec() %></td>
                        <td><%= cv.getMoTa() %></td>
                        <td><%= cv.getNgayGiao() %></td>
                        <td><%= cv.getHanHoanThanh() %></td>
                        <td><span class="status <%= cv.getTrangThaiCss() %>"><%= cv.getTrangThai() %></span></td>
                        <td>
                            <button class="btn_edit" onclick="window.location.href='/editJob.jsp?id=<%= cv.getId() %>'">
                                <i class="fa-solid fa-pen"></i>
                            </button>
                            <button class="btn_delete" onclick="confirmDelete(<%= cv.getId() %>)">
                                <i class="fa-solid fa-trash"></i>
                            </button>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="./main.js"></script>
</body>
</html>
