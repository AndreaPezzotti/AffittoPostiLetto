package affittiPostiLetto;

public class Nodo 
{
	private Invitato info;
	private Nodo link;
	
	public Nodo (Invitato persona)
	{
		setInfo(persona);
		link=null;
	}

	public Invitato getInfo() {
		return info;
	}

	public void setInfo(Invitato info) 
	{
		this.info = new Invitato( info);
	}

	public Nodo getLink() {
		return link;
	}

	public void setLink(Nodo link) {
		this.link = link;
	}
}
