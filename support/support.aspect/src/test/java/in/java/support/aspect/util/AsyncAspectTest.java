package in.java.support.aspect.util;

import static org.junit.Assert.assertEquals;

import in.java.support.aspect.test.model.Car;

public class AsyncAspectTest {

	public void testAsync() throws Exception {
		Car car = new Car("farrari", true);
		car.accelerator();
//		assertEquals("run", run);
	}
}
