package com.example.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Vehicle;

public class HibernateCrudTest {


	public static void main(String[] args) {
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		
//----------------------------------- Save Entity [START]---------------//
		// Open session.
		Session session = factory.openSession();
		//Transaction begin.
		session.beginTransaction();
		
		//Creation entity object.
		Vehicle vehicle = new Vehicle();
		//Set primary key of the entity in case manual key generation. 
		vehicle.setVehicleId(1L);
		//Set property of the entity. 
		vehicle.setVehicleName("Honda");
		
		//Saving entity object.
		Long id = (Long) session.save(vehicle);
		
		//Updating property after session.save() - DIRTY CHECKING.
		vehicle.setVehicleName("Honda-Amaze");
		
		//Commit transaction.
		session.getTransaction().commit();
		
		//Session close.
		session.close();
		
		
		
//----------------------------------- Save Entity [END]---------------//
		

//----------------------------------- get/load Entity [START]---------------//
		
		// Open session.
				session = factory.openSession();
				
				//Transaction begin.
				session.beginTransaction();
				
				//Calling get method for 1st time 
				System.out.println("======Calling get method for 1st time [START]=====");
				Vehicle vehicleGetFirst = (Vehicle) session.load(Vehicle.class, id);
				System.out.println("vehicleGetFirst : "+vehicleGetFirst);
				System.out.println("======Calling get method for 1st time [END]=====");
				
				//Calling get method for 2nd time
				System.out.println("======Calling get method for 2nd time [START]=====");
				Vehicle vehicleGetSecond = (Vehicle) session.load(Vehicle.class, id);
				System.out.println("vehicleGetFirst : "+vehicleGetSecond);
				System.out.println("======Calling get method for 2nd time [END]=====");
				
				//Clear session
				session.clear();
				
				//Calling get method after clearing session
				System.out.println("======Calling get method after clearing session [START]=====");
				Vehicle vehicleGetSessionClear = (Vehicle) session.load(Vehicle.class, id);
				System.out.println("vehicleGetFirst : "+vehicleGetSessionClear);
				System.out.println("======Calling get method after clearing session [END]=====");
						
				//Calling get method with id which is not present 
				System.out.println("======Calling get method with id which is not present [START]=====");
				//Vehicle vehicleGetNoId = (Vehicle) session.load(Vehicle.class, 2L);
				//System.out.println("vehicleGetNoId : "+vehicleGetNoId);
				System.out.println("======Calling get method with id which is not present [END]=====");
				
				//Commit transaction.
				session.getTransaction().commit();
						
				//Session close.
				session.close();
		
//----------------------------------- get/load Entity [END]---------------//
		
		
		
//----------------------------------- Get Entity and make detached [START]---------------//
		
		//begin new session
		session = factory.openSession();
		Vehicle vehicleAfterGet = (Vehicle) session.get(Vehicle.class, id);
		
		//Close current session which makes vehicleAfterGet detached
		session.close();
		
		//updating detached object -- DIRTY CHECKING
		//vehicleAfterGet.setVehicleName("Toyota");
		
//----------------------------------- Get Entity and make detached [END]---------------//
		
		
		
//----------------------------------- Get same entity as detached [START]---------------//
		//begin new session
		session = factory.openSession();
		
		//begin new transaction
		session.beginTransaction();
		
		//Get same object as detached object
		Vehicle vehicleAfterGetAgain = (Vehicle) session.load(Vehicle.class, id);
		
		/*Try to save detached object :
		 * 1. primary key auto-generated : New row will inserted with new primary key 
		 * 2. primary key manual : exception throw  org.hibernate.NonUniqueObjectException*/
//		session.save(vehicleAfterGet);
		
		/*Try to persist detached object :
		 * 1. primary key auto-generated : Exception in thread "main" org.hibernate.PersistentObjectException 
		 * 2. primary key manual : Exception in thread "main"  org.hibernate.NonUniqueObjectException*/
//		session.persist(vehicleAfterGet);
		
		/*Try to update detached object :
		 * 1. primary key auto-generated : exception throw  org.hibernate.NonUniqueObjectException 
		 * 2. primary key manual : exception throw  org.hibernate.NonUniqueObjectException*/
		session.update(vehicleAfterGet);

		/*Try to save detached object :
		 * 1. primary key auto-generated : Update existing row of same primary key (update record)  
		 * 2. primary key manual : Update existing row of same primary key (update record)*/
//		session.merge(vehicleAfterGet);
		
		/*Try to save detached object :
		 * 1. primary key auto-generated : exception throw  org.hibernate.NonUniqueObjectException 
		 * 2. primary key manual : exception throw  org.hibernate.NonUniqueObjectException*/
//		session.saveOrUpdate(vehicleAfterGet);
		
		session.getTransaction().commit();
		session.close();
		
//----------------------------------- Get same entity as detached [END]---------------//
		
	}
}
