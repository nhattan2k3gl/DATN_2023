var app = angular.module("app",[])
app.controller("ctrl",function($scope,$http){
	
	$http.get("/Rest/PhanQuyen").then(resp=> {
		$scope.db = resp.data;
		console.log(resp.data);
	})
	
	$scope.index_of = function(email,id_vt)
	{
		
		return $scope.db.phanquyen
			.findIndex(a => a.taikhoan.email == email  && a.vaitro.id_vt == id_vt);
			
	}
	$scope.update = function(id_pq,email,matkhau, hovaten, diachi, anh, id_vt, tenvaitro){
		var index = $scope.index_of(email,id_vt);
		if(index>0){
			var id = $scope.db.phanquyen[index].id_pq;
			$http.delete(`/Rest/PhanQuyen/Delete/${id}`).then(resq =>{
				$scope.db.phanquyen.splice(index,1);
			})
		}
		else{
			var authority ={
				
				taikhoan:{email:email, matkhau:matkhau, hovaten:hovaten, diachi:diachi, anh:anh},
				vaitro:{id_vt:id_vt, tenvaitro:tenvaitro}
			};
			console.log(authority);
			$http.post('/Rest/PhanQuyen/Create',authority).then(resq=>{
				$scope.db.phanquyen.push(resq.data)
		})
		}
		
	}
	
	
	
	
});