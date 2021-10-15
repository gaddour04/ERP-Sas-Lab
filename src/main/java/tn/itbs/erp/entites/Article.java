package tn.itbs.erp.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="article")
@Data @AllArgsConstructor @NoArgsConstructor
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "code")
public class Article implements Serializable {
	@Id
	private String code;
	
	
	private String description;
	
	
	private int qte;
	
	
	private double prix;
	
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	@JsonIgnore
	//@JsonIdentityReference(alwaysAsId=true)
	private List<LigneFacture> ligneFacture=new ArrayList<>();
	
	@PreRemove
	public void checkLigneFactureBeforRemove() {
		if(!this.ligneFacture.isEmpty()) {
			System.out.println("matajamch tfaskh");
			throw new RuntimeException("Can't remove a Article that has ligne facture");
		}
	}
}
