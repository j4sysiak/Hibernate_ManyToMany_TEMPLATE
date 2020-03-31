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
		//-----------Create Student	
			Student tmpStudent1=new Student("Jack", "Sysiak", "jacek@wp.pl");
			Student tmpStudent2=new Student("Mark", "Knophler", "ajaja@wp.pl");
	
		//----------- Create Course
			Course tmpCourse = new Course("Hibernate technics for idiots");
			
		// Add Student to the Course
			tmpCourse.addStudent(tmpStudent1);
			tmpCourse.addStudent(tmpStudent2);
			
			// start a transaction
			session.beginTransaction();
			
			// save the courses
			session.save(tmpStudent1);
			session.save(tmpStudent2);
			
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
