package com.pifrans.project.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import com.pifrans.project.annotations.IdentifyFieldSearch;

@Audited
@Entity
@Table(name = "country")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@IdentifyFieldSearch(fieldDescription = "Código", fieldSearch = "cou_id")
	private Long cou_id;

	@Column(nullable = false, length = 80)
	@IdentifyFieldSearch(fieldDescription = "Nome", fieldSearch = "cou_name", fieldMain = 1)
	private String cou_name;

	@Column(nullable = true, length = 15)
	private String cou_initials;

	@Version
	@Column(name = "version_num")
	private int versionNum;

	public Long getCou_id() {
		return cou_id;
	}

	public void setCou_id(Long cou_id) {
		this.cou_id = cou_id;
	}

	public String getCou_name() {
		return cou_name;
	}

	public void setCou_name(String cou_name) {
		this.cou_name = cou_name;
	}

	public String getCou_initials() {
		return cou_initials;
	}

	public void setCou_initials(String cou_initials) {
		this.cou_initials = cou_initials;
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
		result = prime * result + ((cou_id == null) ? 0 : cou_id.hashCode());
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
		Country other = (Country) obj;
		if (cou_id == null) {
			if (other.cou_id != null)
				return false;
		} else if (!cou_id.equals(other.cou_id))
			return false;
		return true;
	}
}
