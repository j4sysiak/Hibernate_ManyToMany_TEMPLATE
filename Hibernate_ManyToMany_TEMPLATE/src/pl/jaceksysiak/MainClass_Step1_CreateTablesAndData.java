package pl.jaceksysiak;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.jaceksysiak.entity.Course;
import pl.jaceksysiak.entity.Student;

public class MainClass_Step1_CreateTablesAndData {
	
	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {	
		//-----------Student	
			Student tmpStudent=new Student("Jack", "Sysiak", "jacek@wp.pl");
	
		//----------- Course
			Course tmpCourse1 = new Course("Hibernate technics for idiots");
			
			Course tmpCourse2 = new Course();
			tmpCourse2.setTitle("Managerka dla wymagaj¹cych");
			
			// start a transaction
			session.beginTransaction();
			
			// save the courses
			session.save(tmpStudent);
			session.save(tmpCourse1);
			session.save(tmpCourse2);
			
			// commit transaction
			session.getTransaction().commit();
		}
		finally {
			
			// add clean up code
			session.close();			
			factory.close();
		}
	}
}
