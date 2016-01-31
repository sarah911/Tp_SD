package com.tp.consensus;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.lang.Object;

public class Consensus {
	private LinkedList<Process> processes;
	private static Consensus uniqueInstance;// Stockage de l'unique instance de cette classe.

	public static Consensus getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Consensus();
		}
		return uniqueInstance;
	}
	
	public void initialize(LinkedList<Process> p) {
		processes = p;
	}

	public void checkLiveness() {

	}

	public void chooseLeader(Process[] p) {

	}

	public void changeView(Process p) {
		if (!processes.contains(p)) {
			LinkedList<Message> pending = new LinkedList<Message>();
			LinkedList<Message> delivered = new LinkedList<Message>();
			for (Process proc : processes) {
				pending.addAll(proc.getPending());
				delivered.addAll(proc.getDelivered());
				proc.updateNeighbour(p,true);
			}
			pending.removeAll(delivered);
			pending = new LinkedList<Message>(new LinkedHashSet<Message>(pending));
			delivered = new LinkedList<Message>(new LinkedHashSet<Message>(delivered));
			p.setPending(pending);
			p.setDelivered(delivered);
		} else {
			for (Process proc : processes) {
				proc.updateNeighbour(p,false);
			}
		}
	}

	public void process(Proposer p, Object message) {
	}
}
