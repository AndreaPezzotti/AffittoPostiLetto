package affittiPostiLetto;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodoTest 
{

	
	@Test
	public void testCostruttoreNodo() 
	{
		Affitto affitto=new Affitto();
		Nodo n=new Nodo(affitto);
		assertTrue("Costruttore nodo", n.getInfo().equals(affitto) && n.getLink()==null);
	}

	@Test
	public void testSetInfo() 
	{
		Affitto a=new Affitto();
		Affitto a1=new Affitto();
		Nodo n=new Nodo(a);
		n.setInfo(a1);
		assertTrue("setInfo",n.getInfo().equals(a1) && n.getLink()==null);
	}
	
	@Test
	public void testSetLink() 
	{
		Affitto a=new Affitto();
		Affitto a1=new Affitto();
		Nodo nodo1=new Nodo(a);
		Nodo nodo2=new Nodo(a1);
		nodo1.setLink(nodo2);
		assertTrue("setLink",nodo1.getInfo().equals(a1) && nodo1.getLink()==nodo2);
	}

}
