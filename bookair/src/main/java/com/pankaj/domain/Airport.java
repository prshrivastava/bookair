package com.pankaj.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.pankaj.route.DestinationUnreachableException;
import com.pankaj.route.Route;

public class Airport {
	private String airportCode;
	private List<Airport> neighbours;

	public Airport(String airportCode) {
		this.airportCode = airportCode;
		neighbours = new ArrayList<Airport>();
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
				+ (airportCode == null ? 0 : airportCode.hashCode());
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

	public void addFlightTo(Airport dest) {
		neighbours.add(dest);
	}

	public List<Airport> getShortestPathTo(Airport to) {
		Set<Airport> processedAirports = new HashSet<Airport>();
		Map<Airport, Route> routeMap = new HashMap<Airport, Route>();

		Queue<Airport> q = new LinkedList<Airport>();

		Airport current = this;
		q.add(this);
		processedAirports.add(this);
		while (!q.isEmpty()) {
			current = q.remove();
			if (current.equals(to))
				break;
			for (Airport ap : current.neighbours)
				if (!processedAirports.contains(ap)) {
					q.add(ap);
					processedAirports.add(ap);
					Route routeToCurrent = routeMap.get(current);
					Route routeToap = routeToCurrent == null ? new Route() : routeToCurrent.clone();
					routeToap.addHop(ap);
					routeMap.put(ap, routeToap);
				}
		}
		Route routeToDestination = routeMap.get(to);
		if (routeToDestination == null)
			// Destination is not reachable
			throw new DestinationUnreachableException("The airport " + to + " is not reacheble");

		return routeToDestination.getHops();
	}

}
