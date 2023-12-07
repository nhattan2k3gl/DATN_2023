var app = angular.module("appTL", [])
app.controller("ctlTL", function($scope, $http, $filter, $window) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initDataTable = function(data) {
		var dataTable = $('#dataTableTL').DataTable({
			"language": {
				"url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
			},
			"searching": true,
			"paging": true,
			"data": data,
			"columns": [
				{ "data": "id_tl", "title": "ID Thể loại" },
				{ "data": "tentheloai", "title": "Tên thể loại" },
				{
					"data": null,
					"title": "Xóa",
					"render": function(data, type, full, meta) {
						return '<a class="text-white bg-danger delete-link">Xóa</a>';
					}
				},
				{
					"data": null, "title": "Sửa", "render": function(data, type, full, meta) {
						return '<a class="text-white bg-info edit-link">Sửa</a>';
					}
				}
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
		$http.get("/rest/theloai").then(resp => {
			$scope.items = resp.data;
			console.log(resp.data);
			$scope.initDataTable($scope.items);

		});

	}
	//khoi dau
	$scope.initialize();
	console.log("day la angular js")

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
		var index = $scope.items.findIndex(item => item.id_tl === updatedItem.id_tl);
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
			$http.post(`/rest/theloai`, item).then(resp => {
				// Update the table
				$scope.updateTable(resp.data);

				Swal.fire({
					icon: 'success',
					title: 'Thành công!',
					text: 'Thêm mới thể loại thành công!',
					showConfirmButton: false // Ẩn nút xác nhận
				});
				setTimeout(() => {
					$window.location.reload();
				}, 3000); // Tải lại trang sau 3 giây
			}).catch(error => {
				Swal.fire({
					icon: 'error',
					title: 'Thất bại!',
					text: 'Lỗi thêm mới thể loại!',
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

		if (!item.tentheloai) {
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
			$http.post(`/rest/theloai`, item).then(resp => {

				$scope.updateTable(resp.data);


				Swal.fire({
					icon: 'success',
					title: 'Thành công!',
					text: 'Cập nhật thể loại thành công!',
					showConfirmButton: false // Ẩn nút xác nhận
				});
				setTimeout(() => {
					$window.location.reload();
				}, 3000); // Tải lại trang sau 3 giây
			}).catch(error => {
				Swal.fire({
					icon: 'error',
					title: 'Thất bại!',
					text: 'Lỗi cập nhật thể loại!',
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
			text: 'Bạn có chắc muốn xóa thể loại này?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: 'Xóa',
			cancelButtonText: 'Hủy bỏ'
		}).then((result) => {
			if (result.isConfirmed) {
				// Nếu người dùng xác nhận xóa, thực hiện yêu cầu DELETE
				$http.delete(`/rest/theloai/${item.id_tl}`).then(resp => {

					//// xoa trên cột
					var index = $scope.items.findIndex(p => p.id_tl == item.id_tl);
					$scope.items.splice(index, 1);
					$scope.reset();
					var dataTable = $('#dataTableTL').DataTable();
					dataTable.clear().rows.add($scope.items).draw();

					// Hiển thị thông báo xóa thành công
					Swal.fire({
						icon: 'success',
						title: 'Thành công!',
						text: 'Xóa thể loại thành công!',
						showConfirmButton: false // Ẩn nút xác nhận
					});
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
							text: 'Lỗi xóa thể loại!'
						});
					}
					console.log("Error", error);
				});
			}
		});
	};






});

