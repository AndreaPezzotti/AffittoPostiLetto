package affittiPostiLetto;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class PostiLettoTest 
{



	@Test	public void testRegitraAffitto() throws IOException, FileException, AffittoException 
	{
		PostiLetto postiLetto=new PostiLetto();
		Affitto affitto1=new Affitto();
		postiLetto.registraAffittoInCoda(affitto1);
		assertTrue("registra Affitto",postiLetto.getElementi()==1);
	}
	
	@Test 
	public void testToString() throws AffittoException 
	{
		PostiLetto postiLetto=new PostiLetto();
		LocalDateTime dataOra=LocalDateTime.now();
		Affitto affitto=new Affitto(1,null, null, dataOra, dataOra);
		//PostiLetto.registraAffittoInCoda(affitto);
		assertTrue(postiLetto.toString().compareToIgnoreCase("-->"+affitto.toString()+"\n")==0);
		
	}
	
	@Test 
	public void testToStringVuoto() 
	{
		PostiLetto postiLetto=new PostiLetto();
		assertTrue("toString ",postiLetto.toString().compareToIgnoreCase("")==0);
		
	}
	
	@Test 
	public void testGetAffitto() throws AffittoException, IOException, FileException 
	{
		PostiLetto postiLetto=new PostiLetto();
		LocalDateTime dataOra=LocalDateTime.now();
		Affitto affitto=new Affitto(1,null, null, dataOra, dataOra);
		postiLetto.registraAffittoInCoda(affitto);
		assertTrue("getAffitto",postiLetto.getAffitto(1).getCodiceIdentificativo()==affitto.getCodiceIdentificativo() && postiLetto.getAffitto(1).getCognome()==affitto.getCognome()   && postiLetto.getAffitto(1).getNome()==affitto.getNome() && postiLetto.getAffitto(1).getDataOraCheckin()==affitto.getDataOraCheckin()  && postiLetto.getAffitto(1).getDataOraCheckout()==affitto.getDataOraCheckout() );
	}
	
	@Test (expected=AffittoException.class)
	public void testGetPostiLettoVuoto() throws AffittoException 
	{
		PostiLetto postiLetto=new PostiLetto();
		postiLetto.getAffitto(1);
	}
	
	@Test (expected=AffittoException.class)
	public void testGetAccessoPosizioneNonValida() throws AffittoException
	{
		PostiLetto postiLetto=new PostiLetto();
		Affitto affitto=new Affitto();
		PostiLetto.registraAffittoInTesta(affitto);
		affitto.getAffitto(2);
	}
	
	@Test 
	public void testEliminaInTesta() throws AffittoException
	{
		PostiLetto postiLetto=new PostiLetto();
		Affitto affitto=new Affitto();
		PostiLetto.registraAffittoInTesta(affitto);
		postiLetto.eliminaInTesta();
		assertTrue("EliminaInTesta", postiLetto.getHead()==null);
	}
	
	@Test (expected=AffittoException.class)
	public void testEliminaInTestaPostiLettoVuoto() throws AffittoException
	{
		PostiLetto postiLetto=new PostiLetto();
		postiLetto.eliminaInTesta();
	}
	
	@Test 
	public void testEliminaInCoda() throws AffittoException
	{
		PostiLetto postiLetto=new PostiLetto();
		Affitto affitto=new Affitto();
		PostiLetto.registraAffittoInCoda(affitto);
		postiLetto.eliminaInCoda();
		assertTrue("EliminaInCoda", postiLetto.getHead()==null);
	}
	
	@Test (expected=AffittoException.class)
	public void testEliminaInCodaPostiLettoVuoto() throws AffittoException
	{
		PostiLetto postiLetto=new PostiLetto();
		postiLetto.eliminaInCoda();
	}
	
	@Test
	public void testSalvaCaricaPostiLetto() throws IOException, ClassNotFoundException 
	{
		PostiLetto postiLetto=new PostiLetto();
		LocalDate data=LocalDate.now();
		LocalTime ora=LocalTime.now();
		LocalDateTime dataOra=LocalDateTime.of(data, ora);
		Affitto affitto=new Affitto(1,null, null, dataOra, dataOra);
		postiLetto.registraAffittoInCoda(affitto);
		postiLetto.salvaPostiLetto(data);
		postiLetto lcopia=postiLetto.caricaPostiLetto(data);
		assertTrue("Salva e carica laboratorio con parametro data",laboratorio.toString().compareTo(lcopia.toString())==0);
	}
	
	@Test
	public void testSalvaCaricaPostiLettoNomeFile() throws IOException, ClassNotFoundException 
	{
		PostiLetto postiLetto=new PostiLetto();
		LocalDate data=LocalDate.now();
		LocalTime ora=LocalTime.now();
		LocalDateTime dataOra=LocalDateTime.of(data, ora);
		Affitto affitto=new Affitto(1,null, null, dataOra, dataOra);
		postiLetto.registraAffittoInCoda(affitto);
		postiLetto.salvaPostiLetto("test.bin");
		PostiLetto lcopia=postiLetto.caricaPostiLetto("test.bin");
		assertTrue("Salva e carica PostiLetto con parametro stringa",postiLetto.toString().compareTo(lcopia.toString())==0);
	}
	
	@Test(expected=IOException.class)
	public void testCaricaLaboratorio() throws IOException, ClassNotFoundException 
	{
		PostiLetto postiLetto=new PostiLetto();
		//E' NECESSARIO INSERIRE UNA DATA PER LA QUALE NON SONO PRESENTI ACCESSI 
		//E QUINDI NON E' MEMORIZZATO IL FILE BINARIO, ALTRIMENTI IL TEST NON ANDRA' A BUON FINE
		LocalDate data=LocalDate.of(-1,1,1);
		postiLetto.caricaPostiLetto(data);
	}
	
	@Test(expected=IOException.class)
	public void testCaricaPostiLettoNomeFile() throws IOException, ClassNotFoundException 
	{
		PostiLetto postiLetto=new PostiLetto();
		PostiLetto.caricaPostiLetto("FileNonPresente.bin");
	
	}

}
