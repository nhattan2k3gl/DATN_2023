var app = angular.module("appSP", [])
app.controller("ctrlSP", function($scope, $http, $filter, $window) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	// Format function for the date
	$scope.formatDate = function(date) {
		return $filter('date')(date, 'dd-MM-yyyy');
	};

	$scope.initDataTable = function(data) {
		var dataTable = $('#dataTableSP').DataTable({
			"language": {
				"url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
			},
			"searching": true,
			"paging": true,
			"data": data,
			"columns": [
				{ "data": "id_sp", "title": "ID Sản Phẩm" },
				{ "data": "ten", "title": "Tên" },
				{
					"data": "gia",
					"title": "Giá",
					"render": function(data, type, full, meta) {
						// Format the number and append "₫"
						return $filter('number')(data, 0) + ' ₫';
					}
				},
				{
					"data": "ngayxuatban",
					"title": "Ngày Xuất Bản",
					"render": function(data, type, full, meta) {
						return $scope.formatDate(data);
					}
				},
				{
					"data": "anh", "title": "Ảnh", "render": function(data, type, full, meta) {
						return '<img src="/assets/products/' + data + '" alt="" width="50px" height="50px">';
					}
				},
				{
					"data": "anh1", "title": "Ảnh 1", "render": function(data, type, full, meta) {
						return '<img src="/assets/products/' + data + '" alt="" width="50px" height="50px">';
					}
				},
				{
					"data": "anh2", "title": "Ảnh 2", "render": function(data, type, full, meta) {
						return '<img src="/assets/products/' + data + '" alt="" width="50px" height="50px">';
					}
				},
				{ "data": "soluongsp", "title": "Số Lượng Sản Phẩm" },
				{ "data": "theloai.tentheloai", "title": "Tên Thể Loại" },
				{
					"data": null, "title": "Xóa", "render": function(data, type, full, meta) {
						return '<a class="text-white bg-danger delete-link">Xóa</a>';
					}
				},
				{
					"data": null, "title": "Sửa", "render": function(data, type, full, meta) {
						return '<a class="text-white bg-info edit-link">Sửa</a>';
					}
				}
				// Thêm các cột khác nếu cần
			],
			"drawCallback": function(settings) {
				// Khi DataTable vẽ lại, gắn sự kiện ng-click cho các liên kết xóa và sửa
				$('.delete-link').on('click', function() {
					var rowData = dataTable.row($(this).closest('tr')).data();
					$scope.delete(rowData);
				});

				$('.edit-link').on('click', function() {
					var rowData = dataTable.row($(this).closest('tr')).data();
					$scope.edit(rowData);

				});
			}
		});
	};

	$scope.initialize = function() {
		//load product
		$http.get("/rest/sanpham").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.ngayxuatban = new Date(item.ngayxuatban)
			})
			$scope.initDataTable($scope.items);
		});

	}
	//khoi dau
	$scope.initialize();
	//xoa form
	$scope.reset = function() {
		$scope.form = {
			createDate: new Date(),
			image: 'cloud-upload.jpg',
			ivailable: true,
		};
	}
	//hien thi len form
	$scope.edit = function(item) {
		$scope.$apply(function() {
			$scope.form = angular.copy(item);
			$('#edit-tab').tab('show');
		});
	}

	$scope.updateTable = function(updatedItem) {
		// Find the index of the updated item in the $scope.items array
		var index = $scope.items.findIndex(item => item.id_sp === updatedItem.id_sp);
		// If the item is found, update it
		if (index !== -1) {
			$scope.items[index] = updatedItem;
		} else {
			// If the item is not found, it's a new item, so push it to the array
			$scope.items.push(updatedItem);
		}
	};
	//them sp moi
	$scope.create = function() {
		var item = angular.copy($scope.form);
		// Perform validation before submitting the data
		if ($scope.validation(item)) {
			$http.post(`/rest/sanpham`, item).then(resp => {
				resp.data.createDate = new Date(resp.data.ngayxuatban);
				// Update the table
				$scope.updateTable(resp.data);
				$scope.reset();
				Swal.fire({
					icon: 'success',
					title: 'Thành công!',
					text: 'Thêm mới thành công!',
					showConfirmButton: false // Ẩn nút xác nhận
				});

				setTimeout(() => {
					$window.location.reload();
				}, 3000); // Tải lại trang sau 3 giây
			}).catch(error => {
				Swal.fire({
					icon: 'error',
					title: 'Thất bại!',
					text: 'Lỗi thêm mới sản phẩm!',
				});
				console.log("Error", error);
			});
		} else {
			// Show an error message or handle validation error
			console.log("Validation failed");
		}
	};


	// Function to perform validation
	$scope.validation = function(item) {
		// Kiểm tra xem các trường bắt buộc đã được điền đầy đủ chưa
		if (!item.ten || !item.gia || !item.theloai || !item.theloai.id_tl || !item.soluongsp || !item.mota) {
			// Hiển thị thông báo lỗi
			Swal.fire({
				icon: 'error',
				title: 'Thất bại!',
				text: 'Vui lòng điền đầy đủ thông tin cho các trường bắt buộc.',
			});
			return false; // Trả về false khi có lỗi
		}

		// Có thể thêm logic kiểm tra bổ sung ở đây

		// Nếu tất cả các kiểm tra hợp lệ
		return true; // Trả về true khi không có lỗi
	};
	//cap nhat sp
	$scope.update = function() {
		var item = angular.copy($scope.form);
		// Perform validation before submitting the data
		if ($scope.validation(item)) {
			$http.post(`/rest/sanpham`, item).then(resp => {
				resp.data.createDate = new Date(resp.data.ngayxuatban);

				$scope.updateTable(resp.data);
				$scope.reset();

				Swal.fire({
					icon: 'success',
					title: 'Thành công!',
					text: 'Cập nhật thành công!',
					showConfirmButton: false // Ẩn nút xác nhận
				});
				setTimeout(() => {
					$window.location.reload();
				}, 3000); // Tải lại trang sau 3 giây
			}).catch(error => {
				Swal.fire({
					icon: 'error',
					title: 'Thất bại!',
					text: 'Lỗi cập nhật sản phẩm!',
				});
				console.log("Error", error);
			});
		} else {
			// Show an error message or handle validation error
			console.log("Validation failed");
		}
	}
	//xoa sp
	$scope.delete = function(item) {
		// Hiển thị thông báo xác nhận với SweetAlert2
		Swal.fire({
			title: 'Xác nhận xóa',
			text: 'Bạn có chắc muốn xóa sản phẩm này?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: 'Xóa',
			cancelButtonText: 'Hủy bỏ'
		}).then((result) => {
			if (result.isConfirmed) {
				// Nếu người dùng xác nhận xóa, thực hiện yêu cầu DELETE
				$http.delete(`/rest/sanpham/${item.id_sp}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id_sp == item.id_sp);
					$scope.items.splice(index, 1);
					$scope.reset();
					// Hiển thị thông báo xóa thành công
					Swal.fire({
						icon: 'success',
						title: 'Thành công!',
						text: 'Xóa sản phẩm thành công!',
						showConfirmButton: false // Ẩn nút xác nhận
					});
					var dataTable = $('#dataTableSP').DataTable();
					dataTable.clear().rows.add($scope.items).draw();
				}).catch(error => {
					// Kiểm tra nếu là lỗi khóa chính hoặc khóa ngoại
					if (error.status === 500 && error.data && error.data.message) {
						// Hiển thị thông báo lỗi cụ thể
						Swal.fire({
							icon: 'error',
							title: 'Lỗi!',
							text: error.data.message
						});
					} else {
						// Hiển thị thông báo lỗi chung khi xóa
						Swal.fire({
							icon: 'error',
							title: 'Lỗi!',
							text: 'Lỗi xóa sản phẩm!'
						});
					}
					console.log("Error", error);
				});
			}
		});
	};

	//upload hinh
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.anh = resp.data.name;
		}).catch(error => {
			alert("Upload Image False!");
			console.log("Error", error);
		})
	}
	//upload hinh1
	$scope.imageChanged1 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.anh1 = resp.data.name;
		}).catch(error => {
			alert("Upload Image False!");
			console.log("Error", error);
		})
	}
	//upload hinh 2
	$scope.imageChanged2 = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/products', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.anh2 = resp.data.name;
		}).catch(error => {
			alert("Upload Image False!");
			console.log("Error", error);
		})
	}


});

