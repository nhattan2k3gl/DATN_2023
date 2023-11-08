$(document).ready(function() {
	$('#FromBL').submit(function(event) {
		event.preventDefault();
		var isValid = true;
		// Kiểm tra lỗi cho từng trường
		$(this).find('input[required]').each(function() {
			var name = $(this).attr('name');
			var value = $(this).val();

			if (value.trim() === '') {
				$('#' + name + '-error').text('Trường này không được để trống.');
				isValid = false;
			} else {
				$('#' + name + '-error').text('');
			}
		});
		// Nếu form hợp lệ, gửi form và hiển thị thông báo thành công
		if (isValid) {
			// Thay đổi thông báo này tùy theo kịch bản của bạn
			Swal.fire({
				icon: 'success',
				title: 'Thành công!',
				text: 'Form đã được gửi thành công.',
				confirmButtonText: 'OK'
			});
			$(this).unbind('submit').submit();
		} else {
			// Hiển thị thông báo lỗi
			Swal.fire({
				icon: 'error',
				title: 'Lỗi!',
				text: 'Form không hợp lệ. Vui lòng kiểm tra lại!',
				confirmButtonText: 'OK'
			});
		}
	});

	var hiddenPasswordElement = document.getElementById("hiddenMatKhau").value;
	var matkhau = document.getElementById("matkhauInput");
	matkhau.value = hiddenPasswordElement;
	// Thay đổi thông báo này tùy theo kịch bản của bạn
	/*Swal.fire({
		icon: 'success',
		title: 'Thành công!',
		text: 'Form đã được gửi thành công.',
		confirmButtonText: 'OK'
	});*/
});