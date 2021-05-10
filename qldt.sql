-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 10, 2021 at 05:31 AM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qldt`
--

-- --------------------------------------------------------

--
-- Table structure for table `archivement`
--

CREATE TABLE `archivement` (
  `id` int(11) NOT NULL,
  `archivement_name` varchar(128) NOT NULL,
  `created_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `archivement`
--

INSERT INTO `archivement` (`id`, `archivement_name`, `created_at`) VALUES
(1, 'Giai Nhat hsg', '2021-04-08 21:52:44');

-- --------------------------------------------------------

--
-- Table structure for table `classroom`
--

CREATE TABLE `classroom` (
  `id` int(11) NOT NULL,
  `classroom_name` varchar(32) NOT NULL,
  `home_room_teacher_id` int(11) NOT NULL,
  `location` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `classroom`
--

INSERT INTO `classroom` (`id`, `classroom_name`, `home_room_teacher_id`, `location`) VALUES
(1, 'Lop 12A1', 1, 'Toa A1');

-- --------------------------------------------------------

--
-- Table structure for table `class_record`
--

CREATE TABLE `class_record` (
  `id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `classroom_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `study_week` int(11) NOT NULL,
  `attendant` int(11) NOT NULL,
  `note` varchar(256) NOT NULL,
  `day_of_week` int(11) NOT NULL,
  `shift` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `class_record`
--

INSERT INTO `class_record` (`id`, `teacher_id`, `classroom_id`, `course_id`, `study_week`, `attendant`, `note`, `day_of_week`, `shift`) VALUES
(1, 1, 1, 1, 1, 1, 'Hoc bai', 5, 1);

-- --------------------------------------------------------

--
-- Table structure for table `conduct`
--

CREATE TABLE `conduct` (
  `id` int(11) NOT NULL,
  `score` varchar(32) NOT NULL,
  `term` int(11) NOT NULL,
  `school_year` int(11) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `course_name` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`id`, `course_name`) VALUES
(1, 'Toan');

-- --------------------------------------------------------

--
-- Table structure for table `device`
--

CREATE TABLE `device` (
  `id` int(11) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `device_name` varchar(128) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `device`
--

INSERT INTO `device` (`id`, `status`, `device_name`) VALUES
(2, 1, 'Xa don'),
(3, 1, 'Bong ro'),
(4, 1, 'da cau'),
(6, 1, 'Xa don'),
(7, 1, 'Bong ban');

-- --------------------------------------------------------

--
-- Table structure for table `device_manage`
--

CREATE TABLE `device_manage` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `device_id` int(11) NOT NULL,
  `lastuse_at` datetime DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `device_manage`
--

INSERT INTO `device_manage` (`id`, `account_id`, `device_id`, `lastuse_at`) VALUES
(2, 1, 2, '2021-04-02 16:31:47'),
(3, 2, 3, '2021-04-15 16:31:47'),
(4, 2, 3, '2021-04-23 16:31:47');

-- --------------------------------------------------------

--
-- Table structure for table `file`
--

CREATE TABLE `file` (
  `id` int(11) NOT NULL,
  `name` varchar(256) NOT NULL,
  `extension` varchar(3) NOT NULL,
  `data` text NOT NULL,
  `study_week` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `grade`
--

CREATE TABLE `grade` (
  `id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `school_year` int(11) NOT NULL,
  `term` int(11) NOT NULL,
  `mid_term_test` int(11) NOT NULL,
  `final_term_test` int(11) NOT NULL,
  `quiz1` int(11) NOT NULL,
  `quiz2` int(11) DEFAULT NULL,
  `quiz3` int(11) DEFAULT NULL,
  `test` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `grade`
--

INSERT INTO `grade` (`id`, `student_id`, `course_id`, `school_year`, `term`, `mid_term_test`, `final_term_test`, `quiz1`, `quiz2`, `quiz3`, `test`) VALUES
(1, 1, 1, 2018, 1, 9, 10, 10, 10, 10, 10),
(2, 1, 1, 2018, 1, 10, 10, 10, 10, 10, 10),
(3, 1, 1, 2018, 2, 10, 10, 10, 10, 10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `health_certification`
--

CREATE TABLE `health_certification` (
  `id` int(11) NOT NULL,
  `height` int(11) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `eye_sight` int(11) DEFAULT NULL,
  `health_status` varchar(256) DEFAULT NULL,
  `disease` varchar(256) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `health_certification`
--

INSERT INTO `health_certification` (`id`, `height`, `weight`, `eye_sight`, `health_status`, `disease`) VALUES
(1, 160, 60, 1, 'Khoe manh', 'khong co');

-- --------------------------------------------------------

--
-- Table structure for table `login_info`
--

CREATE TABLE `login_info` (
  `username` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `permission` int(128) NOT NULL,
  `id` int(11) NOT NULL,
  `personal_entity_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_info`
--

INSERT INTO `login_info` (`username`, `password`, `permission`, `id`, `personal_entity_id`) VALUES
('duykien', '$2a$10$TUe8Dgv2hasJHFy6oKtDOu0pNBouzhmRV0fuL5v4mKSxRYm.403pu', 1, 1, NULL),
('teacher', '$2a$10$F3L.0CdtsJXUQokLt2OZ.eH1HqxokIp5ny1yf9B/10QVvktm88nEK', 3, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `parent`
--

CREATE TABLE `parent` (
  `id` int(11) NOT NULL,
  `avocation` varchar(256) NOT NULL,
  `student_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `parent`
--

INSERT INTO `parent` (`id`, `avocation`, `student_id`) VALUES
(1, 'Lai xe', 1);

-- --------------------------------------------------------

--
-- Table structure for table `personal_info`
--

CREATE TABLE `personal_info` (
  `id` int(11) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `gender` int(11) NOT NULL,
  `date_of_birth` datetime NOT NULL,
  `address` varchar(128) NOT NULL,
  `ethnicity` varchar(32) DEFAULT NULL,
  `religion` varchar(32) DEFAULT NULL,
  `phone_number` char(11) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `personal_info`
--

INSERT INTO `personal_info` (`id`, `first_name`, `last_name`, `gender`, `date_of_birth`, `address`, `ethnicity`, `religion`, `phone_number`, `email`, `account_id`) VALUES
(1, 'Nguyen', 'Hoa', 2, '2011-05-10 22:54:10', 'Huong canh', 'Kinh', 'Khong', '0985068971', 'hoa@gmail.com', 2),
(2, 'Nguyen', 'Kien', 1, '2021-04-08 21:34:39', 'Kim Hoa', 'Kinh', 'Khong', '0985068974', 'kienlop9altt2014@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `classroom_id` int(11) NOT NULL,
  `health_certification_id` int(11) DEFAULT NULL,
  `archive_id` int(11) DEFAULT NULL,
  `status` varchar(64) DEFAULT NULL,
  `admission_year` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`id`, `classroom_id`, `health_certification_id`, `archive_id`, `status`, `admission_year`, `account_id`) VALUES
(1, 1, 1, 1, 'Hoc tap', 2018, 1);

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `archive_id` int(11) DEFAULT NULL,
  `account_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `course_id`, `archive_id`, `account_id`) VALUES
(1, 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `timetable`
--

CREATE TABLE `timetable` (
  `id` int(11) NOT NULL,
  `classroom_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  `day_of_week` int(11) NOT NULL,
  `shift` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `timetable`
--

INSERT INTO `timetable` (`id`, `classroom_id`, `teacher_id`, `course_id`, `day_of_week`, `shift`) VALUES
(2, 1, 1, 1, 3, 0),
(3, 1, 1, 1, 4, 1),
(5, 1, 1, 1, 1, 2),
(7, 1, 1, 1, 3, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `archivement`
--
ALTER TABLE `archivement`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Class_Teacher` (`home_room_teacher_id`);

--
-- Indexes for table `class_record`
--
ALTER TABLE `class_record`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ClassRecord_Teacher` (`teacher_id`),
  ADD KEY `FK_ClassRecord_Classroom` (`classroom_id`),
  ADD KEY `FK_ClassRecord_Course` (`course_id`);

--
-- Indexes for table `conduct`
--
ALTER TABLE `conduct`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Conduct_Student` (`student_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `device`
--
ALTER TABLE `device`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `device_manage`
--
ALTER TABLE `device_manage`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_DeviceMana_Device` (`device_id`),
  ADD KEY `loginInforDevice` (`account_id`);

--
-- Indexes for table `file`
--
ALTER TABLE `file`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_File_Teacher` (`teacher_id`),
  ADD KEY `FK_File_Course` (`course_id`);

--
-- Indexes for table `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Grade_Student` (`student_id`),
  ADD KEY `FK_Grade_Course` (`course_id`);

--
-- Indexes for table `health_certification`
--
ALTER TABLE `health_certification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `login_info`
--
ALTER TABLE `login_info`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `parent`
--
ALTER TABLE `parent`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Parrent_Student` (`student_id`);

--
-- Indexes for table `personal_info`
--
ALTER TABLE `personal_info`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Person_Archivement` (`archive_id`),
  ADD KEY `FK_Student_Class` (`classroom_id`),
  ADD KEY `FK_Student_Health` (`health_certification_id`),
  ADD KEY `FK_LoginInfo` (`account_id`);

--
-- Indexes for table `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Person_Archive` (`archive_id`),
  ADD KEY `FK_Teacher_Course` (`course_id`),
  ADD KEY `FKhygqcatr5vou732ycigu0i0vk` (`account_id`);

--
-- Indexes for table `timetable`
--
ALTER TABLE `timetable`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Schedule_Class` (`classroom_id`),
  ADD KEY `FK_Schedule_Teacher` (`teacher_id`),
  ADD KEY `FK_Schedule_Course` (`course_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `archivement`
--
ALTER TABLE `archivement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `classroom`
--
ALTER TABLE `classroom`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `class_record`
--
ALTER TABLE `class_record`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `conduct`
--
ALTER TABLE `conduct`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `device`
--
ALTER TABLE `device`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `device_manage`
--
ALTER TABLE `device_manage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `file`
--
ALTER TABLE `file`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `grade`
--
ALTER TABLE `grade`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `health_certification`
--
ALTER TABLE `health_certification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `login_info`
--
ALTER TABLE `login_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `personal_info`
--
ALTER TABLE `personal_info`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `timetable`
--
ALTER TABLE `timetable`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `classroom`
--
ALTER TABLE `classroom`
  ADD CONSTRAINT `FK_Class_Teacher` FOREIGN KEY (`home_room_teacher_id`) REFERENCES `teacher` (`id`);

--
-- Constraints for table `class_record`
--
ALTER TABLE `class_record`
  ADD CONSTRAINT `FK_ClassRecord_Classroom` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`),
  ADD CONSTRAINT `FK_ClassRecord_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `FK_ClassRecord_Teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`);

--
-- Constraints for table `conduct`
--
ALTER TABLE `conduct`
  ADD CONSTRAINT `FK_Conduct_Student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `device_manage`
--
ALTER TABLE `device_manage`
  ADD CONSTRAINT `FK_DeviceMana_Device` FOREIGN KEY (`device_id`) REFERENCES `device` (`id`),
  ADD CONSTRAINT `loginInforDevice` FOREIGN KEY (`account_id`) REFERENCES `login_info` (`id`);

--
-- Constraints for table `file`
--
ALTER TABLE `file`
  ADD CONSTRAINT `FK_File_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `FK_File_Teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`);

--
-- Constraints for table `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `FK_Grade_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `FK_Grade_Student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `login_info`
--
ALTER TABLE `login_info`
  ADD CONSTRAINT `FKv1wus45cfsrigvnauagcj938` FOREIGN KEY (`personal_entity_id`) REFERENCES `personal_info` (`id`);

--
-- Constraints for table `parent`
--
ALTER TABLE `parent`
  ADD CONSTRAINT `FK_Parrent_Student` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`);

--
-- Constraints for table `personal_info`
--
ALTER TABLE `personal_info`
  ADD CONSTRAINT `personal_info_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `login_info` (`id`);

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `FK_LoginInfo` FOREIGN KEY (`account_id`) REFERENCES `login_info` (`Id`),
  ADD CONSTRAINT `FK_Person_Archivement` FOREIGN KEY (`archive_id`) REFERENCES `archivement` (`id`),
  ADD CONSTRAINT `FK_Student_Class` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`),
  ADD CONSTRAINT `FK_Student_Health` FOREIGN KEY (`health_certification_id`) REFERENCES `health_certification` (`id`);

--
-- Constraints for table `teacher`
--
ALTER TABLE `teacher`
  ADD CONSTRAINT `FK_LoginInfo_teacher` FOREIGN KEY (`account_id`) REFERENCES `login_info` (`Id`),
  ADD CONSTRAINT `FK_Person_Archive` FOREIGN KEY (`archive_id`) REFERENCES `archivement` (`id`),
  ADD CONSTRAINT `FK_Teacher_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `FKhygqcatr5vou732ycigu0i0vk` FOREIGN KEY (`account_id`) REFERENCES `personal_info` (`id`);

--
-- Constraints for table `timetable`
--
ALTER TABLE `timetable`
  ADD CONSTRAINT `FK_Schedule_Class` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`id`),
  ADD CONSTRAINT `FK_Schedule_Course` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `FK_Schedule_Teacher` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
