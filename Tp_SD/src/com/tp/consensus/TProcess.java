package com.tp.consensus;

import java.util.LinkedList;

public class TProcess implements Runnable {
	private Process p;
	private String name;
	private LinkedList<Message> l_msg;

	public TProcess(Process p, String name, LinkedList<Message> l_msg){
		this.p = p;
		this.name = name;
		this.l_msg = l_msg;
	}

	public static Message minimum (LinkedList<Message> l){
		Message min = l.getFirst();
		int indice =0;
		int minId = l.getFirst().getId();
		for (Message m:l){
			if (m.getId()<=minId) {
				minId=m.getId();
				indice=l.indexOf(m);
			}

		}
		/*for(int cpt = 2; cpt <= l.size(); cpt++){
			if (minId > l.get(cpt-1).getId()){
				min = l.get(cpt-1);
				minId = l.get(cpt-1).getId();
			}
		}*/
		return l.get(indice);
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
		//while(true){
		if(!p.getPending().isEmpty()){				
			Message min = minimum (p.getPending());
			Message minGlobal=minimum(l_msg);
			if (min.equals(minGlobal)) {
				boolean noLeader=true;
				for (Process n:p.getNeighbours() ){
					if (n.isLeader()) {
						noLeader=false; 
					}
				}
				if (noLeader){
					p.propose(min);
					System.out.println("the process n° "+p.getProcessId()+" is proposing the message "+ min.getInfo());
				}
			}
			if (!p.isLeader() && p.isAlive() && p.isReceived(minGlobal)){
				p.acknowledge(p.getProposer(),minGlobal);
				System.out.println("the process n° "+p.getProcessId()+" acknowledged the request from "+p.getProposer().getProcessId());
			}
			else if (p.isLeader() && p.isAlive()) {
				while (p.getCompteur() <= p.getNeighbours().size()/2 );
				p.deliver(minGlobal);
				System.out.println("The leader n° "+p.getProcessId()+" delivered the message "+minGlobal.getInfo());
			}
			if (!p.isLeader() && p.isAlive()&& p.isReceived(minGlobal) &&  p.getProposer().isDeliver()){
				p.deliver(minGlobal);
				System.out.println("The process n° "+p.getProcessId()+" delivered the message "+minGlobal.getInfo());
			}
			if (p.isDeliver()){
				boolean b=true;
				for (Process n:p.getNeighbours()) {						
					if (!n.isDeliver()){b=false;break;}
				}
				if (b){
					p.setLeader(false);
					p.setDeliver(false);
					l_msg.remove(minGlobal);
				}


			}
			//}
		}
	}

}
