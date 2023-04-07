package br.com.myprojectsonarlint.app;

public class School {
	public String name;
	
	public int schoolYear;
	
	public Team team;
	
	public boolean addTeam(Team team) {
		this.team = team;
		
		return true;
	} 
}
