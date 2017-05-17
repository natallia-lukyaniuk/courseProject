package edu.bsuir.univer.entity;

public class User {

	private Integer id;
    private String login;
    private String password;
    private boolean admin;

	public User() {

	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String login, String password, boolean admin) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.setAdmin(admin);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		// TODO Auto-generated method stub
		this.admin = admin;
	}
}
