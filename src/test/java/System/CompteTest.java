package System;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class CompteTest {

	@Test
	void test() throws ParseException {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("dd/mm/yyyy");
    
	Date d1=simpleDateFormat.parse("10/10/2017");
	
	Date d2=simpleDateFormat.parse("11/11/2017");
	
	
	Compte c1=new Compte(d1, 1, 1000);
	Compte c2=new Compte(d2, 2, 2000);
	
	Client cli=new Client("zakrani", 11, "abdelali");
	cli.m_Compte.add(c1);
	cli.m_Compte.add(c2);
	cli.afficherClient();
	c1.crediter(500);
	cli.afficherClient();
	c1.virement(c2, 1000);
	cli.afficherClient();
		}

	}
