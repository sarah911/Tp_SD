/*Test avec 2 process et envoi de 2 messages*/
package com.tp.test;

import java.util.LinkedList;

import com.tp.consensus.Message;
import com.tp.consensus.Process;
import com.tp.consensus.TProcess;


public class Test2messages {
	public static void main(String[] args) {
		//initialization of requests
		Message m1 = new Message(1,"hello world!");
		Message m2 = new Message(2,2);
		final LinkedList<Message> l_msg = new LinkedList<Message>();
		l_msg.add(m1);
		l_msg.add(m2);
		//initialization of processes 
		Process p1= new Process(1);
		Process p2= new Process(2);
		p1.getNeighbours().add(p2);
		p2.getNeighbours().add(p1);

		//Send requests to some processes
		p1.receiveClientMsg(m1);
		p2.receiveClientMsg(m2);
		
	    Thread t = new Thread(new TProcess(p1, "p1", l_msg));
	    Thread t2 = new Thread(new TProcess(p2, "p2",l_msg));
	    t.start();
	    t2.start();
	}
}
