package system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class CompteTest {

	private static final float DELTA = 0.001f;

	@Test
	void test() throws ParseException, SecurityException, IOException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date d1 = simpleDateFormat.parse("10/10/2017");
		Date d2 = simpleDateFormat.parse("11/11/2017");

		Compte c1 = new Compte(d1, 1, 1000);
		Compte c2 = new Compte(d2, 2, 2000);

		Client cli = new Client("zakrani", 11, "abdelali");
		cli.initialiseFileHandler();
		cli.getMCompte().add(c1);
		cli.getMCompte().add(c2);

		assertEquals(2, cli.getMCompte().size());
		assertSame(c1, cli.getMCompte().get(0));

		cli.afficherClient();
		c1.crediter(500);
		assertEquals(1500f, c1.getSolde(), DELTA);
		cli.afficherClient();
		c1.virement(c2, 1000);
		assertEquals(500f, c1.getSolde(), DELTA);
		assertEquals(3000f, c2.getSolde(), DELTA);
		cli.afficherClient();

		assertNotNull(c1.toString());
		cli.closeFileHandler();
	}

	@Test
	void testDebiter() {
		Compte c = new Compte(new Date(), 3, 100);

		c.debiter(40);
		assertEquals(60f, c.getSolde(), DELTA);

		c.debiter(1000);
		assertEquals(60f, c.getSolde(), DELTA);
	}

	@Test
	void testSetSolde() {
		Compte c = new Compte();
		assertEquals(0f, c.getSolde(), DELTA);

		c.setSolde(250);
		assertEquals(250f, c.getSolde(), DELTA);

		c.setSolde(-10);
		assertEquals(250f, c.getSolde(), DELTA);
	}

	@Test
	void testAccesseursCompte() {
		Date d = new Date();
		Compte c = new Compte();
		c.setDateCreation(d);
		c.setNumCompte(7);

		assertSame(d, c.getDateCreation());
		assertEquals(7, c.getNumCompte());
		assertTrue(c.toString().contains("numCompte=7"));
	}

	@Test
	void testAccesseursClient() {
		Client cli = new Client();
		cli.setNomClient("alami");
		cli.setNumClient(5);
		cli.setPrenomClient("sara");

		assertEquals("alami", cli.getNomClient());
		assertEquals(5, cli.getNumClient());
		assertEquals("sara", cli.getPrenomClient());

		List<Compte> comptes = new ArrayList<>();
		cli.setMCompte(comptes);
		assertSame(comptes, cli.getMCompte());
		assertTrue(cli.getMCompte().isEmpty());
	}

	@Test
	void testConstructeurClient() {
		Client cli = new Client("zakrani", 11, "abdelali");

		assertEquals("zakrani", cli.getNomClient());
		assertEquals(11, cli.getNumClient());
		assertEquals("abdelali", cli.getPrenomClient());
	}

	@Test
	void testAfficherSansHandler() {
		Client cli = new Client("zakrani", 11, "abdelali");
		cli.closeFileHandler();
		cli.afficherClient();

		assertTrue(cli.getMCompte().isEmpty());
	}
}