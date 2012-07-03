package fr.prozero.linkshare.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "LS_USER")
public class LsUser implements UserDetails {

	private static final long serialVersionUID = 2L;

	public LsUser() {
	}	
	
	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "OPENID_URL")
    String openIdUrl;
	
	@Column(name = "FULLNAME")
    String fullname;
	
	@Column(name = "EMAIL", unique = true, nullable = false)
    String userEmail;
	
	@Column(name = "IS_ENABLED")
	boolean enabled = true;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    List<LsLink> userLinks = new ArrayList<LsLink>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}	

	public String getOpenIdUrl() {
		return openIdUrl;
	}

	public void setOpenIdUrl(String openIdUrl) {
		this.openIdUrl = openIdUrl;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<LsLink> getUserLinks() {
		return userLinks;
	}

	public void setUserLinks(List<LsLink> userLinks) {
		this.userLinks = userLinks;
	}	

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}
	
	@Override
	public String getUsername() {
		return userEmail;
	}

	@Override
	public String getPassword() {
		return userEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	} 	
}
