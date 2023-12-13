const app = angular.module("appCart", []);

app.controller("cart-ctrl", function($scope, $http, $window, $location) {
	// quản lý giỏ hàng
	var $cart = $scope.cart = {
		items: [],
		add(id_sp) { // thêm sản phẩm vào giỏ hàng
			var item = this.items.find(item => item.id_sp == id_sp);
			if (item) {
				item.soluongsp++;
				this.saveToLocalStorage();
			}
			else {
				$http.get(`/rest/products/${id_sp}`).then(resp => {
					resp.data.soluongsp = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		remove(id_sp) { // xóa sản phẩm khỏi giỏ hàng
			var index = this.items.findIndex(item => item.id_sp == id_sp);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		clear() { // Xóa sạch các mặt hàng trong giỏ
			this.items = []
			this.saveToLocalStorage();
		},
		amt_of(item) { // tính thành tiền của 1 sản phẩm
			return item.gia * item.soluongsp;
		},
		get count() { // tính tổng số lượng các mặt hàng trong giỏ
			return this.items
				.map(item => item.soluongsp)
				.reduce((total, soluongsp) => total += soluongsp, 0);
		},
		get amount() { // tổng thành tiền các mặt hàng trong giỏ
			return this.items
				.map(item => item.soluongsp * item.gia)
				.reduce((total, amt) => total += amt, 0);
		},
		saveToLocalStorage() { // lưu giỏ hàng vào local storage
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		loadFromLocalStorage() { // đọc giỏ hàng từ local storage
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
		}


	}
	//load lên form
	$cart.loadFromLocalStorage();

	// Đặt hàng
	$scope.order = {
		diachi: "",
		ngaytaohoadon: new Date(),
		taikhoan: { email: $("#email").val() },
		get hoadonchitiet() {
			return $cart.items.map(item => {
				return {
					sanpham: { id_sp: item.id_sp },
					gia: item.gia * item.soluongsp,
					soluong: item.soluongsp
				}
			});
		},
		purchase(savedOrder) {
			var order = savedOrder;
			// Thực hiện đặt hàng
			console.log("đây là dữ liệu order truyền xuống", order)
			$http.post("/rest/orders", order).then(resp => {
				Swal.fire({
					icon: 'success',
					title: 'Thành công!',
					text: 'Đặt hàng thành công!'
				}).then(() => {
					// Chuyển hướng sau khi SweetAlert đã được đóng
					location.href = "/order/success";
					$cart.clear();
				});
			}).catch(error => {
				Swal.fire({
					icon: 'error',
					title: 'Thât Bại!',
					text: 'Lỗi khi đặt hàng!'
				})
				console.log(error);
			});
		},
	}

	var $palpay = $scope.pay = {
		payment() {
			$scope.vnd = 0;
			$scope.amount = $cart.amount;
			$scope.usd = 0;

			var orderData = angular.copy($scope.order);
			localStorage.setItem('savedOrder', JSON.stringify(orderData));

			$http.get('https://v6.exchangerate-api.com/v6/f4817124b03dc83695d4eb33/latest/USD')
				.then(function(response) {
					// Lấy dữ liệu từ phản hồi của API
					$scope.vnd = response.data.conversion_rates.VND;
					// Tính toán giá trị USD sau khi nhận được dữ liệu từ API
					$scope.usd = $scope.amount / $scope.vnd; // Làm tròn đến 2 chữ số thập phân
					$http.get('/rest/palpay/' + $scope.usd)
						.then(function(response) {
							$scope.urlPalPay = response.data.redirectUrl;
							$window.location.href = $scope.urlPalPay;
						})
						.catch(function(error) {
							// Xử lý lỗi nếu có
							console.log(error);
						});
				})
				.catch(function(error) {
					// Xử lý lỗi nếu có
					console.log(error);
				});
		},
		paySuccessOrCannel() {
			try {
				var paymentId = new URLSearchParams(window.location.search).get("paymentId");
				var payerId = new URLSearchParams(window.location.search).get("PayerID");
				var token = new URLSearchParams(window.location.search).get("token");
				if (paymentId && payerId) {
					var savedOrderData = localStorage.getItem('savedOrder');
					// Kiểm tra xem savedOrderData có tồn tại hay không
					if (savedOrderData) {
						// Chuyển đổi chuỗi JSON thành đối tượng JavaScript
						var savedOrder = JSON.parse(savedOrderData);
						$scope.order.purchase(savedOrder);
						// Sau khi sử dụng dữ liệu, xóa nó khỏi Local Storage
						localStorage.removeItem('savedOrder');
						console.log("sau khi xóa saveOrder", savedOrder)
					} else {
						console.log("Không có dữ liệu lưu trữ trong Local Storage.");
					}
				}
				if (token && payerId == null && paymentId == null) {
					Swal.fire({
						icon: 'error',
						title: 'Thất bại!',
						text: 'Thanh Toán thất bại',
					});
					var savedOrderData = localStorage.getItem('savedOrder');
					var cannelOrder = JSON.parse(savedOrderData);
					localStorage.removeItem('cannelOrder');
				}
			}
			catch (errors) {
				console.log(errors)
			}
			return null
		}
	};
	///load lên form
	$palpay.paySuccessOrCannel();

	$scope.formBL = {};
	$scope.comment = function() {

		if ($scope.myForm.$valid) {
			Swal.fire({
				icon: 'warning',
				title: 'Cảnh báo!',
				text: 'Vui lòng nhập đủ dữ liệu!',
			});
		}

		$scope.formBL.binhluan = angular.element(document.getElementById('binhluan')).val();
		$scope.formBL.taikhoan = {
			"email": document.getElementById('email').getAttribute('value')
		};
		$scope.formBL.sanpham = {
			"id_sp": parseInt(document.getElementById('id_sp').getAttribute('value'), 10)
		};
		console.log($scope.formBL);

		$http.post(`/rest/binhluan`, $scope.formBL).then(resp => {
			Swal.fire({
				icon: 'success',
				title: 'Thành công!',
				text: 'Bình Luận Thành Công',
				timer: 3000, // Thời gian hiển thị thông báo (3 giây)
				showConfirmButton: false // Ẩn nút xác nhận
			});

			setTimeout(() => {
				$window.location.reload();
			}, 3000); // Tải lại trang sau 3 giây
		}).catch(error => {
			Swal.fire({
				icon: 'error',
				title: 'Thất bại!',
				text: 'Bình Luận Thất Bại',
			});
			console.log("Error", error);
		});
	};


})