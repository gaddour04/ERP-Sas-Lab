package tn.itbs.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import tn.itbs.erp.entites.LigneFacture;

public interface LigneFactureReoisitory extends JpaRepository<LigneFacture, Integer> {
	//@Query("select count(*) from LigneFacture x inner join Facture f on x.facture.id=f.id   where f.datefacture BETWEEN  (SELECT DATE_SUB(CURDATE(),INTERVAL Extract(DAY from now())-1 DAY) ) and (SELECT last_day(curdate()))") 
	@Query(nativeQuery = true, value ="SELECT count(*) FROM ligne_facture INNER JOIN facture ON ligne_facture.facture_id =facture.id where facture.datefacture BETWEEN (SELECT DATE_SUB(CURDATE(),INTERVAL Extract(DAY from now())-1 DAY) ) and (SELECT last_day(curdate())) ; ")
	public int  moisCurrent();
	
	@Query(nativeQuery = true, value ="SELECT count(*) FROM ligne_facture INNER JOIN facture ON ligne_facture.facture_id =facture.id where facture.datefacture BETWEEN (select last_day(curdate() - interval 2 month) + interval 1 day ) and (SELECT DATE_SUB(CURDATE(),INTERVAL Extract(DAY from now()) DAY)) ; ")
	public int  moisPrecedent();
	
	@Query(nativeQuery = true,value ="select DISTINCT MONTH(datefacture) as mois ,CONCAT(SUBSTR(MONTHNAME(datefacture), 1, 3),' ', YEAR(CURDATE())) as moisname ,sum(total) as sum from facture WHERE YEAR(datefacture)=YEAR(CURDATE()) and facture.numero_facture like 'FACTC%' group by MONTH(datefacture),YEAR(datefacture) ")
	List<Object> sumtotalclient();
	
	@Query(nativeQuery = true,value ="select DISTINCT MONTH(datefacture) as mois ,CONCAT(SUBSTR(MONTHNAME(datefacture), 1, 3),' ', YEAR(CURDATE())) as moisname  ,sum(total) as sum from facture WHERE YEAR(datefacture)=YEAR(CURDATE()) and facture.numero_facture like   'FACTF%' group by MONTH(datefacture),YEAR(datefacture) ")
	List<Object> sumtotalfournisseur();
	
	@Query(nativeQuery = true,value ="SELECT MONTH(datefacture),CONCAT(SUBSTR(MONTHNAME(datefacture), 1, 3),' ', YEAR(CURDATE())) as moisname ,sum(ligne_facture.qte) FROM ligne_facture INNER JOIN facture ON ligne_facture.facture_id =facture.id WHERE YEAR(facture.datefacture)=YEAR(CURDATE()) and facture.numero_facture like 'FACTC%' group by MONTH(facture.datefacture),YEAR(facture.datefacture)")
	List<Object> nbrArticleVendus();
	
	
}
//where x.id =:x 
//@Param("x") long idPaiement
/*
 SELECT count(*)
FROM ligne_facture
INNER JOIN facture ON ligne_facture.facture_id =facture.id where facture.datefacture BETWEEN  "2021-08-09" and "2021-8-15" ;
SELECT DATE_SUB(CURDATE(),INTERVAL Extract(DAY from now())-1 DAY);
SELECT DATE_SUB(CURDATE(),INTERVAL Extract(DAY from now()) DAY);
SELECT last_day(curdate());
select last_day(curdate() - interval 2 month) + interval 1 day



SELECT sum(count) FROM (SELECT COUNT(*) AS count FROM ligne_facture INNER JOIN facture ON ligne_facture.facture_id =facture.id where facture.datefacture = "2021-08-09" group by ligne_facture.facture_id ) as A


select DISTINCT MONTH(datefacture),count(*) from facture WHERE YEAR(datefacture)=YEAR(CURDATE()) group by MONTH(datefacture),YEAR(datefacture)
select DISTINCT MONTH(datefacture) as mois ,sum(total) as sum from facture WHERE YEAR(datefacture)=YEAR(CURDATE()) group by MONTH(datefacture),YEAR(datefacture)



SELECT MONTH(datefacture),sum(ligne_facture.qte) FROM ligne_facture INNER JOIN facture ON ligne_facture.facture_id =facture.id WHERE YEAR(facture.datefacture)=YEAR(CURDATE()) and facture.numero_facture like "FACTC%" group by MONTH(facture.datefacture),YEAR(facture.datefacture)
 */
