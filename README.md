# Internet Shop

## Table of Contents

* [Description](#description)
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Launch](#launch)

## Description

An application in which user can view,
 add and remove items from the shopping cart,
  create an order and edit it. 
  Administrator can view list of users, manage
  them, add and remove their roles. Administrator manage 
  list of available products.

## Introduction
Aim of the project: improve my programming skills and 
consolidate knowledge of Hibernate Framework and jdbc.

## Technologies
* Java 11
* Maven 4.0
* Servlet 4.0.1
* Hibernate 5.4.5
* MySQL database
* log4j

## Launch
1. Clone or download the project from github.com
2. Add to IntelliJ IDEA as maven project
3. Add tomcat configuration
4. Add artifact internetshop:war exploded
5. Run all queries from init_db.sql file
6. Set your actual login and password in hibernate.cfg.xml file
7. Set your login and password in DriverManager.getConnection() method in Factory.class
8. Change log4j.properties, set new path for internetshop.log
 file in your file system.


