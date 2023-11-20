app.controller("product-ctrl",function($scope,$http){
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};

    
    //them sp moi
    
    //cap nhat sp
    
    //xoa sp
    
    //upload hinh
    $scope.imageChanged = function(files){
		var data = new FormData();
		data.append('file',files[0]);
		$http.post('/rest/upload/images',data,{
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