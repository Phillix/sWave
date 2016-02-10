DROP DATABASE IF EXISTS SWAVE;
CREATE DATABASE SWAVE;
USE SWAVE;

CREATE TABLE USERS (
    USERID     INT(5)      NOT NULL AUTO_INCREMENT,
    USERNAME   VARCHAR(20) NOT NULL,
    EMAIL      VARCHAR(70) NOT NULL,
    FNAME      VARCHAR(20),
    LNAME      VARCHAR(20),
    PASSWD     VARCHAR(97) NOT NULL,
    ADD1       VARCHAR(30),
    ADD2       VARCHAR(30),
    CITY       VARCHAR(20),
    COUNTY     VARCHAR(20),
    ADMIN      BOOLEAN     NOT NULL,
    CONSTRAINT PK_USERID   PRIMARY KEY (USERID)
);

/*
CREATE TABLE ORDERS (

);

CREATE TABLE ORDER_SONG (

);

CREATE TABLE TICKETS (

);

CREATE TABLE ADS (
    AD_ID  INT(4)      NOT NULL,
    AD_URL VARCHAR(20) NOT NULL
);

CREATE TABLE SONGS (
    SONG_ID INT(4)       NOT NULL,
    TITLE   VARCHAR(20)  NOT NULL,
    ARTIST  VARCHAR(30),
    GENRE   VARCHAR(20),
    RELYEAR INT(4),
    LICENCE VARCHAR(300) NOT NULL
);

CREATE TABLE SONG_DATA (
    SONG_ID INT(4)     NOT NULL,
    DATA    MEDIUMBLOB NOT NULL
);

CREATE TABLE PLAYLISTS (
    PLAY_ID INT(6) NOT NULL,
    USER_ID INT(5)
);

CREATE TABLE PLAYLIST_ENTRIES (
    PLAY_ID INT(6) NOT NULL,
    SONG_ID INT(4) NOT NULL
);

CREATE TABLE STREAMS (
    STREAM_ID INT(3)       NOT NULL,
    TITLE     VARCHAR(50)  NOT NULL,
    STR_URL   VARCHAR(100) NOT NULL
);
*/
