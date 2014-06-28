package com.pankaj.routetest;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import com.pankaj.domain.*;
import com.pankaj.route.*;

public class RouteCalculatorTest {
	
	static Airport pune, mumbai, delhi, goa, chennai = null;
	static Flight pne_bom, pne_del,bom_del, del_goa,bom_goa,goa_mad,pne_mad,del_bom, bom_mad =null;
	@BeforeClass
	public static void setupFlights(){
		pune = new Airport("PNQ");
		mumbai = new Airport("BOM");
		delhi = new Airport("DEL");
		goa = new Airport("GOA");
		chennai = new Airport("MAD");
		pne_bom = new Flight("1", pune, mumbai);
		pne_del = new Flight ("2", pune, delhi);
		bom_del = new Flight ("3", mumbai, delhi);
		del_goa = new Flight("4", delhi, goa);
		bom_goa = new Flight("5", mumbai, goa);
		goa_mad = new Flight("6", goa, chennai);
		pne_mad = new Flight("7", pune, chennai);
		del_bom = new Flight("8", delhi, mumbai);
		bom_mad = new Flight("9",mumbai, chennai);
	}
	
	@Test
	public void testDirectRoute(){
		Set<Flight> flights = new HashSet<Flight>();
		flights.add(pne_bom);
		flights.add(pne_del);
		flights.add(bom_del);
		
		RouteCalculator routeCalc = new RouteCalculatorImpl(flights);
		
		List<Airport> toDel = routeCalc.getShortestPath(pune, delhi);
		assertEquals(1, toDel.size());
		assertEquals(delhi, toDel.get(0));
	}
	
	@Test
	public void testRouteWithOnehop(){
		Set<Flight> flights = new HashSet<Flight>();
		flights.add(pne_bom);
		flights.add(pne_del);
		flights.add(bom_goa);
		RouteCalculator routeCalc = new RouteCalculatorImpl(flights);
		
		List<Airport> toGoa = routeCalc.getShortestPath(pune, goa);
		assertEquals(2, toGoa.size());
		assertEquals(mumbai, toGoa.get(0));
		assertEquals(goa, toGoa.get(1));
	}
	
	@Test
	public void testRouteWithTwoHops(){
		Set<Flight> flights = new HashSet<Flight>();
		flights.add(pne_bom);
		flights.add(pne_del);
		flights.add(bom_goa);
		flights.add(goa_mad);
		RouteCalculator routeCalc = new RouteCalculatorImpl(flights);
		
		List<Airport> toChennai = routeCalc.getShortestPath(pune, chennai);
		assertEquals(3, toChennai.size());
		assertEquals(mumbai, toChennai.get(0));
		assertEquals(goa, toChennai.get(1));
		assertEquals(chennai, toChennai.get(2));
	}
	
	@Test
	public void testRoutewithLesserHops(){
		Set<Flight> flights = new HashSet<Flight>();
		flights.add(pne_bom);
		flights.add(pne_del);
		flights.add(bom_goa);
		flights.add(goa_mad);
		
		//To the previous testRouteWithTwoHops graph, we add a flight from mumbai to chennai
		flights.add(bom_mad);
		RouteCalculator routeCalc = new RouteCalculatorImpl(flights);
		
		List<Airport> toChennai = routeCalc.getShortestPath(pune, chennai);
		assertEquals(2, toChennai.size());
		assertEquals(mumbai, toChennai.get(0));
		assertEquals(chennai, toChennai.get(1));
	}
	
	@Test(expected=DestinationUnreachableException.class)
	public void testUnreachableDestination(){
		Set<Flight> flights = new HashSet<Flight>();
		flights.add(pne_bom);
		flights.add(pne_del);
		flights.add(bom_goa);
		
		RouteCalculator routeCalc = new RouteCalculatorImpl(flights);
		routeCalc.getShortestPath(pune, chennai);
	}

}
