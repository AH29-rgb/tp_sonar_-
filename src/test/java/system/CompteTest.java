package system;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

class CompteTest {

    private static final float DELTA = 0.001f;

    @Test
    void test() throws ParseException, IOException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d1 = simpleDateFormat.parse("10/10/2017");
        Date d2 = simpleDateFormat.parse("11/11/2017");

        Compte c1 = new Compte(d1, 1, 1000);
        Compte c2 = new Compte(d2, 2, 2000);

        Client cli = new Client("ensam", 11, "ensam");
        cli.initialiseFileHandler();
        try {
            cli.getMCompte().add(c1);
            cli.getMCompte().add(c2);
            cli.afficherClient();

            c1.crediter(500);
            assertEquals(1500, c1.getSolde(), DELTA);
            cli.afficherClient();

            c1.virement(c2, 1000);
            assertEquals(500, c1.getSolde(), DELTA);
            assertEquals(3000, c2.getSolde(), DELTA);
            cli.afficherClient();
        } finally {
            cli.closeFileHandler();
        }
    }

    @Test
    void debiterAvecSoldeSuffisant() {
        Compte c = new Compte(new Date(), 1, 100);
        c.debiter(40);
        assertEquals(60, c.getSolde(), DELTA);
    }

    @Test
    void debiterAvecSoldeInsuffisantNeChangeRien() {
        Compte c = new Compte(new Date(), 1, 100);
        c.debiter(150);
        assertEquals(100, c.getSolde(), DELTA);
    }

    @Test
    void setSoldeNegatifEstIgnore() {
        Compte c = new Compte();
        c.setSolde(50);
        c.setSolde(-10);
        assertEquals(50, c.getSolde(), DELTA);
    }

    @Test
    void gettersEtSettersCompte() {
        Date d = new Date();
        Compte c = new Compte();
        c.setDateCreation(d);
        c.setNumCompte(7);
        assertSame(d, c.getDateCreation());
        assertEquals(7, c.getNumCompte());
        assertEquals(0, c.getSolde(), DELTA);
    }

    @Test
    void toStringContientLesInformations() {
        Compte c = new Compte(new Date(), 3, 25);
        String s = c.toString();
        assertTrue(s.contains("NumCompte=3"));
        assertTrue(s.contains("Solde=25.0"));
    }

    @Test
    void gettersEtSettersClient() {
        Client cli = new Client();
        assertNull(cli.getNomClient());
        cli.setNomClient("Alami");
        cli.setPrenomClient("Sara");
        cli.setNumClient(5);
        assertEquals("Alami", cli.getNomClient());
        assertEquals("Sara", cli.getPrenomClient());
        assertEquals(5, cli.getNumClient());

        ArrayList<Compte> comptes = new ArrayList<>();
        cli.setMCompte(comptes);
        assertSame(comptes, cli.getMCompte());
    }

    @Test
    void afficherClientAvecLogDesactive() {
        Logger logger = Logger.getLogger(Client.class.getName());
        Level ancien = logger.getLevel();
        logger.setLevel(Level.OFF);
        try {
            Client cli = new Client("a", 1, "b");
            cli.getMCompte().add(new Compte());
            cli.afficherClient();
            assertNotNull(cli.getMCompte());
        } finally {
            logger.setLevel(ancien);
        }
    }

    @Test
    void closeFileHandlerSansInitialisationNeFaitRien() {
        Client cli = new Client();
        cli.closeFileHandler();
        assertTrue(new File(".").exists());
    }
}
