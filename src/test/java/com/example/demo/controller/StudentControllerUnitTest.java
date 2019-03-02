package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentController.class)
public class StudentControllerUnitTest {
	
	@MockBean
    private StudentService studentService;
	
	@Autowired
    private MockMvc mockMvc;
	
	@Test
	public void testCreateStudent()
	  throws Exception {
	     
		List<Student> studentsList = new ArrayList<Student>();
		Student s1 = new Student("Navneet Bhojane", "Class 10");
		Student s2 = new Student("Sumit Sharma", "Class 9");
		Student s3 = new Student("Rohit Chauhan", "Class 10");
        studentsList.add(s1);
        studentsList.add(s2);
        studentsList.add(s3);
	 
        Mockito.when(studentService.createStudent()).thenReturn(studentsList);
	 
	    mockMvc.perform(post("/api/students")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().is2xxSuccessful());
	      
	}
	
	@Test
	public void testRetrieveStudent()
	  throws Exception {
	     
		Student s1 = new Student("Navneet Bhojane", "Class 10");
		Mockito.when(studentService.retrieveStudent(1)).thenReturn(s1); 
	    
	    mockMvc.perform(get("/api/students/1")
	    		.contentType(MediaType.APPLICATION_JSON))
	        .andDo(print())
		    .andExpect(status().isOk())
	        .andExpect(content().string(containsString("Navneet")));
	      
	}
	
	

}
