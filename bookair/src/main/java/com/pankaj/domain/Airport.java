package com.pankaj.domain;

public class Airport {
	private String airportCode;

	public Airport(String airportCode) {
		this.airportCode = airportCode;
	}
	
	public String getAirportCode() {
		return airportCode;
	}
	
	@Override
	public String toString() {
		return "[" + airportCode + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((airportCode == null) ? 0 : airportCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airport other = (Airport) obj;
		if (airportCode == null) {
			if (other.airportCode != null)
				return false;
		} else if (!airportCode.equals(other.airportCode))
			return false;
		return true;
	}

}
