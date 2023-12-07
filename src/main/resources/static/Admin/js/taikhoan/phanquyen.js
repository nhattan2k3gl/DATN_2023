var app = angular.module("appPQ", [])
app.controller("ctrlPQ", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initDataTable = function(data) {
		var dataTable = $('#dataTablePQ').DataTable({
			"language": {
				"url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
			},
			"searching": true,
			"paging": true,
			"data": data,
			"columns": [
				{ "data": "taikhoan.email", "title": "Email" },
				{ "data": "taikhoan.hovaten", "title": "Họ và tên" },
				{
					"data": "taikhoan.anh",
					"title": "Ảnh",
					"render": function(data, type, full, meta) {
						return '<img src="/assets/accounts/' + data + '" alt="" width="50px" height="50px">';
					}
				},
				{ "data": "taikhoan.diachi", "title": "Địa chỉ" },
				{ "data": "vaitro.tenvaitro", "title": "Vai trò" },
				{
					"data": null, "title": "Xóa", "render": function(data, type, full, meta) {
						return '<a class="text-white bg-danger delete-link">Xóa</a>';
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
		$http.get("/Rest/PhanQuyen1").then(resp => {
			$scope.items = resp.data;
			$scope.initDataTable($scope.items);
		});

	}
	//khoi dau
	$scope.initialize();

	/*xoa */
	$scope.delete = function(item) {
		// Hiển thị thông báo xác nhận với SweetAlert2
		Swal.fire({
			title: 'Xác nhận xóa',
			text: 'Bạn có chắc muốn xóa tài khoản này?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: 'Xóa',
			cancelButtonText: 'Hủy bỏ'
		}).then((result) => {
			if (result.isConfirmed) {
				// Nếu người dùng xác nhận xóa, thực hiện yêu cầu DELETE
				
				$http.delete(`/Rest/PhanQuyen/Delete/${item.id_pq}`).then(resp => {
					
					/*xoa trên bảng*/
					var index = $scope.items.findIndex(p => p.id_pq == item.id_pq);
					$scope.items.splice(index, 1);
					var dataTable = $('#dataTablePQ').DataTable();
					dataTable.clear().rows.add($scope.items).draw();
					
					/*xóa bên tài khoản*/
					
					$http.delete(`/Rest/TaiKhoan/${item.taikhoan.email}`).then(resp =>{
						
					}).catch(Error =>{
						Swal.fire({
							icon: 'error',
							title: 'Lỗi!',
							text: 'Lỗi xóa tài khoản!'
						});
					})
					
					// Hiển thị thông báo xóa thành công
					Swal.fire({
						icon: 'success',
						title: 'Thành công!',
						text: 'Xóa tài khoản thành công!',
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
							text: 'Lỗi xóa tài khoản!'
						});
					}
					console.log("Error", error);
				});
			}
		});
	};

});

