
USE tourist_guide;

INSERT INTO cities(city)
VALUES ('København'),
       ('Aarhus'),
       ('Aalborg');

INSERT INTO tags(tag)
VALUES ('Historie'),
       ('Mad og drikke'),
       ('Forlystelse'),
       ('Akademi');

INSERT INTO touristAttractions(city_id, name, description)
VALUES (1,'Tivoli','Super sjovt'),
       (1,'Den lille havfrue', 'En meget fin statue'),
       (1,'Erhvervsakademi Kobenhavn','Vores skole'),
       (1,'Noma', 'Der er god mad');

INSERT INTO touristAttraction_tags(touristAttraction_id, tag_id)
VALUES (1,3),(1,2),(2,1),(3,4),(4,2);