CREATE TABLE GENRES
(MOVIEID int,
GENRE VARCHAR(30)
);

CREATE TABLE MOVIE_COUNTRY
(MOVIEID int,
COUNTRY VARCHAR(30)
);

CREATE TABLE MOVIE_LOCATION
(MOVIEID int,
LOC1 VARCHAR(100)
);

CREATE TABLE MOVIES(
MOVIEID int,
TITLE VARCHAR(150),
YEAR int,
AR FLOAT(126),
NR int,
TCR FLOAT(126),
TCRN int,
AUR FLOAT(126),
AURN int
);

CREATE TABLE MOVIETAGGED 
(MOVIEID int,
TAGID int,
TAGW int
);

CREATE TABLE TAG
(TAGID int,
TAGVALUE  VARCHAR(150)
);


create index movieYear on MOVIES(YEAR);
create index movieGenre on GENRES(GENRE);
create index movieCountry on MOVIE_COUNTRY(COUNTRY);
create index movieLoc on MOVIE_LOCATION(LOC1);
create index movietag on TAG(TAGID);
create index movietagV on TAG(TAGVALUE);
create index movieIDV on MOVIES(MOVIEID);
create index movietagI on MOVIETAGGED(TAGID);
create index movieIDG on GENRES(MOVIEID);
create index movietagW on MOVIETAGGED(TAGW);