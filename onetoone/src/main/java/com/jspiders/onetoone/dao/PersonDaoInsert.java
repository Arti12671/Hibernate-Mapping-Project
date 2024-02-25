package com.jspiders.onetoone.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.onetoone.dto.AadharCardDto;
import com.jspiders.onetoone.dto.PersonDto;

public class PersonDaoInsert {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;
	
	public static void main(String[] args) {
		
		PersonDto person = new PersonDto();
		person.setName("Umesh");
		person.setEmail("umesh@gmail.com");
		person.setMobile(7876543211l);
		person.setAge(24);
		
		AadharCardDto aadharCard  = new AadharCardDto();
		aadharCard.setAadharNumber(323456781235l);
		
		person.setAadharCardDto(aadharCard);
		
		openConnection();
		entityTransaction.begin();
		
		entityManager.persist(aadharCard);
		entityManager.persist(person);
		
		entityTransaction.commit();
		closeConnection();
	}
	
	private static void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory("person");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction  = entityManager.getTransaction();
	}
	
	private static void closeConnection() {
		if (entityManagerFactory!=null) {
			entityManagerFactory.close();
		}
		if (entityManager!=null) {
			entityManager.close();
		}
		if (entityTransaction!=null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}
}
