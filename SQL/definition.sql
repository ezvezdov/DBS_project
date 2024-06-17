CREATE TABLE IF NOT EXISTS Person(
	email VARCHAR(128) PRIMARY KEY,
	surname VARCHAR(128) NOT NULL,
	sex CHAR(1) DEFAULT NULL,
	
	CONSTRAINT check_email
		CHECK (email like '_%@_%.__%'),
	CONSTRAINT check_sex
		CHECK (sex='M' or sex='F')
);

CREATE TABLE IF NOT EXISTS Names(
	email VARCHAR(128) NOT NULL CONSTRAINT email_fk_person REFERENCES Person (email) ON UPDATE CASCADE ON DELETE CASCADE, 
	name VARCHAR(128) NOT NULL,
	PRIMARY KEY (email,name)
);

CREATE TABLE IF NOT EXISTS Login(
	username VARCHAR(64) PRIMARY KEY,
	password VARCHAR(64) NOT NULL 
);

CREATE TABLE IF NOT EXISTS Site(
	email VARCHAR(128) PRIMARY KEY,
	username VARCHAR(64) UNIQUE DEFAULT NULL,
	
	CONSTRAINT email_fk_person
		FOREIGN KEY (email)
		REFERENCES Person (email)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT username_fk_login
		FOREIGN KEY (username)
		REFERENCES Login (username)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Viewer(
	email VARCHAR(128) PRIMARY KEY,
	viewerID CHAR(32) UNIQUE NOT NULL,
	CONSTRAINT email_fk_person
		FOREIGN KEY (email)
		REFERENCES Person (email)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS Ticket(
	ticketID CHAR(32) PRIMARY KEY,
	transactionID CHAR(32) UNIQUE DEFAULT NULL,
	sector VARCHAR(64) NOT NULL,
	cost DECIMAL(8,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS Purchase(
	viewerID CHAR(32) PRIMARY KEY,
	ticketID CHAR(32) UNIQUE NOT NULL,
	CONSTRAINT viewerID_fk_Viewer
		FOREIGN KEY (viewerID)
		REFERENCES Viewer (viewerID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT ticketID_fk_Ticket
		FOREIGN KEY (ticketID)
		REFERENCES Ticket (ticketID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Staff(
	email VARCHAR(128) NOT NULL CONSTRAINT email_fk_person REFERENCES Person (email) ON UPDATE CASCADE ON DELETE CASCADE, 
	employeeID CHAR(32) PRIMARY KEY,
	position VARCHAR(64) NOT NULL
);
CREATE TABLE IF NOT EXISTS Stadium(
	name VARCHAR(128) NOT NULL,
	city VARCHAR(64) NOT NULL,
	country VARCHAR(64) NOT NULL,
	PRIMARY KEY (name,city,country)
);
CREATE TABLE IF NOT EXISTS Work(
	employe CHAR(32) NOT NULL CONSTRAINT employe_fk_Staff REFERENCES Staff (employeeID),
	stadium_name VARCHAR(64) NOT NULL,
	stadium_city VARCHAR(64) NOT NULL,
	stadium_country VARCHAR(64) NOT NULL,
	PRIMARY KEY (employe, stadium_name,stadium_city,stadium_country),

	CONSTRAINT stadium_info_fk_Stadium
		FOREIGN KEY (stadium_name, stadium_city, stadium_country)
		REFERENCES Stadium (name,city,country)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Sale(
	ticketID CHAR(32) NOT NULL CONSTRAINT ticketID_fk_Ticket REFERENCES Ticket (ticketID) ON UPDATE CASCADE ON DELETE CASCADE,
	stadium_name VARCHAR(64) NOT NULL,
	stadium_city VARCHAR(64) NOT NULL,
	stadium_country VARCHAR(64) NOT NULL,
	PRIMARY KEY (ticketID, stadium_name,stadium_city,stadium_country),
	
	CONSTRAINT stadium_info_fk_Stadium
		FOREIGN KEY (stadium_name, stadium_city, stadium_country)
		REFERENCES Stadium (name,city,country)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Biathlete(
	email VARCHAR(128) PRIMARY KEY CONSTRAINT email_fk_person REFERENCES Person (email) ON UPDATE CASCADE ON DELETE NO ACTION,
	biathleteID CHAR(32) UNIQUE NOT NULL,
	points INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS Media(
	biathlete CHAR(32) NOT NULL CONSTRAINT biathlete_fk_Biathlete REFERENCES Biathlete (biathleteID) ON UPDATE CASCADE ON DELETE CASCADE,
	link VARCHAR(128) NOT NULL,
	PRIMARY KEY(biathlete,link)
);

CREATE TABLE IF NOT EXISTS Stats(
	biathlete CHAR(32) PRIMARY KEY,
	average_speed DECIMAL(8,2) NOT NULL,
	average_shooting INTEGER NOT NULL,
	
	CONSTRAINT CHK_speed
		CHECK (average_speed>=0),
	CONSTRAINT CHK_shooting
		CHECK (average_shooting>=0 AND average_shooting<=100),
	
	CONSTRAINT biathlete_fk_Biathlete
		FOREIGN KEY (biathlete)
		REFERENCES Biathlete (biathleteID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Teammates(
	teammate CHAR(32) PRIMARY KEY,
	biathlete CHAR(32) NOT NULL CONSTRAINT biathlete_fk_Biathlete REFERENCES Biathlete (biathleteID),
	
	CONSTRAINT teammate_fk_Biathlete
		FOREIGN KEY (teammate)
		REFERENCES Biathlete (biathleteID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Competition(
	competitionID CHAR(32) PRIMARY KEY,
	type VARCHAR(64) NOT NULL,
	date DATE NOT NULL,
	time TIME NOT NULL
);

CREATE TABLE IF NOT EXISTS Holder(
	competition CHAR(32) CONSTRAINT competition_fk_Competition REFERENCES Competition (competitionID),
	stadium_name VARCHAR(64) NOT NULL,
	stadium_city VARCHAR(64) NOT NULL,
	stadium_country VARCHAR(64) NOT NULL,
	PRIMARY KEY (competition, stadium_name,stadium_city,stadium_country),
	
	CONSTRAINT stadium_info_fk_Stadium
		FOREIGN KEY (stadium_name, stadium_city, stadium_country)
		REFERENCES Stadium (name,city,country)	
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Leaderboard(
	place SMALLINT NOT NULL,
	competition CHAR(32) NOT NULL CONSTRAINT competition_fk_Competition REFERENCES Competition (competitionID) ON UPDATE CASCADE ON DELETE NO ACTION,
	biathlete CHAR(32) NOT NULL CONSTRAINT biathlete_fk_Biathlete REFERENCES Biathlete (biathleteID) ON UPDATE CASCADE ON DELETE NO ACTION,
	recordID CHAR(32) NOT NULL UNIQUE,
	PRIMARY KEY(place,competition,biathlete),
	
	CONSTRAINT CHK_place
		CHECK (place>=1)
	
);

CREATE TABLE IF NOT EXISTS Results(
	competition CHAR(32) NOT NULL CONSTRAINT competition_fk_Competition REFERENCES Competition (competitionID) ON UPDATE CASCADE ON DELETE NO ACTION,
	recordID CHAR(32) NOT NULL CONSTRAINT recordID_fk_Leaderboard REFERENCES Leaderboard (recordID) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY(competition, recordID)
);

CREATE TABLE IF NOT EXISTS Finish(
	biathlete CHAR(32) NOT NULL CONSTRAINT biathlete_fk_Biathlete REFERENCES Biathlete (biathleteID) ON UPDATE CASCADE ON DELETE NO ACTION,
	recordID CHAR(32) NOT NULL CONSTRAINT recordID_fk_Leaderboard REFERENCES Leaderboard (recordID) ON UPDATE CASCADE ON DELETE CASCADE,
	PRIMARY KEY(biathlete, recordID)
);

