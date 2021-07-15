package com.ezypay.subs.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
@Table(name = "invoice")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
	
	@Column(name = "invoice_date")
	private Long invoiceDate;
    
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice )) return false;
        return id != null && id.equals(((Invoice) o).getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}