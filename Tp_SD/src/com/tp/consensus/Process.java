package com.tp.consensus;

import java.util.LinkedList;

public class Process {
	private int processId;
	private boolean alive;
	private boolean leader;
	private boolean deliver;
	private boolean received;
	private int compteur;
	private Process proposer;
	private LinkedList<Message> pending = new LinkedList<Message>();
	private LinkedList<Message> delivered = new LinkedList<Message>();
	private int neighboursNum;
	private LinkedList<Process> neighbours = new LinkedList<Process>();
	private boolean ack;

	public Process(int processId) {
		this.processId = processId;
		this.alive = true;
	}

	public int getNeighboursNum() {
		return this.neighboursNum;
	}

	public void setNeighboursNum(int neighboursNum) {
		this.neighboursNum = neighboursNum;
	}

	public int getProcessId() {
		return this.processId;
	}

	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public LinkedList<Process> getNeighbours() {
		return this.neighbours;
	}

	public void setNeighbours(LinkedList<Process> neighbours) {
		this.neighbours = neighbours;
	}

	public LinkedList<Message> getPending() {
		return this.pending;
	}

	public void setPending(LinkedList<Message> mess) {
		this.pending = mess;
	}

	public LinkedList<Message> getDelivered() {
		return this.delivered;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public boolean isReceived(Message received) {
		return this.received;
	}

	public void receivedLeaderMessage(Message received) {
		this.received = true;
		//getPending().add(received);
	}
	
	public void setReceived(boolean received) {
		this.received = received;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public boolean isDeliver() {
		return deliver;
	}

	public void setDeliver(boolean deliver) {
		this.deliver = deliver;
	}

	public Process getProposer() {
		return proposer;
	}

	public void setProposer(Process proposer) {
		this.proposer = proposer;
	}
	
	public void receiveClientMsg(Message message) {
		if (isAlive()) {
			getPending().add(message);
		}
	}

	public void propose(Message message) { // send msg to neighbours = call
											// their ack
		if (isAlive()) {
			for (Process n : neighbours) {
				n.receivedLeaderMessage(message);
				n.setProposer(this);
				n.getPending().add(message);
			}
			setLeader(true);
			System.out.println("Proc " + this.processId + " broadcast message " + message.getId());
		}
	}

	public void acknowledge(Process proposer, Message message) {
		if (isAlive()) {
			ackMessage(message);
			proposer.setCompteur(getCompteur() + 1);
			System.out.println("Proc " + this.processId + " acknowledges message " + message.getId());
		}
	}

	public void deliver(Message message) {
		if (isAlive()) {
			getPending().remove(message);
			getDelivered().add(message);
			setDeliver(true);
			System.out.println("Proc " + this.processId + " delivers message " + message.getId());
		}
	}

	public void deliver(Process proposer, Message message) {
		if (proposer.isDeliver() && isAlive()) {
			getPending().remove(message);
			getDelivered().add(message);
			System.out.println("Proc " + this.processId + "(acceptor) delivers message " + message.getId());
		}
	}

	public boolean isAcknowledge(Message message) {
		return this.ack;
	}
	public void ackMessage(Message message) {
		this.ack = true;
		//getPending().add(received);
	}
	
	public void setAcknowledge(boolean ack) {
		this.ack = ack;
	}
}
