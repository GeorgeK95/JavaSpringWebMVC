-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.1.21-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win32
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for resident_evil_db
CREATE DATABASE IF NOT EXISTS `resident_evil_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `resident_evil_db`;

-- Dumping structure for table resident_evil_db.viruses
CREATE TABLE IF NOT EXISTS `viruses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creator_type` varchar(255) DEFAULT NULL,
  `description` longtext NOT NULL,
  `hours_until_turn` int(11) DEFAULT NULL,
  `is_curable` bit(1) NOT NULL,
  `is_deadly` bit(1) NOT NULL,
  `magnitude_type` varchar(255) DEFAULT NULL,
  `mutation_type` varchar(255) NOT NULL,
  `name` varchar(10) NOT NULL,
  `release_on` date NOT NULL,
  `side_effect` varchar(50) DEFAULT NULL,
  `turnover_rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=latin1;

-- Dumping data for table resident_evil_db.viruses: ~112 rows (approximately)
/*!40000 ALTER TABLE `viruses` DISABLE KEYS */;
INSERT INTO `viruses` (`id`, `creator_type`, `description`, `hours_until_turn`, `is_curable`, `is_deadly`, `magnitude_type`, `mutation_type`, `name`, `release_on`, `side_effect`, `turnover_rate`) VALUES
	(1, 'Corp', 'A football virus.', 5, b'0', b'0', 'MEDIUM', 'T_078_TYRANT', 'JMourinho', '2018-03-09', 'Ugly football.', 5),
	(2, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(3, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(4, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(5, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(6, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(7, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(8, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(9, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(10, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(11, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(12, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(13, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(14, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(15, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(16, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(17, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(18, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(19, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(20, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(21, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(22, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(23, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(24, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(25, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(26, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(27, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(28, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(29, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(30, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(31, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(32, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(33, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(34, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(35, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(36, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(37, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(38, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(39, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(40, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(41, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(42, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(43, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(44, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(45, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(46, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(47, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(48, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(49, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(50, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(51, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(52, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(53, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(54, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(55, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(56, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(57, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(58, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(59, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(60, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(61, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(62, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(63, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(64, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(65, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(66, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(67, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(68, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(69, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(70, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(71, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(72, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(73, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(74, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(75, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(76, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(77, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(78, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(79, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(80, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(81, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(82, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(83, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(84, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(85, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(86, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(87, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(88, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(89, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(90, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(91, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(92, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(93, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(94, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(95, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(96, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(97, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(98, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(99, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(100, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(101, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(102, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(103, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(104, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(105, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(106, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(107, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(108, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(109, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(110, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(111, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5),
	(112, 'corp', 'football virus', 5, b'0', b'0', 'LOW', 'T_078_TYRANT', 'MOU', '2018-03-09', 'deffensive football', 5);
/*!40000 ALTER TABLE `viruses` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
