package com.pifrans.project.listener;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import com.pifrans.framework.util.FrameworkUtil;
import com.pifrans.project.model.classes.Entity;
import com.pifrans.project.model.classes.InformationRevision;

@Service
public class CustomListener implements RevisionListener, Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revisionEntity) {
		InformationRevision informationRevision = (InformationRevision) revisionEntity;
		Long idUser = FrameworkUtil.geThreadLocal().get();
		Entity entity = new Entity();

		if (idUser != null && idUser != 0L) {
			entity.setEnt_id(idUser);
			informationRevision.setEntity(entity);
		}
	}

}
