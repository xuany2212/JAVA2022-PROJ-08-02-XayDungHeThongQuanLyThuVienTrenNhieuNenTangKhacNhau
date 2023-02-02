<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="index.jsp"%>
<!-- Main content -->
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark"></h1>
				</div>
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /.content-header -->
	<section class="content">

		<div class="container-fluid">

			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<!-- general form elements -->
					<div class="card card-primary">
						<div class="card-header">
							<h3 class="card-title">Chỉnh sửa tên thể loại</h3>
						</div>
						<div class="row justify-content-center"
							style="margin-top: 15px; margin-bottom: -15px;">
							<div style="color: red;">${errorString}</div>
						</div>
						<!-- /.card-header -->
						<!-- form start -->
						<form role="form" method="post"
							action="${pageContext.request.contextPath}/EditBook"
							enctype="multipart/form-data">

							<div class="card-body">

								<input type="hidden" name="masach" value="${book.getMaSach() }" />
								<div class="form-group">
									<label>Tên sách</label> <input type="text" class="form-control"
										id="tensach" name="tensach" value="${book.getTenSach()}">
								</div>
								<div class="form-group">
									<label>Tên tác giả</label> <input type="text"
										class="form-control" id="tentacgia" name="tentacgia"
										value="${book.getTenTacGia()}">
								</div>
								<div class="form-group">
									<label>Giá thuê</label> <input type="number"
										class="form-control" id="giathue" name="giathue"
										value="${book.getGiaThue()}">
								</div>
								<div class="form-group">
									<label>Mô tả</label> <input type="text" class="form-control"
										id="mota" name="mota" value="${book.getMoTa()}">
								</div>
								<div class="form-group">
									<label>Vị trí</label> <input type="text" class="form-control"
										id="vitri" name="vitri" value="${book.getViTri()}">
								</div>
								<div class="form-group">
									<label>Số lượng bản copy</label> <input type="number"
										class="form-control" id="slcp" name="slcp"
										value="${book.getSoLuongCP()}">
								</div>
								<div class="form-group">
									<label>Nhà phát hành</label> <select name="nxb" id="nxb"
										class="form-control" data-selected="${book.getMaNPH()}"
										required>
										<!-- <option value="">Chọn 1 thể loại</option> -->
										<c:forEach items="${listNXB}" var="listnxb">
											<option value="${Integer.toString(listnxb.getMaNPH())}"
												<c:if test="${listnxb.getMaNPH() == book.getMaNPH()}">selected="true"</c:if>>
												${listnxb.getTenNPH()}- ${listnxb.getMaNPH()}</option>
										</c:forEach>
									</select>
								</div>




								<%-- <div class="form-group">
									<label>Thể loại</label> <select name="category" id="category"
										class="form-control"
										data-selected="${book.getCategory().getName()}" required>
										<!-- <option value="">Chọn 1 thể loại</option> -->
										<c:forEach items="${categoryList}" var="category">
											<option value="${Integer.toString(category.getId())}"
												<c:if test="${category.getId() == book.getCategory().getId()}">selected="true"</c:if>>${category.getName()}</option>
										</c:forEach>
									</select>
								</div>
 --%>





							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-primary ">Lưu</button>
								<input type="button" value="Trở lại" class="btn btn-primary"
									onclick="location.href='${pageContext.request.contextPath}/ManageBook'">
							</div>
						</form>
					</div>
					<!-- /.card -->

				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
	<%@ include file="footer.jsp"%>
	<script src="./Resources/plugins/jquery/jquery.min.js"></script>

	<script
		src="./Resources/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>
	<!-- AdminLTE App -->
	<script src="./Resources/js/adminlte.min.js"></script>
	<!-- AdminLTE for demo purposes -->
	<script src="./Resources/js/demo.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			bsCustomFileInput.init();
		});
	</script>