package com.ezypay.subs.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ezypay.subs.app.bean.InvoiceBean;
import com.ezypay.subs.app.model.Customer;
import com.ezypay.subs.app.model.Invoice;

public interface CustomerService {
	List<Customer> getAllCustomers();
	void saveCustomer(Customer customer);
	Customer saveCustomerWithReturn(Customer customer);
	Customer getCustomerById(long id);
	void deleteCustomerById(long id);
	Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	Customer findByEmailAndPassword(String email, String password);
	List<InvoiceBean> setInvoices(Date start, Date end, String subsType);
}
