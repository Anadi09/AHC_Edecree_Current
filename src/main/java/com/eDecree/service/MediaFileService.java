package com.eDecree.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eDecree.model.MediaFile;
import com.eDecree.model.MediaFileStage;

@Service
public class MediaFileService {
	
	@PersistenceContext(unitName="persistenceUnitDMS")
	@Qualifier(value = "entityManagerFactoryDMS")
	private EntityManager em;
	
	@Transactional
	public MediaFile save(MediaFile s) {
		MediaFile master = null;
		try {
			master = em.merge(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}
	
	@Transactional
	public MediaFileStage saveStage(MediaFileStage mfs) {
		MediaFileStage master = null;
		try {
			master = em.merge(mfs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return master;
	}

	public List<MediaFile> getMediaFilesByDocId(Long id) {
		// TODO Auto-generated method stub
		List<MediaFile> result = em.createQuery("SELECT d FROM MediaFile d WHERE d.mf_fd_mid=:mf_fd_mid")
				.setParameter("mf_fd_mid", id).getResultList();

		return result;
	}

	public MediaFile getDocumentById(Long mf_id) {
		// TODO Auto-generated method stub
		MediaFile r= new MediaFile();
		try {
			Query query  =  em.createQuery("SELECT r from MediaFile r WHERE r.mf_id =:id");
			query.setParameter("id", mf_id);
			r= (MediaFile) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public MediaFile checkMediaFileExist(Long folder_id, String encryptedfilename) {
		// TODO Auto-generated method stub
		MediaFile d= new MediaFile();
		try {
			Query query  =  em.createQuery("SELECT d FROM MediaFile d WHERE d.mf_folder_id =:folder_id AND d.mf_encrypted_name=:encryptedfilename");
			query.setParameter("folder_id", folder_id);
			query.setParameter("encryptedfilename", encryptedfilename);
			d=(MediaFile) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
} 
