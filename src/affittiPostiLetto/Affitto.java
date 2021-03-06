package affittiPostiLetto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Affitto implements Serializable
{
	private int codiceIdentificativo;
	private String cognome;
	private String nome;
	private LocalDateTime dataOraCheckin;
	private LocalDateTime dataOraCheckout;
	private boolean checkout;
	
	/**
	 * Costruttore. Quando viene istanziata un nuovo affitto si assegnare il codiceIdentificativo,cognome,nome,
	 * datOraCheckin mentre dataOraCheckout viene lasciato null fin quando non viene effettuata l'uscita.
	 * @param codiceIdentificativo nome del computer che ha inviato la richiesta di stampa
	 * @param cognome cognome del cliente che affitta un posto letto
	 * @param nome nome del cliente che affitta un posto letto
	 * @param dataOraCheckin ora che un cliente entra nella struttura
	 * @param dataOraCheckou ora che un cliente esce dalla struttura
	 */
public Affitto(int codiceIdentificativo, String cognome, String nome, LocalDateTime dataOraCheckin, LocalDateTime dataOraCheckout)
	{
		setCodiceIdentificativo(codiceIdentificativo);
		setCognome(cognome);
		setNome(nome);
		setDataOraCheckin(dataOraCheckin);	
		setDataOraCheckout(dataOraCheckout);
		setCheckout(false);
	}
/**
 * Costruttore di copia.
 * @param affitto
 */

public Affitto(Affitto affitto)
{
	affitto.setCodiceIdentificativo(getCodiceIdentificativo());
	affitto.setCognome(getCognome());
	affitto.setNome(getNome());
	affitto.setDataOraCheckin(getDataOraCheckin());
	affitto.setDataOraCheckout(getDataOraCheckout());
	affitto.setCheckout(getCheckout());
}

/**
 * Costruttore Vuoto.
 */
public Affitto()
{
	setCodiceIdentificativo(0);
	setCognome("");
	setNome("");
	setDataOraCheckin(null);
	setDataOraCheckout(null);
	setCheckout(false);
}
/** 
* Metodo getter che restituisce il codiceIdentificativo del cliente che ha affittato un posto letto.
* @return codiceIdentificativo del cliente che ha affittato un posto letto.
*/
public int getCodiceIdentificativo() 
{
	return codiceIdentificativo;
}

/**
 * Metodo setter che consente di settare il codiceIdentificativo del cliente che ha affittato un posto letto.
 * @param codiceIdentificativo del cliente che ha affittato un posto letto.
 */

public void setCodiceIdentificativo(int codiceIdentificativo) 
{
	this.codiceIdentificativo = codiceIdentificativo;
}
/** 
* Metodo getter che restituisce il cognome del cliente che ha affittato un posto letto.
* @return cognome del cliente che ha affittato un posto letto.
*/
public String getCognome() 
{
	return cognome;
}
/** 
* Metodo setter che consente di settare il cognome del cliente che ha affittato un posto letto.
* @return cognome del cliente che ha affittato un posto letto.
*/
public void setCognome(String cognome) 
{
	this.cognome = cognome;
}
/** 
* Metodo getter che restituisce il nome del cliente che ha affittato un posto letto.
* @return nome del cliente che ha affittato un posto letto.
*/
public String getNome() 
{
	return nome;
}
/** 
* Metodo setter che consente di settare il nome del cliente che ha affittato un posto letto.
* @return nome del cliente che ha affittato un posto letto.
*/
public void setNome(String nome) 
{
	this.nome = nome;
}
/** 
* Metodo getter che restituisce la data e l'ora che un cliente entra nella struttura.
* @return data e ora che un cliente entra nella struttura.
*/
public LocalDateTime getDataOraCheckin() 
{
	return dataOraCheckin;
}
/** 
* Metodo setter che consente di settare la data e l'ora che un cliente entra nella struttura.
* @return data e ora che un cliente entra nella struttura.
*/
public void setDataOraCheckin(LocalDateTime dataOraCheckin) 
{
	this.dataOraCheckin = dataOraCheckin;
}
/** 
* Metodo getter che restituisce la data e l'ora che un cliente esce nella struttura.
* @return data e ora che un cliente esce nella struttura.
*/
public LocalDateTime getDataOraCheckout() 
{
	return dataOraCheckout;
}
/** 
* Metodo setter che consente di settare la data e l'ora che un cliente esce nella struttura.
* @return data e ora che un cliente esce nella struttura.
*/
public void setDataOraCheckout(LocalDateTime dataOraCheckout) 
{
	this.dataOraCheckout = dataOraCheckout;
}

/**
 * Metodo getter che restituisce true o false in base se sull'oggetto � stato effettuato il checkout
 * @return checkout 
 */
public boolean getCheckout() 
{
	return checkout;
}
/**
 * Metodo setter che consente di settare true o false se sull'oggetto � stato effettuato o no il checkout
 * @return checkout 
 */
public void setCheckout(boolean checkout) 
{
	this.checkout = checkout;
}
/**
 * Restituisce una stringa contenente codiceIdentificativo,cognome,nome,checkin,checkout
 * @return codiceIdentificativo,cognome,nome,checkin,checkout
 */
public String toString()
{
	return ("ID: "+getCodiceIdentificativo()+"; Cognome: "+getCognome()+"; Nome: "+getNome()+"; Data Checkin: "+getDataOraCheckin()+"; Data Checkout: "+getDataOraCheckout());
}
/**
 * Svolge il checkout aggiungendo data e ora di checkout all'oggetto
 * @param data e ora del checkout
 */
public void faiCheckout (LocalDateTime dataOraCheckout)
{
	setDataOraCheckout(dataOraCheckout);
	setCheckout(true);
}
/**
 * verifica se 2 affitti sono uguali
 * @param a affitto da confrontare
 * @return se sono uguali
 */
public boolean equals(Affitto a)
{
	if(getCodiceIdentificativo()==a.getCodiceIdentificativo())
		return true;
	else
		return false;
}
}
