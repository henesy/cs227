package hw1;

public class BowlingTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TenPinBowling bowl = new TenPinBowling(3);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		System.out.println(bowl.getExtraRolls());
		System.out.println("");
		
		bowl.roll(2);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		System.out.println(bowl.getExtraRolls());
		System.out.println("");
		
		bowl.roll(4);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		System.out.println(bowl.getExtraRolls());
		System.out.println("");

		bowl.roll(1);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		System.out.println(bowl.getExtraRolls());
		System.out.println("");
		
		bowl.roll(3);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		System.out.println(bowl.getExtraRolls());
		System.out.println("");
		
		bowl.roll(6);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		System.out.println(bowl.getExtraRolls());
		System.out.println("");

		bowl.roll(4);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		System.out.println(bowl.getExtraRolls());
		System.out.println("");
		
		bowl.roll(2);
		System.out.println(bowl.getFrameNumber());
		System.out.println(bowl.getRollNumber());
		System.out.println(bowl.isOver());
		System.out.println(bowl.getExtraRolls());
		System.out.println("");
		
		System.out.println(bowl.getScore());
		
	}

}
