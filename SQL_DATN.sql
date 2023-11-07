create database DATN_2023_SQL
go
use DATN_2023_SQL
go

create table VaiTro(
	ID_VT varchar(9) primary key,
	TenVaiTro varchar(50) not null
)
create table PhanQuyen(
	ID_PQ int PRIMARY KEY IDENTITY(1,1),
	Email varchar(50) not null, 
	ID_VT varchar(9) not null
)
create table TaiKhoan(
	Email varchar(50) primary key,
	MatKhau varchar(50) not null,
	HoVaTen nvarchar(100) not null,
	Anh varchar(100),
	DiaChi nvarchar(200)
)
create table HoaDon(
	ID_HD varchar(9) primary key,
	NgayTaoHoaDon date not null,
	DiaChi nvarchar(200) not null,
	Email varchar(50) not null
)
create table HoaDonChiTiet(
	ID_HDCT int PRIMARY KEY IDENTITY(1,1),
	ID_HD varchar(9) not null,
	ID_SP varchar(9) not null,
	SoLuong int not null
)

create table SanPham(
	ID_SP varchar(9) primary key,
	Ten nvarchar(100) not null,
	Gia float not null,
	NgayXuatBan date not null,
	Anh varchar(100),
	SoLuongSP int not null,
	ID_TL varchar(9) not null
)
create table TheLoai(
	ID_TL varchar(9) primary key,
	TenTheLoai nvarchar(50) not null
)

create table BinhLuan (
	ID_BL int PRIMARY KEY IDENTITY(1,1),
	Email varchar(50) not null,
	ID_SP varchar(9) not null,
	BinhLuan nvarchar(200),
	DanhGia int
)


create table MaGiamGia(
	ID_MGG int PRIMARY KEY IDENTITY(1,1),
	Email varchar(50) not null,
	ID_SP varchar(9) not null,
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
	('nhattan@gmail.com','123',N'Đoàn Nhât Tân','anh1.jpg',N'333 Lê Văn Sĩ'),
	('anhkhoa@gmail.com','123',N'Bùi Anh Khoa','anh2jpg',N'1072 Lê Đức Thọ'),
	('toanthang@gmail.com','123',N'Trương Toàn Thắng','anh3.jpg',N'134 Nguyễn Văn Quá'),
	('anhhao@gmail.com','123',N'Nguyễn Anh Hào','anh4.jpg',N'1072 Lê Đức Thọ'),
	('hoainam@gmail.com','123',N'Nguyễn Hoài Nam','anh5.jpg',N'1072 Lê Đức Thọ')
select * from TaiKhoan

insert into VaiTro values
	('VT_001','KhachHang'),
	('VT_002','NhanVien'),
	('VT_003','QuanLy')
select * from VaiTro

Insert into PhanQuyen values
	('nhattan@gmail.com','VT_003'),
	('anhhao@gmail.com','VT_001'),
	('anhkhoa@gmail.com','VT_002'),
	('hoainam@gmail.com','VT_002')
select * from PhanQuyen

Insert into HoaDon values 
	('HD_001','2023/1/1',N'123 Lê Đức Thọ','nhattan@gmail.com'),
	('HD_002','2022/2/2',N'123 Nguyễn Văn Quá','anhkhoa@gmail.com'),
	('HD_003','2021/3/1',N'188 Dương Thị Mười','anhhao@gmail.com'),
	('HD_004','2013/3/1',N'2 Thống Nhất','hoainam@gmail.com'),
	('HD_005','2021/1/8',N'3 Đình Quá','toanthang@gmail.com')
select * from HoaDon

insert into TheLoai values
	('TL_001',N'Học Thuật'),
	('TL_002',N'Văn học'),
	('TL_003',N'Tự lực, Kỹ năng'),
	('TL_004',N'Khoa học viễn tưởng'),
	('TL_005',N'Trẻ em và Thiếu nhi'),
	('TL_006',N'Sách tham khảo'),
	('TL_007',N'Tôn giáo và Tâm linh'),
	('TL_008',N'Kinh doanh và Tài chính')
select * from TheLoai
	

insert into SanPham values
	('SP_001',N'Cây Cam Ngọt Của Tôi',134.00, CAST('2023-04-30' AS Date),'caycam.png',12,'TL_001' ),
	('SP_002',N'Địa đàng trần gian',134.00, CAST('2023-04-30' AS Date),'diadang.png' ,20,'TL_007' ),
	('SP_003',N'RANH GIỚI ĐỊNH MỆNH',134.00, CAST('2023-04-30' AS Date),'dinhmenh.png',22,'TL_007' ),
	('SP_004',N'Biệt Thự LONGBOURN',134.00, CAST('2023-04-30' AS Date),'bietthu.png',9,'TL_002' ),
	('SP_005',N'Hoa Vẫn Nở Mỗi Ngày',134.00, CAST('2023-04-30' AS Date),'hoavan.png',2,'TL_001' ),
	('SP_006', N'Portrait Photography', 19.39, CAST('2023-04-30' AS Date), 'product-item1.jpg', 1,'TL_001'),
	('SP_007', N'Great Travel At Desert', 35.13, CAST('2023-04-30' AS Date), 'product-item2.jpg', 4,'TL_003'),
	('SP_008', N'The Lady Beauty Scarlett', 11.55, CAST('2023-02-25' AS Date), 'product-item3.jpg', 3,'TL_005'),
	('SP_009', N'Once Upon A Time', 36.78, CAST('2023-05-19' AS Date), 'product-item4.jpg', 7,'TL_006'),
	('SP_0010', N'Way Of Happiness', 32.51, CAST('2023-05-19' AS Date), 'product-item5.jpg', 6,'TL_002')
select * from SanPham

insert into HoaDonChiTiet values
	('HD_001','SP_001',1),
	('HD_002','SP_001',2),
	('HD_003','SP_001',3),
	('HD_004','SP_001',4),
	('HD_005','SP_001',1)
select * from HoaDonChiTiet

insert into BinhLuan values 
	('hoainam@gmail.com','SP_001',N'Sách Hay Vãi Ò',3),
	('anhhao@gmail.com','SP_001',N'Tuyệt',5),
	('anhkhoa@gmail.com','SP_002',N'Đọc cũng được',4),
	('hoainam@gmail.com','SP_002',N'Bình Thường',3),
	('toanthang@gmail.com','SP_003',N'Um',2)
select * from BinhLuan

insert into MaGiamGia values
	('nhattan@gmail.com','SP_001',0.1),
	('anhkhoa@gmail.com','SP_001',0.1),
	('hoainam@gmail.com','SP_001',0.1)
select * from MaGiamGia
