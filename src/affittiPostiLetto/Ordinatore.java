package affittiPostiLetto;

import java.io.IOException;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;

public class Ordinatore implements Serializable
{
	public static void scambia (PostiLetto postiLetto, int pos1, int pos2) throws AffittoException, IOException, FileException
	{
		if(pos1 > pos2)
			{
				int s;
				s = pos1;
				pos1 = pos2;
				pos2  =s;
			}
		
		if (pos1 < 1 || pos2 < 1 || pos1>postiLetto.getElementi() || pos2 > postiLetto.getElementi())
			throw new AffittoException("Posizione di scambio non valide");
		
		Affitto m1 = new Affitto(postiLetto.getAffitto(pos1));
		Affitto m2 = new Affitto(postiLetto.getAffitto(pos2));
		
		postiLetto.registraAffittoInPosizione(pos2, m1);
		postiLetto.registraAffittoInPosizione(pos1, m2);
		
		postiLetto.eliminaInPosizione(pos1+1);
		postiLetto.eliminaInPosizione(pos2+1);	
	}	
	
	public static void scambia (Affitto[] array, int pos1, int pos2)
	{
		Affitto s;
		
		if (pos1 < 0 || pos2 < 0 || pos1 >= array.length || pos2 >= array.length)
			return;
		
		else
		{
			s = array[pos1];
			array[pos1] = array[pos2];
			array[pos2] = s;
		}			
	}
	
	private static PostiLetto copiaAutofficina (PostiLetto autofficina) throws IOException, ClassNotFoundException
	{
		PostiLetto autofficina2 = new PostiLetto();
		autofficina.salvaPostiLetto("postiLettoCopia.bin");
		autofficina2 = autofficina2.caricaPostiLetto("postiLettoCopia.bin");
		
		return autofficina2;
	}
	
	public static PostiLetto OrdininaPerCognome(PostiLetto postiLetto) throws AffittoException, ClassNotFoundException, IOException, FileException
	{
		PostiLetto postiLetto2 = copiaAutofficina(postiLetto);
		boolean scambioAvvenuto;
		
		do
		{
			scambioAvvenuto = false;
			
			for (int i = 1; i < postiLetto2.getElementi(); i++) 
			{
				if(postiLetto2.getAffitto(i).getCognome().compareTo(postiLetto2.getAffitto(i+1).getCognome())>0)
				{
					scambia(postiLetto2,i,i+1);
					scambioAvvenuto = true;
				}
			}
		}
		while(scambioAvvenuto == true);
		
		return postiLetto2;
	}
	



}
