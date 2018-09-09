
public class SeqRec {
	private int time_of_entry;
	private int time_satisfied;
	private int cylinder;
	private int time_so_far;
	
	public SeqRec(int cylinder) {
		this.cylinder = cylinder;
		time_of_entry = -1;
		time_satisfied = -1;
		time_so_far = -1;
	}
	
	/**
	 * Clears entry and exit times to allow reuse in simulation. 
	 */
	public void clearTimes() {
		time_of_entry = -1;
		time_satisfied = -1;
		time_so_far = -1;
	}
	
	/**
	 * Sets time at which seek request enters the queue;
	 * @param start_time
	 */
	public void startRequest(int start_time) {
		time_of_entry = start_time;
	}
	
	public void setCurDelay(int time) {
		time_so_far = time;
	}
	
	/**
	 * Sets time at which seek request exits the queue;
	 * @param end_time
	 */
	public void finishRequest(int end_time) {
		this.time_satisfied = end_time;
	}
	
	/**
	 * returns -1 if the request has not yet been processed. 
	 * If request has already been processed then the difference between exit and entry times to the queue is returned. 
	 */
	public int getDelay() {
		return time_satisfied - time_of_entry;
	}
	
	/**
	 * used by custom to keep track of old seek requests.
	 */
	public int getCurrentDelay() {
		return time_so_far - time_of_entry;
	}
	
	/**
	 * Returns the cylinder for this request. 
	 */
	public int getCylinder() {
		return cylinder;
	}
	
	/**
	 * Returns score. 
	 */
	public int getScore() {
		double score = (double)getDelay() * Math.sqrt((double)getDelay());
		return (int)score;
	}
}
