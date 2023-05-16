-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema announcement
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema announcement
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `announcement` DEFAULT CHARACTER SET utf8 ;
USE `announcement` ;

-- -----------------------------------------------------
-- Table `announcement`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `announcement`.`categories` (
  `idCategory` INT NOT NULL,
  `categoryName` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idCategory`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `announcement`.`announcements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `announcement`.`announcements` (
  `idAnnouncement` INT NOT NULL auto_increment,
  `announcementTitle` VARCHAR(200) NOT NULL,
  `announcementDescription` VARCHAR(10000) NOT NULL,
  `publishDate` DATETIME NULL,
  `closeDate` DATETIME NULL,
  `announcementDisplay` ENUM('Y', 'N') NOT NULL DEFAULT 'N',
  `announcementCategory` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`idAnnouncement`),
  INDEX `fk_Announcement_Category_idx` (`announcementCategory` ASC) VISIBLE,
  CONSTRAINT `fk_Announcement_Category`
    FOREIGN KEY (`announcementCategory`)
    REFERENCES `announcement`.`categories` (`idCategory`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
