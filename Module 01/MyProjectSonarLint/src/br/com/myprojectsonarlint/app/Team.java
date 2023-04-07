package br.com.myprojectsonarlint.app;

import java.util.ArrayList;
import java.util.List;

public class Team {
	
	public Team() {}
	
	public String name;
	
	public List<Student> students = new ArrayList<Student>();
	
	public Teacher teacher;
	
	
	public boolean addTeacher(Teacher teacher) {
		this.teacher = teacher;
		
		return true;
	}
	
	public boolean addStudent(Student student) {
		students.add(student);
				
		return true;
	}
}
