
public class SeekGenerator {
	private static SeekGenerator sg = null;
	private SeekGenerator() {}
	public static SeekGenerator getSeekGenerator() {
		if(sg == null)
			sg = new SeekGenerator();
		return sg;
	}
	
	/**
	 * Generates an array of random seeks.
	 *  
	 * @param lower_bound - lowest possible seek
	 * @param upper_bound - highest possible seek
	 * @param num_seeks - number of seeks to generate
	 */
	public SeqRec[] getRandomSeeks(int lower_bound , int upper_bound , int num_seeks) {
		SeqRec[] seeks = new SeqRec[num_seeks];
		
		for(int i = 0 ; i < num_seeks ; i++) {
			int cylinder = (int)(Math.random() * (upper_bound-1)) + lower_bound;
			seeks[i] = new SeqRec(cylinder);
		}
		
		return seeks;
	}
}
