package affittiPostiLetto;

import java.io.Serializable;

/**
 * 
 * @version 1.0
 * @author user
 */
public class Nodo implements Serializable
{
	private Affitto info;
	private Nodo link;
	
	/**
	 * Costruttore. Quando viene istanziato un nuovo 
	 * nodo la componente link viene settata a null.
	 * @param processo oggetto di tipo Manutenzione
	 * che va a costituire la parte informativa del nodo
	 *  
	 */
	public Nodo (Affitto affitto)
	{
		setInfo(affitto);
		setLink(null);	
	}
	/**
	 *  Metodo getter che restituisce la componente
	 *  link del nodo
	 * @return link reference del prossimo oggetto nodo
	 * 
	 */
	public Nodo getLink() 
	{
		return link;
	}
	/**
	 * Metodo setter che consente di settare la
	 * componente link del nodo 
	 * @param link reference del prossimo oggetto nodo
	 * 
	 */
	public void setLink(Nodo link) 
	{
		this.link = link;
	}
	/**
	 * Metodo getter che restituisce la 
	 *  componente informativa del nodo.
	 *  @param info oggetto di tipo Manutenzio
	 * 
	 */
	public Affitto getInfo() 
	{
		return info;
	}
	/**
	 * Metodo setter che consente di settare la
	 *  componente informativa del nodo
	 * @param  info oggetto di tipo Manutenzione
	 */
	public void setInfo(Affitto info) 
	{
		this.info = info;
	}
	
	
}
