-- creates the table user for hibernate project

CREATE TABLE `hibernate_test`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nick` VARCHAR(20) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `is_admin` TINYINT NOT NULL DEFAULT 0,
  `last_pw_change` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `old_password` VARCHAR(64) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nick_UNIQUE` (`nick` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'users for the application';