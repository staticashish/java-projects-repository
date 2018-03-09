package com.example.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.ContractEmployee;
import com.example.entity.Employee;
import com.example.entity.PermanentEmployee;

public class HibernateInheritanceTest {

	public static void main(String[] args) {

		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		
		// Open session.
		Session session = factory.openSession();
		//Transaction begin.
		session.beginTransaction();
		
		Employee employee = new Employee();
		employee.setEmpName("employee_1");
		
		//Creating Child object (PermanentEmployee) of Parent (Employee) 
		PermanentEmployee employeePerm = new PermanentEmployee();
		employeePerm.setEmpName("employee_perm_1");
		employeePerm.setiCardColor("BLUE");
		employeePerm.setSalary(50000.0);
		
		//Creating Child object (ContractEmployee) of Parent (Employee)
		ContractEmployee contractEmployee = new ContractEmployee();
		contractEmployee.setEmpName("employee_cont_1");
		contractEmployee.setGradeBand("A");
		contractEmployee.setPerDay(1000.0);
		
		//Saving Parent object
		session.save(employee);
		
		//Saving both child objects
		session.save(employeePerm);
		session.save(contractEmployee);
		
		
		//Transaction commit
		session.getTransaction().commit();
		
		//session close
		session.close();
	}

}
