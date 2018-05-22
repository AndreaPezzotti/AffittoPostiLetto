package affittiPostiLetto;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class AffittoTest 
{

	@Test
	public void testCostruttoreAffitto() 
	{
		LocalDateTime dataOra=LocalDateTime.now();
		Affitto affitto=new Affitto(1,null, null, dataOra, dataOra);
		assertTrue("Costruttore",affitto.getCodiceIdentificativo()== 1 && affitto.getCognome()=="ciao" && affitto.getNome()=="fiu" && affitto.getDataOraCheckin()==dataOra && affitto.getDataOraCheckout()==dataOra);
	}
	
	@Test
	public void testCostruttore() 
	{
		LocalDateTime dataOra=LocalDateTime.now();
		Affitto affitto=new Affitto();
		assertTrue("Costruttore affitto",affitto.getCodiceIdentificativo()==0 && affitto.getCognome()==null && affitto.getNome()==null && affitto.getDataOraCheckin()==dataOra && affitto.getDataOraCheckout()==dataOra);
	}

	@Test
	public void testSetCodiceIdentificativo() 
	{
		Affitto affitto=new Affitto();
		affitto.setCodiceIdentificativo(1);
		assertTrue("setCodiceIdentificativo",affitto.getCodiceIdentificativo()==1);
	}
	
	@Test
	public void testSetCognome() 
	{
		Affitto affitto=new Affitto();
		affitto.setCognome("ciao");
		assertTrue("setCognome",affitto.getCognome()=="ciao");
	}
	
	@Test
	public void testSetNome() 
	{
		Affitto affitto=new Affitto();
		affitto.setNome("lovvo");
		assertTrue("setNome",affitto.getNome()=="lovvo");
	}
	
	
	@Test
	public void testSetDataOraCheckin() 
	{
		Affitto affitto=new Affitto();
		LocalDateTime dataOraCheckin=LocalDateTime.now();
		affitto.setDataOraCheckin(dataOraCheckin);
		assertTrue("setDataOraCheckin",affitto.getDataOraCheckin()==dataOraCheckin);
	}
	
	@Test
	public void testSetDataOracheckout() 
	{
		Affitto affitto=new Affitto();
		LocalDateTime dataOraCheckout=LocalDateTime.now();
		affitto.setDataOraCheckin(dataOraCheckout);
		assertTrue("setDataOraCheckout",affitto.getDataOraCheckout()==dataOraCheckout);
	}
	
	@Test
	public void testEquals() 
	{
		LocalDateTime dataOra=LocalDateTime.now();
		Affitto affitto=new Affitto(1,null, null, dataOra, dataOra);
		Affitto affitto1=new Affitto(affitto);
		assertTrue("setEquals",affitto.equals(affitto1)==true);
	}
}
