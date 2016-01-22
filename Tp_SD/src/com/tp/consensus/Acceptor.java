package com.tp.consensus;

public class Acceptor extends Process {
	public Acceptor(int processId) {
		super(processId);
		// TODO Auto-generated constructor stub
	}

	public void acknowledge(Proposer from, Object message){
		//Si on re�oit un message de proposer, on dit qu'on a bien re�u le message
		if (from.isSent()&& isAlive() ){
			System.out.println("Accepter n� " + getProcessId()+
					" acknowledge message from process n�  " + from.getProcessId() );
			getPending().add(message);
		}

	}
//test
public void deliver(Proposer p,Object message) {
	if (p.getDelivered().contains(message) && isAlive()){
		System.out.println("message " + message  +" delivered from acceptor n� "+getProcessId());
		getDelivered().add(message);
		getPending().remove(message);
	}
//missing functions
}
}
