package com.pifrans.project.model.classes;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;

import com.pifrans.project.annotations.IdentifyFieldSearch;

@Audited
@Entity
@Table(name = "city")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@IdentifyFieldSearch(fieldDescription = "Código", fieldSearch = "cit_id", fieldMain = 1)
	private Long cit_id;

	@Column(length = 100, nullable = false)
	@IdentifyFieldSearch(fieldDescription = "Descrição", fieldSearch = "cid_description", fieldMain = 2)
	private String cid_description;

	@Basic
	@ManyToOne
	@JoinColumn(name = "state", nullable = false)
	@ForeignKey(name = "fk_city_state")
	@IdentifyFieldSearch(fieldDescription = "Estado", fieldSearch = "state.sta_name")
	private State state = new State();

	@Version
	@Column(name = "version_num")
	private int versionNum;

	public Long getCit_id() {
		return cit_id;
	}

	public void setCit_id(Long cit_id) {
		this.cit_id = cit_id;
	}

	public String getCid_description() {
		return cid_description;
	}

	public void setCid_description(String cid_description) {
		this.cid_description = cid_description;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
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
		result = prime * result + ((cit_id == null) ? 0 : cit_id.hashCode());
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
		City other = (City) obj;
		if (cit_id == null) {
			if (other.cit_id != null)
				return false;
		} else if (!cit_id.equals(other.cit_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "City [cit_id=" + cit_id + ", cid_description=" + cid_description + "]";
	}

}
