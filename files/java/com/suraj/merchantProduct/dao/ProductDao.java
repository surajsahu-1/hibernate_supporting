package com.suraj.merchantProduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.suraj.merchantProduct.dto.Merchant;
import com.suraj.merchantProduct.dto.Product;

public class ProductDao {
	EntityManager manager = Persistence.createEntityManagerFactory("dev").createEntityManager();

	public Product saveProduct(Product product, int merchant_id) {
		Merchant m = manager.find(Merchant.class, merchant_id);
		if (m != null) {
			product.setMerchant(m);
			m.getProducts().add(product);
			EntityTransaction transaction = manager.getTransaction();
			manager.persist(product);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}
	public List<Product> viewProduct(int id) {
		Merchant m=manager.find(Merchant.class, id);
		if(m!=null) {
			return  m.getProducts();
		}
		else {
			return null;
		}
	}
	
	public List<Product> viewProductbyBrand(String brand) {
		String qry="select p from product p where p.brand=?1";
		Query q=manager.createQuery(qry);
		q.setParameter(1, brand);
		try {
			return  (List<Product>) q.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Product> viewProductbyCategory(String category) {
		String qry="select p from product p where p.category=?1";
		Query q=manager.createQuery(qry);
		q.setParameter(1, category);
		try {
			List<Product> pro=(List<Product>) q.getSingleResult();
			return  pro;
		}catch (NoResultException e) {
			return null;
		}
	}
	
	public Product updateProduct(Product product, int merchant_id) {
		
		Merchant m = manager.find(Merchant.class, merchant_id);
		if (m != null) {
			product.setMerchant(m);
			m.getProducts().add(product);
			EntityTransaction transaction = manager.getTransaction();
			manager.merge(product);
			transaction.begin();
			transaction.commit();
			return product;
		}
		return null;
	}

}
