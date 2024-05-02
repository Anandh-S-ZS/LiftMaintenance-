package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import service.LiftBoy;

public class LiftProcess {
	static LiftBoy lb = LiftBoy.getInstance();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of lifts:");
		int n = sc.nextInt();
		
		
		for(int i=1;i<=n;i++) {
			List<Integer> pos = new ArrayList<>();
			System.out.println("No of floors "+i+" list will stop:");
			int temp  =sc.nextInt();
			System.out.println("Enter stopings of lift "+i+" :");
			for(int j=0;j<temp;j++)pos.add(sc.nextInt());
			System.out.println("Enter capacity:");
			int cap = sc.nextInt();
			if(lb.createLift(0,pos,cap)) {
				System.out.println("lift "+i+"created");
			}
		}
		
		while(true) {
			System.out.println("1.Get Lift || 2.set Mainteinance || 3.Remove Mainteinance || 0.shutDown");
			int choice = sc.nextInt();
			if(choice == 1) {
				printLiftPositions(n);
				System.out.println("Enter current floor:");
				int userCurPos = sc.nextInt();
				System.out.println("Enter Destination Floor");
				int userDesPos = sc.nextInt();
				String myLift = lb.getMyLift(userCurPos, userDesPos);
				if(myLift == null)
					System.out.println("No lift is available");
				else
					System.out.println("Your lift is "+myLift);
			}
			else if(choice == 2) {
				System.out.println("Enter Lift Id for Mainteinance:");
				String id = sc.next();
				lb.setLiftMainteinance(id,false);
				System.out.println("lift set to mainteinance");
			}
			else if(choice == 3) {
				System.out.println("Enter Lift Id for Mainteinance:");
				String id = sc.next();
				lb.setLiftMainteinance(id,true);
				System.out.println("lift removed from mainteinance");
			}
			else if(choice == 0) {
				break;
			}
		}
		
	}

	private static void printLiftPositions(int n) {
		System.out.println("Lifts	||  Floor");
		for(int i=0;i<n;i++) {
			int pos = lb.getLiftData(i);
			System.out.println("Lift "+(i+1)+"	: "+pos);
		}
	}
}
