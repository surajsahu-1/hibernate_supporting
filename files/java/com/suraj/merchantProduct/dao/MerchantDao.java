package com.suraj.merchantProduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.suraj.merchantProduct.dto.Merchant;

public class MerchantDao {
	EntityManager manager=Persistence.createEntityManagerFactory("dev").createEntityManager();
	public Merchant saveMerchant(Merchant merchant) {
		EntityTransaction transaction=manager.getTransaction();
		manager.persist(merchant);
		transaction.begin();
		transaction.commit();
		return merchant;
	}
	public Merchant updateMerchant(Merchant merchant) {
		EntityTransaction transaction=manager.getTransaction();
		manager.merge(merchant);
		transaction.begin();
		transaction.commit();
		return merchant;
	}
	public Merchant verifyMerchant(long phone, String password) {
		String qry= "select m from Merchant m where m.phone=?1 and m.password=?2";
		Query q=manager.createQuery(qry);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		try {
			return (Merchant) q.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
}
