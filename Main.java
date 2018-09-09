public class Main {

	public static void main(String[] args) {
		UI ui = UI.getUI();
		SeekGenerator sg = SeekGenerator.getSeekGenerator();
		
		Scheduler fcfs = new FCFS_Scheduler(50);
		Scheduler ssf = new SSF_Scheduler(50);
		Scheduler elevator = new Elevator_Scheduler(50);
		Scheduler custom = new Custom_Scheduler(50);
		
		int num_seeks = ui.welcomeMessage();
		SeqRec[] master_list = sg.getRandomSeeks(1, 100, num_seeks);
		
		int[][] stats = new int[4][5];
		
		stats[0][0] = fcfs.runSeeks(master_list);
		int[] fcfs_stats = getStats(master_list);
		
		stats[1][0] = ssf.runSeeks(master_list);
		int[] ssf_stats = getStats(master_list);
		
		stats[2][0] = elevator.runSeeks(master_list);
		int[] elevator_stats = getStats(master_list);
		
		stats[3][0] = custom.runSeeks(master_list);
		int[] custom_stats = getStats(master_list);
		
		for(int col = 1 ; col < 5 ; col++) {
			stats[0][col] = fcfs_stats[col-1];
			stats[1][col] = ssf_stats[col-1];
			stats[2][col] = elevator_stats[col-1];
			stats[3][col] = custom_stats[col-1];
		}
		
		ui.printResultsChart(stats);
	}
	
	/**
	 * Collects data from the seeks, clears the times and returns a results array in form: 
	 * [average delay , max delay , average score , max score]
	 */
	private static int[] getStats(SeqRec[] seeks) {
		int[] results = new int[4];
		
		int total_delay = 0;
		int max_delay = seeks[0].getDelay();
		int total_score = 0;
		int max_score = seeks[0].getScore();
		
		for(int i = 0 ; i < seeks.length ; i++) {
			total_delay += seeks[i].getDelay();
			total_score += seeks[i].getScore();
			
			if(seeks[i].getScore() > max_score)
				max_score = seeks[i].getScore();
			if(seeks[i].getDelay() > max_delay)
				max_delay = seeks[i].getDelay();
			
			seeks[i].clearTimes();
		}
		
		results[0] = total_delay / seeks.length;
		results[1] = max_delay;
		results[2] = total_score / seeks.length;
		results[3] = max_score;
		
		return results;
	}

}
