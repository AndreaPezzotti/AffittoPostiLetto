package affittiPostiLetto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFile 
{
	private char mode;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public TextFile(String nomeFile, char mode) throws IOException
	{
		this.mode='R';
		if (mode=='W' || mode=='w')
		{
			this.mode='W';
			writer=new BufferedWriter(new FileWriter(nomeFile));  //per scrivere in append,ovvero senza sovrascrivere aggiungo nella parentesi ",true"
		}
		else
			reader=new BufferedReader(new FileReader(nomeFile));
	}
	
	public void toFile (String line) throws IOException, FileException
	{
		if (mode=='R')
		{
			throw new FileException("File aperto in lettura");
		}
		writer.write(line);
		writer.newLine(); //va a capo
	}
	
	public String fromFile() throws FileException, IOException, EccezioneTextFileEOF
	{
		String line;
		if (mode=='W')
		{
			throw new FileException("File aperto in scrittura");
		}
		line=reader.readLine();
		if (line==null)
			throw new EccezioneTextFileEOF();
		return line;
	}
	
	public void closeFile() throws IOException
	{
		if (mode=='R') 
		{
			reader.close();
		}
		else
		{
			writer.close();
		}
	}
}
