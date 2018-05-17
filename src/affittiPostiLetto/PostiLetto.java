package affittiPostiLetto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;

public class PostiLetto implements Serializable
{
	private Nodo head;
	private int elementi;
	
	String nomefile= "postiLetto.bin";
	
	//COSTRUTTORI 
	public PostiLetto()
	{
		head = null;
		elementi = 0;
	}
	
	public PostiLetto(PostiLetto x)
	{
		this.head = x.head;
		this.elementi = x.elementi;
	}
	
	//METODI
	
	public int getElementi() 
	{
		return elementi;
	}
	
	public Nodo creaNodo(Affitto affitto, Nodo link)
	{
		Nodo nodo = new Nodo(affitto);
		nodo.setLink(link);
		return nodo;
	}
	
	private Nodo getLinkPosizione(int posizione) throws AffittoException
	{
		Nodo p;
		int n;
		p=head;
		n=1;
		if(posizione<1 || posizione>elementi)
			throw new AffittoException("Posizione non valida");
		if(elementi==0)
			throw new AffittoException("Lista vuota");
		while(p.getLink()!= null && n<posizione)
		{
			p = p.getLink();	
			n++;
		}
		return p;
	}
	
	public void registraAffittoInTesta(Affitto affitto) throws IOException, FileException
	{
		Nodo p=creaNodo(affitto, head); // il nodo p punta a head
		head=p; //head andrà a puntare a p
		elementi++;
	}
	
	public void registraAffittoInCoda(Affitto affitto) throws IOException, FileException, AffittoException
	{
		if(elementi==0)
		{
			registraAffittoInTesta(affitto);
			return;
		}
		Nodo pn= creaNodo(affitto, null);
		Nodo p=getLinkPosizione(elementi);
		p.setLink(pn);
		
		elementi++;
	}
	
	public void registraAffittoInPosizione(int posizione, Affitto affitto) throws AffittoException, IOException, FileException
	{
		if(posizione<=1)
		{
			registraAffittoInTesta(affitto);
			return;
		}
		if(posizione>elementi)
		{
			registraAffittoInTesta(affitto);
			return;
		}
		Nodo pn=creaNodo(affitto, getLinkPosizione(posizione));
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(pn);
		elementi++;
	}
	
	public void eliminaInTesta() throws AffittoException
	{
		if(elementi==0)
			throw new AffittoException("Lista vuota");
		head=head.getLink();
		elementi--;
	}
	
	public void eliminaInCoda() throws AffittoException
	{
		if(elementi==0)
			throw new AffittoException("Lista vuota");
		if(elementi==1)
			{
			eliminaInTesta();
			return;
			}
		Nodo p=getLinkPosizione(elementi-1);
		p.setLink(null);
		elementi--;
	}

	public void eliminaInPosizione(int posizione) throws AffittoException
	{
		if(elementi==0)
			throw new AffittoException("Lista vuota");
		if(posizione<0 || posizione>elementi)
			throw new AffittoException("Posizione non valida");
		if(posizione==1)
		{
			eliminaInTesta();
			return;
		}
		if(posizione==elementi)
		{
			eliminaInCoda();
			return;
		}
		Nodo p=getLinkPosizione(posizione);
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(p.getLink());
		elementi--;
	}
	
	public void eliminaAfffitto(int id) throws AffittoException
	{
		int elementiPrimaDiEliminazione=elementi;
		LocalDate oggi = LocalDate.now();
		long giorniTrascorsi;
		
		
		if(elementi==0)
			throw new AffittoException("Lista vuota");
		for (int i = 1; i <= elementi; i++) 
		{
			
				
				boolean avvenutaEliminazione = false;
				if((i==1)&&(getLinkPosizione(i).getInfo().getCodiceIdentificativo()==id))
				{
					eliminaInTesta();
					avvenutaEliminazione=true;
					
				}
				
				if((i==elementi)&&(getLinkPosizione(i).getInfo().getCodiceIdentificativo()==id))
				{
					eliminaInCoda();
					avvenutaEliminazione=true;
					
					return;
				}
				if(avvenutaEliminazione==false)
					{
						if(getLinkPosizione(i).getInfo().getCodiceIdentificativo()==id)
						{
							Nodo p=getLinkPosizione(i);
							Nodo precedente=getLinkPosizione(i-1);
							precedente.setLink(p.getLink());
							elementi--;
						
						}
					}
				if(elementi>0 && getLinkPosizione(i).getInfo().getCodiceIdentificativo()==id)
					i=0;
				}
		if(elementiPrimaDiEliminazione==getElementi())
			System.out.println("Matricola macchinario non presente, eliminazione non avventuta");
				
	}
	
	public Affitto getAffitto(int posizione)throws AffittoException
	{
		if(elementi==0)
			throw new AffittoException("Lista vuota");
		
		if(posizione<0 || posizione>elementi)
			throw new AffittoException("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo();
	}
	
	public void salvaAffitti(String nomeFile) throws IOException
	{
		FileOutputStream file=new FileOutputStream(nomeFile);
		ObjectOutputStream writer= new ObjectOutputStream(file);
		writer.writeObject(this); //salva questo oggetto
		writer.flush();
		writer.close();
	}
	
	public Affitto caricaAffitti(String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file= new FileInputStream(nomeFile);
		ObjectInputStream reader=new ObjectInputStream(file);
		
		Affitto affitto;
		affitto=(Affitto)reader.readObject();
		file.close();
		return affitto;
	}

	

}
