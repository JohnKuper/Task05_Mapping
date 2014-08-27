package com.johnkuper.dataformapping;

import com.johnkuper.annotations.ClassAnnotation;
import com.johnkuper.annotations.FieldAnnotation;

@ClassAnnotation(name = "BillingDetails")
public class CreditCard {

	@FieldAnnotation(name = "CC_number")
	private String number = "4276680045129865";
	
	@FieldAnnotation(name = "CC_cardType")
	private String cardType = "Visa";
	
	@FieldAnnotation(name = "CC_exp_month")
	private byte expMonth = 10;
	
	@FieldAnnotation(name = "CC_exp_year")
	private int expYear = 2016;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public byte getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(byte expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}
	
	

	
}
