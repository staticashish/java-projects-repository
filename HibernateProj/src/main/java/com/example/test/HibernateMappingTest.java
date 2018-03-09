package com.example.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Student;
import com.example.entity.StudentSubjectMapping;
import com.example.entity.Subject;

public class HibernateMappingTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory factory = configuration.buildSessionFactory();
		
//----------------------------------- Save Entity [START]---------------//
		// Open session.
		Session session = factory.openSession();
		//Transaction begin.
		session.beginTransaction();
		
		Student student = new Student();
		student.setStudentName("Ashish");
		
		Subject subjectMaths = new Subject();
		subjectMaths.setSubjectName("Maths");
		
		Subject subjectHistory = new Subject();
		subjectHistory.setSubjectName("History");
		
		session.save(subjectHistory);
		session.save(subjectMaths);
		
		StudentSubjectMapping mappingMaths = new StudentSubjectMapping();
		mappingMaths.setStudent(student);
		mappingMaths.setSubject(subjectMaths);
		mappingMaths.setMarks(40L);
		
		StudentSubjectMapping mappingHistory = new StudentSubjectMapping();
		mappingHistory.setStudent(student);
		mappingHistory.setSubject(subjectHistory);
		mappingHistory.setMarks(50L);
		
		student.addUserGroup(mappingMaths);
		student.addUserGroup(mappingHistory);
		
		session.save(student);
		
		session.getTransaction().commit();
				
		//Session close.
		session.close();
		
//----------------------------------- Save Entity [END]---------------//	
		
		
		session = factory.openSession();
		//Transaction begin.
		session.beginTransaction();
		//Student studentGet = (Student) session.get(Student.class, id);
		//Commit transaction.
		session.getTransaction().commit();
						
		//Session close.
		session.close();
	}

}
