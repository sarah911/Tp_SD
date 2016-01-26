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
private LinkedList<Object> pending = new LinkedList<Object> ();
private LinkedList<Object> delivered = new LinkedList<Object> ();
private int neighboursNum;
private LinkedList<Process> neighbours =new LinkedList<Process>();
public Process(int processId){
	this.processId=processId;
	this.alive=true;
}
public void receiveMsg(Object message){
	if (isAlive()){
		getPending().add(message);
	}
}
public void propose(Object message){
	if(isAlive()){
		for (Process n:neighbours){
		 n.setReceived(message);
		 n.setProposer(this);
		}
		setLeader(true);		
	}
}
public void acknowledge(Process proposer, Object message){
	if (isAlive()){
		getPending().add(message);
		proposer.setCompteur(getCompteur()+1);
	}
}
public void deliver(Object message){
	if (isAlive()){
		getPending().remove(message);
		getDelivered().add(message);
		setDeliver(true);
	}
}
public void deliver(Process proposer, Object message){
	if(proposer.isDeliver() && isAlive()){
		getPending().remove(message);
		getDelivered().add(message);
	}
}
public int getNeighboursNum(){
	return this.neighboursNum;
}
public void setNeighboursNum(int neighboursNum){
	this.neighboursNum=neighboursNum;
}
public int getProcessId() {
	return this.processId;
}
public void setProcessId(int processId){
	this.processId=processId;
}
public LinkedList<Process> getNeighbours() {
	return this.neighbours;
}
public void setNeighbours(LinkedList<Process> neighbours){
	this.neighbours=neighbours;
}
public LinkedList<Object> getPending(){
	return this.pending;
}
public LinkedList<Object> getDelivered(){
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
public boolean isReceived(Object received) {
	return this.received;
}
public void setReceived(Object received) {
	this.received=true;
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
}
