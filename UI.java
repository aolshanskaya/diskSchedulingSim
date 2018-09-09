import java.util.*;
public class UI {
	private static UI ui = null;
	private UI() {}
	public static UI getUI() {
		if(ui == null)
			ui = new UI();
		return ui;
	}
	
	/**
	 * Prompts user for number of seeks to generate. If invalid response is given program runs a default of 1000 seeks. 
	 */
	public int welcomeMessage() {
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to the Disk Arm Simulator.");
		System.out.print("How many seeks would you like to generate? ");
		String answer = kb.next();
		kb.close();
		
		int num_seeks;
		
		try {
			num_seeks = (int)(Integer.parseInt(answer));
		}catch(Exception e) {
			num_seeks = -1;
		}
		
		if(num_seeks < 1) {
			System.out.println("Your response was invalid, we will proceed with 1000 seeks.");
			num_seeks = 1000;
		}

		return num_seeks;
	}
	
	/**
	 * Prints out the results comparing each of the four algorithms. 
	 */
	public void printResultsChart(int[][] results) {
		
		System.out.println("\n\t| Running Sum\t| Avg Delay\t| Max Delay\t| Avg Score\t| Max Score\t|");
		for(int row = 0 ; row < results.length ; row++) {
			if(row == 0) {
				System.out.println("FCFS\t| " + results[row][0] + "\t\t| - \t\t| - \t\t| - \t\t|-\t\t|");
			}else {
				if(row == 1) {
					System.out.print("SSF\t| ");
				}else if(row ==2){
					System.out.print("Elevator| ");
				}else {
					System.out.print("Custom\t| ");
				}
				
				for(int col = 0 ; col < results[0].length ; col++) {
					System.out.print(results[row][col] + "\t\t| ");
				}
				System.out.println();
			}
		}
		
	}

}
