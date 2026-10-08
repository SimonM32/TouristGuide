CREATE DATABASE tourist_guide;
USE tourist_guide;

CREATE TABLE cities(

city_id INT PRIMARY KEY AUTO_INCREMENT,
city VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE touristAttractions(

touristAttraction_id INT PRIMARY KEY AUTO_INCREMENT,
city_id INT NOT NULL,
FOREIGN KEY (city_id) REFERENCES cities(city_id),
name VARCHAR(100) NOT NULL,
description VARCHAR(300) NOT NULL
);

CREATE TABLE tags(

tag_id INT PRIMARY KEY AUTO_INCREMENT,
tag VARCHAR(100) NOT NULL UNIQUE
);


CREATE TABLE touristAttraction_tags(

touristAttraction_tags_id INT PRIMARY KEY AUTO_INCREMENT,
touristAttraction_id INT NOT NULL,
FOREIGN KEY (touristAttraction_id) REFERENCES touristAttractions(touristAttraction_id),
tag_id INT NOT NULL,
FOREIGN KEY (tag_id) REFERENCES tags(tag_id)
);
