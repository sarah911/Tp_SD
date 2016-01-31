package com.tp.consensus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class MessageSerializerDeserializer {
	private static MessageSerializerDeserializer uniqueInstance;// Stockage de l'unique instance de cette classe.
	
	public static MessageSerializerDeserializer getInstance()
	{
		if(uniqueInstance==null)
		{
			uniqueInstance = new MessageSerializerDeserializer();
		}
		return uniqueInstance;
	}
	public void init() {
		File f = new File("p1-pending.txt");
		if(f.exists())
			f.delete();
		File f2 = new File("p2-pending.txt");
		if(f2.exists())
			f2.delete();
	}
	public void messageSerializer(LinkedList<Message> messages, int procName){
		ObjectOutputStream out = null;
		try {
			FileOutputStream fileOut = new FileOutputStream("p" + procName + "-pending.txt");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(messages);
			out.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public LinkedList<Message> messageDeserializer(int procName){
		ObjectInputStream in = null;
		LinkedList<Message> messages = null;
		try {
			FileInputStream fileIn = new FileInputStream("p" + procName + "-pending.txt");
			in = new ObjectInputStream(fileIn);
			messages = (LinkedList<Message>) in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}
		return messages;
	}
}
