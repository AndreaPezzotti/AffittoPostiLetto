package affittiPostiLetto;

import java.io.Serializable;

public class Nodo implements Serializable
{
	private Affitto info;
	private Nodo link;
	
	public Nodo (Affitto affitto)
	{
		setInfo(affitto);
		setLink(null);	
	}
	
	public Nodo getLink() {
		return link;
	}
	
	public void setLink(Nodo link) {
		this.link = link;
	}
	
	public Affitto getInfo() {
		return info;
	}
	
	public void setInfo(Affitto info) {
		this.info = info;
	}
	
	
}
