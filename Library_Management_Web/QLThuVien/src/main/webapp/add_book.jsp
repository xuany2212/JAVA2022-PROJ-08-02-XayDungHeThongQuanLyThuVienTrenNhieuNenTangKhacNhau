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
							<h3 class="card-title">Thêm sách vào thư viện</h3>
						</div>
						<div class="row justify-content-center"
							style="margin-top: 15px; margin-bottom: -15px;">
							<div style="color: red;">${errorString}</div>
						</div>
						<!-- /.card-header -->
						<!-- form start -->
						<form role="form" method="post"
							action="${pageContext.request.contextPath}/AddBook"
							enctype="multipart/form-data">

							<div class="card-body">
								<div class="form-group">
									<label>Nhập tên sách</label> <input type="text"
										class="form-control" id="tensach" name="tensach"
										placeholder="Nhập tên sách">
								</div>
								<div class="form-group">
									<label>Tên tác giả</label> <input type="text"
										class="form-control" id="tentacgia" name="tentacgia"
										placeholder="Tên tác giả">
								</div>
								<div class="form-group">
									<label>Giá thuê</label> <input type="number"
										class="form-control" id="giathue" name="giathue">
								</div>
								<div class="form-group">
									<label>Mô tả</label> <input type="text"
										class="form-control" id="mota" name="mota"
										placeholder="Mô tả">
								</div>
								<div class="form-group">
									<label>Vị trí</label> <input type="text"
										class="form-control" id="vitri" name="vitri"
										placeholder="Vị trí">
								</div>
								<div class="form-group">
									<label>Số lượng bản copy</label> <input type="number"
										class="form-control" id="slcp" name="slcp">
								</div>
								<div class="form-group">
									<label>Nhà phát hành</label> <select name="nxb" id="nxb"
										class="form-control" required>
										<!-- <option value="">Chọn 1 thể loại</option> -->
										<c:forEach items="${listNXB}" var="listnxb">
											<option value="${Integer.toString(listnxb.getMaNPH())}">${listnxb.getTenNPH()} - ${listnxb.getMaNPH()}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="card-footer">
								<button type="submit" class="btn btn-primary ">Lưu</button>
								<input type="button" value="Trở lại" class="btn btn-primary"
									onclick="location.href='${pageContext.request.contextPath}/ManageBook'">
							</div>
						</form>
					</div>


				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
	<%@ include file="footer.jsp"%>
	<!-- jQuery -->
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
	<!-- /.card -->