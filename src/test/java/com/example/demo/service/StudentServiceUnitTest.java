package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

@RunWith(SpringRunner.class)
public class StudentServiceUnitTest {

	@TestConfiguration
    static class StudentServiceTestContextConfiguration {
  
        @Bean
        public StudentService studentService() {
            return new StudentService();
        }
    }
	
	@Autowired
    private StudentService studentService;
	
	@MockBean
	private StudentRepository studentRepository;
	
	@Test
	public void testCreateStudent() throws Exception {
		
		List<Student> studentsList = new ArrayList<Student>();
		Student s1 = new Student("Navneet Bhojane", "Class 10");
		Student s2 = new Student("Sumit Sharma", "Class 9");
		Student s3 = new Student("Rohit Chauhan", "Class 10");
        studentsList.add(s1);
        studentsList.add(s2);
        studentsList.add(s3);
	 
        Mockito.when(studentRepository.saveAll(Mockito.any(Iterable.class))).thenReturn(studentsList);
	    
	    List<Student> savedStudents = studentService.createStudent();
	    
	    assertThat(savedStudents).isNotNull();
	    assertThat(savedStudents).isNotEmpty();
	    assertThat(savedStudents.size()).isEqualTo(studentsList.size());
	    assertThat(savedStudents, hasItems(s1, s2, s3));
	}
	
	@Test
	public void testRetrieveStudent() throws Exception {
		
		Optional<Student> s1 = Optional.of(new Student("Navneet Bhojane", "Class 10"));
		
        Mockito.when(studentRepository.findById(1)).thenReturn(s1);
        assertTrue(studentService.retrieveStudent(1).getName().contains("Navneet"));;
	    
	}
	
	
}
