package com.ezipay.client.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.ezipay.client.app.bean.Customer;
import com.ezipay.client.app.utils.DateUtils;

@Controller
public class CustomerClientController {

	private String apiUri = "http://localhost:8081/api/customer";
	
	@Autowired
    private RestTemplate restTemplate;

	@PostMapping("/saveCustomer")
	public String saveCustomer(Model model, @ModelAttribute("customer") Customer customer) {
		// save Customer to database
		System.out.println("Save customer -----------------------------------");
		System.out.println("sending --"+customer.toString());
		customer.setSubs_start_date(DateUtils.getLongDate(customer.getStr_subs_start_date()));
		customer.setSubs_end_date(DateUtils.getLongDate(customer.getStr_subs_end_date()));
		
		
		Customer savedCustomer = restTemplate.postForObject( apiUri, customer, Customer.class);
		
		savedCustomer.setStr_subs_start_date(DateUtils.getddMMyyyyDateString(savedCustomer.getSubs_start_date()));
		savedCustomer.setStr_subs_end_date(DateUtils.getddMMyyyyDateString(savedCustomer.getSubs_end_date()));
    	
		//date conversion for ui
    	List<String> invoiceDates = new ArrayList<>();
    	savedCustomer.getInvoices().forEach((a) -> {
    		invoiceDates.add(DateUtils.getddMMyyyyDateString(a.getInvoiceDate()));
    	});
    	
		model.addAttribute("customer", savedCustomer);
		model.addAttribute("invoiceDates", invoiceDates);
		System.out.println("receiving --"+savedCustomer.toString());
		return "existing_customer";
	}	
	
	@PostMapping("/login")
	public String login(Model model, @ModelAttribute("customer") Customer customer)
	{
		String dest;
	    Customer loggedCustomer = restTemplate.postForObject(apiUri+"/clientlogin", customer, Customer.class);
	    
	    //simple validation
	    if (null != loggedCustomer 
	    		&& loggedCustomer.getEmail().equalsIgnoreCase(customer.getEmail()) 
	    		&& loggedCustomer.getPassword().equals(customer.getPassword())) {
	    	//System.out.println("LOGIN SUCCESS --------");
	    		    	
	    	loggedCustomer.setStr_subs_end_date(DateUtils.getddMMyyyyDateString(loggedCustomer.getSubs_end_date()));
	    	loggedCustomer.setStr_subs_start_date(DateUtils.getddMMyyyyDateString(loggedCustomer.getSubs_start_date()));
	    	
	    	//date conversion for ui
	    	List<String> invoiceDates = new ArrayList<>();
	    	loggedCustomer.getInvoices().forEach((a) -> {
	    		invoiceDates.add(DateUtils.getddMMyyyyDateString(a.getInvoiceDate()));
	    	});
	    	
	    	model.addAttribute("customer", loggedCustomer);
	    	model.addAttribute("invoiceDates", invoiceDates);
	    	
	    	dest = "existing_customer";	
	    }else {
	    	model.addAttribute("error", "Invalid Login!");
	    	dest = "login";
	    }
	    
	    return dest;
	}
	
	@PostMapping("/doUpdate")
	public String doUpdate(Model model, @ModelAttribute("customer") Customer customer)
	{
	    ResponseEntity<Customer> c = restTemplate.getForEntity(apiUri+"/"+customer.getId(), Customer.class);
	    System.out.println("doUpdate ------------------- client side");
	    System.out.println(c.toString());
	    
	    Customer updatedCustomer = (Customer) c.getBody();
	    updatedCustomer.setStr_subs_start_date(DateUtils.getddMMyyyyDateString(updatedCustomer.getSubs_start_date()));
	    updatedCustomer.setStr_subs_end_date(DateUtils.getddMMyyyyDateString(updatedCustomer.getSubs_end_date()));
   	    
	    System.out.println(updatedCustomer.toString());
	    model.addAttribute("customer",  updatedCustomer);
	    return "update_customer";
	}	
	
	@PostMapping("/saveNewCustomer")
	public String saveNewCustomer(Model model, @ModelAttribute("customer") Customer customer) {
		// save Customer to database
		System.out.println("Save New customer -----------------------------------");
		System.out.println("sending --"+customer.toString());
		customer.setSubs_start_date(DateUtils.getLongDate(customer.getStr_subs_start_date()));
		customer.setSubs_end_date(DateUtils.getLongDate(customer.getStr_subs_end_date()));
		Customer savedCustomer = restTemplate.postForObject( apiUri, customer, Customer.class);
		model.addAttribute("customer", savedCustomer);
		System.out.println("receiving --"+savedCustomer.toString());
		return "login";
	}	

	
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }	
	
    @GetMapping("/newUser")
    public String newUser(Model model) {
    	model.addAttribute("customer",  new Customer());
        return "new_customer";
    }	    
}
