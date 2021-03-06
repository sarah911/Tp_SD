/*Test avec 2 process et envoi d'un message*/
package com.tp.test;

import java.util.LinkedList;

import com.tp.consensus.Message;
import com.tp.consensus.MessageSerializerDeserializer;
import com.tp.consensus.Process;
import com.tp.consensus.TProcess;


public class Test1message {
	public static void main(String[] args) {
		MessageSerializerDeserializer.getInstance().init();
		//initialization of requests
		Message m1 = new Message(1,"hello world!");
		
		final LinkedList<Message> l_msg = new LinkedList<Message>();
		l_msg.add(m1);
		//initialization of processes 
		Process p1= new Process(1);
		Process p2= new Process(2);
		p1.getNeighbours().add(p2);
		p2.getNeighbours().add(p1);

		//Send requests to some processes
		p1.receiveClientMsg(m1);
		
	    Thread t = new Thread(new TProcess(p1, "p1", l_msg));
	    Thread t2 = new Thread(new TProcess(p2, "p2",l_msg));
	    t.start();
	    t2.start();
	}
}
