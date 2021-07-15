package com.ezypay.subs.app.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ezypay.subs.app.bean.InvoiceBean;
import com.ezypay.subs.app.model.Customer;
import com.ezypay.subs.app.model.Invoice;
import com.ezypay.subs.app.model.Subscription;
import com.ezypay.subs.app.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer Customer) {
		this.customerRepository.save(Customer);
	}
	
	public Customer saveCustomerWithReturn(Customer Customer) {
		return this.customerRepository.save(Customer);
	}	

	@Override
	public Customer getCustomerById(long id) {
		Optional<Customer> optional = customerRepository.findById(id);
		Customer Customer = null;
		if (optional.isPresent()) {
			Customer = optional.get();
		} else {
			throw new RuntimeException(" Customer not found for id :: " + id);
		}
		return Customer;
	}

	@Override
	public void deleteCustomerById(long id) {
		this.customerRepository.deleteById(id);
	}

	@Override
	public Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.customerRepository.findAll(pageable);
	}

	@Override
	public Customer findByEmailAndPassword(String email, String password) {
		return this.customerRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public List<InvoiceBean> setInvoices(Date start, Date end, String subsType) {
		System.out.println("saving customer ...setInvoices -- "+subsType);
		List<InvoiceBean> invoices = new ArrayList<>();
		List<LocalDate> listOfDates = new ArrayList<>();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate startDate = LocalDate.ofInstant(start.toInstant(), defaultZoneId);
		LocalDate endDate = LocalDate.ofInstant(end.toInstant(), defaultZoneId);
		
		if(subsType.equals(Subscription.DAILY.toString())) {
			listOfDates = startDate.datesUntil(endDate)
			                            .collect(Collectors.toList());
			System.out.println(listOfDates.size());
			
		}else if(subsType.equals(Subscription.WEEKLY.toString())) {
			listOfDates = startDate.datesUntil(endDate, Period.ofWeeks(1))
                    .collect(Collectors.toList());
			System.out.println(listOfDates.size());
						
		}else if(subsType.equals(Subscription.MONTHLY.toString())){
			listOfDates = startDate.datesUntil(endDate, Period.ofMonths(1))
                    .collect(Collectors.toList());
			System.out.println(listOfDates.size());
		}
		
		for(LocalDate i:listOfDates) {
			InvoiceBean inv = new InvoiceBean();
			inv.setInvoiceDate(Date.from(i.atStartOfDay(defaultZoneId).toInstant()).getTime());
			invoices.add(inv);
		}
		
		return invoices;
	}

}
