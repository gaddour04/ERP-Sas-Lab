package tn.itbs.erp.service;

import org.springframework.data.domain.Page;


import tn.itbs.erp.entites.Evenement;

public interface IEvenementService {
	Evenement save(Evenement evenement);
	Page<Evenement> findAllPagination(int p,int s);

}
