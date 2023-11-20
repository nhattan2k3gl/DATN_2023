console.log('hu');
var giaTriTC = document.getElementById("TB").textContent;
if (giaTriTC === "ThanhCong") {
        Swal.fire({
            icon: 'success',
            title: 'Thành công!',
            confirmButtonText: 'OK'
        });
    } 

if (giaTriTC === "LoiFK") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            text: 'Lỗi FOREIGN KEY REFERENCES',
            confirmButtonText: 'OK'
        });
    } 

if (giaTriTC === "Loi") {
        Swal.fire({
            icon: 'error',
            title: 'Lỗi!',
            confirmButtonText: 'OK'
        });
    }  

