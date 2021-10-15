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
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "user_app")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class)
public class AppUser implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Email
	@NotEmpty
	private String email;
	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) 
	private String password;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name="user_role",
			joinColumns = @JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name="role_id"))
	//@JsonManagedReference
	private List<Role> roles=new ArrayList<>();
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	/*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Avoir> avoirs;*/

	public AppUser(@Email @NotEmpty String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	
}
