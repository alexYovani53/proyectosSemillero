package com.mapeo.restjpa2.dto;

import java.io.Serializable;

public class ProcedimientoInsertPolizaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6328856293705783425L;
	

	private Integer polizanumber ;
	
	private Integer dnicl ;

	public Integer getPolizanumber() {
		return polizanumber;
	}

	public void setPolizanumber(Integer polizanumber) {
		this.polizanumber = polizanumber;
	}

	public Integer getDnicl() {
		return dnicl;
	}

	public void setDnicl(Integer dnicl) {
		this.dnicl = dnicl;
	}

	

}
