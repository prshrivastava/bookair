package com.pankaj.route;
import java.util.*;
import com.pankaj.domain.*;

public class Route {
	private List<Airport> airportHops;
		
	public Route() {
		airportHops = new ArrayList<Airport>();
		}
	private Route(List<Airport> hops){
		airportHops = hops;
	}

	public void addHop(Airport onTheRoute){
		airportHops.add(onTheRoute);
	}
	
	public List<Airport> getHops(){
		return new ArrayList<Airport>(airportHops);
	}
	
	public Route clone(){
		return new Route(getHops());
	}
}