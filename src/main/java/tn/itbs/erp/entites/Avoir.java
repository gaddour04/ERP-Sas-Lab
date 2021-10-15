package tn.itbs.erp.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Avoir implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String numeroAvoir;
	@ManyToOne(fetch = FetchType.EAGER )
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	//@JsonBackReference
	private Client client; 
	@ManyToOne(fetch = FetchType.EAGER )
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	//@JsonBackReference
	private Fournisseur fournisseur; 
	private Date dateAvoir;
	private String description;
	private double montant;
	@ManyToOne(fetch = FetchType.EAGER )
	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	@JsonBackReference(value="avoirs")
	private Facture facture;
	
	
	

}
