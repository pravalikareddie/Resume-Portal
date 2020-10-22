-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema resume_portal
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema resume_portal
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `resume_portal` DEFAULT CHARACTER SET utf8 ;
USE `resume_portal` ;

-- -----------------------------------------------------
-- Table `resume_portal`.`user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume_portal`.`user_profile` (
  `id` INT NOT NULL,
  `designation` VARCHAR(255) NULL DEFAULT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `first_name` VARCHAR(255) NULL DEFAULT NULL,
  `last_name` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  `summary` VARCHAR(255) NULL DEFAULT NULL,
  `theme` INT NOT NULL,
  `user_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resume_portal`.`education`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume_portal`.`education` (
  `id` INT NOT NULL,
  `college` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `qualification` VARCHAR(255) NULL DEFAULT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  `summary` VARCHAR(255) NULL DEFAULT NULL,
  `education_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKe4luhr5gn7dausnqyy1p0o92m` (`education_id` ASC) VISIBLE,
  CONSTRAINT `FKe4luhr5gn7dausnqyy1p0o92m`
    FOREIGN KEY (`education_id`)
    REFERENCES `resume_portal`.`user_profile` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resume_portal`.`hibernate_sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume_portal`.`hibernate_sequence` (
  `next_val` BIGINT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resume_portal`.`job`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume_portal`.`job` (
  `id` INT NOT NULL,
  `company` VARCHAR(255) NULL DEFAULT NULL,
  `designation` VARCHAR(255) NULL DEFAULT NULL,
  `end_date` DATE NULL DEFAULT NULL,
  `is_current_job` BIT(1) NOT NULL,
  `start_date` DATE NULL DEFAULT NULL,
  `job_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKo7oev48gl87roopgfmsishw6a` (`job_id` ASC) VISIBLE,
  CONSTRAINT `FKo7oev48gl87roopgfmsishw6a`
    FOREIGN KEY (`job_id`)
    REFERENCES `resume_portal`.`user_profile` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resume_portal`.`job_responsibilities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume_portal`.`job_responsibilities` (
  `job_id` INT NOT NULL,
  `responsibilities` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FKpklahjjkiney50ky5vpqc59ue` (`job_id` ASC) VISIBLE,
  CONSTRAINT `FKpklahjjkiney50ky5vpqc59ue`
    FOREIGN KEY (`job_id`)
    REFERENCES `resume_portal`.`job` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resume_portal`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume_portal`.`user` (
  `id` INT NOT NULL,
  `active` BIT(1) NOT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  `roles` VARCHAR(255) NULL DEFAULT NULL,
  `user_name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `resume_portal`.`user_profile_skills`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `resume_portal`.`user_profile_skills` (
  `user_profile_id` INT NOT NULL,
  `skills` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FK3gma2rfu2lanwb7hac2058qgm` (`user_profile_id` ASC) VISIBLE,
  CONSTRAINT `FK3gma2rfu2lanwb7hac2058qgm`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `resume_portal`.`user_profile` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;



use resume_portal;
insert into user (id, user_name, password, active, roles) values
(1, 'einstein', 'einstein', true, 'USER'),
(2, 'newton', 'newton', true, 'USER'),
(3, 'pravalika', 'pravalika', true, 'USER');

insert into user_profile (id, user_name, theme, summary, first_name, last_name, email, phone, designation) values
(1, 'einstein', 1, 'Developed the theory of relativity, one of the two pillars of modern physics. My work is also known for its influence on the philosophy of science.', 'Albert', 'Einstein', 'einstein@gmail.com', '111-111-1111', 'Theoretical physicist'),
(2, 'newton', 2, 'Widely recognised as one of the most influential scientists of all time and as a key figure in the scientific revolution', 'Isaac', 'Newton', 'newton@gmail.com', '222-222-2222', 'Mathematician, physicist, astronomer, theologian, and author');

INSERT INTO `education` VALUES (1,'Awesome College','2020-01-01','Useless Degree','2019-05-01','Studied a lot',1),(2,'Awesome College','2020-01-01','Useless Degree','2019-05-01','Studied a lot',1);
INSERT INTO `job` VALUES (3,'Company 1','Designation',NULL,_binary '','2020-01-01',1),(4,'Company 2','Designation','2020-01-01',_binary '\0','2019-05-01',1);
INSERT INTO `user_profile` VALUES (1,'Theoretical physicist','einstein@gmail.com','Albert','Einstein','111-111-1111','Developed the theory of relativity, one of the two pillars of modern physics. My work is also known for its influence on the philosophy of science.',1,'einstein'),(2,'Mathematician, physicist, astronomer, theologian, and author','newton@gmail.com','Isaac','Newton','222-222-2222','Widely recognised as one of the most influential scientists of all time and as a key figure in the scientific revolution',2,'newton');
INSERT INTO `user_profile_skills` VALUES (1,'Quantum physics'),(1,'Modern Physics'),(1,'Violin'),(1,'Philosophy');
