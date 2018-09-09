
public interface Scheduler {
	/**
	 * Each scheduler services all the given seek requests and returns the running sum after completion. 
	 */
	public int runSeeks(SeqRec[] seeks);
	
}
