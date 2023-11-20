var app = angular.module("appSP",[])
app.controller("ctrlSP",function($scope,$http){
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};
    
    $scope.initialize = function(){
        //load product
        $http.get("/rest/sanpham").then(resp => {
            $scope.items = resp.data;
            console.log(resp.data);
            $scope.items.forEach(item => {
                item.ngayxuatban = new Date(item.ngayxuatban)
            })
        });
        
    }
    //khoi dau
    $scope.initialize();
    console.log("day la angular js")

    //xoa form
    $scope.reset= function(){
		$scope.form = {
		createDate:new Date(),
		image:'cloud-upload.jpg',
		ivailable:true,
		};
    }
    //hien thi len form
    $scope.edit = function(item){
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    }
    //them sp moi
    $scope.create = function(){
	    var item = angular.copy($scope.form);
        $http.post(`/rest/sanpham`,item).then(resp => {
            resp.data.createDate = new Date(resp.data.createDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm mới thành công!");
        }).catch(error => {
            alert("Lỗi thêm mới sản phẩm!");
            console.log("Error",error);
        });
    }
    //cap nhat sp
    $scope.update = function(){
	    var item = angular.copy($scope.form);
        $http.put(`/rest/sanpham/${item.id}`,item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            $scope.reset();
            alert("Cập nhật sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm!");
            console.log("Error",error);
        });
    }
    //xoa sp
    $scope.delete = function(item){
        $http.delete(`/rest/sanpham/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index,1);
            $scope.reset();
            alert("Xóa sản phẩm thành công!");
        }).catch(error => {
            alert("Lỗi xóa sản phẩm!");
            console.log("Error",error);
        });
    }
    
    //upload hinh
    $scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/products',data,{
            transformRequest: angular.identity,
            headers: {'Content-Type':undefined}
        }).then(resp => {
            $scope.form.image = resp.data.name;
        }).catch(error => {
            alert("Upload Image False!");
            console.log("Error",error);
        })
    }
    
});

