package com.tp.consensus;

public class Proposer extends Process {
	private boolean sent;
	public Proposer(int processId) {
		super(processId);
		this.setSent(false);
		// TODO Auto-generated constructor stub
	}
public void propose (Object message){
//renvoyer le message à tous les processus vivants (neighboorhood)
	System.out.println("proposition");
for (int n: getProcessNeighbourhood()){
	System.out.println("Sending message to process number  "
+ n);
}
	getPending().add(message);//a mettre au lancement avec le client
	this.setSent(true);
}
public boolean isSent() {
	return sent;
}
public void setSent(boolean sent) {
	this.sent = sent;
}

public void deliver(Object message) {
	if (isAlive()){
	System.out.println("message " + message  +" delivered from proposer");
	getDelivered().add(message);
	if (getPending().contains(message)){
	getPending().remove(message);
	}
	}
}

}
