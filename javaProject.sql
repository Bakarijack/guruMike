-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Feb 14, 2022 at 02:07 PM
-- Server version: 8.0.26
-- PHP Version: 8.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javaProject`
--

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `cust_id` int NOT NULL,
  `phone` varchar(25) NOT NULL,
  `locationN` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id` int NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `secondName` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`cust_id`, `phone`, `locationN`, `gender`, `email`, `id`, `firstName`, `secondName`) VALUES
(1, '0797544060', 'Kilifi', 'Male', 'bakariKilu@gmail.com', 36505804, 'Bakari', 'Mtua'),
(25, '0712345678', 'Mombasa', 'Male', 'ibrahim@gmail.com', 37829468, 'Ibrahim', 'Kilu');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `dep_code` varchar(30) NOT NULL,
  `dep_name` varchar(30) NOT NULL,
  `emp_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`dep_code`, `dep_name`, `emp_id`) VALUES
('HB12', 'Brooder_Department', 'HB12/00001/21'),
('LG16', 'Logistic_Department', 'LG16/00001/21'),
('MD11', 'Management_Derector', 'MD11/00001/21'),
('OS15', 'Office_Department', 'OS15/00001/21'),
('PA13', 'Packing_Department', 'PA13/00001/21'),
('PR14', 'Production_Department', 'PR14/00001/21');

-- --------------------------------------------------------

--
-- Table structure for table `invoices`
--

CREATE TABLE `invoices` (
  `invoice_code` int NOT NULL,
  `order_code` varchar(50) NOT NULL,
  `invoice_total` double DEFAULT NULL,
  `invoice_date` varchar(15) NOT NULL,
  `invoice_time` varchar(15) NOT NULL,
  `payment_terms_code` int NOT NULL,
  `sentStatus` tinyint(1) DEFAULT '0',
  `printed` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `invoices`
--

INSERT INTO `invoices` (`invoice_code`, `order_code`, `invoice_total`, `invoice_date`, `invoice_time`, `payment_terms_code`, `sentStatus`, `printed`) VALUES
(2, '5', 1005, '2022/01/03', '14:32:37', 3, 0, 0),
(3, '8', 160, '2022/01/04', '12:17:49', 1, 0, 0),
(4, '9', 75, '2022/01/04', '12:23:34', 4, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `invoice_status`
--

CREATE TABLE `invoice_status` (
  `sentStatus` int NOT NULL,
  `status_name` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `invoice_status`
--

INSERT INTO `invoice_status` (`sentStatus`, `status_name`) VALUES
(1, 'Sent'),
(0, 'Not Sent');

-- --------------------------------------------------------

--
-- Table structure for table `netPay`
--

CREATE TABLE `netPay` (
  `payment_date` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `number_of_employees` int DEFAULT '0',
  `total_paye` double DEFAULT '0',
  `month` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `year` varchar(15) NOT NULL,
  `approved_status` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `netPay`
--

INSERT INTO `netPay` (`payment_date`, `number_of_employees`, `total_paye`, `month`, `year`, `approved_status`) VALUES
('2022/01/20', 2, 29000, '12', '2021', 1);

-- --------------------------------------------------------

--
-- Table structure for table `orderDetails`
--

CREATE TABLE `orderDetails` (
  `order_code` varchar(50) NOT NULL,
  `product_code` varchar(50) NOT NULL,
  `ordered_trays` int NOT NULL,
  `order_time` varchar(50) NOT NULL,
  `order_date` varchar(50) NOT NULL,
  `ordered_eggs` int NOT NULL,
  `invoice_generated_status` tinyint(1) DEFAULT '0',
  `cost` double DEFAULT NULL,
  `payment_status_code` int DEFAULT '2'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orderDetails`
--

INSERT INTO `orderDetails` (`order_code`, `product_code`, `ordered_trays`, `order_time`, `order_date`, `ordered_eggs`, `invoice_generated_status`, `cost`, `payment_status_code`) VALUES
('2', 're2', 3, '21:30:39', '2022/01/02', 0, 1, 900, 1),
('3', 're2', 2, '21:37:28', '2022/01/02', 0, 1, 600, 1),
('5', 're2', 3, '22:14:02', '2022/01/02', 0, 1, 900, 1),
('6', 're2', 0, '22:18:35', '2022/01/02', 5, 1, 80, 1),
('5', 'ce1', 0, '22:29:44', '2022/01/02', 7, 1, 105, 1),
('7', 're2', 5, '10:58:52', '2022/01/04', 0, 0, 1500, 1),
('8', 're2', 0, '11:03:50', '2022/01/04', 10, 1, 160, 1),
('9', 'ce1', 0, '12:23:15', '2022/01/04', 5, 1, 75, 2);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `order_code` varchar(50) NOT NULL,
  `cust_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`order_code`, `cust_id`) VALUES
('1', 1),
('2', 1),
('3', 1),
('5', 1),
('6', 1),
('7', 25),
('8', 25),
('9', 1);

-- --------------------------------------------------------

--
-- Table structure for table `paymentRatesPerHour`
--

CREATE TABLE `paymentRatesPerHour` (
  `payment_rate_code` int NOT NULL,
  `payment_amount_per_hour` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `paymentRatesPerHour`
--

INSERT INTO `paymentRatesPerHour` (`payment_rate_code`, `payment_amount_per_hour`) VALUES
(1, 50.5),
(2, 62.5);

-- --------------------------------------------------------

--
-- Table structure for table `payment_on_order_status`
--

CREATE TABLE `payment_on_order_status` (
  `payment_status_code` int NOT NULL,
  `payment_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment_on_order_status`
--

INSERT INTO `payment_on_order_status` (`payment_status_code`, `payment_status`) VALUES
(1, 'Paid'),
(2, 'Not Paid');

-- --------------------------------------------------------

--
-- Table structure for table `payment_terms`
--

CREATE TABLE `payment_terms` (
  `payment_terms_code` int NOT NULL,
  `payment_term` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment_terms`
--

INSERT INTO `payment_terms` (`payment_terms_code`, `payment_term`) VALUES
(1, 'Cash'),
(2, 'M-Pesa'),
(3, 'Bank_Account'),
(4, 'Debt');

-- --------------------------------------------------------

--
-- Table structure for table `poultry`
--

CREATE TABLE `poultry` (
  `poultry_code` int NOT NULL,
  `poultry_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `poultry_quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `poultry`
--

INSERT INTO `poultry` (`poultry_code`, `poultry_type`, `poultry_quantity`) VALUES
(1, 'convectional hover brooder', 50),
(2, 'Radiant Brooder', 500),
(3, 'Kienyeji Brooder', 400);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `product_quantity` int DEFAULT NULL,
  `poultry_code` int DEFAULT NULL,
  `price_per_tray` double DEFAULT '0',
  `price_per_egg` double DEFAULT '0',
  `egg_quantity` int NOT NULL,
  `tray_quantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_name`, `product_code`, `product_quantity`, `poultry_code`, `price_per_tray`, `price_per_egg`, `egg_quantity`, `tray_quantity`) VALUES
('Convectional Eggs', 'CE1', 320, 1, 320, 15, 5, 0),
('Kienyeji eggs', 'KE3', 600, 3, 370, 17, 0, 20),
('Radiant eggs', 'RE2', 700, 2, 300, 16, 25, 4);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_nu` int NOT NULL,
  `role` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_nu`, `role`) VALUES
(1, 'manager_director'),
(2, 'head_brooder'),
(3, 'packing_manager'),
(4, 'logistic_manager'),
(5, 'office_supervisor'),
(6, 'production_manager'),
(7, 'office_worker'),
(8, 'worker');

-- --------------------------------------------------------

--
-- Table structure for table `salaries`
--

CREATE TABLE `salaries` (
  `emp_id` varchar(25) NOT NULL,
  `amount` double DEFAULT '0',
  `date` varchar(15) NOT NULL,
  `year` varchar(15) NOT NULL,
  `paid_status` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `salaries`
--

INSERT INTO `salaries` (`emp_id`, `amount`, `date`, `year`, `paid_status`) VALUES
('OS15/00002/21', 13000, '12', '2021', 1),
('os15/00001/21', 16000, '12', '2021', 1),
('OS15/00002/21', 13500, '11', '2021', 1);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `emp_id` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role_nu` int NOT NULL,
  `firstName` varchar(25) NOT NULL,
  `secondName` varchar(25) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dep_code` varchar(25) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `location` varchar(25) NOT NULL,
  `date_recruited` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`emp_id`, `username`, `password`, `role_nu`, `firstName`, `secondName`, `email`, `dep_code`, `gender`, `phone`, `location`, `date_recruited`) VALUES
('HB12/00001/21', 'Sebana', '12345678', 2, 'Sebana', 'SebanaH', 'sebana@gmail.com', 'HB12', 'Male', '0732146791', 'Kisumu', NULL),
('HB12/00001/22', 'Lucy', '12345678', 7, 'Lucy', 'Onyango', 'lucy@gmail.com', 'HB12', 'Female', '0798653421', 'Kisumu', '2022/02/08'),
('HB12/00002/22', 'Harun', '12345678', 7, 'Harun', 'Morphat', 'harun@gmail.com', 'HB12', 'Male', '0794325623', 'Mombasa', '2022/02/08'),
('LG16/00001/21', 'Rabiib', '12345678', 4, 'Rabiib', 'RabiibLg', 'rabiib@gmail.com', 'LG16', 'Male', '0756453870', 'Kisumu', NULL),
('MD11/00001/21', 'Kalanzi', '12345678', 1, 'Kalanzi', 'KalanziD', 'kalanzi@gmail.com', 'MD11', 'Male', '0746735896', 'Kisii', NULL),
('OS15/00001/21', 'Rithwanah', '12345678', 5, 'Rithwanah', 'RithwanahOs', 'rithwanah@gmail.com', 'OS15', 'Female', '0795345321', 'Kisii', NULL),
('OS15/00001/22', 'Denis', '12345678', 7, 'Denis', 'Denoo', 'denis@gmail.com', 'OS15', 'Male', '0798651256', 'Nairobi', '2022/02/07'),
('OS15/00002/21', 'Mbugua', '12345678', 7, 'Mbugua', 'MbuguaOs', 'mbugua@gmail.com', 'OS15', 'male', '0762849522', 'Narok', NULL),
('PA13/00001/21', 'Rahmah', '12345678', 3, 'Rahmah', 'RahmahPa', 'rahmah@gmail.com', 'PA13', 'Male', '0767846327', 'Kisii', NULL),
('PR14/00001/21', 'BakariB', '12345678', 6, 'Bakari', 'Kilu', 'bakarikilu@gmail.com', 'PR14', 'Male', '0797544060', 'Kisii', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `workerMonthlyRecord`
--

CREATE TABLE `workerMonthlyRecord` (
  `emp_id` varchar(25) NOT NULL,
  `month` varchar(25) NOT NULL,
  `year` varchar(25) NOT NULL,
  `full_days` int DEFAULT '0',
  `half_days` int DEFAULT '0',
  `absent_days` int DEFAULT '0',
  `paid_off_days` int DEFAULT '0',
  `total_hours` double DEFAULT '0',
  `payment_rate_code` int DEFAULT '0',
  `over_time_count` int DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `workerMonthlyRecord`
--

INSERT INTO `workerMonthlyRecord` (`emp_id`, `month`, `year`, `full_days`, `half_days`, `absent_days`, `paid_off_days`, `total_hours`, `payment_rate_code`, `over_time_count`) VALUES
('os15/00001/21', '01', '2022', 1, 0, 0, 0, 10, 0, 1),
('os15/00002/21', '01', '2022', 2, 0, 0, 0, 23.5, 1, 2),
('os15/00002/21', '02', '2022', 1, 0, 0, 0, 12, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `workingHours`
--

CREATE TABLE `workingHours` (
  `emp_id` varchar(25) NOT NULL,
  `day_off` tinyint(1) DEFAULT '0',
  `half_day` tinyint(1) DEFAULT '0',
  `paid_off_day` tinyint(1) DEFAULT '0',
  `over_time` tinyint(1) DEFAULT '0',
  `total_hours_per_day` double DEFAULT '0',
  `date` varchar(10) NOT NULL,
  `month` varchar(10) NOT NULL,
  `year` varchar(10) NOT NULL,
  `full_day` tinyint(1) DEFAULT '0',
  `full_day_hours` double DEFAULT '0',
  `half_day_hours` double DEFAULT '0',
  `paid_day_off_hours` double DEFAULT '0',
  `over_time_hours` double DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `workingHours`
--

INSERT INTO `workingHours` (`emp_id`, `day_off`, `half_day`, `paid_off_day`, `over_time`, `total_hours_per_day`, `date`, `month`, `year`, `full_day`, `full_day_hours`, `half_day_hours`, `paid_day_off_hours`, `over_time_hours`) VALUES
('os15/00002/21', 0, 0, 0, 1, 11, '13', '01', '2022', 1, 7.5, 0, 0, 3.5),
('os15/00002/21', 0, 1, 0, 0, 6.5, '14', '01', '2022', 0, 0, 6.5, 0, 0),
('os15/00001/21', 0, 0, 0, 1, 10, '15', '01', '2022', 1, 6.5, 0, 0, 3.5),
('os15/00002/21', 0, 0, 0, 1, 11.5, '15', '01', '2022', 1, 7.5, 0, 0, 4),
('os15/00002/21', 0, 0, 0, 1, 12, '05', '02', '2022', 1, 8, 0, 0, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`cust_id`,`phone`,`email`,`id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`dep_code`);

--
-- Indexes for table `invoices`
--
ALTER TABLE `invoices`
  ADD PRIMARY KEY (`invoice_code`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`order_code`);

--
-- Indexes for table `poultry`
--
ALTER TABLE `poultry`
  ADD PRIMARY KEY (`poultry_code`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_code`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_nu`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`emp_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `cust_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `invoices`
--
ALTER TABLE `invoices`
  MODIFY `invoice_code` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
