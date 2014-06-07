package ProposalListTest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import ProposalListTest.Learner;;

public class Follower extends Learner {
	
	

	Scanner r = new Scanner(System.in);
	
    HashMap<Long,Proposal> propos ;
	
	
	
	
	public Follower() {
		super();
		this.proposals = getLinkedListProposal();
	}




	public void print(){
	//	proposals = getLinkedListProposal();

       while (true){
    	   System.out.println("Enter 1 for incremant count ");
    	   System.out.println("Enter 2 for print MapSoze");
    	   System.out.println("Print proposal detials1"
    	   		+ "");


    	   switch(r.nextInt()){
    	   
    	   
    	   case 1: 
        	   System.out.println("Enter zixd ");
        	   Long zxid = r.nextLong();
    		   Proposal p = getMapProposal().get(zxid);
    		   p.count++;
        	   System.out.println("Count number for " + zxid + " is " + p.count);

    	   
    	   break;
    	   case 2:
		    //System.out.println("Number of proposals = " + getMapProposal().size());
			System.out.println(getMapProposal().size());
			System.out.println(getLinkedListProposal().size());

		    break;
		    
    	   case 3:
    		   Proposal p1 = new Proposal();
    		   System.out.println("Enter Zixd ");
    		   p1 = getProposal(r.nextLong());
    		   System.out.println("zxid = " + p1.zxid);
    		   System.out.println("packet = " + p1.packet);
    		   System.out.println("count = " + p1.count);



       }
	
		
	}
	
	}
	
}
