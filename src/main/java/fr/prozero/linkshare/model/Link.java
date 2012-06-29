package fr.prozero.linkshare.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import fr.prozero.linkshare.model.adapter.JaxbDateAdapter;

@Entity
@Table(name = "LS_LINK")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Link {		
	
	public Link(String link, Date createDate, User user) {
		this.link = link;
		this.createDate = createDate;
		this.user = user;
	}

	public Link() {
	}

	@Id
	@Column(name = "LINK_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "LINK")
	private String link;
	
	@XmlJavaTypeAdapter(JaxbDateAdapter.class)
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE")
	private Date createDate;
	
	@ManyToOne
    User user;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}	
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
