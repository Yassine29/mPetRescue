package com.yassine.mpetrescue.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import static java.util.Objects.requireNonNull;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
@Entity
@Table(name = "user")
public class User implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "username", unique = true)
	String username;

	@Column(name = "password")
	String password;

	@Column(name = "firstname")
	String firstName;

	@Column(name = "lastname")
	String lastName;

	@Column(name = "email")
	String email;

	@Column(name = "rang")
	String Rang;

	@Column(name = "photo")
	String photo;

	@Column(name = "nbr_cas")
	Integer nbr_cas;

	@OneToMany(mappedBy = "rapporteur")
	@JsonIgnore
	private List<Cas> cas = new ArrayList<Cas>();

	@JsonCreator
	public User(@JsonProperty("id") final Long id, @JsonProperty("username") final String username,
			@JsonProperty("password") final String password, @JsonProperty("firstName") final String firstName,
			@JsonProperty("lastName") final String lastName, @JsonProperty("email") final String email,
			@JsonProperty("rang") final String rang, @JsonProperty("photo") final String photo,
			@JsonProperty("nbr_cas") final Integer nbr_cas) {
		super();
		this.id = id;
		this.username = requireNonNull(username);
		this.password = requireNonNull(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.Rang = rang;
		this.photo = photo;
		this.nbr_cas = nbr_cas;
	}

	@JsonIgnore
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
