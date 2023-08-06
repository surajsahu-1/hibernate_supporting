package com.suraj.merchantProduct.controller;

import java.util.List;
import java.util.Scanner;

import com.suraj.merchantProduct.dao.MerchantDao;
import com.suraj.merchantProduct.dao.ProductDao;
import com.suraj.merchantProduct.dto.Merchant;
import com.suraj.merchantProduct.dto.Product;

public class Merchant_productController {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		MerchantDao mDao = new MerchantDao();
		ProductDao pDao = new ProductDao();
		System.out.println("Enter sequence number(1-8) to perform task");
		System.out.println("1. Save Merchent");
		System.out.println("2. Update Merchent");
		System.out.println("3. Add Product");
		System.out.println("4. Verify Merchent by Phone And Password");
		System.out.println("5. View Products by merchant Id");
		System.out.println("6. View Product by Brand");
		System.out.println("7. View Product by Category");
		System.out.println("8. Update Product");
		int choice = s.nextInt();
		switch (choice) {
		case 1: {
			System.out.println("Enter Your name, Gst, Phone , Email and Password");
			Merchant m = new Merchant();
			m.setName(s.next());
			m.setGst(s.next());
			m.setPhone(s.nextLong());
			m.setEmail(s.next());
			m.setPassword(s.next());
			System.out.println("Merchant save with id: " + mDao.saveMerchant(m).getId());
		}
			break;
		case 2: {
			System.out.println("Enter Your Merchant Id to update Your details");
			int id = s.nextInt();
			System.out.println("Enter your name,gst,phone,email and password to save");
			Merchant u = new Merchant();
			u.setId(id);
			u.setName(s.next());
			u.setGst(s.next());
			u.setPhone(s.nextLong());
			u.setEmail(s.next());
			u.setPassword(s.next());
			mDao.updateMerchant(u);
			System.out.println("merchant updated Succesfully");
			break;
		}
		case 3: {
			System.out.println("Enter Merchant id for the product : ");
			int p_id = s.nextInt();
			System.out.println("Enter Product Name, Brand, Category, Descreption, and  Cost");
			Product p = new Product();
			p.setName(s.next());
			p.setBrand(s.next());
			p.setCategory(s.next());
			p.setDescription(s.next());
			p.setCost(s.nextDouble());
			System.out.println("Product is added with  id : " + pDao.saveProduct(p, p_id).getId());
			break;
		}
		case 4: {
			System.out.println("Enter Phone and Password : ");
			long phone = s.nextLong();
			String password = s.next();
			Merchant m = mDao.verifyMerchant(phone, password);
			if (m != null) {
				System.out.println("Verified Succesfully");
				System.out.println("Merchant Id:" + m.getId());
				System.out.println("Merchant Name:" + m.getName());
				System.out.println("GST:" + m.getGst());
				System.out.println("Phone:" + m.getPhone());
				System.out.println("Email:" + m.getEmail());
			} else {
				System.out.println("Wrong Cradiential: ");
			}
			break;
		}
		case 5: {
			System.out.println("Enter Merchant id to get product: ");
			List<Product> products = pDao.viewProduct(s.nextInt());
			if (products.size() > 0) {
				for (Product p : products) {
					System.out.println("product Id:" + p.getId());
					System.out.println("product name:" + p.getName());
					System.out.println("Brand:" + p.getBrand());
					System.out.println("category:" + p.getCategory());
					System.out.println("Cost:" + p.getCost());
					System.out.println("About product:" + p.getDescription());
					System.out.println("==============================");
				}
			} else {
				System.err.println("You have entered an Invalid Merchant id");
			}
			break;
		}
		case 6:{
			System.out.println("Enter Brand to Fetch data");
			List<Product> products = pDao.viewProductbyBrand(s.next());
			if (products.size() > 0) {
				for (Product p : products) {
					System.out.println("product Id:" + p.getId());
					System.out.println("product name:" + p.getName());
					System.out.println("Brand:" + p.getBrand());
					System.out.println("category:" + p.getCategory());
					System.out.println("Cost:" + p.getCost());
					System.out.println("About product:" + p.getDescription());
					System.out.println("==============================");
				}
			} else {
				System.err.println("You have entered an Invalid Brand id");
			}
			break;
		}
		case 7:{
			System.out.println("Enter Category to Fetch data");
			List<Product> products = pDao.viewProductbyCategory(s.next());
			if (products.size() > 0) {
				for (Product p : products) {
					System.out.println("product Id:" + p.getId());
					System.out.println("product name:" + p.getName());
					System.out.println("Brand:" + p.getBrand());
					System.out.println("category:" + p.getCategory());
					System.out.println("Cost:" + p.getCost());
					System.out.println("About product:" + p.getDescription());
					System.out.println("==============================");
				}
			} else {
				System.err.println("You have entered an Invalid Category id");
			}
			break;
		}
		case 8:{
			System.out.println("Enter Merchant id to update the product : ");
			int p_id = s.nextInt();
			System.out.println("Enter Product Name, Brand, Category, Descreption, and  Cost");
			Product p = new Product();
			p.setName(s.next());
			p.setBrand(s.next());
			p.setCategory(s.next());
			p.setDescription(s.next());
			p.setCost(s.nextDouble());
			System.out.println("Product is added with  id : " + pDao.updateProduct(p, p_id));
			break;
		}
		default:
			System.err.println("Please Enter in range");
			break;
		}
	}
}