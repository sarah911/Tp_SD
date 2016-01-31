/*Test serializer et deserializer d'une liste de messages*/
package com.tp.test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import com.tp.consensus.Message;
import com.tp.consensus.MessageSerializerDeserializer;

public class TestSerializer {

	public static void main(String[] args) throws ClassNotFoundException {
		LinkedList<Message> pending = new LinkedList<Message> ();
		Message m1 = new Message(1,2);
		Message m2 = new Message(3,2);
		int procName = 1;
		pending.add(m1);
		pending.add(m2);
		
		MessageSerializerDeserializer.getInstance().messageSerializer(pending, procName);
		
		LinkedList<Message> data = MessageSerializerDeserializer.getInstance().messageDeserializer(procName);
		
		System.out.println("Deserialized info from main : \n" + data.toString());
		
	}

}
