package com.tp.consensus;

public class Consensus {
	
	public void initialize(int n){
		
	}
	public void checkLiveness(){
		
	}
	public void chooseLeader(Process[] p){
		
	}
public void changeView(Process p){
	//Si nouveau processus, il renvoie un signal ,
	//les autres processus lui transmettent leurs delivred/pending
	//le nouveau processus met � jour son delivred/pending
	//les autres processus delivrent ce qui n'a pas �t� d�livr�
	
	//si processus meurt, on l'enl�ve de la vue
}

public void process(Proposer p, Object message){
	//des threads de process
	//lancer le process connaissant le proposer,
	//ses voisins vivants, le message
	//si un processus de ne fait un ack dans un temps t, il est consid�r� mort
	//mise � jour de la liste des vivants
}
}
