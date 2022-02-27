package com.mapeo.restjpa2.models;

public class PolizaCount {

	private Integer dniCl;
	private Long total;
	
	public PolizaCount(Integer dniCl, Long total) {
		super();
		this.dniCl = dniCl;
		this.total = total;
	}

	public Integer getDniCl() {
		return dniCl;
	}

	public void setDniCl(Integer dniCl) {
		this.dniCl = dniCl;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	
	
}
