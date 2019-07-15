package com.pifrans.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.pifrans.project.annotations.IdentifyFieldSearch;

@Audited
@Entity
@Table(name = "state")
public class State implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@IdentifyFieldSearch(fieldDescription = "Código", fieldSearch = "sta_id")
	private Long sta_id;

	@Column(length = 10, nullable = true)
	private String sta_initials;

	@Column(length = 100, nullable = false)
	@IdentifyFieldSearch(fieldDescription = "Nome", fieldSearch = "sta_name", fieldMain = 1)
	private String sta_name;

	@NotAudited
	@OneToMany(mappedBy = "state", orphanRemoval = false)
	@Cascade(value = { org.hibernate.annotations.CascadeType.SAVE_UPDATE, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private List<City> cities = new ArrayList<City>();
	
	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country", nullable = false)
	@ForeignKey(name = "fk_state_country")
	private Country country = new Country();
	
	@Version
	@Column(name = "version_num")
	private int versionNum;

	public Long getSta_id() {
		return sta_id;
	}

	public void setSta_id(Long sta_id) {
		this.sta_id = sta_id;
	}

	public String getSta_initials() {
		return sta_initials;
	}

	public void setSta_initials(String sta_initials) {
		this.sta_initials = sta_initials;
	}

	public String getSta_name() {
		return sta_name;
	}

	public void setSta_name(String sta_name) {
		this.sta_name = sta_name;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sta_id == null) ? 0 : sta_id.hashCode());
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
		State other = (State) obj;
		if (sta_id == null) {
			if (other.sta_id != null)
				return false;
		} else if (!sta_id.equals(other.sta_id))
			return false;
		return true;
	}
}
