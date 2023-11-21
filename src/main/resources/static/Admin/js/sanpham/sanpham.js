var app = angular.module("appSP", [])
app.controller("ctrlSP", function($scope, $http) {
	$scope.items = [];
	$scope.cates = [];
	$scope.form = {};

	$scope.initialize = function() {
		//load product
		$http.get("/rest/sanpham").then(resp => {
			$scope.items = resp.data;
			console.log(resp.data);
			$scope.items.forEach(item => {
				console.log(item.ngayxuatban)
				item.ngayxuatban = new Date(item.ngayxuatban)
			})
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
		console.log(item.mota)
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
		$http.delete(`/rest/sanpham/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1);
			$scope.reset();
			alert("Xóa sản phẩm thành công!");
		}).catch(error => {
			alert("Lỗi xóa sản phẩm!");
			console.log("Error", error);
		});
	}

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

