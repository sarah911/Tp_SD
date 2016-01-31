package com.tp.consensus;

import java.util.LinkedList;
import java.lang.Object;

public class Consensus {
	private LinkedList<Process> processes = new LinkedList<Process>();
	private static Consensus uniqueInstance;// Stockage de l'unique instance de cette classe.

	public static Consensus getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Consensus();
		}
		return uniqueInstance;
	}
	
	public void initialize(int n) {

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
		}else {
			for (Process proc : processes) {
				proc.updateNeighbour(p,false);
			}
		}
		// Si nouveau processus, il renvoie un signal ,
		// les autres processus lui transmettent leurs delivred/pending
		// le nouveau processus met � jour son delivred/pending
		// les autres processus delivrent ce qui n'a pas �t� d�livr�

		// si processus meurt, on l'enl�ve de la vue
	}

	public void process(Proposer p, Object message) {
		// des threads de process
		// lancer le process connaissant le proposer,
		// ses voisins vivants, le message
		// si un processus de ne fait un ack dans un temps t, il est consid�r�
		// mort
		// mise � jour de la liste des vivants
	}
}
