package com.tp.consensus;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class TestSerializer {

	public static void main(String[] args) throws ClassNotFoundException {
		LinkedList<Message> pending = new LinkedList<Message> ();
		Message m1 = new Message(1,2);
		Message m2 = new Message(3,2);
		String procName = "procName";
		pending.add(m1);
		pending.add(m2);
		ObjectOutputStream out = null;
		try {
			FileOutputStream fileOut = new FileOutputStream(procName + ".txt");
			out = new ObjectOutputStream(fileOut);
			out.writeObject(pending);
			out.close();
			fileOut.close();
			System.out.println("\nSerialization Successful into " + procName + ".txt");
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

		ObjectInputStream in = null;
		try {
			FileInputStream fileIn = new FileInputStream(procName + ".txt");
			in = new ObjectInputStream(fileIn);
			LinkedList<Message> mess = (LinkedList<Message>) in.readObject();
			System.out.println("Deserialized Data: \n" + mess.toString());
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
	}

}
