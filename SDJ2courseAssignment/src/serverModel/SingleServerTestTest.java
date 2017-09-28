package serverModel;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SingleServerTestTest {

	private SingleServer server1;
	private SingleServer server2;
	
	@Before
	public void setUp() throws Exception {
		server1=SingleServer.getInstance();
		server2=SingleServer.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		server1=null;
		server2=null;
	}

	// testing that the 2 SingleServer objects are actually the same object comparing their address in memory
	@Test
	public void testSameMemorySpaceOfDifferentVariables() {
		assertTrue(server1.equals(server2));
	}
	// testing the server1 object with a new instance from the SingleServer class by comparing the address in memory
	@Test
	public void testNewInstance(){
		assertTrue(server1.equals(SingleServer.getInstance()));
	}
	
	//server1 is defined as null,after the get instance method is called and compared with a new instance of the class
	@Test
	public void test(){
		server1=null;
		server1=server1.getInstance();
		assertTrue(server1.equals(server2));
	}
}
