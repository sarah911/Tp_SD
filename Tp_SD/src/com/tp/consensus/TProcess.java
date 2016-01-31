package com.tp.consensus;

import java.util.LinkedList;

public class TProcess implements Runnable {
	private Process p;
	private String name;
	public  LinkedList<Message> l_msg;

	public TProcess(Process p, String name, LinkedList<Message> l_msg){
		this.p = p;
		this.name = name;
		this.l_msg = l_msg;
	}

	public Message minimum (LinkedList<Message> l){
		int minId = l.getFirst().getId();
		Message min=l.getFirst(); 
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
				Message minGlobal=minimum(l_msg);
				if ((!p.getDelivered().isEmpty()) &&  (p.getDelivered().contains(minGlobal))){				
					l_msg.remove(minGlobal);
					minGlobal=minimum(l_msg);
				}

				if (min.equals(minGlobal)) {
					boolean noLeader=true;
					for (Process n:p.getNeighbours() ){
						if (n.isLeader()) {
							noLeader=false; break;
						}
					}
					if (noLeader){
						p.propose(min);
					}
				}
				if (!p.isLeader() 
					&& p.isReceived(minGlobal)
						&& !p.getProposer().isDeliver() 
					&&  !p.isAcknowledge(minGlobal)
						){
							p.acknowledge(p.getProposer(),minGlobal);
							}
				 if (p.isLeader()&& !p.isDeliver()& !p.getDelivered().contains(minGlobal)) {
					while( p.getCompteur() <= p.getNeighbours().size()/2 );
					p.deliver(minGlobal);
				}
				if (!p.isLeader() && p.isReceived(minGlobal) 
						&&  p.getProposer().isDeliver()
						&& p.isAcknowledge(minGlobal)
						&& !p.getDelivered().contains(minGlobal)){
					p.deliver(minGlobal);
					p.setAcknowledge(false);
				}
				if (p.isDeliver()){
					boolean b=true;
					for (Process n:p.getNeighbours()) {						
						if (!n.isDeliver()){b=false;break;}
					}
					if (b){
						p.setLeader(false);
						p.setDeliver(false);
						p.setReceived(false);
						
					}
				}
			}
		}
	}

}

