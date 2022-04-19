USE [master]
GO
/****** Object:  Database [QLBH]    Script Date: 3/21/2022 10:14:44 AM ******/
CREATE DATABASE [QLBH]
GO
ALTER DATABASE [QLBH] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QLBH].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QLBH] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QLBH] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QLBH] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QLBH] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QLBH] SET ARITHABORT OFF 
GO
ALTER DATABASE [QLBH] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QLBH] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QLBH] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QLBH] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QLBH] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QLBH] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QLBH] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QLBH] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QLBH] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QLBH] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QLBH] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QLBH] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QLBH] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QLBH] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QLBH] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QLBH] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QLBH] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QLBH] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QLBH] SET  MULTI_USER 
GO
ALTER DATABASE [QLBH] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QLBH] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QLBH] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QLBH] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [QLBH] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [QLBH] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [QLBH] SET QUERY_STORE = OFF
GO
USE [QLBH]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[MaTheLoai] [nvarchar](50) NOT NULL,
	[TenTheLoai] [nvarchar](50) NOT NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaTheLoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Client]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Client](
	[MaKhachHang] [int] IDENTITY(1,1) NOT NULL,
	[TenKhachHang] [nvarchar](50) NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[DienThoai] [nvarchar](20) NOT NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Color]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Color](
	[maMau] [int] IDENTITY(1,1) NOT NULL,
	[temMau] [nvarchar](100) NOT NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[maMau] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Detailed_Invoice]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Detailed_Invoice](
	[MaHDCT] [int] IDENTITY(1,1) NOT NULL,
	[MaHoaDon] [int] NOT NULL,
	[MaSanPhamChiTiet] [nvarchar](50) NOT NULL,
	[Anh] [nvarchar](250) NULL,
	[SoLuong] [int] NOT NULL,
	[DonGia] [float] NOT NULL,
	[GiamGia] [float] NOT NULL,
	[Tong] [float] NOT NULL,
	[TrangThai] [nvarchar](50) NULL,
	[GhiChu] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHDCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GiamGia]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GiamGia](
	[MaGiamGia] [int] IDENTITY(1,1) NOT NULL,
	[PhanTram] [float] NULL,
	[NgayBatDau] [date] NULL,
	[NgayKetThuc] [date] NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaGiamGia] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Invoice]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Invoice](
	[MaHoaDon] [int] IDENTITY(1,1) NOT NULL,
	[MaNhanVien] [nvarchar](50) NOT NULL,
	[NgayBan] [date] NOT NULL,
	[MaKhachHang] [int] NOT NULL,
	[TongTien] [float] NOT NULL,
	[TrangThai] [nvarchar](50) NULL,
	[GhiChu] [text] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[MaSanPham] [nvarchar](50) NOT NULL,
	[TenSanPham] [nvarchar](50) NOT NULL,
	[MaTheLoai] [nvarchar](50) NOT NULL,
	[GhiChu] [nvarchar](250) NULL,
	[TrangThai] [bit] NULL,
	[IsDelete] [bit] NULL,
	[MaGiamGia] [int] NULL,
	[anh] [nchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSanPham] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product_Detail]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product_Detail](
	[MaSanPhamChiTiet] [nvarchar](50) NOT NULL,
	[TenSanPham] [nvarchar](50) NOT NULL,
	[SoLuong] [int] NOT NULL,
	[GiaNhap] [float] NOT NULL,
	[GiaBan] [float] NOT NULL,
	[MaSanPham] [nvarchar](50) NULL,
	[MaTheLoai] [nvarchar](50) NULL,
	[maSize] [int] NULL,
	[maMau] [int] NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[MaSanPhamChiTiet] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Size]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Size](
	[maSize] [int] IDENTITY(1,1) NOT NULL,
	[TenSize] [nvarchar](50) NOT NULL,
	[TrangThai] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[maSize] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Staff]    Script Date: 3/21/2022 10:14:44 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Staff](
	[MaNhanVien] [nvarchar](50) NOT NULL,
	[TaiKhoan] [nvarchar](50) NOT NULL,
	[TenNhanVien] [nvarchar](50) NOT NULL,
	[MatKhau] [nvarchar](50) NOT NULL,
	[GioiTinh] [bit] NOT NULL,
	[DiaChi] [nvarchar](100) NOT NULL,
	[DienThoai] [nvarchar](20) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[NgaySinh] [date] NOT NULL,
	[VaiTro] [bit] NOT NULL,
	[TrangThai] [bit] NULL,
	[Avatar] [nchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[MaNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Category] ([MaTheLoai], [TenTheLoai], [TrangThai]) VALUES (N'A012', N'Áo', 1)
INSERT [dbo].[Category] ([MaTheLoai], [TenTheLoai], [TrangThai]) VALUES (N'Q012', N'Quần', 1)
GO
SET IDENTITY_INSERT [dbo].[Client] ON 

INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (1, N'Nguyễn Tiến Hoàng', N'Hà Nội', N'0918234921', 1)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (2, N'Nguyễn Bảo Vi', N'Hà Nội', N'0918912831', 1)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (3, N'Phạm Khả Hân', N'Hà Nội', N'0912320283', 1)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (4, N'Nguyễn Gia Hân', N'Hà Nội', N'0911234184', 1)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (5, N'Nguyễn Văn An', N'Hà Nội', N'0917382192', 1)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (6, N'Nguyễn Ngô Hoàng Anh', N'Hà Nội', N'0965482671', 1)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (7, N'Nguyễn Quốc Việt', N'Hà Nội', N'0954351689', 1)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (8, N'Trần Thị Hương Ly', N'Hà Nội', N'0962586574', 1)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (9, N'Minh', N'Ha noi', N'0987654321', NULL)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (10, N'Minh', N'Ha noi', N'0987654321', NULL)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (11, N'hai', N'Ha noi', N'0987654321', NULL)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (12, N'Ba si', N'Ha noi', N'0987654321', NULL)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (13, N'Hai si', N'Ha noi', N'0987654321', NULL)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (14, N'4', N'Ha noi', N'0987654321', NULL)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (15, N'5', N'Ha noi', N'0987654321', NULL)
INSERT [dbo].[Client] ([MaKhachHang], [TenKhachHang], [DiaChi], [DienThoai], [TrangThai]) VALUES (16, N'Trung', N'Ha noi', N'0987654321', NULL)
SET IDENTITY_INSERT [dbo].[Client] OFF
GO
SET IDENTITY_INSERT [dbo].[Color] ON 

INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (1, N'Đỏ', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (2, N'Cam đỏ', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (3, N'Cam', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (4, N'Vàng cam', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (5, N'Vàng', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (6, N'Đỏ hun', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (7, N'Đỏ cờ', 0)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (8, N'Vàng đất', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (9, N'Vàng chanh', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (10, N'Xanh lá mạ', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (11, N'Vàng xanh', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (21, N'Xanh lá cây', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (31, N'Xanh lam', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (41, N'Lục', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (51, N'Xanh tím', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (61, N'Xanh nõn chuối', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (71, N'Xanh cô ban', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (81, N'Xanh ngọc', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (91, N'Xanh rêu', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (101, N'Đen', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (102, N'Ghi đá', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (103, N'Ghi sáng', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (104, N'Nâu', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (111, N'Đen', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (201, N'Trắng', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (211, N'Ghi đá', 1)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (301, N'Hồng đậm', 0)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (302, N'Hồng phấn', 0)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (311, N'Ghi sáng', 0)
INSERT [dbo].[Color] ([maMau], [temMau], [TrangThai]) VALUES (411, N'Nâu', 0)
SET IDENTITY_INSERT [dbo].[Color] OFF
GO
SET IDENTITY_INSERT [dbo].[Detailed_Invoice] ON 

INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (12, 11, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (13, 12, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (14, 13, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (15, 14, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (16, 15, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (17, 15, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (18, 16, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (19, 11, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (20, 11, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (21, 11, N'SPA001', N'', 2, 70000, 0, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (22, 21, N'SPA011', NULL, 0, 130000, 0, 0, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (23, 21, N'SPA011', NULL, 0, 130000, 0, 0, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (24, 21, N'SPA012', NULL, 0, 130000, 0, 0, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (25, 22, N'SPA02', NULL, 12, 70000, 0, 840000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (26, 22, N'SPA019', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (27, 22, N'SPA019', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (28, 23, N'SPA02', NULL, 12, 70000, 0, 840000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (29, 23, N'SPA019', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (30, 23, N'SPA019', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (31, 24, N'SPA014', NULL, 12, 130000, 0, 1560000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (32, 24, N'SPA02', NULL, 2, 70000, 0, 140000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (33, 24, N'SPA02', NULL, 2, 70000, 0, 140000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (34, 25, N'SPA02', NULL, 1, 70000, 0, 70000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (35, 25, N'SPA021', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (36, 25, N'SPA021', NULL, 3, 130000, 0, 390000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (37, 26, N'SPA021', NULL, 12, 130000, 0, 1560000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (38, 26, N'SPA022', NULL, 12, 130000, 0, 1560000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (39, 26, N'SPA017', NULL, 3, 130000, 0, 390000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (40, 26, N'SPA017', NULL, 3, 130000, 0, 390000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (41, 27, N'SPA019', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (42, 27, N'SPA022', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (43, 28, N'SPA021', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (44, 28, N'SPA021', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (45, 28, N'SPA021', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
INSERT [dbo].[Detailed_Invoice] ([MaHDCT], [MaHoaDon], [MaSanPhamChiTiet], [Anh], [SoLuong], [DonGia], [GiamGia], [Tong], [TrangThai], [GhiChu]) VALUES (46, 28, N'SPA021', NULL, 2, 130000, 0, 260000, N'Đã bán', NULL)
SET IDENTITY_INSERT [dbo].[Detailed_Invoice] OFF
GO
SET IDENTITY_INSERT [dbo].[GiamGia] ON 

INSERT [dbo].[GiamGia] ([MaGiamGia], [PhanTram], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (1, 40, CAST(N'2021-11-20' AS Date), CAST(N'2021-12-01' AS Date), 1)
INSERT [dbo].[GiamGia] ([MaGiamGia], [PhanTram], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (2, 20, CAST(N'2021-11-20' AS Date), CAST(N'2021-12-01' AS Date), 1)
INSERT [dbo].[GiamGia] ([MaGiamGia], [PhanTram], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (3, 50, CAST(N'2021-11-20' AS Date), CAST(N'2021-12-01' AS Date), 1)
INSERT [dbo].[GiamGia] ([MaGiamGia], [PhanTram], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (4, 40, CAST(N'2021-12-12' AS Date), CAST(N'2021-12-20' AS Date), 1)
INSERT [dbo].[GiamGia] ([MaGiamGia], [PhanTram], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (5, 50, CAST(N'2021-12-12' AS Date), CAST(N'2021-12-20' AS Date), 1)
INSERT [dbo].[GiamGia] ([MaGiamGia], [PhanTram], [NgayBatDau], [NgayKetThuc], [TrangThai]) VALUES (6, 20, CAST(N'2021-12-12' AS Date), CAST(N'2021-12-20' AS Date), 1)
SET IDENTITY_INSERT [dbo].[GiamGia] OFF
GO
SET IDENTITY_INSERT [dbo].[Invoice] ON 

INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (11, N'NV001', CAST(N'2021-11-22' AS Date), 1, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (12, N'NV001', CAST(N'2021-11-22' AS Date), 1, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (13, N'NV003', CAST(N'2021-11-22' AS Date), 1, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (14, N'NV003', CAST(N'2021-11-23' AS Date), 1, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (15, N'NV002', CAST(N'2021-11-23' AS Date), 2, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (16, N'NV001', CAST(N'2021-11-24' AS Date), 2, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (17, N'NV002', CAST(N'2021-11-24' AS Date), 2, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (18, N'NV003', CAST(N'2021-11-24' AS Date), 2, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (19, N'NV002', CAST(N'2021-11-24' AS Date), 2, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (20, N'NV003', CAST(N'2021-11-24' AS Date), 2, 140000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (21, N'AD001', CAST(N'2022-03-13' AS Date), 9, 0, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (22, N'AD001', CAST(N'2022-03-13' AS Date), 10, 920000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (23, N'AD001', CAST(N'2022-03-13' AS Date), 11, 920000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (24, N'AD001', CAST(N'2022-03-13' AS Date), 12, 1104000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (25, N'AD001', CAST(N'2022-03-13' AS Date), 13, 562000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (26, N'AD001', CAST(N'2022-03-13' AS Date), 14, 2808000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (27, N'AD001', CAST(N'2022-03-13' AS Date), 15, 364000, N'Đã thanh toán', N'')
INSERT [dbo].[Invoice] ([MaHoaDon], [MaNhanVien], [NgayBan], [MaKhachHang], [TongTien], [TrangThai], [GhiChu]) VALUES (28, N'AD001', CAST(N'2022-03-13' AS Date), 16, 0, N'Đã hủy', N'Nguyên nhân: Khach khong lay nua, Ngày h?y: 2022-03-13, T?ng ti?n: 832000.0')
SET IDENTITY_INSERT [dbo].[Invoice] OFF
GO
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'9fdcf77a-ee20-4d74-9d3b-d4c5c1fcc0bd', N'123', N'Q012', NULL, 1, NULL, NULL, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP001', N'Áo phông adidas', N'A012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP002', N'Áo thun ngắn tay', N'A012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP003', N'Áo thun dài tay', N'A012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP004', N'Áo thun ba lỗ', N'A012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP005', N'Áo ba lỗ', N'A012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP006', N'Áo BOMBER', N'A012', NULL, 1, 1, 2, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP007', N'Áo khoác LV', N'A012', NULL, 1, 1, 2, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP008', N'Áo phao lông vũ', N'A012', NULL, 1, 1, 2, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP009', N'Áo khoác lông cừu', N'A012', NULL, 1, 1, 2, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP010', N'Áo BOMBER nỉ', N'A012', NULL, 1, 1, 2, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP111', N'Áo hoodie', N'A012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SP112', N'Áo hoodie nỉ', N'A012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SPH01', N'Quần short', N'Q012', NULL, 1, 1, 3, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SPQ01', N'Quần baggy', N'Q012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SPQ02', N'Quần kaki', N'Q012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SPQ03', N'Quần jean trơn', N'Q012', NULL, 1, 1, 1, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SPQ04', N'Quần jean rách gối', N'Q012', NULL, 1, 1, 2, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SPQ05', N'Quần jean ống rộng', N'Q012', NULL, 1, 1, 2, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SPQ06', N'Quần thể thao nỉ', N'Q012', NULL, 1, 1, 2, NULL)
INSERT [dbo].[Product] ([MaSanPham], [TenSanPham], [MaTheLoai], [GhiChu], [TrangThai], [IsDelete], [MaGiamGia], [anh]) VALUES (N'SPQ07', N'Quần jogger', N'Q012', NULL, 1, 1, 2, NULL)
GO
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'886ef707-d06e-4eb6-b06d-e291537880d9', N'bo di nhe', 23, 123, 3444, N'9fdcf77a-ee20-4d74-9d3b-d4c5c1fcc0bd', NULL, 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'ee3010ea-e681-4768-9b42-f6b0fe4858b2', N'bo di nhe', 12, 12, 3, N'9fdcf77a-ee20-4d74-9d3b-d4c5c1fcc0bd', NULL, 4, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA001', N'Áo thun ngắn tay', 30, 100000, 130000, N'SP002', N'A012', 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA002', N'Áo thun ngắn tay', 30, 100000, 130000, N'SP002', N'A012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA003', N'Áo thun ngắn tay', 30, 100000, 130000, N'SP002', N'A012', 3, 2, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA004', N'Áo thun ngắn tay', 30, 100000, 130000, N'SP002', N'A012', 1, 2, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA01', N'Áo phông adidas', 10, 50000, 70000, N'SP001', N'A012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA010', N'Áo thun dài tay', 30, 100000, 130000, N'SP003', N'A012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA011', N'Áo thun dài tay', 30, 100000, 130000, N'SP003', N'A012', 3, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA012', N'Áo thun ba lỗ', 30, 100000, 130000, N'SP004', N'A012', 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA013', N'Áo thun ba lỗ', 30, 100000, 130000, N'SP004', N'A012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA014', N'Áo ba lỗ', 30, 100000, 130000, N'SP005', N'A012', 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA015', N'Áo ba lỗ', 30, 100000, 130000, N'SP005', N'A012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA016', N'Áo BOMBER', 30, 100000, 130000, N'SP006', N'A012', 3, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA017', N'Áo BOMBER', 30, 100000, 130000, N'SP006', N'A012', 4, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA019', N'Áo khoác LV', 30, 100000, 130000, N'SP007', N'A012', 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA02', N'Áo phông adidas', 15, 50000, 70000, N'SP001', N'A012', 3, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA020', N'Áo phao lông vũ', 30, 100000, 130000, N'SP008', N'A012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA021', N'Áo phao lông vũ', 30, 100000, 130000, N'SP008', N'A012', 3, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA022', N'Áo hoodie', 30, 100000, 130000, N'SP111', N'A012', 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA023', N'Áo hoodie', 30, 100000, 130000, N'SP111', N'A012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA03', N'Áo phông adidas', 20, 50000, 70000, N'SP001', N'A012', 1, 2, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPA04', N'Áo phông adidas', 10, 50000, 70000, N'SP001', N'A012', 2, 2, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ001', N'Quần short', 30, 100000, 130000, N'SPH01', N'Q012', 3, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ002', N'Quần short', 30, 100000, 130000, N'SPH01', N'Q012', 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ003', N'Quần baggy', 30, 100000, 130000, N'SPQ01', N'Q012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ004', N'Quần baggy', 30, 100000, 130000, N'SPQ01', N'Q012', 3, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ005', N'Quần kaki', 30, 100000, 130000, N'SPQ02', N'Q012', 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ006', N'Quần kaki', 30, 100000, 130000, N'SPQ02', N'Q012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ007', N'Quần jean trơn', 30, 100000, 130000, N'SPQ03', N'Q012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ008', N'Quần jean trơn', 30, 100000, 130000, N'SPQ03', N'Q012', 1, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ009', N'Quần thể thao nỉ', 30, 100000, 130000, N'SPQ06', N'Q012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ010', N'Quần thể thao nỉ', 30, 100000, 130000, N'SPQ06', N'Q012', 3, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ011', N'Quần jogger', 30, 100000, 130000, N'SPQ07', N'Q012', 2, 1, 1)
INSERT [dbo].[Product_Detail] ([MaSanPhamChiTiet], [TenSanPham], [SoLuong], [GiaNhap], [GiaBan], [MaSanPham], [MaTheLoai], [maSize], [maMau], [TrangThai]) VALUES (N'SPQ012', N'Quần jogger', 30, 100000, 130000, N'SPQ07', N'Q012', 1, 1, 1)
GO
SET IDENTITY_INSERT [dbo].[Size] ON 

INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (1, N'S', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (2, N'M', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (3, N'L', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (4, N'XL', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (5, N'XXL', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (6, N'XXXL', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (7, N'27', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (8, N'28', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (9, N'29', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (10, N'30', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (11, N'31', 1)
INSERT [dbo].[Size] ([maSize], [TenSize], [TrangThai]) VALUES (12, N'32', 1)
SET IDENTITY_INSERT [dbo].[Size] OFF
GO
INSERT [dbo].[Staff] ([MaNhanVien], [TaiKhoan], [TenNhanVien], [MatKhau], [GioiTinh], [DiaChi], [DienThoai], [Email], [NgaySinh], [VaiTro], [TrangThai], [Avatar]) VALUES (N'AD001', N'chuong', N'Chuong', N'1', 0, N'Yên Bái', N'0368689239', N'kd.kendy9x@gmail.com', CAST(N'2002-08-08' AS Date), 1, 1, N'726e48026af997a7cee8.png                                                                                                                                                                                                                                       ')
INSERT [dbo].[Staff] ([MaNhanVien], [TaiKhoan], [TenNhanVien], [MatKhau], [GioiTinh], [DiaChi], [DienThoai], [Email], [NgaySinh], [VaiTro], [TrangThai], [Avatar]) VALUES (N'AD002', N'truong', N'Truong', N'1', 0, N'Hà Nội', N'0965246780', N'truong2010@gmail.com', CAST(N'2001-10-20' AS Date), 1, 1, NULL)
INSERT [dbo].[Staff] ([MaNhanVien], [TaiKhoan], [TenNhanVien], [MatKhau], [GioiTinh], [DiaChi], [DienThoai], [Email], [NgaySinh], [VaiTro], [TrangThai], [Avatar]) VALUES (N'NV001', N'hao', N'Hao', N'1', 0, N'Phường Xuân Đỉnh- Quận Bắc Từ Liêm- Hà Nội', N'0962964504', N'hello@gmail.com', CAST(N'2001-09-23' AS Date), 1, 1, NULL)
INSERT [dbo].[Staff] ([MaNhanVien], [TaiKhoan], [TenNhanVien], [MatKhau], [GioiTinh], [DiaChi], [DienThoai], [Email], [NgaySinh], [VaiTro], [TrangThai], [Avatar]) VALUES (N'NV002', N'hieu', N'Hieu', N'1', 1, N'Hà Nam- Hà Nội', N'0923948218', N'huhu@gmail.com', CAST(N'2002-08-26' AS Date), 1, 1, NULL)
INSERT [dbo].[Staff] ([MaNhanVien], [TaiKhoan], [TenNhanVien], [MatKhau], [GioiTinh], [DiaChi], [DienThoai], [Email], [NgaySinh], [VaiTro], [TrangThai], [Avatar]) VALUES (N'NV003', N'tung', N'Tung', N'1', 0, N'Hà Nội', N'0819234021', N'tung@gmail.com', CAST(N'2003-09-09' AS Date), 1, 1, NULL)
INSERT [dbo].[Staff] ([MaNhanVien], [TaiKhoan], [TenNhanVien], [MatKhau], [GioiTinh], [DiaChi], [DienThoai], [Email], [NgaySinh], [VaiTro], [TrangThai], [Avatar]) VALUES (N'NV003', N'staff', N'staff', N'1', 0, N'Hà Nội', N'0819234021', N'tung@gmail.com', CAST(N'2002-09-09' AS Date), 0, 1, NULL)
GO
ALTER TABLE [dbo].[Staff] ADD  DEFAULT ((0)) FOR [TrangThai]
GO
ALTER TABLE [dbo].[Detailed_Invoice]  WITH CHECK ADD FOREIGN KEY([MaHoaDon])
REFERENCES [dbo].[Invoice] ([MaHoaDon])
GO
ALTER TABLE [dbo].[Detailed_Invoice]  WITH CHECK ADD FOREIGN KEY([MaSanPhamChiTiet])
REFERENCES [dbo].[Product_Detail] ([MaSanPhamChiTiet])
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD FOREIGN KEY([MaKhachHang])
REFERENCES [dbo].[Client] ([MaKhachHang])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Invoice]  WITH CHECK ADD FOREIGN KEY([MaNhanVien])
REFERENCES [dbo].[Staff] ([MaNhanVien])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([MaGiamGia])
REFERENCES [dbo].[GiamGia] ([MaGiamGia])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([MaTheLoai])
REFERENCES [dbo].[Category] ([MaTheLoai])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Product_Detail]  WITH CHECK ADD FOREIGN KEY([maMau])
REFERENCES [dbo].[Color] ([maMau])
GO
ALTER TABLE [dbo].[Product_Detail]  WITH CHECK ADD FOREIGN KEY([MaSanPham])
REFERENCES [dbo].[Product] ([MaSanPham])
GO
ALTER TABLE [dbo].[Product_Detail]  WITH CHECK ADD FOREIGN KEY([maSize])
REFERENCES [dbo].[Size] ([maSize])
GO
USE [master]
GO
ALTER DATABASE [QLBH] SET  READ_WRITE 
GO
