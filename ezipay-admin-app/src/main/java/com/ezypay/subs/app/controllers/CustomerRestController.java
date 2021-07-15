	package com.ezypay.subs.app.controllers;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ezypay.subs.app.bean.CustomerBean;
import com.ezypay.subs.app.model.Customer;
import com.ezypay.subs.app.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CustomerRestController {
	private final CustomerService customerService;
	
	@GetMapping("/api/customer")
	public List<Customer> getAllCustomer(){
		return customerService.getAllCustomers();
	}

	@GetMapping("/api/customer/{id}")
	public CustomerBean getCustomerById(@PathVariable long id){
		Customer c = customerService.getCustomerById(id);
		CustomerBean cb = new CustomerBean();
		BeanUtils.copyProperties(c, cb);
		
		cb.setInvoices(customerService
				.setInvoices(new Date(c.getSubs_start_date()), 
						 new Date(c.getSubs_end_date()), 
						 c.getSubscription()
						 ));
		
		return cb;
	}
	
	@PostMapping("/api/customer/clientlogin")
	public CustomerBean loginFromClient(@Valid @RequestBody Customer customer) {
		System.out.println(customer.getEmail());
		System.out.println(customer.getPassword());
		
		Customer c = customerService.findByEmailAndPassword(customer.getEmail(),customer.getPassword());
		CustomerBean cb = null;
				
		
    	if(null != c && c.getSubs_start_date() != null && c.getSubs_end_date() != null) {
    		System.out.println("saving customer set INVOICE...");
    		cb = new CustomerBean();
    		BeanUtils.copyProperties(c, cb);
    		cb.setInvoices(customerService
    								.setInvoices(new Date(c.getSubs_start_date()), 
    											 new Date(c.getSubs_end_date()), 
    											 c.getSubscription()
    											 ));
    		 
    	}
		
		return cb;
	}	
	
    @PostMapping("/api/customer")
    ResponseEntity<CustomerBean> createCustomer(@Valid @RequestBody Customer customer) throws URISyntaxException {
    	System.out.println("saving customer ..."+customer.toString());
    	
    	if(null != customer && customer.getSubs_start_date() != null && customer.getSubs_end_date() != null) {
    		System.out.println("saving customer set INVOICE...");
//    		customer.setInvoices(customerService
//    								.setInvoices(new Date(customer.getSubs_start_date()), 
//    											 new Date(customer.getSubs_end_date()), 
//    											 customer.getSubscription()
//    											 ));
    		 
    		//customerService.setInvoices(customer);
    	}
    	
        Customer c = customerService.saveCustomerWithReturn(customer);
        CustomerBean cb = new CustomerBean();
        if (null!=c) {
        	BeanUtils.copyProperties(c, cb);
        	cb.setInvoices(customerService
					.setInvoices(new Date(c.getSubs_start_date()), 
								 new Date(c.getSubs_end_date()), 
								 c.getSubscription()
								 ));
        }
		
        return ResponseEntity.ok().body(cb);
    }	

}
