package tn.itbs.erp.entites;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Paiement implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String numeroPaiement;
	private int numeroCompte;
	private String journal;
	private double montant;
	@DateTimeFormat(pattern = "yyyy-MM-dd")  
	private Date datePayement;
	@ManyToOne(fetch = FetchType.EAGER)
	//@JsonIdentityReference(alwaysAsId=true)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	//@JsonBackReference(value="paiements")
	private Facture facture;
	
	//private String nameImage;

	//@Column(name = "image", length = 16777215)
	//private byte[] image;

}
