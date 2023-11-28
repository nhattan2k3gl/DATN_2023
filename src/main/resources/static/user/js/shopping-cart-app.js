const app = angular.module("appCart", []);


app.controller("cart-ctrl", function($scope, $http){
	// quản lý giỏ hàng
	var $cart = $scope.cart = {
        items: [],
        add(id_sp){ // thêm sản phẩm vào giỏ hàng
        	var item = this.items.find(item => item.id_sp == id_sp);
            if(item){
                item.soluongsp++;
                this.saveToLocalStorage();
            }
            else{
            	$http.get(`/rest/products/${id_sp}`).then(resp => {
            		resp.data.soluongsp = 1;
            		this.items.push(resp.data);
            		this.saveToLocalStorage();
            	})
            }
        },
        remove(id_sp){ // xóa sản phẩm khỏi giỏ hàng
        	var index = this.items.findIndex(item => item.id_sp == id_sp);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        clear(){ // Xóa sạch các mặt hàng trong giỏ
            this.items = []
            this.saveToLocalStorage();
        },
        amt_of(item){ // tính thành tiền của 1 sản phẩm
        	return item.gia * item.soluongsp;
        },
        get count(){ // tính tổng số lượng các mặt hàng trong giỏ
            return this.items
            	.map(item => item.soluongsp)
                .reduce((total, soluongsp) => total += soluongsp, 0);
        },
        get amount(){ // tổng thành tiền các mặt hàng trong giỏ
            return this.items
            	.map(item => item.soluongsp * item.gia)
                .reduce((total, amt) => total += amt, 0);
        },
        saveToLocalStorage(){ // lưu giỏ hàng vào local storage
        	var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        loadFromLocalStorage(){ // đọc giỏ hàng từ local storage
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
        
        
    }
    
   
	
	$cart.loadFromLocalStorage();
	
	// Đặt hàng
	$scope.order = {
			diachi: "",
			ngaytaohoadon: new Date(),
			taikhoan : {email: $("#email").val()},
			get hoadonchitiet(){
				return $cart.items.map(item => {
					return {
						sanpham:{id_sp: item.id_sp},
						gia: item.gia * item.soluongsp,
						soluong: item.soluongsp
					}
				});
			},
			purchase(){
				var order = angular.copy(this);
				// Thực hiện đặt hàng
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
			}
	}
	
	// search
	$scope.search = {
    purchase() {
        var tentheloai = $("#tentheloai").text(); 

        $http.get("/rest/shop", { params: { tentheloai: tentheloai } }).then(resp => {
			$scope.items = resp.data;
        }).catch(error => {
            alert("Đặt hàng lỗi!");
            console.log(error);
        });
    }
}

	
})