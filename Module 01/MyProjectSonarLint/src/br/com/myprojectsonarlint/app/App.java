package br.com.myprojectsonarlint.app;

public class App {

	private static School school;

	public static void main(String[] args) {
		
		school = null;
		
		school.schoolYear = 2023;
				
		Team team = null;
		
		Teacher teacher = null;
		
		teacher.name = "Silva";
		
		teacher.code = "0993273";
		
		teacher.dateBirth = "20/06/2019";
		
		teacher.documentNumber = "1230981223-12";
		
		Student student = null;
		
		student.name = "Haha";
		
		student.tutor = "Alpa";

		student.code = "1230943424-23";
		
		team.name = "8ª B";
		
		team.teacher = teacher;
		
		team.students.add(student);
				
		team.addTeacher(teacher);
		
		team.addStudent(student);
		
		school.addTeam(team);
	}
}
