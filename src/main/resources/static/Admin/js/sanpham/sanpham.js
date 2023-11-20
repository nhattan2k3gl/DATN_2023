var app = angular.module("appSP",[])
app.controller("ctrlSP",function($scope,$http){
    $scope.items = [];
    $scope.cates = [];
    $scope.form = {};
    
    console.log("ham angular js");
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

