package com.mapeo.restjpa2.models;

public class PolizaCount {

	private Integer numeroPoliza;
	private Long total;
	
	public PolizaCount(Integer numeroPoliza, Long total) {
		super();
		this.numeroPoliza = numeroPoliza;
		this.total = total;
	}

	public Integer getNumeroPoliza() {
		return numeroPoliza;
	}

	public void setNumeroPoliza(Integer numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
	
	
	
	
}
