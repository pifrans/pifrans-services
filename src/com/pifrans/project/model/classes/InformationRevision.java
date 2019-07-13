package com.pifrans.project.model.classes;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import com.pifrans.project.listener.CustomListener;

@javax.persistence.Entity
@Table(name = "revinfo")
@RevisionEntity(CustomListener.class)
public class InformationRevision extends DefaultRevisionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@ForeignKey(name = "entity_fk")
	@JoinColumn(nullable = false, name = "entity")
	private Entity entity;

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

}
