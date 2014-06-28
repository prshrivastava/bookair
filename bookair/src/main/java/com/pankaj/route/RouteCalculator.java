package com.pankaj.route;

import java.util.List;

import com.pankaj.domain.Airport;

public interface RouteCalculator {

	public List<Airport> getShortestPath(Airport from, Airport to);

}