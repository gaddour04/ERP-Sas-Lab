package tn.itbs.erp.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
//@JsonIgnoreProperties(value = {"paiements", "handler","hibernateLazyInitializer"}, allowSetters = true) //https://stackoverflow.com/questions/30892298/infinite-loop-with-spring-boot-in-a-one-to-many-relation
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Facture implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String numeroFacture;
	//numero de facture
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date datefacture;
	private double total_unitaire;
	private double tva;
	private double timbre;
	private double total;
	
	@ManyToOne(fetch = FetchType.EAGER )
	//@JsonManagedReference(value = "client")
	private Client client;
	
	@ManyToOne(fetch = FetchType.EAGER )
	//@JsonManagedReference(value = "client")
	private Fournisseur fournisseur;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "evenement_id", referencedColumnName = "id")
	//@JsonBackReference(value="evenement")
	private Evenement evenement;
	
	
	@OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
	//@JsonManagedReference(value="lignefacture")
    private List<LigneFacture> lignefactures;
	
	@OneToMany(mappedBy = "facture",cascade = CascadeType.ALL)
	//@JsonManagedReference(value="paiements")
	private List<Paiement> paiements;
	//etat et date d'etat non payé -partiel payé -payé
	private String etatFacture;
	
	@OneToMany(mappedBy = "facture", cascade = CascadeType.ALL)
	//@JsonManagedReference(value="avoirs")
	private List<Avoir> avoirs;
	
	/*
	 * public Facture(AppUser user,Date datefacture, LigneFacture... ligneFactures)
	 * { this.user = user; this.datefacture=datefacture; for(LigneFacture
	 * lignefacture : ligneFactures)lignefacture.setFacture(this);
	 * 
	 * this.lignefactures = Stream.of(ligneFactures).collect(Collectors.toSet()); }
	 */
	 
	
	
	
	

}
