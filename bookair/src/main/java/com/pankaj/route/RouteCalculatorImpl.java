package com.pankaj.route;

import java.util.List;

import com.pankaj.domain.Airport;

/* An implementation of Dijkstra's algorithm for shortest path */
public class RouteCalculatorImpl implements RouteCalculator {

	public List<Airport> getShortestPath(Airport from, Airport to) {
		return from.getShortestPathTo(to);
	}
}
