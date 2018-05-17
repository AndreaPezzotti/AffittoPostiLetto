package affittiPostiLetto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Affitto 
{
	private int codiceIdentificativo;
	private String cognome;
	private String nome;
	private LocalDateTime dataOraCheckin;
	private LocalDateTime dataOraCheckout;
	private boolean checkout;
	
	
public Affitto(int codiceIdentificativo,String cognome,String nome,LocalDateTime dataOraCheckin,LocalDateTime dataOraCheckout)
	{
		setCodiceIdentificativo(codiceIdentificativo);
		setCognome(cognome);
		setNome(nome);
		setDataOraCheckin(dataOraCheckin);	
		setDataOraCheckout(dataOraCheckout);
		setCheckout(false);
	}

public Affitto(Affitto affitto)
{
	affitto.setCodiceIdentificativo(getCodiceIdentificativo());
	affitto.setCognome(getCognome());
	affitto.setNome(getNome());
	affitto.setDataOraCheckin(getDataOraCheckin());
	affitto.setDataOraCheckout(getDataOraCheckout());
	affitto.setCheckout(getCheckout());
}

public Affitto()
{
	setCodiceIdentificativo(0);
	setCognome("");
	setNome("");
	setDataOraCheckin(null);
	setDataOraCheckout(null);
	setCheckout(false);
}
	
public int getCodiceIdentificativo() 
{
	return codiceIdentificativo;
}

public void setCodiceIdentificativo(int codiceIdentificativo) 
{
	codiceIdentificativo = codiceIdentificativo;
}

public String getCognome() 
{
	return cognome;
}

public void setCognome(String cognome) 
{
	cognome = cognome;
}

public String getNome() 
{
	return nome;
}

public void setNome(String nome) 
{
	nome = nome;
}

public LocalDateTime getDataOraCheckin() 
{
	return dataOraCheckin;
}

public void setDataOraCheckin(LocalDateTime dataOraCheckin) 
{
	dataOraCheckin = dataOraCheckin;
}

public LocalDateTime getDataOraCheckout() 
{
	return dataOraCheckout;
}

public void setDataOraCheckout(LocalDateTime dataOraCheckout) 
{
	dataOraCheckout = dataOraCheckout;
}
	
public boolean getCheckout() 
{
	return checkout;
}

public void setCheckout(boolean checkout) 
{
	this.checkout = checkout;
}

public String toString()
{
	return (getCodiceIdentificativo()+" "+getCognome()+" "+getNome()+" "+getDataOraCheckin()+" "+getDataOraCheckout());
}

public void faiCheckout (LocalDateTime dataOraCheckout)
{
	setDataOraCheckout(dataOraCheckout);
	setCheckout(true);
}
	
}
