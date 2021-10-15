package tn.itbs.erp.entites;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)

public class LigneFacture implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private int remise;
	private int qte;
	private double total;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    //@JsonIdentityReference(alwaysAsId=true)
    //@JsonBackReference(value="article")
    private Article article;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
    //@JsonIdentityReference(alwaysAsId=true)
    //@JsonBackReference(value="lignefacture")
    private Facture facture;

	public LigneFacture(int remise, Article article,int qte,double total) {
		super();
		this.remise = remise;
		this.article = article;
		this.qte=qte;
		this.total=total;
	}
	
	
    
    
    
    

}
