CREATE SCHEMA `internetshop` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(225) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`item_id`));

INSERT INTO `internetshop`, `items` ('name', 'price') VALUES ('iPhone 11', '1000');

SELECT * FROM internetshop.items where item_id=1;

INSERT INTO internetshop.items (name, price) VALUES ('kot', '12.')

UPDATE internetshop.items SET name='robot', price='1234.' where item_id=23

DELETE FROM internetshop.items WHERE item_id=17

