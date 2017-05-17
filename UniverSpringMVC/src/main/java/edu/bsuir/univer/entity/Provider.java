package edu.bsuir.univer.entity;

public class Provider {

	private Integer id;
    private String firm;
    private String address;
    private String phone;
	private Integer bank_account;

	public Provider() {

	}

	public Provider(Integer id, String firm, String address, String phone, Integer bank_account) {
		this.id = id;
		this.firm = firm;
		this.address = address;
		this.phone = phone;
		this.bank_account = bank_account;
	}

	public String getFirm() {
		return firm;
	}

	public void setFirm(String firm) {
		this.firm = firm;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getBankAccount() {
		return bank_account;
	}

	public void setBankAccount(Integer bank_account) {
		this.bank_account = bank_account;
	}

}
