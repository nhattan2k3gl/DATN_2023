var app = angular.module("appBL", [])
app.controller("ctrlBL", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load product
		$http.get("/rest/binhluan").then(resp => {
			$scope.items = resp.data;
			console.log(resp.data);
		});

	}
	//khoi dau
	$scope.initialize();
	console.log("day la angular js")

	/*xoa */
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
				$http.delete(`/rest/binhluan/delete/${item.id_bl}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id_bl == item.id_bl);
					$scope.items.splice(index, 1);
					// Hiển thị thông báo xóa thành công
					Swal.fire({
						icon: 'success',
						title: 'Thành công!',
						text: 'Xóa sản phẩm thành công!'
					});
				}).catch(error => {
					// Hiển thị thông báo lỗi khi xóa
					Swal.fire({
						icon: 'error',
						title: 'Lỗi!',
						text: 'Lỗi xóa sản phẩm!'
					});
					console.log("Error", error);
				});
			}
		});
	};

});

