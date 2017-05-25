-- Question 1
EXPLAIN SELECT distinct titre 
FROM film  
WHERE realisateur = 'Tarentino' ;

-- Question 2
SELECT distinct c.salle 
FROM cinema c NATURAL JOIN film f
WHERE f.realisateur = 'Tarentino' and f.titre = c.titre;

--Question 3
SELECT distinct c.salle 
FROM cinema c 
WHERE c.salle NOT IN(
SELECT salle 
FROM cinema c2 NATURAL JOIN film f2
WHERE f2.realisateur = 'Tarentino');

--Question 4
SELECT distinct c.salle
FROM cinema c 
WHERE c.salle NOT IN
(SELECT salle 
FROM film f NATURAL JOIN cinema c2
WHERE f.realisateur <> 'Tarentino');

--Question 5
SELECT distinct c.nomc
FROM cinema c NATURAL JOIN film f NATURAL JOIN jouedans j
WHERE f.realisateur = j.noma and f.titre = j.titre and f.titre = c.titre;

--Question 6
SELECT distinct f.titre
FROM cinema c NATURAL JOIN film f NATURAL JOIN jouedans j
WHERE f.realisateur = j.noma and f.titre = j.titre and f.titre = c.titre;
