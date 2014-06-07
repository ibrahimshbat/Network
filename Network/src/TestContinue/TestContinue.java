package TestContinue;


public class TestContinue {

	public static void main(String[] args) {
		
		int [] nums = {2,5,7,4,3,1,7,8};
		for (int i = 0 ; i< nums.length; i++){
			if (i < 5)
				continue;
				System.out.println(nums[i]);
			

			
		}
	}

}
