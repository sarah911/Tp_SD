package com.tp.consensus;

import java.util.LinkedList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//utiliser plusieurs scénarios de transmission
		
		//Elire le proposer ...
		//forcer des processus à mort/vivant
		//lancer le consenus par un propose par exemple
		
		//HashSet<Integer> 
		//initialize network
		LinkedList<Integer> p = new LinkedList<Integer>();
		for (int i=0;i<5;i++){
			p.add(i);
		}
		
		
		//choose a leader (proposer)
		Proposer p1= new Proposer(3);
		LinkedList<Integer> neighbours = p;
		neighbours.remove(p.get(3));
		p1.setProcessNeighbourhood(neighbours);
		
		//propose
		Object message ="hello";
		p1.propose(message);
		
		//Get the Neighbourhood as acceptors
		LinkedList<Acceptor> a=new LinkedList<Acceptor>();
		for (int n: p1.getProcessNeighbourhood()){
			a.add(new Acceptor(n));
		}
		
		//kill someone (manual kill)
		Acceptor a1 = a.get(2);
		a1.setAlive(false);
		a.remove(a1);
		
		//Acknowledgement
		for (Acceptor aa:a){
		  aa.acknowledge(p1, message);	
		}
		
		//Delivery
		p1.deliver(message);
		for (Acceptor aa:a){
			  aa.deliver(p1,message);	
			}		
	}

}
