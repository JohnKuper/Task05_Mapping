package com.johnkuper.dataformapping;

public class BillingDetails {

	private String billing_details_id;
	private String CC_number;
	private String CC_cardType;
	private byte CC_exp_month;
	private int CC_exp_year;
	private String BA_account;
	private String BA_bankname;
	private String BA_swift;

	public String getBilling_details_id() {
		return billing_details_id;
	}

	public String getCC_cardType() {
		return CC_cardType;
	}

	public void setCC_cardType(String cC_cardType) {
		CC_cardType = cC_cardType;
	}

	public void setBilling_details_id(String billing_details_id) {
		this.billing_details_id = billing_details_id;
	}

	public String getCC_number() {
		return CC_number;
	}

	public void setCC_number(String cC_number) {
		CC_number = cC_number;
	}

	public byte getCC_exp_month() {
		return CC_exp_month;
	}

	public void setCC_exp_month(byte cC_exp_month) {
		CC_exp_month = cC_exp_month;
	}

	public int getCC_exp_year() {
		return CC_exp_year;
	}

	public void setCC_exp_year(int cC_exp_year) {
		CC_exp_year = cC_exp_year;
	}

	public String getBA_account() {
		return BA_account;
	}

	public void setBA_account(String bA_account) {
		BA_account = bA_account;
	}

	public String getBA_bankname() {
		return BA_bankname;
	}

	public void setBA_bankname(String bA_bankname) {
		BA_bankname = bA_bankname;
	}

	public String getBA_swift() {
		return BA_swift;
	}

	public void setBA_swift(String bA_swift) {
		BA_swift = bA_swift;
	}

}
