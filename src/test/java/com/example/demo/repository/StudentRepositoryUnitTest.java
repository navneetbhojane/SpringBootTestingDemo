package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Student;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryUnitTest {

	@Autowired
    private StudentRepository repository;
	
	@Test
	public void testSaveStudent() throws Exception {
		
		Student savedStudent = repository.save(new Student("Navneet Bhojane", "Class 10"));
		assertThat(savedStudent.getName()).isEqualTo("Navneet Bhojane");
	}

	@Test
	public void testSaveAllStudents() throws Exception {
		
		List<Student> studentsList = new ArrayList<Student>();
		Student s1 = new Student("Navneet Bhojane", "Class 10");
		Student s2 = new Student("Sumit Sharma", "Class 9");
		Student s3 = new Student("Rohit Chauhan", "Class 10");
        studentsList.add(s1);
        studentsList.add(s2);
        studentsList.add(s3);
       
        List<Student> savedStudents = new ArrayList<Student>();
        Iterable<Student> itrStudents = repository.saveAll(studentsList);
        itrStudents.forEach(savedStudents::add);
        
        assertThat(savedStudents.size()).isEqualTo(studentsList.size());
		assertThat(savedStudents, hasItems(s1, s2, s3));
		
		Student s4 = new Student("Venkat Murthy", "Class 8");
		assertThat(savedStudents, not(hasItem(s4)));
		
	}
	
	@Test
	public void testFindByIdStudent() throws Exception {
		
		Student s1 = repository.save(new Student("Navneet Bhojane", "Class 10"));
		Student s2 = repository.findById(s1.getId()).orElse(new Student());
		assertThat(s1).isEqualTo(s2);
	}
	
}
