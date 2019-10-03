CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DECIMAL(4,2) NOT NULL,
  PRIMARY KEY (`item_id`));

CREATE TABLE `users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `surname` VARCHAR(45) NULL,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `token` VARCHAR(45) NULL,
  PRIMARY KEY (`user_id`));

CREATE TABLE `orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `date` DATE NULL,
  PRIMARY KEY (`order_id`),
  INDEX `orders.user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `orders.user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `orders_items` (
  `orders_items_id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`orders_items_id`),
  INDEX `orders_items.orders.fk_idx` (`order_id` ASC) VISIBLE,
  INDEX `orders_items.items_fk_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `orders_items.orders_fk`
    FOREIGN KEY (`order_id`)
    REFERENCES `orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `orders_items.items_fk`
    FOREIGN KEY (`item_id`)
    REFERENCES `items` (`item_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `buckets` (
  `bucket_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`bucket_id`),
  INDEX `buckets.user_id_fk_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `buckets.user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`));

CREATE TABLE `users_roles` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_role_id`),
  INDEX `users_roles.user_id_fk_idx` (`user_id` ASC) VISIBLE,
  INDEX `users_roles.role_id_fk_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `users_roles.user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `users_roles.role_id_fk`
    FOREIGN KEY (`role_id`)
    REFERENCES `roles` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `buckets_items` (
  `bucket_item_id` INT NOT NULL AUTO_INCREMENT,
  `bucket_id` INT NOT NULL,
  `item_id` INT NOT NULL,
  PRIMARY KEY (`bucket_item_id`),
  INDEX `buckets_items.bucket_id_fk_idx` (`bucket_id` ASC) VISIBLE,
  INDEX `buckets_items.itemt_id_fk_idx` (`item_id` ASC) VISIBLE,
  CONSTRAINT `buckets_items.bucket_id_fk`
    FOREIGN KEY (`bucket_id`)
    REFERENCES `buckets` (`bucket_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `buckets_items.itemt_id_fk`
    FOREIGN KEY (`item_id`)
    REFERENCES `items` (`item_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `roles` (`role_name`) VALUES ('ADMIN');
INSERT INTO `roles` (`role_name`) VALUES ('USER');

