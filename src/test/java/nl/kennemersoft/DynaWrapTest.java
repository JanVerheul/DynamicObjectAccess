package nl.kennemersoft;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DynaWrapTest {

	DynaWrap<Pojo> dynaWrap;
	
	@Before
	public void setup() {
		Pojo pojo1 = new Pojo("Pieter", 33, 30.33, null);
		Pojo pojo2 = new Pojo("Karel", 66, 60.66, pojo1);
		Pojo pojo3 = new Pojo("Joris", 22, 20.22, pojo2);
		Pojo pojo4 = new Pojo("Victor", 44, 40.44, pojo3);
		this.dynaWrap = new DynaWrap<>(pojo4);
	}
	
	@Test
	public void testGetString() {
		assertEquals("Victor", dynaWrap.getString("name"));
		assertEquals("Joris", dynaWrap.getString("nextPojo.name"));
		assertEquals("Karel", dynaWrap.getString("nextPojo.nextPojo.name"));
		assertEquals("Pieter", dynaWrap.getString("nextPojo.nextPojo.nextPojo.name"));
	}

	@Test
	public void testGetInt() {
		assertEquals(44, dynaWrap.getInt("age"));
		assertEquals(22, dynaWrap.getInt("nextPojo.age"));
		assertEquals(66, dynaWrap.getInt("nextPojo.nextPojo.age"));
		assertEquals(33, dynaWrap.getInt("nextPojo.nextPojo.nextPojo.age"));
	}
	
	@Test
	public void testDiscoverClassName() {
		assertEquals("nl.kennemersoft.Pojo", dynaWrap.discoverClassName());
	}
	
	@Test
	public void testDiscoverProperties() {
		List<String> properties = dynaWrap.discoverProperties();
		assertTrue(properties.contains("name"));
		assertTrue(properties.contains("name.bytes"));
		assertTrue(properties.contains("age"));
		assertTrue(properties.contains("weight"));
		assertTrue(properties.contains("nextPojo.name"));
		assertTrue(properties.contains("nextPojo.name.bytes"));
		assertTrue(properties.contains("nextPojo.age"));
		assertTrue(properties.contains("nextPojo.weight"));
		assertTrue(properties.contains("nextPojo.nextPojo"));
		assertTrue(properties.contains("nextPojo.nextPojo.name"));
		assertTrue(properties.contains("nextPojo.nextPojo.name.bytes"));
		assertTrue(properties.contains("nextPojo.nextPojo.age"));
		assertTrue(properties.contains("nextPojo.nextPojo.weight"));
		assertTrue(properties.contains("nextPojo.nextPojo.nextPojo"));
		assertTrue(properties.contains("nextPojo.nextPojo.nextPojo.name"));
		assertTrue(properties.contains("nextPojo.nextPojo.nextPojo.name.bytes"));
		assertTrue(properties.contains("nextPojo.nextPojo.nextPojo.age"));
		assertTrue(properties.contains("nextPojo.nextPojo.nextPojo.weight"));
		assertTrue(properties.contains("nextPojo.nextPojo.nextPojo.nextPojo"));
		assertEquals(20, properties.size());
	}

}
