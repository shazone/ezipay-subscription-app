package com.ezypay.subs.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
		
	@Column(name = "subscription")
	private String subscription;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "start_date")
	private Long subs_start_date;
	
	@Column(name = "subs_end_date")
	private Long subs_end_date;

	private String str_subs_start_date;
	private String str_subs_end_date; 
//	
//	@OneToMany(fetch=FetchType.LAZY,mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Invoice> invoices = new ArrayList<>();
//	
//    public void addInvoice(Invoice invoice){
//    	invoice.setCustomer(this);
//    	invoices.add(invoice);
//    }
//
//    public void removeInvoice(Invoice invoice){
//    	invoice.setCustomer(this);
//    	invoices.remove(invoice);
//    }
	
}
