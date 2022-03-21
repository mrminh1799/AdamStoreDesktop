
CREATE DATABASE QLBH1
GO
USE QLBH1
GO

	if OBJECT_ID('Category') is not null --Bảng thể loại
	drop table Category
go
	create table Category(
		MaTheLoai nvarchar(50) primary key not null,
		TenTheLoai nvarchar(50) not null,
		TrangThai bit,
	)


	create table GiamGia(
		MaGiamGia  int identity primary key not null,
		PhanTram float,
		NgayBatDau date ,
		NgayKetThuc date,
		TrangThai bit
	)


	go
	if OBJECT_ID('Size') is not null -- Bảng size
	drop table Size
	create table Size(
		
		maSize int identity primary key not null, 
		TenSize nvarchar(50) not null,
		TrangThai bit,
		--foreign key (MaSanPhamChiTiet) references Product_Detail (MaSanPhamChiTiet) on update cascade on delete no action,
	)

go
	if OBJECT_ID('Color') is not null -- Bảng màu
	drop table Color
	create table Color(
		
		maMau int identity primary key not null, 
		temMau nvarchar(100) not null,
		TrangThai bit,
		--foreign key (MaSanPhamChiTiet) references Product_Detail (MaSanPhamChiTiet) on update cascade on delete no action,
	)

go 
	if OBJECT_ID('Product') is not null -- Bảng sản phẩm
	drop table Product
go
	create table Product(
	MaSanPham nvarchar(50) primary key not null,
	TenSanPham nvarchar(50) not null,
	MaTheLoai nvarchar(50) not null,
	--SoLuong int not null,
	--GiaNhap float not null,
	GhiChu nvarchar(250) null,
	TrangThai bit,
	IsDelete bit,
	MaGiamGia int,
	foreign key (MaTheLoai) references Category (MaTheLoai) on update cascade on delete no action,
	foreign key (MaGiamGia) references GiamGia (MaGiamGia) on update cascade on delete no action,
	)
	go


go 
	if OBJECT_ID('Product_Detail') is not null -- Bảng sản phẩm
	drop table Product_Detail
go
	create table Product_Detail(
	MaSanPhamChiTiet nvarchar(50)  primary key,
	TenSanPham nvarchar(50) not null,
	SoLuong int not null,
	GiaNhap float not null,
	GiaBan float not null,
	MaSanPham nvarchar(50),
	MaTheLoai nvarchar(50),
	maSize int,
	maMau int,
	TrangThai bit,

	foreign key (MaSanPham) references Product (MaSanPham),-- on update cascade on delete no action,
	--foreign key (MaTheLoai) references Category (MaTheLoai), --on update cascade on delete no action,
	foreign key (maMau) references Color (maMau),
	foreign key (maSize) references Size (maSize),
	)
	go


	if OBJECT_ID('Staff') is not null -- Bảng Nhân viên
	drop table Staff
go
	create table Staff(
	MaNhanVien nvarchar(50) primary key not null,
	TaiKhoan nvarchar(50) not null,
	TenNhanVien nvarchar(50) not null,
	MatKhau nvarchar(50) not null,
	GioiTinh bit not null,
	DiaChi nvarchar(100) not null,
	DienThoai nvarchar(20) not null,
	Email nvarchar(50) not null,
	NgaySinh date not null,
	VaiTro bit not null,
	TrangThai bit default(0),
	Avatar Nvarchar(250) 

	)
	go 
	if OBJECT_ID('Client') is not null -- Bảng Khách hàng
	drop table Client
go
	create table Client(
	MaKhachHang int identity primary key not null,
	TenKhachHang nvarchar(50) not null,
	DiaChi nvarchar(100) not null,
	DienThoai nvarchar(20) not null,
	TrangThai bit,
	)



go 
	if OBJECT_ID('Invoice') is not null -- Bảng Hoá đơn
	drop table Invoice
go
	create table Invoice(
	MaHoaDon int identity primary key not null,
	MaNhanVien nvarchar(50) not null,
	NgayBan date not null,
	MaKhachHang int not null,
	TongTien float not null,
	TrangThai nvarchar(50),
	GhiChu text,
	foreign key (MaNhanVien) references Staff (MaNhanVien) on update cascade on delete no action,
	foreign key (MaKhachHang) references Client (MaKhachHang) on update cascade on delete no action,
	)
go
	if OBJECT_ID('Detailed_Invoice') is not null -- Bảng Hoá đơn chi tiết
	drop table Detailed_Invoice
go
	create table Detailed_Invoice(
	    MaHDCT int identity primary key not null,
		MaHoaDon int  not null,
		MaSanPhamChiTiet nvarchar(50) not null,
		Anh nvarchar(250) null,
		SoLuong int not null,
		DonGia float not null,
		GiamGia float not null,
		Tong float not null,
		TrangThai nvarchar(50),
		GhiChu text,
		foreign key (MaHoaDon) references Invoice (MaHoaDon), 
		foreign key (MaSanPhamChiTiet) references Product_Detail (MaSanPhamChiTiet)

	)
			select * from staff
				ALTER TABLE Staff
  ADD Avatar Nvarchar(100)
--Nhập liệu
 Insert into Staff(MaNhanVien, TaiKhoan, TenNhanVien, MatKhau, GioiTinh, DiaChi, DienThoai, Email, NgaySinh, VaiTro, TrangThai) Values
 ('NV001','manhnt23', N'Nguyễn Tiến Mạnh','123456',0, N'Phường Xuân Đỉnh- Quận Bắc Từ Liêm- Hà Nội', '0962964504', 'manh23092001@gmail.com','2001-09-23',1,1)
 Insert into Staff(MaNhanVien, TaiKhoan, TenNhanVien, MatKhau, GioiTinh, DiaChi, DienThoai, Email, NgaySinh, VaiTro, TrangThai) Values
 ('NV002','ngapt26', N'Phạm Thị Nga','123456',1, N'Hà Nam- Hà Nội', '0923948218', 'nga26082002@gmail.com','2002-08-26',1,1)
 Insert into Staff(MaNhanVien, TaiKhoan, TenNhanVien, MatKhau, GioiTinh, DiaChi, DienThoai, Email, NgaySinh, VaiTro, TrangThai) Values
 ('NV003','tien01', N'Nguyễn Văn Tiến','123456',0, N'Hà Nội', '0819234021', 'tiennv0921@gmail.com','1998-09-09',1,1)
 Insert into Staff(MaNhanVien, TaiKhoan, TenNhanVien, MatKhau, GioiTinh, DiaChi, DienThoai, Email, NgaySinh, VaiTro, TrangThai) Values
 ('AD001','tue08', N'Hoàng Văn Tuệ','123456',0, N'Yên Bái','0368689239','kd.kendy9x@gmail.com','1998-08-08', 1, 1)
  Insert into Staff(MaNhanVien, TaiKhoan, TenNhanVien, MatKhau, GioiTinh, DiaChi, DienThoai, Email, NgaySinh, VaiTro, TrangThai) Values
 ('AD002','truong20', N'Trần Văn Trưởng','123456',0, N'Hà Nội','0965246780','truong2010@gmail.com','1999-10-20', 1, 1)
 select * from Staff
 -- Mau do
  Insert into Color(maMau, temMau, TrangThai) Values ('01',N'Đỏ', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('02',N'Cam đỏ', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('03',N'Cam', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('04',N'Vàng cam', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('05',N'Vàng', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('06',N'Đỏ hun', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('07',N'Đỏ cờ', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('08',N'Vàng đất', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('09',N'Vàng chanh', 0)
  SET IDENTITY_INSERT [dbo].[Color] ON
  -- Mau xanh
  Insert into Color(maMau, temMau, TrangThai) Values ('011',N'Vàng xanh', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('021',N'Xanh lá cây', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('031',N'Xanh lam', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('041',N'Lục', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('051',N'Xanh tím', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('061',N'Xanh nõn chuối', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('071',N'Xanh cô ban', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('081',N'Xanh ngọc', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('091',N'Xanh rêu', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('010',N'Xanh lá mạ', 0)

 -- mau toi
  Insert into Color(maMau, temMau, TrangThai) Values ('0111',N'Đen', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('0211',N'Ghi đá', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('0311',N'Ghi sáng', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('0411',N'Nâu', 0)

  Insert into Color(maMau, temMau, TrangThai) Values ('101',N'Đen', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('102',N'Ghi đá', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('103',N'Ghi sáng', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('104',N'Nâu', 0)


  Insert into Color(maMau, temMau, TrangThai) Values ('201',N'Trắng', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('301',N'Hồng đậm', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('302',N'Hồng phấn', 0)
  Insert into Color(maMau, temMau, TrangThai) Values ('302',N'Hồng phấn', 0)

 select * from Color


 Insert into Size(maSize, TenSize, TrangThai) Values ('36','S', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('37','M', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('38','L', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('39','XL', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('40','XXL', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('41','XXXL', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('27','27', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('28','28', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('29','29', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('30','30', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('31','31', 0)
 Insert into Size(maSize, TenSize, TrangThai) Values ('32','32', 0)

 SET IDENTITY_INSERT [dbo].[Size] OFF
 select * from Size
 DELETE FROM Size


 Insert into Category(MaTheLoai, TenTheLoai, TrangThai) Values ('Q012',N'Quần', 0)
 Insert into Category(MaTheLoai, TenTheLoai, TrangThai) Values ('A012',N'Áo', 0)
 select * from Category

 Insert into GiamGia(MaGiamGia, PhanTram, NgayBatDau,NgayKetThuc,TrangThai) 
 Values ('102',N'40','2021-11-20','2021-12-01',1)
 Insert into GiamGia(MaGiamGia, PhanTram, NgayBatDau,NgayKetThuc,TrangThai) 
 Values ('103',N'20','2021-11-20','2021-12-01',1)
 Insert into GiamGia(MaGiamGia, PhanTram, NgayBatDau,NgayKetThuc,TrangThai) 
 Values ('104',N'50','2021-11-20','2021-12-01',1)
 Insert into GiamGia(MaGiamGia, PhanTram, NgayBatDau,NgayKetThuc,TrangThai) 
 Values ('1012',N'40','2021-12-12','2021-12-20',1)
 Insert into GiamGia(MaGiamGia, PhanTram, NgayBatDau,NgayKetThuc,TrangThai) 
 Values ('1013',N'50','2021-12-12','2021-12-20',1)
 Insert into GiamGia(MaGiamGia, PhanTram, NgayBatDau,NgayKetThuc,TrangThai) 
 Values ('1014',N'20','2021-12-12','2021-12-20',1)
 SET IDENTITY_INSERT [dbo].[GiamGia] OFF
  select * from GiamGia


 Insert into Client(MaKhachHang, TenKhachHang, DiaChi,DienThoai,TrangThai) 
 Values ('1001',N'Nguyễn Tiến Hoàng',N'Hà Nội','0918234921',1)
 Insert into Client(MaKhachHang, TenKhachHang, DiaChi,DienThoai,TrangThai) 
 Values ('1002',N'Nguyễn Bảo Vi',N'Hà Nội','0918912831',1)
 Insert into Client(MaKhachHang, TenKhachHang, DiaChi,DienThoai,TrangThai) 
 Values ('1003',N'Phạm Khả Hân',N'Hà Nội','0912320283',1)
 Insert into Client(MaKhachHang, TenKhachHang, DiaChi,DienThoai,TrangThai) 
 Values ('1004',N'Nguyễn Gia Hân',N'Hà Nội','0911234184',1)
 Insert into Client(MaKhachHang, TenKhachHang, DiaChi,DienThoai,TrangThai) 
 Values ('1005',N'Nguyễn Văn An',N'Hà Nội','0917382192',1)
 Insert into Client(MaKhachHang, TenKhachHang, DiaChi,DienThoai,TrangThai) 
 Values ('1006',N'Nguyễn Ngô Hoàng Anh',N'Hà Nội','0965482671',1)
 Insert into Client(MaKhachHang, TenKhachHang, DiaChi,DienThoai,TrangThai) 
 Values ('1007',N'Nguyễn Quốc Việt',N'Hà Nội','0954351689',1)
 Insert into Client(MaKhachHang, TenKhachHang, DiaChi,DienThoai,TrangThai) 
 Values ('1008',N'Trần Thị Hương Ly',N'Hà Nội','0962586574',1)
 SET IDENTITY_INSERT [dbo].[Client] OFF

  select * from Client
  select * from GiamGia
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP001', N'Áo phông adidas','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP002', N'Áo thun ngắn tay','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP003', N'Áo thun dài tay','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP004', N'Áo thun ba lỗ','A012',1,1,2)

  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP005', N'Áo ba lỗ','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP006', N'Áo BOMBER','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP007', N'Áo khoác LV','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP008', N'Áo phao lông vũ','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP009', N'Áo khoác lông cừu','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP010', N'Áo BOMBER nỉ','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP111', N'Áo hoodie','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SP112', N'Áo hoodie nỉ','A012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPQ01', N'Quần baggy','Q012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPQ02', N'Quần kaki','Q012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPQ03', N'Quần jean trơn','Q012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPQ04', N'Quần jean rách gối','Q012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPQ05', N'Quần jean ống rộng','Q012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPQ06', N'Quần thể thao nỉ','Q012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPQ07', N'Quần jogger','Q012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPH01', N'Quần short','Q012',1,1,2)
  Insert into Product(MaSanPham, TenSanPham, MaTheLoai,TrangThai, IsDelete, MaGiamGia)
  Values ('SPH01', N'Quần đùi','Q012',1,1,2)
  select * from Product

  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA01', N'Áo phông adidas','10','50000','70000','SP001','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA02', N'Áo phông adidas','15','50000','70000','SP001','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA03', N'Áo phông adidas','20','50000','70000','SP001','A012','39','2',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA04', N'Áo phông adidas','10','50000','70000','SP001','A012','38','2',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA001', N'Áo thun ngắn tay','30','100000','130000','SP002','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA002', N'Áo thun ngắn tay','30','100000','130000','SP002','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA003', N'Áo thun ngắn tay','30','100000','130000','SP002','A012','39','2',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA004', N'Áo thun ngắn tay','30','100000','130000','SP002','A012','38','2',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA010', N'Áo thun dài tay','30','100000','130000','SP003','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA011', N'Áo thun dài tay','30','100000','130000','SP003','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA012', N'Áo thun ba lỗ','30','100000','130000','SP004','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA013', N'Áo thun ba lỗ','30','100000','130000','SP004','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA014', N'Áo ba lỗ','30','100000','130000','SP005','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA015', N'Áo ba lỗ','30','100000','130000','SP005','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA016', N'Áo BOMBER','30','100000','130000','SP006','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA017', N'Áo BOMBER','30','100000','130000','SP006','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA018', N'Áo khoác LV','30','100000','130000','SP007','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA019', N'Áo khoác LV','30','100000','130000','SP007','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA020', N'Áo phao lông vũ','30','100000','130000','SP008','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA021', N'Áo phao lông vũ','30','100000','130000','SP008','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA022', N'Áo hoodie','30','100000','130000','SP111','A012','39','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPA023', N'Áo hoodie','30','100000','130000','SP111','A012','38','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ001', N'Quần short','30','100000','130000','SPH01','Q012','28','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ002', N'Quần short','30','100000','130000','SPH01','Q012','29','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ003', N'Quần baggy','30','100000','130000','SPQ01','Q012','28','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ004', N'Quần baggy','30','100000','130000','SPQ01','Q012','29','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ005', N'Quần kaki','30','100000','130000','SPQ02','Q012','29','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ006', N'Quần kaki','30','100000','130000','SPQ02','Q012','28','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ007', N'Quần jean trơn','30','100000','130000','SPQ03','Q012','29','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ008', N'Quần jean trơn','30','100000','130000','SPQ03','Q012','28','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ009', N'Quần thể thao nỉ','30','100000','130000','SPQ06','Q012','29','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ010', N'Quần thể thao nỉ','30','100000','130000','SPQ06','Q012','28','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ011', N'Quần jogger','30','100000','130000','SPQ07','Q012','28','1',1)
  Insert into Product_Detail(MaSanPhamChiTiet, TenSanPham, SoLuong,GiaNhap, GiaBan, MaSanPham, MaTheLoai, maSize, maMau, TrangThai)
  Values ('SPQ012', N'Quần jogger','30','100000','130000','SPQ07','Q012','29','1',1)
  select * from Product_Detail
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('101','NV001','2021-11-22', '1001', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('102','NV001','2021-11-22', '1002', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('103','NV003','2021-11-22', '1003', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('104','NV003','2021-11-23', '1004', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('105','NV002','2021-11-23', '1005', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('106','NV001','2021-11-24', '1001', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('107','NV002','2021-11-24', '1003', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('108','NV003','2021-11-24', '1001', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('109','NV002','2021-11-24', '1002', '140000',N'Đã thanh toán','')
  Insert into Invoice(MaHoaDon, MaNhanVien, NgayBan,MaKhachHang, TongTien, TrangThai, GhiChu)
  Values ('110','NV003','2021-11-24', '1005', '140000',N'Đã thanh toán','')



  SET IDENTITY_INSERT [dbo].[Invoice] off
  SET IDENTITY_INSERT [dbo].[Client] ON
  select * from Invoice
  select * from Staff
  select * from Client

  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1101','101','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1102','102','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1103','103','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1104','104','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1105','105','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1106','106','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1107','107','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1108','108','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1109','109','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  Insert into Detailed_Invoice(MaHDCT,MaHoaDon, MaSanPhamChiTiet, Anh,SoLuong, DonGia, GiamGia, Tong, TrangThai, GhiChu)
  Values ('1110','110','SPA001','', '2', '70000','','140000',N'Đã thanh toán','')
  SET IDENTITY_INSERT [dbo].[Detailed_Invoice] off

  select * from Category
  select * from Invoice
  select * from Detailed_Invoice
  select * from GiamGia
  select * from Product
  select * from Product_Detail
  select* from Staff
  select* from Client
  select * from Color
  select * from Size

  --- Phần xoá nhân viên
  delete Staff where MaNhanVien = 

  Update Staff set Avatar = 1 where MaNhanVien = 'AD001'


  select * from Staff

  update Color set TrangThai = 1 where TrangThai =0

  update size set  TrangThai = 1 where TrangThai =0

