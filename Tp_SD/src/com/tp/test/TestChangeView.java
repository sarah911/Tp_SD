/*Test changement de view avec 2 processus existants et ajout d'un processus*/
package com.tp.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import com.tp.consensus.Consensus;
import com.tp.consensus.Message;
import com.tp.consensus.MessageSerializerDeserializer;
import com.tp.consensus.Process;

public class TestChangeView {

	public static void main(String[] args) throws ClassNotFoundException {
		LinkedList<Message> pending = new LinkedList<Message>();
		LinkedList<Message> pending2 = new LinkedList<Message>();
		LinkedList<Message> delivered2 = new LinkedList<Message>();
		Message m1 = new Message(1, 1);
		Message m2 = new Message(2, 2);
		Message m3 = new Message(3, 3);
		Message m4 = new Message(4, 4);

		pending.add(m1);
		pending.add(m2);
		pending.add(m4);
		pending2.add(m3);
		pending2.add(m2);
		delivered2.add(m4);

		Process p1 = new Process(1);
		Process p2 = new Process(2);
		Process p3 = new Process(3);
		p1.getNeighbours().add(p2);
		p2.getNeighbours().add(p1);
		p1.setPending(pending);
		p2.setPending(pending2);
		LinkedList<Process> procs = new LinkedList<Process>();
		procs.add(p1);
		procs.add(p2);
		Consensus.getInstance().initialize(procs);
		Consensus.getInstance().changeView(p3);
		System.out.println("P3 pending messages " + p3.getPending());
		System.out.println("P3 delivered messages " + p3.getDelivered());
		System.out.println("P1 has " + p1.getNeighboursNum() + " neighbours");
	}

}
