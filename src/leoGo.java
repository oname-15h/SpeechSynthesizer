import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
public class leoGo {
	public static void main(String[] args) throws IOException{
		String workingPath = "./LeoBot-v.1/";
	    ArrayList<String> soundsList = new ArrayList<String>();
	    String[] soundsArrayTesting = {"AA"," ","K","AE","T"," ","AH","T","AE","K","S"," ","AA"," ","HH","Y","UW","M","AH","N" };
	    Scanner keyboard = new Scanner(System.in);
	    String nextLine = "";
	    String help ="\n---------------------------"
	    		+ "\nINPUT SOUND CODES HERE "
	    		+ "\n OR USE \"TEST\" to output a test sentence "
	    		+ "\n OR USE \"INPUT\" TO CONVERT A WORD TO SOUNDS(\"END\" DOES NOT END) "
	    		+ "\n (TYPE END TO STOP AND TYPE HELP TO REPRINT THIS MESSAGE)"
	    		+ "\n BY TYPING PHONETIC SOUNDS YOU CAN ADD THOSE TO THE BUFFER"
	    		+ "\n USE \"CLEAR\" TO CLEAR BUFFER AND \"RUN\" TO RUN IT"
	    		+ "\n---------------------------";
	    System.out.println("\"WELCOE TO LEOBOT BETA V.2 WRITTEN BY MIGUEL SOLIS, LEO DOAN, VOICED BY LEO DOAN"+help);
	    System.out.print("?:");
	    nextLine = keyboard.nextLine().toUpperCase();	
	    while(!nextLine.equals("END")&&!nextLine.equals("QUIT")&&!nextLine.equals("EXIT")) { //processing the command aka terminal
	    	if(nextLine.contentEquals("INPUT")) {
	    	    System.out.print(">");
	    		nextLine = keyboard.nextLine().toUpperCase();
	    		convertSentenceToPhonemes(nextLine);
	    	}
	    	else  if (nextLine.equals("TEST")) {
		    	for(int x = 0; x<soundsArrayTesting.length; x++) {
			    	if(soundsArrayTesting[x].equals(" ")) pause();
			    	else playSound(workingPath+soundsArrayTesting[x]+".wav");
			    }
		    	System.out.println("\"A CAT ATTACKS A HUMAN\"");
	    	}
	    	else if(nextLine.equals("HELP")) {
	    		System.out.println("HELP MENU:"+help);
	    	}
	    	else if(nextLine.contentEquals("RUN")) {
	    	    System.out.println(soundsList);
	    	    for(int x = 0; x<soundsList.size(); x++) {
	    	    	if(soundsList.get(x).equals(" ")) {
	    	    		pause();
	    	    		try {
							AudioSystem.getClip().flush();
						} catch (LineUnavailableException e) {e.printStackTrace();}
	    	    	}
	    	    	else playSound(workingPath+soundsList.get(x)+".wav");
	    		
	    	}}
	    	else if(nextLine.contentEquals("CLEAR")) {
	    		soundsList.clear();
	    	}
	    	else if(nextLine.contentEquals("LIST")) {
	    		System.out.println(soundsList);
	    	}
	    	else {
	    	soundsList.add(nextLine);
	    	System.out.println("\""+nextLine+"\"");
	    	}
	    	System.out.print("?:");
	    	nextLine = keyboard.nextLine().toUpperCase();
	    }

	   
	    
		
	}
	@SuppressWarnings("resource")
	public static void convertSentenceToPhonemes(String sentence) throws IOException {
		File dictionary = new File("./cmudict.txt");
        Scanner keyboard = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new FileReader("./cmudict.txt"));
		String line = in.readLine();
		while(line != null&&!(line.contains(" "+sentence+" "))) {
            line = in.readLine();  
		}
		in.close();
		System.out.println(line);
		
	}
	public static void playSound(String audioFilePath) {
		File audioFile = new File(audioFilePath);
		long fileSize = audioFile.length();
		long waitTime = (long) (((fileSize*0.000005661)*1000)*1.1);
	    try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile.getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        
	        clip.open(audioInputStream);
	        clip.start();
	    	try {Thread.sleep(waitTime);} 
			catch (InterruptedException e) {e.printStackTrace();}
	    	if(clip.available() < 20) {
	    		clip.flush();
	    	}
	    } catch(Exception ex) {
	        System.out.println("Error with playing sound.");
	        ex.printStackTrace();
	    }

	}
	public static void pause() {
		try {
			Thread.sleep(250);//500
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
