package com.ezipay.client.app.bean;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter	
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Customer {
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String subscription; 
	private String price;
	private Long subs_start_date;//store as long
	private Long subs_end_date;//store as long
	private String str_subs_start_date;
	private String str_subs_end_date;
	private List<Invoice> invoices;

}
