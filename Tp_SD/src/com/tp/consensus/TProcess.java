package com.tp.consensus;

import java.util.LinkedList;

public class TProcess implements Runnable {
	private Process p;
	private String name;
	private int minGlobal;
	
	public TProcess(Process p, String name, int minGlobal){
		this.p = p;
		this.name = name;
		this.minGlobal = minGlobal;
	}
	
	public static Message minimum (LinkedList<Message> l){
		Message min = l.getFirst();
		int minId = l.getFirst().getId();
		for(int cpt = 2; cpt <= l.size(); cpt++){
			if (minId > l.get(cpt-1).getId()){
				min = l.get(cpt-1);
				minId = l.get(cpt-1).getId();
			}
		}
		return min;
	}
	
	public void init(){
		LinkedList<Message> pending = MessageSerializerDeserializer.getInstance().messageDeserializer(this.name);
		// un proc qui revit..
		if(pending != null){
			p.setPending(pending);
		}
	}
	
	public void run() {
		init();
		while(true){
			if(!p.getPending().isEmpty()){
				Message min = minimum (p.getPending());
				if (min.getId() == minGlobal) p.propose(min);
				if (!p.isLeader() && p.isAlive() && p.isReceived(min)){
					p.acknowledge(p.getProposer(),min);
				}
				else if (p.isLeader() && p.isAlive()) {
					while (p.getCompteur() <= p.getNeighbours().size()/2 );
					p.deliver(min);
				}
				if (!p.isLeader() && p.isAlive()&& p.isReceived(min)){
					p.deliver(p.getProposer(),min);
				}
				p.setLeader(false);
				p.setDeliver(false);
			}

		}

	}

}
