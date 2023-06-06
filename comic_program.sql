-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th6 06, 2023 lúc 08:16 AM
-- Phiên bản máy phục vụ: 8.0.31
-- Phiên bản PHP: 8.1.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `comic_program`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int NOT NULL AUTO_INCREMENT,
  `name_category` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id_category`, `name_category`) VALUES
(1, 'kinh di'),
(2, 'khủng khiếp'),
(3, 'khủng dady');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chapter`
--

DROP TABLE IF EXISTS `chapter`;
CREATE TABLE IF NOT EXISTS `chapter` (
  `id_comic` int NOT NULL,
  `id_chapter` int NOT NULL AUTO_INCREMENT,
  `chapter_number` double NOT NULL,
  PRIMARY KEY (`id_chapter`),
  UNIQUE KEY `chapter_number` (`chapter_number`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chapter`
--

INSERT INTO `chapter` (`id_comic`, `id_chapter`, `chapter_number`) VALUES
(1, 3, 22),
(1, 5, 4),
(2, 6, 12),
(0, 8, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chapter_present`
--

DROP TABLE IF EXISTS `chapter_present`;
CREATE TABLE IF NOT EXISTS `chapter_present` (
  `id_present` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_comic` int NOT NULL,
  `chapter_present` double NOT NULL,
  PRIMARY KEY (`id_comic`,`id_user`),
  UNIQUE KEY `id_present` (`id_present`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chapter_present`
--

INSERT INTO `chapter_present` (`id_present`, `id_user`, `id_comic`, `chapter_present`) VALUES
(1, 2, 1, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `comic`
--

DROP TABLE IF EXISTS `comic`;
CREATE TABLE IF NOT EXISTS `comic` (
  `id_comic` int NOT NULL AUTO_INCREMENT,
  `id_category` int NOT NULL,
  `name_comic` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `url_image` varchar(300) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id_comic`,`id_category`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `comic`
--

INSERT INTO `comic` (`id_comic`, `id_category`, `name_comic`, `url_image`) VALUES
(1, 1, 'ddd', 'https://2.bp.blogspot.com/29dFJX3E06ldQvOdfv_Fzm5eepvxF5cHJvOhU6YshSfteM-78W6saToXwoEjPsMffHtTmh4HWAcf=s1600'),
(2, 2, 'aaaa', 'https://2.bp.blogspot.com/29dFJX3E06ldQvOdfv_Fzm5eepvxF5cHJvOhU6YshSfteM-78W6saToXwoEjPsMffHtTmh4HWAcf=s1600');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `pages`
--

DROP TABLE IF EXISTS `pages`;
CREATE TABLE IF NOT EXISTS `pages` (
  `id_chapter` int NOT NULL,
  `id_page` int NOT NULL AUTO_INCREMENT,
  `url_image` varchar(300) COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id_page`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `pages`
--

INSERT INTO `pages` (`id_chapter`, `id_page`, `url_image`) VALUES
(6, 2, 'https://2.bp.blogspot.com/osgNi4b6f-IqVvtM-5hkHgnE2x5eLqm5LBS-0eu_cL6ibDW-24bXDsj5bUI1megQTV1ESpmtxWpB=s1600'),
(3, 3, 'https://2.bp.blogspot.com/osgNi4b6f-IqVvtM-5hkHgnE2x5eLqm5LBS-0eu_cL6ibDW-24bXDsj5bUI1megQTV1ESpmtxWpB=s1600'),
(5, 4, 'https://2.bp.blogspot.com/osgNi4b6f-IqVvtM-5hkHgnE2x5eLqm5LBS-0eu_cL6ibDW-24bXDsj5bUI1megQTV1ESpmtxWpB=s1600');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id_user` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` char(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` char(30) COLLATE utf8mb3_unicode_ci NOT NULL,
  `name` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `mail` varchar(50) COLLATE utf8mb3_unicode_ci NOT NULL,
  `permission` tinyint NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `id_user` (`id_user`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `name`, `mail`, `permission`) VALUES
(1, 'vuong1', 'vuong2', 'vuong a', 'bmgaming1230@gmail.com', 0),
(2, 'vuong2', 'vuong2', 'vuong2', 'bmgaming1230@gmail.com', 0),
(3, 'vuong1111', 'vuong1111', 'vvs', 'bmgaming1230@gmail.com', 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
