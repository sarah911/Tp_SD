package com.tp.consensus;

import java.util.LinkedList;


public class Test {
static Object min_global=0;
	public static void main(String[] args) {
			//initialization of requests
		String m1="hello world!";
		int m2=2;
		
			//initialization of processes 
		Process p1=new Process(1);
		Process p2= new Process(2);
		p1.getNeighbours().add(p2);
		p2.getNeighbours().add(p1);
		
		//Send requests to some processes
		p1.receiveMsg(m1);
		p2.receiveMsg(m2);
		
		min_global=m1;
		//Threads
		Thread(p1);
		Thread(p2);
	}
	public static Object minimum (LinkedList<Object> l){
		return 0;
	}
	public static void Thread(Process p){
		while(true){
			if(!p.getPending().isEmpty()){
	            Object min = minimum (p.getPending());
	            if (min == min_global) p.propose(min);
	            if (!p.isLeader() && p.isAlive() && p.isReceived(min)){
	                p.acknowledge(p.getProposer(),min);
	            }
	            else if (p.isLeader() && p.isAlive() && p.getCompteur()> p.getNeighbours().size()/2){
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
