package tn.itbs.erp.entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Evenement implements Serializable {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private String message;
	private Date dateStart;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date dateStop; 
	 
	 @OneToOne(mappedBy = "evenement")
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	 //@JsonBackReference(value="evenement")
	private Facture factures;

}
