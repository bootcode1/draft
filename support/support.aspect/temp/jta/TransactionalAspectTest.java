package in.java.support.aspect.jta;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.aspectj.lang.Aspects;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.PlatformTransactionManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class TransactionalAspectTest {

	@Configuration
	@ComponentScan("in.java.support.aspect.jta")
//	@Profile("production")
    static class ContextConfiguration {
		@Bean
	    public DataSource h2DataSource() {
	        EmbeddedDatabase database = new EmbeddedDatabaseBuilder()
	                .setType(EmbeddedDatabaseType.H2)
	                .addScript("createOrderItemTable.sql")
	                .build();
			return database;
	    }

	    @Bean
	    public PlatformTransactionManager transactionManager() {
	        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
	        transactionManager.setDataSource(h2DataSource());
	        return transactionManager;
	    }
	    
	    @Bean
	    public JdbcTemplate jdbcTemplate() {
	        return new JdbcTemplate(h2DataSource());
	    }
    }
	
	@Autowired
	ApplicationContext context;

	@Autowired
	BeanFactory beanFactory;

	@Autowired 
	PlatformTransactionManager transactionManager;
	
	@Before 
	public void setUp() {
		
	}
    
    @Autowired
    private OrderService orderService;
    
    
    @Test
    public void persistOrderItems() {
    		TransactionalAspect transactionalAspect = Aspects.aspectOf(TransactionalAspect.class);
    		transactionalAspect.setTransactionManager(transactionManager);
        List<OrderItem> orders = Arrays.asList(
                new OrderItem("BWell Ethernet Cable", 5),
                new OrderItem("EDrive SSD", 2000)
        );
        try {
            orderService.persistOrders(orders);
        } catch (Exception e) {
            logException(e);
        }
        List<OrderItem> allOrders = orderService.getAllOrders();
        System.out.println("loaded orders: " + allOrders);

        System.out.println("-- second attempt --");
        List<OrderItem> orders2 = Arrays.asList(
                new OrderItem("BWell Ethernet Cable", 5),
                new OrderItem("EDrive SSD", 20)
        );
        try {
            orderService.persistOrders(orders2);
        } catch (Exception e) {
            logException(e);
        }
        List<OrderItem> allOrders2 = orderService.getAllOrders();
        System.out.println("loaded orders: " + allOrders2);
    }

    private static void logException(Exception e) {
        System.out.println("-- exception --");
        System.err.println("Exception: "+e.getClass().getName());
        System.err.println("Message: "+ e.getMessage());
        System.out.println("---------");
    }
}
