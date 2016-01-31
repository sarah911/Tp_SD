package com.tp.consensus;

import java.util.LinkedList;

public class TProcess implements Runnable {
	private Process p;
	private String name;
	public static LinkedList<Message> l_msg;

	public TProcess(Process p, String name, LinkedList<Message> l_msg) {
		this.p = p;
		this.name = name;
		this.l_msg = l_msg;
	}

	public static Message minimum(LinkedList<Message> l) {
		int indice = 0;
		if (!l.isEmpty()) {
			int minId = l.getFirst().getId();
			for (Message m : l) {
				if (m.getId() <= minId) {
					minId = m.getId();
					indice = l.indexOf(m);
				}

			}
			return l.get(indice);
		}else{
			return null;
		}
	}

	public void init() {
		LinkedList<Message> pending = MessageSerializerDeserializer.getInstance().messageDeserializer(this.name);
		// un proc qui revit..
		if (pending != null) {
			p.setPending(pending);
		}
	}

	public void run() {
		init();
		while (true) {
			if (!p.getPending().isEmpty()) {
				Message min = minimum(p.getPending());
				Message minGlobal = minimum(l_msg);
				if (min.equals(minGlobal)) {
					boolean noLeader = true;
					for (Process n : p.getNeighbours()) {
						if (n.isLeader()) {
							noLeader = false;
						}
					}
					if (noLeader) {
						p.propose(min);
					}
				}
				if (!p.isLeader() && p.isReceived(minGlobal)) {
					p.acknowledge(p.getProposer(), minGlobal);
				} else if (p.isLeader()) {
					while (p.getCompteur() <= p.getNeighbours().size() / 2)
						;
					p.deliver(minGlobal);
				}
				if (!p.isLeader() && p.isReceived(minGlobal) && p.getProposer().isDeliver()) {
					p.deliver(minGlobal);
				}
				if (p.isDeliver()) {
					boolean b = true;
					for (Process n : p.getNeighbours()) {
						if (!n.isDeliver()) {
							b = false;
							break;
						}
					}
					if (b) {
						p.setLeader(false);
						p.setDeliver(false);
						p.setReceived(false);
					}
					/*Message sent = minimum(l_msg);
					System.out.println(sent.getId());
					l_msg.remove(sent);*/
				}
			}
		}
	}

}
