package affittiPostiLetto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PostiLetto implements Serializable
{
	private Nodo head;
	private int elementi;
	
	String nomefile= "postiLetto.bin";
	
	//COSTRUTTORI 
	
	/**
	 * Crea una lista vuota che verrà poi riempita
	 */
	public PostiLetto()
	{
		head = null;
		elementi = 0;
	}
	/**
	 * Costruttore di copia di posti letto
	 * @param x
	 */
	
	public PostiLetto(PostiLetto x)
	{
		this.head = x.head;
		this.elementi = x.elementi;
	}
	
	//METODI
	
	/**
	 * Restituisce il numero degli oggetti presenti nella lista
	 * @return elementi (int)
	 */
	public int getElementi() 
	{
		return elementi;
	}
	
	/**
	 * Crea un nodo
	 * @param affitto
	 * @param link
	 * @return nodo
	 */
	public Nodo creaNodo(Affitto affitto, Nodo link)
	{
		Nodo nodo = new Nodo(affitto);
		nodo.setLink(link);
		return nodo;
	}
	
	/**
	 * Ottiene il link di una posizione
	 * @param posizione
	 * @return nodo
	 * @throws AffittoException
	 */
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
		
		while(p.getLink() != null && n<posizione)
		{
			p = p.getLink();	
			n++;
		}
		return p;
	}
	
	/**
	 * Inserisce l'oggetto in testa
	 * @param affitto (Affitto)
	 */
	public void registraAffittoInTesta(Affitto affitto) throws IOException, FileException
	{
		Nodo p=creaNodo(affitto, head); 
		head = p;
		elementi++;
	}
	
	/**
	 * Inserisce l'oggetto in coda
	 * @param affitto (Affitto)
	 * @throws AffittoException
	 */
	public void registraAffittoInCoda(Affitto affitto) throws IOException, FileException, AffittoException
	{
		if(elementi == 0)
		{
			registraAffittoInTesta(affitto);
			
			return;
		}
		
		Nodo pn= creaNodo(affitto, null);
		Nodo p=getLinkPosizione(elementi);
		p.setLink(pn);
		
		elementi++;
	}
	
	/**
	 * Inserisce l'oggetto nella posizione desiderata
	 * @param affitto (Affitto)
	 * @param posizione (int)
	 * @throws AffittoException
	 */
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
	/**
	 * Elimina il primo oggetto della lista
	 * @throws AffittoException
	 */
	public void eliminaInTesta() throws AffittoException
	{
		if(elementi==0)
			throw new AffittoException("Lista vuota");
		head=head.getLink();
		elementi--;
	}
	/**
	 * Elimina l'ultimo oggetto della lista
	 * @throws AffittoException
	 */
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
	/**
	 * Elimina l'oggetto nella posizione desiderata
	 * @param posizione (int)
	 * @throws AffittoException
	 */
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
	/**
	 * Elimina un oggetto risalente a piu di 30 giorni dal'ora
	 *  in cui si esegue il software
	 * @param posizione (int)
	 * @throws AffittoException
	 */
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
	/**
	 * Ottieni l'oggetto nella posizione desiderata
	 * @param posizione (int)
	 * @return Affitto
	 * @throws AffittoException
	 */
	public Affitto getAffitto(int posizione) throws AffittoException
	{
		if(elementi==0)
			throw new AffittoException("Lista vuota");
		
		if(posizione < 0 || posizione > elementi)
			throw new AffittoException("Posizione non valida");
		
		Nodo p = getLinkPosizione(posizione);
		return p.getInfo();
	}
	/**
	 * Salva oggetto su file
	 * @param posizione (int)
	 * @throws IOException
	 */
	public void salvaPostiLetto(String nomeFile) throws IOException
	{
		FileOutputStream file = new FileOutputStream(nomeFile);
		ObjectOutputStream writer = new ObjectOutputStream(file);
		
		writer.writeObject(this); 
		writer.flush();
		writer.close();
	}
	/**
	 * Carica l'oggetto del file salvato
	 * @param nomeFile (string)
	 * 
	 */
	public PostiLetto caricaPostiLetto(String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file = new FileInputStream(nomeFile);
		ObjectInputStream reader = new ObjectInputStream(file);
		
		PostiLetto postiLetto;
		postiLetto = (PostiLetto)reader.readObject();
		file.close();
		
		return postiLetto;
	}
	/**
	 * Esegue il checkout dell'affitto mediante id
	 * @param posizione (int)
	 * @throws AffittoException
	 */
	public void checkoutAffitto(int id) throws AffittoException
	{
		LocalDateTime dataOdierna = LocalDateTime.now();
		
		if(elementi == 0)
			throw new AffittoException("Lista vuota");

		for (int i = 1; i <= elementi; i++) 
		{
			System.out.println(getAffitto(i).getCodiceIdentificativo());
			
			if(getAffitto(i).getCodiceIdentificativo() == id)
			{
				getAffitto(i).faiCheckout(dataOdierna);
				System.out.println("Svolto checkout nell'affitto di Codice Identificativo: " + id);
				return;
			}
		}
		
		System.out.println("Nessuna affitto presente con Codice Identificativo: " + id);
		return;
	}
	/**
	 * Trasforma una lista in un array
	 * @param posizione (int)
	 * @return Affitto[]
	 * @throws AffittoException
	 */
	public Affitto[] convertiListaArray() throws AffittoException
	{
		Affitto[] copia = new Affitto[elementi];
		
		for (int i = 0; i < copia.length; i++)
		{
			copia[i] = getAffitto(i+1);	
		}
		
		return copia;
	}
	/**
	 * ordina e visualizza gli affitti in base al cognome
	 * @return risultato
	 * @throws AffittoException
	 */
	public String visualizzaAffittiCognome() throws ClassNotFoundException, AffittoException, IOException, FileException
	{
		String risultato = "";
		if(elementi == 0)
			throw new AffittoException("Lista vuota");
		
		risultato = risultato + Ordinatore.OrdininaPerCognome(this).toString();
		
		
		return risultato;
	}
	/**
	 * ordina e visualizza gli affitti in base alla data
	 * @return risultato
	 * @throws AffittoException
	 */
	public String visualizzaAffittiData() throws ClassNotFoundException, AffittoException, IOException, FileException
	{
		String risultato = "";
		if(elementi == 0)
			throw new AffittoException("Lista vuota");
		
		risultato = risultato + Ordinatore.ordinaPerData(this).toString();
		
		
		return risultato;
	}
	
	/**
	 * Trasforma in stringa
	 */
	public String toString()
	{
		String risultato = "";
		Nodo nodo = head;
		
		while(nodo != null)
		{
			risultato += nodo.getInfo().toString()+"\n";
			nodo = nodo.getLink(); 
		}
		
		return risultato;
	}	
	
	public void esportaCSV (String nomeFile) throws IOException, AffittoException, FileException 
	{
		TextFile file = new TextFile (nomeFile,'W');
		String BackoupAffittiCSV;
		Affitto affitto;
		
		for (int i = 0; i < getElementi(); i++) 
		{
			affitto=getAffitto(i);
			BackoupAffittiCSV=affitto.getCodiceIdentificativo()+";"+affitto.getCognome()+";"+affitto.getNome()+";"+affitto.getDataOraCheckin()+";"+affitto.getDataOraCheckout();
			file.toFile(BackoupAffittiCSV);
		}
		file.closeFile();
		
	}
}
