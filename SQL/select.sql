-- JOIN types: https://www.youtube.com/watch?v=9yeOJ0ZMUYw

-- Get female biathletes and their stats
SELECT CONCAT(surname, ' ', name) as name, average_speed, average_shooting
FROM Biathlete
NATURAL JOIN (
	SELECT DISTINCT ON (email) email, name
	FROM Names
) as one_name
NATURAL JOIN Person
INNER JOIN Stats
	ON Stats.biathlete = Biathlete.biathleteID
WHERE sex='F'
ORDER BY surname;

-- Get contact information for every biathlete (email and social media link)
SELECT CONCAT(surname, ' ', name) as name, email, link
FROM Biathlete
LEFT JOIN (
	SELECT DISTINCT ON (biathlete) biathlete, link
		FROM Media
	) AS links
ON Biathlete.biathleteID = links.biathlete
NATURAL JOIN Person
NATURAL JOIN (
	SELECT DISTINCT ON (email) email, name
	FROM Names
) as one_name
ORDER BY surname;

-- Get top 10 of biathletes, who has medals (1st, 2nd and 3rd place)
SELECT CONCAT(surname, ' ', name) as name, medals_count
FROM (
	SELECT biathlete, COUNT(*) AS medals_count
	FROM LEADERBOARD
	WHERE (place < 4)
	GROUP BY biathlete
	ORDER BY medals_count DESC
	LIMIT 10
) AS biathlete_medals
INNER JOIN Biathlete
	ON Biathlete.Biathleteid = biathlete_medals.biathlete
NATURAL JOIN Person
NATURAL JOIN (
	SELECT DISTINCT ON (email) email, name
	FROM Names
) as one_name
ORDER BY medals_count DESC;

-- Get the 10 oldest competitions in Czech and Republic Germany
( 
	SELECT type as Competition, date, stadium_name, stadium_country
	FROM Competition
	INNER JOIN Holder
		ON Competition.competitionID = Holder.competition
	WHERE stadium_country IN ('Germany')
	ORDER BY date ASC
	LIMIT 5
) UNION ALL( 
	SELECT type as Competition, date, stadium_name, stadium_country
	FROM Competition
	INNER JOIN Holder
		ON Competition.competitionID = Holder.competition
	WHERE stadium_country IN ('Czech Republic')
	ORDER BY date ASC
	LIMIT 5
)
ORDER BY date ASC