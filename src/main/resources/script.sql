CREATE DATABASE nightclubs;
CREATE USER 'nightclubuser'@'localhost' IDENTIFIED BY 'club';
GRANT ALL PRIVILEGES ON nightclubs.* TO 'nightclubuser'@'localhost';
FLUSH PRIVILEGES;
