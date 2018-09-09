import java.util.*;

public class Custom_Scheduler implements Scheduler{
	private int time;
	private int starting_cylinder;
	private boolean going_up;
	
	/**
	 * Constructor: sets the initial cylinder. 
	 */
	public Custom_Scheduler(int starting_cylinder){
		this.starting_cylinder = starting_cylinder;
		going_up = true;
	}

	/**
	 * Returns running sum of the custom scheduler.
	 * 
	 * The custom scheduler behaves similarly to the elevator scheduler but factors in the direction of the oldest 
	 * seek requests in deciding which way to proceed. 
	 */
	public int runSeeks(SeqRec[] seeks) {
		time = 0;
		int current_cylinder = starting_cylinder;
		int seek_pointer = 0;
		
		List<SeqRec> queue = new Stack<>();
		
		do{
			if(queue.size() < 5) {
				for(int i = 0 ; i < 10 ; i++) {
					if(seek_pointer >= seeks.length) {
						break;
					}
					SeqRec s = seeks[seek_pointer];
					s.startRequest(time);
					queue.add(s);
					seek_pointer++;
				}
				
				SeqRec oldest = queue.get(0);
				for(SeqRec s : queue) {
					if(s.getCurrentDelay() > oldest.getCurrentDelay())
						oldest = s;
				}
				going_up = true;
				if(oldest.getCylinder() < current_cylinder)
					going_up = false;
			}
			
			SeqRec seek = null;
			while(seek == null) {
				for(SeqRec s : queue) {
					if(going_up && s.getCylinder() >= current_cylinder) {
						if(seek == null || s.getCylinder() < seek.getCylinder()) {
							seek = s;
						}
					}else if(!going_up && s.getCylinder() <= current_cylinder){
						if(seek == null || s.getCylinder() > seek.getCylinder()) {
							seek = s;
						}
					}
					s.setCurDelay(time);
				}
				if(seek == null)
					going_up = !going_up;
			}
							
			time += Math.abs(current_cylinder - seek.getCylinder());
			seek.finishRequest(time);
			current_cylinder = seek.getCylinder();
			queue.remove(seek);
			
		}while(!queue.isEmpty());
		
		return time;
	}

}
