package com.tp.consensus;

import java.util.LinkedList;

public class Process {
private int processId;
private boolean alive;
private LinkedList<Object> pending = new LinkedList<Object> ();
private LinkedList<Object> delivered = new LinkedList<Object> ();
private int neighboursNum;
private LinkedList<Integer> neighbours =new LinkedList<Integer>();
public Process(int processId){
	this.processId=processId;
	this.alive=true;
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
public LinkedList<Integer> getProcessNeighbourhood() {
	return this.neighbours;
}
public void setProcessNeighbourhood(LinkedList<Integer> neighbours){
	this.neighbours=neighbours;
}
public LinkedList<Object> getPending(){
	return this.pending;
}
public LinkedList<Object> getDelivered(){
	return this.delivered;
}
public void deliver(int processId, Object message) {
	//pour le proposer, delivrer si on a reçu une majorité de ack des processus vivants
	//pour l'acceptor, delivrer si le proposer vient de délivrer
}
public boolean isAlive() {
	return alive;
}
public void setAlive(boolean alive) {
	this.alive = alive;
}
}
