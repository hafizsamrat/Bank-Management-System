-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 09, 2018 at 02:21 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oop1`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `userId` varchar(30) NOT NULL,
  `userName` varchar(30) NOT NULL,
  `phoneNumber` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`userId`, `userName`, `phoneNumber`) VALUES
('hafiz', 'Hafiz', '+8801738372994');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `userId` varchar(12) NOT NULL,
  `customerNamer` varchar(30) NOT NULL,
  `phoneNumber` varchar(14) NOT NULL,
  `address` varchar(50) NOT NULL,
  `Balance` double(20,3) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`userId`, `customerNamer`, `phoneNumber`, `address`, `Balance`) VALUES
('c001', 'Customer1', '+8801234567892', 'Banani', 500.000),
('c002', 'customer2', '+8810826151672', 'Gazipur', 400.000),
('c009', 'Rahman', '01927252526', 'Gazipur', 50.000);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `userId` varchar(12) NOT NULL,
  `employeeName` varchar(50) NOT NULL,
  `phoneNumber` varchar(14) NOT NULL,
  `salary` double(8,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`userId`, `employeeName`, `phoneNumber`, `salary`) VALUES
('e001', 'Employee1', '+8801234567890', 90000.00),
('e002', 'Employee2', '+8801234567891', 50000.00),
('e003', 'samrat', '84776175573', 46800.00);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `userId` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `status` int(1) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`userId`, `password`, `status`) VALUES
('e001', 'e001', 0),
('hafiz', 'hafiz', 2),
('c001', 'c001', 1),
('e002', 'e002', 0),
('c002', 'c002', 1),
('c009', 'c009', 1),
('e003', 'e003', 0);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `userId` varchar(40) NOT NULL,
  `Type` varchar(40) NOT NULL,
  `Sender` varchar(40) NOT NULL,
  `Receiver` varchar(40) NOT NULL,
  `Amount` double(20,5) NOT NULL,
  `Date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`userId`, `Type`, `Sender`, `Receiver`, `Amount`, `Date`) VALUES
('c001', 'Deposit', 'c001', 'c001', 0.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c001', 0.00000, '2018-12-08'),
('c002', 'Deposit', 'c002', 'c002', 0.00000, '2018-12-08'),
('c001', 'Deposit', 'c001', 'c001', 0.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c001', 0.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c001', 0.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c001', 50.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c001', 50.00000, '2018-12-08'),
('c001', 'Deposit', 'c001', 'c001', 50.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c001', 50.00000, '2018-12-08'),
('c001', 'Deposit', 'c001', 'c001', 100.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c002', 50.00000, '2018-12-08'),
('c002', 'Deposit', 'c001', 'c002', 50.00000, '2018-12-08'),
('c001', 'Deposit', 'c001', 'c001', 1000.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c002', 500.00000, '2018-12-08'),
('c002', 'Deposit', 'c001', 'c002', 500.00000, '2018-12-08'),
('c002', 'Withdraw', 'c002', 'c009', 100.00000, '2018-12-08'),
('c009', 'Deposit', 'c002', 'c009', 100.00000, '2018-12-08'),
('c002', 'Withdraw', 'c002', 'c009', 50.00000, '2018-12-08'),
('c009', 'Deposit', 'c002', 'c009', 50.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c009', 50.00000, '2018-12-08'),
('c009', 'Deposit', 'c001', 'c009', 50.00000, '2018-12-08'),
('c001', 'Deposit', 'c001', 'c001', 50.00000, '2018-12-08'),
('c009', 'Deposit', 'c009', 'c009', 100.00000, '2018-12-08'),
('c001', 'Withdraw', 'c001', 'c009', 50.00000, '2018-12-08'),
('c009', 'Deposit', 'c001', 'c009', 50.00000, '2018-12-08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD UNIQUE KEY `userId` (`userId`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD UNIQUE KEY `userId` (`userId`),
  ADD UNIQUE KEY `userId_2` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
