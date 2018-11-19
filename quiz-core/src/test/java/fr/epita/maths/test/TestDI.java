package fr.epita.maths.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"/applicationContext.xml"})
public class TestDI {
	
	private static final Logger LOGGER = LogManager.getLogger(TestDI.class);

	@Inject
	String query;
	
	
	@Inject
	DataSource ds;
	
	@Test
	public void firstDITest() {
		LOGGER.info(query);
		Assert.assertNotNull(query);
	}
	
	@Test
	public void dsInjectionTest() throws SQLException {
		Connection connection = ds.getConnection();
		
		String schema = connection.getSchema();
		Assert.assertEquals("PUBLIC", schema);
		
		LOGGER.info("schema : {}", schema);
		connection.close();
	}
	
}
