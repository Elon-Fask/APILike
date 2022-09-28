package com.apilike.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.apilike.entity.ReQuest;
import com.apilike.entity.enums.Profile;
import com.apilike.entity.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements Serializable {

	/**
	 * Written By Atman Boulaajaili
	 * https://github.com/Elon-Fask 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotBlank
	@Column(name = "username")
	@Size(min=6 , max=18)
	private String username;
	
	@NotBlank
	@Column(unique = true, name = "email")
	private String email;
	
	@JsonIgnore
	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(min=6, max=16)
	@Column(name = "password")
	private String password;

	private Integer type;
	
	
	@ElementCollection
	@CollectionTable(name = "Profiles")
	private Set<Integer> profiles = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "phone")
	private Set<String> phone = new HashSet<>();
	/*
	 * I chose to create a set list,
	 * since the telephone is totally dependent on the Client,
	 * so we already avoided a problem 
	 * that would be the repetition since I have a set list
	 * 
	 * */
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<ReQuest> request = new ArrayList<>();
	
	
	public User() {
		addProfile(Profile.USER);
	}
	
	public User(Integer id, String username, String email, String password, UserType type) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		addProfile(Profile.USER);
		this.type = (type == null) ? null : type.getCode(); /*A trick made to transform the type into NUMBERS,
		                                                      in the case of Integer, 
		                                                      so for the outside world what is left is the name,
		                                                      already in the inside is your ID, 
		                                                      that's why it is being declared as Integer!
		                                               		*/
	}
	
	public Set<Profile> getProfiles(){
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());	
	}
	private void addProfile(Profile profile) {
		profiles.add(profile.getCode()); //Add a profil
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ReQuest> getRequest() {
		return request;
	}

	public void setRequest(List<ReQuest> request) {
		this.request = request;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
