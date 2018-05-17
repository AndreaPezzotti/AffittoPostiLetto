package affittiPostiLetto;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;

public class MainClass 
{
	
		public static void main(String[] args) throws IOException 
		{
			
			
			ConsoleInput tastiera=new ConsoleInput();
			//Scanner tastiera=new Scanner(System.in);
			int ora,minuti;
			
			String[] elenco= {
					"1-Registrare nuovo micro-affitto",
					"2-Check-out",
					"3-Visualizza dati micro-affitti per i quali non è stato ancora svolto il checkout ordinandoli in base a Cognome e Nome",
					"4-Visualizza dati micro-affitti per i quali non è stato ancora svolto il checkout ordinandoli in base a data e ora di checkin più vicina",
					"5-Esci"};
			System.out.println("BENVENUTO \n");
			Affitto Affitto1 = new Affitto();
			Menu m1= new Menu(elenco);
			String nomeFile = "affitti.txt";
			LocalTime oraAttuale;
			String pp=null;
			PostiLetto p1=new PostiLetto();
			
			
			
			try 
			{
			p1.caricaAffitti(nomeFile);
			} 
			catch (ClassNotFoundException | IOException e) 
			{
				System.out.println("Impossibile caricare la lista");
			}
			
			
			
		}
	

}
