var app = angular.module("appTL", [])
app.controller("ctlTL", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load product
		$http.get("/rest/theloai").then(resp => {
			$scope.items = resp.data;
			console.log(resp.data);
			
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
		$scope.form = angular.copy(item);
		console.log(item)
		$('#edit-tab').tab('show');
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
					text: 'Thêm mới thành công!',
				});
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
					text: 'Cập nhật thành công!',
				});
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
				$http.delete(`/rest/theloai/delete/${item.id_tl}`).then(resp => {
					var index = $scope.items.findIndex(p => p.id_tl == item.id_tl);
					$scope.items.splice(index, 1);
					$scope.reset();
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

