package com.ezypay.subs.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezypay.subs.app.model.Customer;
import com.ezypay.subs.app.service.CustomerService;
import com.ezypay.subs.app.utils.DateUtils;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	// display list of Customers
	@GetMapping("/customers")
	public String viewHomePage(Model model, @AuthenticationPrincipal OidcUser user) {
			System.out.println(findPaginated(1, "firstName", "asc", model));
			model.addAttribute("userName",user.getFullName());
			return "customers";
	}
	
	@GetMapping("/showNewCustomerForm")
	public String showNewCustomerForm(Model model) {
		// create model attribute to bind form data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "new_customer";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		// save Customer to database
		customerService.saveCustomer(customer);
		return "redirect:/customers";
	}
	
	@GetMapping("/viewCustomerSubs/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get Customer from the service
		Customer customer = customerService.getCustomerById(id);
		customer.setStr_subs_start_date(DateUtils.getddMMyyyyDateString(customer.getSubs_start_date()));
		customer.setStr_subs_end_date(DateUtils.getddMMyyyyDateString(customer.getSubs_end_date()));
		// set Customer as a model attribute to pre-populate the form
		model.addAttribute("customer", customer);
		return "view_customer";
	}
	
	@GetMapping("/deleteCustomer/{id}")
	public String deleteCustomer(@PathVariable (value = "id") long id) {
		
		// call delete Customer method 
		this.customerService.deleteCustomerById(id);
		return "redirect:/customers";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Customer> page = customerService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Customer> listCustomers = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listCustomers", listCustomers);
		return "customers";
	}
	
//	@GetMapping("/logout")  
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {  
//		System.out.println("Logout called");
//        if(null != getAuthenticated()) {
//        	new SecurityContextLogoutHandler().logout(request, response, getAuthenticated());
//        	System.out.println("Logout triggered");
//        }
//        	
//        return "redirect:/login?logout=true";  
//     }
//
//	private Authentication getAuthenticated() {
//		return SecurityContextHolder.getContext().getAuthentication();  
//	} 	
		
}
