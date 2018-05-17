package affittiPostiLetto;

public class AffittoException extends Exception 
{
	private String messaggio;
	
	public AffittoException(String messaggio)
	{
		this.messaggio=messaggio;
	}
	public String toString()
	{
		return messaggio;
	}
}