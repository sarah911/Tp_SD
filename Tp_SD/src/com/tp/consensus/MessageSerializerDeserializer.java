package com.tp.consensus;

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
	
	public void messageSerializer(LinkedList<Message> messages, String procName){
		ObjectOutputStream out = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(procName + "-pending.txt");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(messages);
			out.close();
			fileOut.close();
			System.out.println("\nSerialization Successful into " + procName + "-pending.txt");
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

	public LinkedList<Message> messageDeserializer(String procName){
		ObjectInputStream in = null;
		LinkedList<Message> messages = null;
		try {
			FileInputStream fileIn = new FileInputStream(procName + "-pending.txt");
			in = new ObjectInputStream(fileIn);
			messages = (LinkedList<Message>) in.readObject();
			System.out.println("Deserialized Data: \n" + messages.toString());
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
