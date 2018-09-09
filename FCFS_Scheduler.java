public class FCFS_Scheduler implements Scheduler{

	private int running_sum;
	private int starting_cylinder;
	
	/**
	 * Constructor: sets the initial cylinder. 
	 */
	public FCFS_Scheduler(int starting_cylinder){
		this.starting_cylinder = starting_cylinder;
	}
	
	/**
	 * Returns running sum of first come first serve scheduler. 
	 */
	public int runSeeks(SeqRec[] seeks) {
		running_sum = 0;
		int current_cylinder = starting_cylinder;
		
		for(int i = 0 ; i < seeks.length ; i++) {
			seeks[i].startRequest(0);
			running_sum += Math.abs(current_cylinder - seeks[i].getCylinder());
			seeks[i].finishRequest(running_sum);
			current_cylinder = seeks[i].getCylinder();
		}
		
		return running_sum;
	}
}
