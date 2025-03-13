<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.DTH_2210900029.entity.CongViec" %>

<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Công Việc</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
<div id="appspotify">
    <div id="col_left">
        <div id="header_left">
            <div class="header-top">
                <div class="ul-header">
                    <a  class="li-header_item" id="logoutLink" style="text-transform: none; color: #ffffff; cursor: pointer">
                        Đăng xuất
                    </a>
                </div>
            </div>
            <div class="header-bottom">
                <div class="listmusic">
                    <div class="find_listmusic">
                        <div class="scroll">
                            <ul class="ul_listmusic" data-vai-tro="<%= request.getSession().getAttribute("vaiTro") %>">
                                <%
                                    String vaiTro = (String) request.getSession().getAttribute("vaiTro");
                                    if ("Admin".equals(vaiTro)) {
                                %>
                                <li class="li_list"><a href="/NhanVienServlet">Nhân viên</a></li>
                                <li class="li_list"><a href="/CongViecServlet">Công việc</a></li>
                                <li class="li_list"><a href="/ChamCongServlet">Chấm công</a></li>
                                <li class="li_list"><a href="/LuongServlet">Lương</a></li>
                                <li class="li_list"><a href="/HopDongServlet">Hợp đồng</a></li>
                                <li class="li_list"><a href="/PhanCongServlet">Phân công</a></li>
                                <%
                                } else if ("Nhân viên".equals(vaiTro)) {
                                %>
                                <li class="li_list"><a href="/CongViecServlet">Công việc</a></li>
                                <li class="li_list"><a href="/ChamCongServlet">Chấm công</a></li>
                                <li class="li_list"><a href="/PhanCongServlet">Phân công</a></li>
                                <%
                                    }
                                %>
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
                <button class="btn_add add_job">
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
                            <button class="btn_edit" data-id="<%= cv.getId() %>"
                                    data-ten="<%= cv.getTenCongViec() %>"
                                    data-mota="<%= cv.getMoTa() %>"
                                    data-ngaygiao="<%= cv.getNgayGiao() %>"
                                    data-hanhoanthanh="<%= cv.getHanHoanThanh() %>"
                                    data-trangthai="<%= cv.getTrangThai() %>">
                                <i class="fa-solid fa-pen"></i>
                            </button>
                            <button class="btn_delete" data-id="<%= cv.getId() %>">
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
<script src="./js/job.js"></script>
<script src="./js/admin.js"></script>
</body>
</html>
