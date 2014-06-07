package ProposalListTest;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		Scanner read = new Scanner(System.in);
//		new Thread() {
//			public void run(){
//				
//				
//				Learner learner = new Learner();
//				learner.startLearner();
//		
//			}
//		}.start();
		

		//new Thread() {
			//public void run(){
				System.out.println("Enter anyThings" + read.next());
				Follower follower = new Follower();
				follower.print();
			//}
		//}.start();
	}

}