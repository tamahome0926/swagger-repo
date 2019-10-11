package com.dylan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dylan.exception.NotFoundException;

@RestController
public class StudentController {
	
    private Map<Integer, Student> students = new HashMap<>();
    
    private int id = 0;
 
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public Map<Integer, Student> getAll() {
        return students;
    }
 
    @RequestMapping(value = "/student", method = RequestMethod.POST)
    public int add(@RequestBody Student student) {
    	students.put(++id, student);
        return id;
    }
 
    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public Student get(@PathVariable("id") int id) {
    	if (students.containsKey(id)) {
    		return students.get(id);
    	} else {
    		throw new NotFoundException();
    	}
    }
    
    @RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
    public int update(@PathVariable("id") int id, @RequestBody Student student) {
    	if (students.containsKey(id)) {
    		students.replace(id, student);
    		return id;
    	} else {
    		throw new NotFoundException();
    	}
    }
    
    @RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable("id") int id) {
    	if (students.containsKey(id)) {
    		students.remove(id);
    		return id;
    	} else {
    		throw new NotFoundException();
    	}
    }
    
    public static class Student {
    	
    	private String firstName;

    	private String lastName;

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
    	
    	
    }
    
}