var app = angular.module("appBL", [])


app.controller("ctrlBL", function($scope, $http, $timeout) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initDataTable = function(data) {
		var dataTable = $('#dataTableBL').DataTable({
			"language": {
				"url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Vietnamese.json"
			},
			"searching": true,
			"paging": true,
			"data": data,
			"columns": [
				{ "data": "id_bl", "title": "ID" },
				{ "data": "binhluan", "title": "Bình luận" },
				{ "data": "danhgia", "title": "Đánh giá" },
				{ "data": "taikhoan.email", "title": "Email" },
				{ "data": "sanpham.id_sp", "title": "ID Sản phẩm" },
				{ "data": "sanpham.ten", "title": "Tên sản phẩm" },
				{ "data": "sanpham.theloai.tentheloai", "title": "Thể loại" },
				{
					"data": null, "title": "Xóa", "render": function(data, type, full, meta) {
						return '<a class="text-white bg-danger delete-link">Xóa</a>';
					}
				}
				// Thêm các cột khác nếu cần
			],
			"drawCallback": function(settings) {
				// Khi DataTable vẽ lại, gắn sự kiện ng-click cho các liên kết xóa
				$('.delete-link').on('click', function() {
					var rowData = dataTable.row($(this).closest('tr')).data();
					$scope.delete(rowData);
				});
			}
		});
	};

	$scope.initialize = function() {
		// Load product data
		$http.get("/rest/binhluan").then(resp => {
			$scope.items = resp.data;
			console.log(resp.data);
			$scope.initDataTable($scope.items);
		});

	}

	//khoi dau
	$scope.initialize();
	/*$scope.initDataTable();*/

	/*xoa */
	$scope.delete = function(item) {
		// Hiển thị thông báo xác nhận với SweetAlert2
		Swal.fire({
			title: 'Xác nhận xóa',
			text: 'Bạn có chắc muốn xóa bình luận này?',
			icon: 'warning',
			showCancelButton: true,
			confirmButtonText: 'Xóa',
			cancelButtonText: 'Hủy bỏ'
		}).then((result) => {
			if (result.isConfirmed) {
				// Nếu người dùng xác nhận xóa, thực hiện yêu cầu DELETE
				$http.delete(`/rest/binhluan/delete/${item.id_bl}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id_bl == item.id_bl);
					$scope.items.splice(index, 1);
					// Hiển thị thông báo xóa thành công
					Swal.fire({
						icon: 'success',
						title: 'Thành công!',
						text: 'Xóa bình luận thành công!'
					});
					
					// Vẽ lại DataTables
					var dataTable = $('#dataTableBL').DataTable();
					dataTable.clear().rows.add($scope.items).draw();
					
				}).catch(error => {
					// Hiển thị thông báo lỗi khi xóa
					Swal.fire({
						icon: 'error',
						title: 'Lỗi!',
						text: 'Lỗi xóa bình luận!'
					});
					console.log("Error", error);
				});
			}
		});
	};


});

