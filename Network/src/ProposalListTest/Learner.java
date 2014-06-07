package ProposalListTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Learner {
	
	int count = 0;
	
	Proposal  proposal = new Proposal();;
	
	
	volatile LinkedList<Proposal> proposals ;
	
	volatile HashMap<Long,Proposal> propos ;

    public Proposal getProposal(Long zxid){
    	
    	return propos.get(zxid);
    	
    }
 
	
	
	 public Learner() {
		
		this.proposals = new LinkedList<Proposal>();
		this.propos = new HashMap<Long,Proposal>();

		new Thread() {
			public void run(){
				
				
				startLearner();
		
			}
		}.start();
	}



	class Proposal{
		Long zxid;
		String packet;
		int count = 0;
	}


	public void setProposalsList(LinkedList<Proposal> proposals){
		this.proposals = proposals;
		
	}
	
	public LinkedList<Proposal> getLinkedListProposal(){
		return this.proposals;
	}
	
	
	public HashMap<Long, Proposal> getMapProposal(){
		return this.propos;
	}
	
	public void queueProposal(Proposal proposal){
		
		this.proposals.add(proposal);
		
		this.propos.put(proposal.zxid, proposal);
		//System.out.println(this.propos.size());
		//System.out.println(this.proposals.size());
		
		
	}
	

public void startLearner(){
	Scanner read = new Scanner(System.in);
	String s;
	Long c = (long) 0;
	while (true){
    Proposal p = new Proposal();
	//System.out.println("Enter zxid: ");
	//p.zxid = read.nextLong();
	//System.out.println("Enter packet: ");
	//p.packet = read.next(); 
     p.zxid =  c++;
     p.packet = "hi " + count;
	 //proposals.add(p);
	 queueProposal(p);
	
	//System.out.println(proposals.size());
		
	}
}
}