package affittiPostiLetto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class MainClass 
{
	
		public static void main(String[] args) throws IOException 
		{	
			
			
			String[] elenco= 
					{
					"1-Registrare nuovo micro-affitto",
					"2-Check-out",
					"3-Visualizza dati micro-affitti per i quali non è stato ancora svolto il checkout ordinandoli in base a Cognome e Nome",
					"4-Visualizza dati micro-affitti per i quali non è stato ancora svolto il checkout ordinandoli in base a data e ora di checkin più vicina",
					"5-Visualizza posti letto registrati",
					"6-Esci"
					};
			
			ConsoleInput tastiera=new ConsoleInput();
			Menu menu= new Menu(elenco);
			PostiLetto postiLetto = new PostiLetto();
			
			try 
			{
				postiLetto=postiLetto.caricaPostiLetto("postiletto.bin");
			} 
			catch (ClassNotFoundException e1) 
			{
				System.out.println("Impossibile caricare oggetti di tipo Affitto");
			}
			catch (FileNotFoundException e) 
			{
				System.out.println("file non presente");
			}

			String nomeFile = "affitti.txt";

			System.out.println("BENVENUTO \n");
			
			int continuare = 1;
			
			do
			{
				switch (menu.scelta()) 
				{
				
					case 1:
					{
						Affitto affitto = new Affitto();
						LocalDateTime dataOra = LocalDateTime.of(1, 1, 1, 1, 1, 0);
						
						try 
						{
							System.out.println("Inserisci codice identificativo: ");
							affitto.setCodiceIdentificativo(tastiera.readInt());
				
							System.out.println("Inserisci cognome: ");
							affitto.setCognome(tastiera.readString());
							
							System.out.println("Inserisci nome: ");
							affitto.setNome(tastiera.readString());
				
							System.out.println("Inserisci l'anno (numero): ");
							dataOra = dataOra.plusYears(tastiera.readInt()-1);
				
							System.out.println("Inserisci il mese (numero): ");
							dataOra = dataOra.plusMonths(tastiera.readInt()-1);
				
							System.out.println("Inserisci il giorno (numero): ");
							dataOra = dataOra.plusDays(tastiera.readInt()-1);
							
							System.out.println("Inserisci l'ora (numero): ");
							dataOra = dataOra.plusHours(tastiera.readInt()-1);
							
							System.out.println("Inserisci i minuti (numero): ");
							dataOra = dataOra.plusMinutes(tastiera.readInt()-1);
							
							dataOra=dataOra.plusSeconds(0);
		
							affitto.setDataOraCheckin(dataOra);
				
							postiLetto.registraAffittoInTesta(affitto);
							postiLetto.salvaPostiLetto("postiletto.bin");
						}
						
						catch (NumberFormatException e) 
						{
							System.out.println("Formato dato inserito non corretto, registrazione AfFITTO fallita.");
							break;
						}
			
						catch (IOException e) 
						{
							System.out.println("Salvataggio fallito");
							break;
						} 
			
						catch (FileException e) 
						{
							System.out.println(e.toString());
							break;
						}
						
						break;
						
					}
					
					case 2:
					{
						System.out.println("Inserisci il Codice Identificativo dell'affito di cui fare il checkout: ");
						try
						{
							postiLetto.checkoutAffitto(tastiera.readInt());
						}
						
						catch (NumberFormatException e) 
						{
							System.out.println("Formato dato inserito non corretto, eliminazione manutenzione fallita");
							break;
						}
						
						catch (AffittoException e) 
						{
							System.out.println(e.toString());
							break;
						}
						
						break;
						
					}
					
					case 3:
					{
						try 
						{
						System.out.println(postiLetto.visualizzaAffittiCognome());
						} 
				
						catch (ClassNotFoundException e) 
						{
							System.out.println("Classe non trovata");
							break;
						} 
				
						catch (AffittoException e) 
						{
							System.out.println(e.toString());
							break;
						} 
				
						catch (IOException e) 
						{
							System.out.println("Errore di lettura o scrittura");
							break;
						} 
				
						catch (FileException e) 
						{
							System.out.println(e.toString());
							break;
						}
						break;
						
					}
					
					case 4:
					{
						try 
						{
							System.out.println(postiLetto.visualizzaAffittiData());
						} 
				
						catch (ClassNotFoundException e) 
						{
							System.out.println("Classe non trovata");
							break;
						} 
				
						catch (AffittoException e) 
						{
							System.out.println(e.toString());
							break;
						} 
				
						catch (IOException e) 
						{
							System.out.println("Errore di lettura o scrittura");
							break;
						} 
				
						catch (FileException e) 
						{
							System.out.println(e.toString());
							break;
						}
						break;
						
					}
			
					case 5:
					{
						System.out.println(postiLetto.toString());
					}
					break;
					

					case 6:
						
						break;
					}
			
					System.out.println("Vuoi continuare a usare il programma? (1--> si) (altro-->no)");
					
					try 
					{
						continuare=tastiera.readInt();
					}	 
				
					catch (NumberFormatException e) 
					{
						System.out.println("Formato dato non corretto");
						break;
					} 
				
					catch (IOException e) 
					{
						System.out.println("Errore di lettura o scrittura");
						break;
					}	
			}
			while(continuare == 1);
		}
}
