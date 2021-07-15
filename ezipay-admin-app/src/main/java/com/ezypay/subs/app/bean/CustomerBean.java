package com.ezypay.subs.app.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerBean {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String subscription;
	private double price;
	private Long subs_start_date;
	private Long subs_end_date;
	private String str_subs_start_date;
	private String str_subs_end_date; 
	private List<InvoiceBean> invoices = new ArrayList<>();
	
}
