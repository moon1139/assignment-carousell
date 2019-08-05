package moon1139;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import moon1139.commands.Cli;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTest2 {

	@Autowired
	private Cli cli;
	
	@Test
	public void test1() {
		
		String res;
		
		res = cli.input("REGISTER user1").getMessage();
		assertEquals("Success", res);
		
		res = cli.input("CREATE_LISTING user1 'Phone model 8' 'Black color, brand new' 1000 'Electronics'").getMessage();
		String listingId1 = res;
		
		res = cli.input("GET_LISTING user1 " + listingId1).getMessage();
		assertEquals(true, res.matches("Phone model 8\\|Black color, brand new\\|1000(.*)\\|Electronics\\|user1"));
		
		res = cli.input("CREATE_LISTING user1 'Black shoes' 'Training shoes' 200 'Sports'").getMessage();
		String listingId2 = res;
		
		res = cli.input("CREATE_LISTING user1 'Red shoes' 'Training shoes' 100 'Sports'").getMessage();
		String listingId3 = res;
		
		res = cli.input("REGISTER user2").getMessage();
		assertEquals("Success", res);
		
		res = cli.input("REGISTER user2").getMessage();
		assertEquals("Error - user already existing", res);
		
		res = cli.input("CREATE_LISTING user2 'T-shirt' 'White color' 20 'Sports'").getMessage();
		String listingId4 = res;
		
		res = cli.input("GET_LISTING user2 " + listingId4).getMessage();
		assertEquals(true, res.matches("T-shirt\\|White color\\|20\\|(.*)\\|Sports\\|user2"));
		
		res = cli.input("GET_CATEGORY user1 Fashion sort_time asc").getMessage();
		assertEquals("Error - category not found", res);
		
		res = cli.input("GET_CATEGORY user1 Sports sort_time desc").getMessage();
		assertEquals(true, res.matches("Red shoes\\|Training shoes\\|100\\|(.*)"+"\n"
									 + "Black shoes\\|Training shoes\\|200\\|(.*)"));
		
		res = cli.input("GET_CATEGORY user1 Sports sort_price desc").getMessage();
		assertEquals(true, res.matches("Black shoes\\|Training shoes\\|200(.*)"+"\n"
									 + "Red shoes\\|Training shoes\\|100(.*)"));
		
		res = cli.input("GET_TOP_CATEGORY user1").getMessage();
		assertEquals("Sports", res);
		
		res = cli.input("DELETE_LISTING user1 "+ listingId4).getMessage();
		assertEquals("Error - listing owner mismatch", res);
		
		res = cli.input("DELETE_LISTING user2 "+ listingId4).getMessage();
		assertEquals("Success", res);
		
		res = cli.input("DELETE_LISTING user1 "+ listingId3).getMessage();
		assertEquals("Success", res);
		
		res = cli.input("GET_TOP_CATEGORY user1").getMessage();
		assertEquals("Electronics\nSports", res);
		
		res = cli.input("GET_TOP_CATEGORY user3").getMessage();
		assertEquals("Error - unknown user", res);
	}
	
	@Test
	public void test2() {
		
		String res;
		
		res = cli.input("REGISTER").getMessage();
		assertEquals("Validator: Too few parameters. Usage: REGISTER <username>", res);
		
		res = cli.input("REGISTER AAA BBB").getMessage();
		assertEquals("Validator: Too many parameters. Usage: REGISTER <username>", res);
		
		res = cli.input("NoSuchCommand").getMessage();
		assertEquals("No command registered for NoSuchCommand", res);
		
		res = cli.input("HELP").getMessage();
		assertEquals("CREATE_LISTING <username> <title> <description> <price> <category>\n"
				+ "DELETE_LISTING <username> <listing_id>\n"
				+ "Exit : to exit\n"
				+ "GET_CATEGORY <username> <category> {sort_price|sort_time} {asc|dsc}\n"
				+ "GET_LISTING <username> <listing_id>\n"
				+ "GET_TOP_CATEGORY <username>\n"
				+ "HELP : I am here to help ^^\n"
				+ "REGISTER <username>", res);
	}

}
