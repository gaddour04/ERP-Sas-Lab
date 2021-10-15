package tn.itbs.erp.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor

//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Client implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String matriculeFiscale;
	
	@Email
	@NotEmpty
	private String email;
	
	private String firstName;
	private String lastName;
	
	//@Size(min = 8)
	private int telephone;
	private String adresse;
	
	
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	//@JsonIdentityReference(alwaysAsId=true)
	//@JsonManagedReference
	private List<Avoir> avoirs;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	//@JsonIdentityReference(alwaysAsId=true)
	//@JsonManagedReference(value = "client")
	@JsonIgnore
	private List<Facture> factures;
	
	@PreRemove
	public void checkLigneFactureBeforRemove() {
		if(!this.factures.isEmpty()) {
			System.out.println("matajamch tfaskh");
			throw new RuntimeException("Can't remove a Client that has facture ");
		}
	}

	
	
	
	
}
