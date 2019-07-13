package com.pifrans.project.model.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

@Audited
@javax.persistence.Entity
public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long ent_id;
	private String ent_user = null;
	private String ent_password;
	private boolean ent_inactive = false;
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_lastAccess;

	public Long getEnt_id() {
		return ent_id;
	}

	public void setEnt_id(Long ent_id) {
		this.ent_id = ent_id;
	}

	public String getEnt_user() {
		return ent_user;
	}

	public void setEnt_user(String ent_user) {
		this.ent_user = ent_user;
	}

	public String getEnt_password() {
		return ent_password;
	}

	public void setEnt_password(String ent_password) {
		this.ent_password = ent_password;
	}

	public boolean getEnt_inactive() {
		return ent_inactive;
	}

	public void setEnt_inactive(boolean ent_inactive) {
		this.ent_inactive = ent_inactive;
	}

	public Date getEnt_lastAccess() {
		return ent_lastAccess;
	}

	public void setEnt_lastAccess(Date ent_lastAccess) {
		this.ent_lastAccess = ent_lastAccess;
	}

}
