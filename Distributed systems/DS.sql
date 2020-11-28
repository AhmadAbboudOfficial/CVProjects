-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 19, 2018 at 11:19 PM
-- Server version: 5.7.22-0ubuntu0.16.04.1
-- PHP Version: 7.0.28-0ubuntu0.16.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `DS`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

CREATE TABLE `clients` (
  `ID` int(15) NOT NULL,
  `Name` varchar(30) DEFAULT NULL,
  `Password` varchar(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`ID`, `Name`, `Password`) VALUES
(1, 'admin', 'admin666'),
(4, 'ahmad abboud', '123456'),
(5, 'ahmed', '3541');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `ID` int(15) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `ProductNumber` int(30) NOT NULL,
  `Price` float UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`ID`, `Name`, `ProductNumber`, `Price`) VALUES
(1, 'white cheese', 12356, 13.5),
(2, 'brown cheese', 15789, 15.5),
(3, 'green cheese', 25487, 18.5),
(4, 'mix cheese', 66694, 22),
(34, 'sample0', 15978, 30),
(35, 'sample1', 12345, 20),
(36, 'sample2', 15468, 14.3),
(37, 'sample3', 123478, 40),
(38, 'sample4', 78546, 16),
(39, 'sample5', 16495, 18),
(40, 'sample6', 134689, 22);

-- --------------------------------------------------------

--
-- Table structure for table `purchases`
--

CREATE TABLE `purchases` (
  `ID` int(15) NOT NULL,
  `ClientID` int(15) NOT NULL,
  `ProductNumber` int(30) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchases`
--

INSERT INTO `purchases` (`ID`, `ClientID`, `ProductNumber`, `quantity`) VALUES
(1, 4, 66694, 1),
(2, 4, 66694, 1),
(3, 4, 12, 1),
(4, 4, 15789, 1),
(5, 4, 25487, 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Password` (`Password`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`ID`,`ProductNumber`);

--
-- Indexes for table `purchases`
--
ALTER TABLE `purchases`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `clients_pupurchases_fk` (`ClientID`),
  ADD KEY `P_Number` (`ProductNumber`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
  MODIFY `ID` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `ID` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT for table `purchases`
--
ALTER TABLE `purchases`
  MODIFY `ID` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `purchases`
--
ALTER TABLE `purchases`
  ADD CONSTRAINT `clients_purchases_fk` FOREIGN KEY (`ClientID`) REFERENCES `clients` (`ID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
