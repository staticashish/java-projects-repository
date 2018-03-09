package com.example.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Address;
import com.example.entity.UserDetails;
import com.example.entity.UserKey;

public class HibernateTest {

	public static void main(String[] args) {

		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		
		//------------------ Save Entity [START]---------------//
		// Open session.
		Session session = factory.openSession();
		//Transaction begin.
		session.beginTransaction();
		
		//Creation entity object.
		UserDetails userDetails = new UserDetails();
		//Set property of the entity. 
		userDetails.setCompanyName("Ness");
		
		UserKey userKey = new UserKey();
		userKey.setUserId(1L);
		userKey.setUserName("qwerty");
		
		userDetails.setUserKey(userKey);
		
		Address address = new Address();
		address.setCity("Mumbai");
		address.setDistrict("Thane");
		address.setState("Maharashtra");
		
		Address address2 = new Address();
		address2.setCity("Mumbai2");
		address2.setDistrict("Thane2");
		address2.setState("Maharashtra2");
		
		List<Address> adresses = new ArrayList<Address>();
		adresses.add(address);
		adresses.add(address2);
		userDetails.setAddress(adresses);

		Address homeAddress = new Address();
		homeAddress.setCity("Mumbai_home");
		homeAddress.setDistrict("Thane_home");
		homeAddress.setState("Maharashtra_home");
		
		List<Address> homeAdresses = new ArrayList<Address>();
		homeAdresses.add(homeAddress);
		
		userDetails.setHomeaddress(homeAdresses);
		//Saving entity object.
		session.save(userDetails);
		
		//Updating property after session.save() - DIRTY CHECKING.
		//userDetails.setCompanyName("Ness_new");
		
		//Commit transaction.
		session.getTransaction().commit();
		//Session close.
		session.close();
		
		//------------------ Save Entity [END]---------------//
		
		//------------------ Get Entity [START]---------------//
		
		// Open session.
		Session sessionForGet = factory.openSession();
		//Transaction begin.
		sessionForGet.beginTransaction();
				
		//Get save object using identifier (in this case UserKey object )
		UserDetails userDetailsDb = (UserDetails) sessionForGet.get(UserDetails.class, userKey);
		
		//set new property value.
		//userDetailsDb.setCompanyName("Ness_update");
		
		//Update persisted object.
		//userDetails.setCompanyName("new");
		//sessionForGet.update(userDetails);
		
		//Commit transaction.
		sessionForGet.getTransaction().commit();
		//Session close.
		sessionForGet.close();
		
		userDetailsDb.setCompanyName("Merge_Name");
		
		//------------------ Get Entity [END]---------------//
		
		//------------------ Save/Update Entity [START]---------------//
		
		// Open session.
		Session sessionForUpdate = factory.openSession();
		//Transaction begin.
		sessionForUpdate.beginTransaction();

		//Should use merge in this case as detached object is present in persistence context
		sessionForUpdate.update(userDetailsDb); 
		
		//Commit transaction.
		sessionForUpdate.getTransaction().commit();
		//Session close.
		sessionForUpdate.close();
		
		//------------------ Save/Update Entity [END]---------------//
		
	}

}
