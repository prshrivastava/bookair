package com.pankaj.domain;

public class Flight {
	private String flightNumber;
	private Airport source;
	private Airport destination;
	public Flight(String flightNumber, Airport from, Airport to) {
		this.flightNumber = flightNumber;
		source = from;
		destination = to;
	}
	
	public boolean fliesFrom(Airport from){
		return source.equals(from);
	}
	public Airport fliesTo(){
		return destination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((flightNumber == null) ? 0 : flightNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Flight other = (Flight) obj;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		return true;
	}
	
	

}
