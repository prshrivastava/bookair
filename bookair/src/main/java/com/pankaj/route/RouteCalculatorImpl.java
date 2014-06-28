package com.pankaj.route;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.pankaj.domain.*;

/* An implementation of Dijkstra's algorithm for shortest path */
public class RouteCalculatorImpl implements RouteCalculator {
	private Set<Flight> routes;
	
	public RouteCalculatorImpl(Set<Flight> r){
		routes = r;
	}
	
	public List<Airport> getShortestPath(Airport from, Airport to){
		Set<Airport> processedAirports = new HashSet<Airport>();
		Map<Airport, Route> routeMap = new HashMap<Airport, Route>();
		
		Queue<Airport> q = new LinkedList<Airport>();
		
		Airport current = from;
		q.add(from);
		processedAirports.add(from);
		while(!q.isEmpty()){
			current = q.remove();
			if(current.equals(to)){
				break;
			}
			else{
				for(Airport ap: getImmediateHops(current)){
					if(!processedAirports.contains(ap)){
						q.add(ap);
						processedAirports.add(ap);
						Route routeToap = null;
						Route routeToCurrent = routeMap.get(current);
						if(routeToCurrent == null)
							routeToap = new Route();
						else
							routeToap = routeToCurrent.clone();
						routeToap.addHop(ap);
						routeMap.put(ap, routeToap);
					}
				}
			}
		}
		Route routeToDestination = routeMap.get(to);
		if(routeToDestination == null){
			//Destination is not reachable
			throw new DestinationUnreachableException("The airport "+to+" is not reacheble");
		}
		
		return routeToDestination.getHops();
	}
	
		
	private List<Airport> getImmediateHops(Airport from){
		List<Airport> immediateHops = new ArrayList<Airport>();
		for(Flight f: routes){
			if(f.fliesFrom(from)){
				immediateHops.add(f.fliesTo());
			}
		}
		return immediateHops;
	}
}
