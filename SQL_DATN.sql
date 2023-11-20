create database DATN_2023_SQL
go
use DATN_2023_SQL
go

create table VaiTro(
	ID_VT int PRIMARY KEY IDENTITY(1,1),
	TenVaiTro varchar(50) not null
)
create table PhanQuyen(
	ID_PQ int PRIMARY KEY IDENTITY(1,1),
	Email varchar(50) not null, 
	ID_VT int not null
)
create table TaiKhoan(
	Email varchar(50) primary key,
	MatKhau varchar(50) not null,
	HoVaTen nvarchar(100) not null,
	Anh varchar(100),
	DiaChi nvarchar(200)
)

ALTER TABLE TaiKhoan
ALTER COLUMN MatKhau varchar(200) not null;


create table HoaDon(
	ID_HD int PRIMARY KEY IDENTITY(1,1),
	NgayTaoHoaDon date not null,
	DiaChi nvarchar(200) not null,
	Email varchar(50) not null
)
create table HoaDonChiTiet(
	ID_HDCT int PRIMARY KEY IDENTITY(1,1),
	ID_HD int not null,
	ID_SP int not null,
	SoLuong int not null
)

create table SanPham(
	ID_SP int  primary key IDENTITY(1,1),
	Ten nvarchar(100) not null,
	Gia float not null,
	NgayXuatBan date not null,
	Anh varchar(100),
	Anh1 varchar(100),
	Anh2 varchar(100),
	SoLuongSP int not null,
	ID_TL int not null,
	MoTa nvarchar(1000),
	
)
create table TheLoai(
	ID_TL int PRIMARY KEY IDENTITY(1,1),
	TenTheLoai nvarchar(50) not null
)

create table BinhLuan (
	ID_BL int PRIMARY KEY IDENTITY(1,1),
	Email varchar(50) not null,
	ID_SP int not null,
	BinhLuan nvarchar(200),
	DanhGia int
)


create table MaGiamGia(
	ID_MGG int PRIMARY KEY IDENTITY(1,1),
	Email varchar(50) not null,
	ID_SP int not null,
	PhanTram float not null
	
 )

ALTER TABLE PhanQuyen
ADD CONSTRAINT fk_Email_PQ
FOREIGN KEY (Email) 
REFERENCES  TaiKhoan(Email);

ALTER TABLE PhanQuyen
ADD CONSTRAINT fk_ID_VT_PQ
FOREIGN KEY (ID_VT) 
REFERENCES  VaiTro(ID_VT);

ALTER TABLE HoaDon
ADD CONSTRAINT fk_Email_HD
FOREIGN KEY (Email) 
REFERENCES  TaiKhoan(Email);

ALTER TABLE HoaDonChiTiet
ADD CONSTRAINT fk_ID_HD_HDCT
FOREIGN KEY (ID_HD) 
REFERENCES  HoaDon(ID_HD);

ALTER TABLE HoaDonChiTiet
ADD CONSTRAINT fk_ID_SP_HDCT
FOREIGN KEY (ID_SP) 
REFERENCES  SanPham(ID_SP);

ALTER TABLE SanPham
ADD CONSTRAINT fk_ID_TL_SP
FOREIGN KEY (ID_TL) 
REFERENCES  TheLoai(ID_TL);

ALTER TABLE BinhLuan
ADD CONSTRAINT fk_ID_SP_BL
FOREIGN KEY (ID_SP) 
REFERENCES  SanPham(ID_SP);

ALTER TABLE BinhLuan
ADD CONSTRAINT fk_Email_BL
FOREIGN KEY (Email) 
REFERENCES  TaiKhoan(Email);

ALTER TABLE MaGiamGia
ADD CONSTRAINT fk_Email_MGG
FOREIGN KEY (Email) 
REFERENCES  TaiKhoan(Email);

ALTER TABLE MaGiamGia
ADD CONSTRAINT fk_ID_SP_MGG
FOREIGN KEY (ID_SP) 
REFERENCES  SanPham(ID_SP);

insert into TaiKhoan values
	('nhattan@gmail.com','$2a$10$2n/7KPuN4OJtOuE0IKxu2eaJPmRgi8icWNgIJvzK2Ahop7ZbxJSqK',N'Đoàn Nhât Tân','tan.png',N'333 Lê Văn Sĩ'),
	('anhkhoa@gmail.com','$2a$10$2n/7KPuN4OJtOuE0IKxu2eaJPmRgi8icWNgIJvzK2Ahop7ZbxJSqK',N'Bùi Anh Khoa','khoa.png',N'1072 Lê Đức Thọ'),
	('toanthang@gmail.com','$2a$10$2n/7KPuN4OJtOuE0IKxu2eaJPmRgi8icWNgIJvzK2Ahop7ZbxJSqK',N'Trương Toàn Thắng','thang.png',N'134 Nguyễn Văn Quá'),
	('anhhao@gmail.com','$2a$10$2n/7KPuN4OJtOuE0IKxu2eaJPmRgi8icWNgIJvzK2Ahop7ZbxJSqK',N'Nguyễn Anh Hào','khoa.png',N'1072 Lê Đức Thọ'),
	('hoainam@gmail.com','$2a$10$2n/7KPuN4OJtOuE0IKxu2eaJPmRgi8icWNgIJvzK2Ahop7ZbxJSqK',N'Nguyễn Hoài Nam','nam.png',N'1072 Lê Đức Thọ')


insert into TaiKhoan values
	('nguyenhoainam@gmail.com','$2a$10$2n/7KPuN4OJtOuE0IKxu2eaJPmRgi8icWNgIJvzK2Ahop7ZbxJSqK',N'Nguyễn Hoài Nam','nam.png',N'1072 Lê Đức Thọ')

select * from TaiKhoan

insert into VaiTro values
	('ADMIN'),
	('USER'),
	('GUEST')
select * from VaiTro

Insert into PhanQuyen values
	('nhattan@gmail.com',3),
	('anhhao@gmail.com',1),
	('anhkhoa@gmail.com',3),
	('hoainam@gmail.com',2)


Insert into PhanQuyen values
	('nguyenhoainam@gmail.com',1)
select * from PhanQuyen


Insert into HoaDon values 
	(CAST('2023-1-1' AS Date),N'123 Lê Đức Thọ','nhattan@gmail.com'),
	(CAST('2023-2-1' AS Date),N'123 Nguyễn Văn Quá','anhkhoa@gmail.com'),
	(CAST('2023-3-1' AS Date),N'188 Dương Thị Mười','anhhao@gmail.com'),
	(CAST('2023-4-1' AS Date),N'2 Thống Nhất','hoainam@gmail.com'),
	(CAST('2023-5-1' AS Date),N'3 Đình Quá','toanthang@gmail.com')
select * from HoaDon

insert into TheLoai values
	(N'Học Thuật'),
	(N'Văn học'),
	(N'Tự lực, Kỹ năng'),
	(N'Khoa học viễn tưởng'),
	(N'Trẻ em và Thiếu nhi'),
	(N'Sách tham khảo'),
	(N'Tôn giáo và Tâm linh'),
	(N'Kinh doanh và Tài chính'),
	(N'Kinh dị'),
	(N'Tài liệu tham khảo'),
	(N'Lãng Mạn')
select * from TheLoai
	

insert into SanPham values
	(N'Cây Cam Ngọt Của Tôi',134.00, CAST('2015-6-21' AS Date),'caycam.png','caycam1.png','caycam2.png',150,1,N'“Vị chua chát của cái nghèo hòa trộn với vị ngọt ngào khi khám phá ra những điều khiến cuộc đời này đáng sống”, đây là dòng đầu tiên mình đọc được khi chạm tay vào cuốn sách này. Hóa ra sự ngây ngô là cách những đứa trẻ tự vệ trong thế giới đầy nghiệt ngã. Đọc Cây cam ngọt của tôi và nếm được đủ các cung bậc cảm xúc qua những câu nói day dứt đến ám ảnh'),
	(N'Địa đàng trần gian',265.00, CAST('2014-08-15' AS Date),'diadang.png','diadang1.png' ,'diadang2.png',234,7,N'Thomas More đã viết Utopia trong những năm từ 1515 đến 1516, thời điểm trí tưởng tượng của con người bị khuyấy đảo trước sự mở rộng đột ngột của các quan niệm về thế giới, và miêu tả của Amerigo Vespucci về những cuộc du hành về phía Tây của ông được in lần đầu tiên vào năm 1507, vẫn còn mới nguyên trong trí óc các học giả uyên thâm. More đã tưởng tượng ra một Raphael Hythloday-cái tên phiên từ tiếng Hy Lạp sang có nghĩa là “Thạo những chuyện tầm phào” – một lữ khách đồng hành cùng Vespucci nhưng không trở về sau chuyến du hành thứ ba, mà rời bỏ các bạn hữu để một thân một mình lang thang nơi xứ sở xa lạ. Rồi anh ta đã tìm thấy hòn đảo Utopia-gốc Hy Lạp sáng có nghĩa là “Không ở nơi đâu”. Và đó là một địa đàng lý tưởng, được More vẽ nên với một phong cách châm biếm sâu cay lối lãnh đạo quốc sự kiểu Châu Âu, mà cụ thể là kiểu Anh.' ),
	(N'RANH GIỚI ĐỊNH MỆNH',231.00, CAST('2000-04-16' AS Date),'dinhmenh.png','dinhmenh1.png','dinhmenh2.png',222,7,N'“Ranh giới định mệnh bắt đầu như một câu chuyện hành động sống còn kịch tính, sau đó là một cuộc đào sâu khám phá về cảm xúc và kích thích tư duy về nhân loại và ý nghĩa của việc đưa ra những lựa chọn đạo đức khó khăn trong hoàn cảnh khó khăn nhất.' ),
	(N'Biệt Thự LONGBOURN',221.00, CAST('2002-05-17' AS Date),'bietthu.png','bietthu1.png','bietthu2.png',200,2,N' Biệt thự Longbourn đào sâu vào quá khứ của các nhân vật này, và kéo dài sau cả cái kết hạnh phúc của Kiêu hãnh và Định kiến; nhưng ở những sự kiện trùng lặp trong cả hai cuốn sách, chúng được mô tả chính xác như trong cuốn sách của Jane Austen. Khi một bữa ăn được dọn ra trong Kiêu hãnh và Định kiến, nó đã được chuẩn bị ở gian bếp của Biệt thự Longbourn.' ),
	(N'Hoa Vẫn Nở Mỗi Ngày',321.00, CAST('2021-06-20' AS Date),'hoavan.png','hoavan1.png','hoavan2.png',120,1,N'Violette Toussaint sống mà như chết. Người phụ nữ ấy bị mẹ đẻ bỏ rơi ngay khi vừa lọt lòng, tới lượt cô con gái nhỏ mà cô yêu thương nhất lại bỏ cô mà đi trong một tai nạn thảm khốc, rồi cả đến người chồng một ngày kia cũng không còn ở lại bên cô. Cuộc đời của một nhân viên gác chắn nơi ga xép với những chuyến tàu nhỏ mỗi ngày đến rồi đi ' ),
	( N'Tết Ở Làng Địa Ngục', 253.39, CAST('2009-07-21' AS Date), 'tet.png','tet1.png','tet2.png',123,9,N'Ngôi làng ấy vốn dĩ không có tên, nhưng những người nơi đây mặc định chốn này là địa ngục. Dân trong làng không ai dám tự ý băng rừng thoát khỏi làng, càng không biết thế giới bên ngoài rộng lớn như thế nào, bởi lẽ họ sợ người khác sẽ biết rằng bản thân mình vốn là hậu duệ của băng cướp khét tiếng ở truông nhà Hồ dưới thời chúa Nguyễn ở Đàng Trong.'),
	( N'Sói Già Phố WALL', 55.13, CAST('2008-04-30' AS Date), 'soigia.png','soigia1.png','soigia2.png', 600,10,N'Sói già phố wall bao gồm 2 phần, nội dung đều nói về quá trình Jordan Belfort từ một kẻ vô danh trở thành huyền thoại trong ngành môi giới chứng khoán cũng như câu chuyện đằng sau của cuộc khủng hoảng tín dụng ở Mỹ. Jordan - con sói già khủng khiếp nhất đội lốt cừu, đây cũng có lẽ là biệt danh xứng đáng nhất được gắn anh. Một gã đàn ông chỉ ba mươi mốt tuổi nhưng luôn mang suy nghĩ của ông lão 60 tuổi.'),
	( N'Kiểm Thử Phần Mềm', 66.55, CAST('2015-02-28' AS Date), 'KTPM.png', 'KTPM1.png','KTPM2.png',250,10,N'Kiểm thử phần mềm là phương pháp kiểm tra xem sản phẩm phần mềm đó trên thực tế có phù hợp với các yêu cầu đã đặt ra hay không, và đảm bảo rằng không có lỗi hay khiếm khuyết. Nó bao gồm việc kiểm tra, phân tích, quan sát và đánh giá các khía cạnh khác nhau của sản phẩm. Người kiểm thử phần mềm (Tester) sử dụng kết hợp các công cụ thủ công và tự động. Sau khi tiến hành kiểm thử, Tester báo cáo kết quả cho team phát triển. Mục đích là xác định các lỗi, khiếm khuyết hoặc các yêu cầu còn thiếu so với yêu cầu thực tế.')
insert into sanpham values 	( N'Oan Hồn Quỷ Có Độc',76.78, CAST('2013-05-19' AS Date), 'quycodoc.png','quycodoc2.png','quycodoc3.png', 150,9,N'ในหมู่บ้านเล็กๆ ท่านศิลาในเขตเทือกเขาของประเทศไทย มีสะพานโบราณทำจากไม้ที่เชื่อมต่อระหว่างฝั่งสองของลำน้ำที่งดงาม. ชาวบ้านมักเล่าเรื่องสยองขวัญเกี่ยวกับสะพานนี้.ตามตำนาน, ในคืนที่มีแสงจันทร์สว่าง วิญญาณผีที่ลึกลับจะปรากฏที่สะพานนี้. เขาคือวิญญาณของหญิงสาวสวยที่สูญหายไปหลายปีที่ผ่านมา. เขาถูกบอกว่าตายจากการจมน้ำที่นี่หลังจากการทะเลาะวิวาทร้ายแรงกับคู่สาว.')

select * from SanPham

insert into HoaDonChiTiet values
	(1,1,2),
	(2,2,6),
	(3,3,6),
	(4,4,7),
	(5,6,4)

select * from HoaDonChiTiet

insert into BinhLuan values 
	('hoainam@gmail.com',1,N'Sách Hay Vãi Ò',3),
	('anhhao@gmail.com',2,N'Tuyệt',5),
	('anhkhoa@gmail.com',3,N'Đọc cũng được',4),
	('hoainam@gmail.com',4,N'Bình Thường',3),
	('toanthang@gmail.com',5,N'Tệ',2),
	('anhhao@gmail.com',6,N'Um',4),
	('anhhao@gmail.com',7,N'Um',5),
	('anhhao@gmail.com',8,N'Um',5)
select * from BinhLuan

insert into MaGiamGia values
	('nhattan@gmail.com',1,0.1),
	('anhkhoa@gmail.com',2,0.1),
	('hoainam@gmail.com',3,0.1)
select * from MaGiamGia

select *
 from HoaDonChiTiet


 select SUM(sanpham.gia*HoaDonChiTiet.SoLuong) as N'Tổng Tiền'
 from HoaDonChiTiet join SanPham on HoaDonChiTiet.ID_HD=sanpham.ID_SP
