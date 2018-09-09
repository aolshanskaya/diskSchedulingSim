import java.util.*;

public class SSF_Scheduler implements Scheduler{

	private int time;
	private int starting_cylinder;
	
	/**
	 * Constructor: sets the initial cylinder. 
	 */
	public SSF_Scheduler(int starting_cylinder){
		this.starting_cylinder = starting_cylinder;
	}
	
	/**
	 * Returns running sum of shortest seek first scheduler. 
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
					SeqRec seek = seeks[seek_pointer];
					seek.startRequest(time);
					queue.add(seek);
					seek_pointer++;
				}
			}
			
			SeqRec seek = queue.get(0);
			for(SeqRec s : queue) {
				if(Math.abs(current_cylinder - s.getCylinder()) < Math.abs(current_cylinder - seek.getCylinder())) {
					seek = s;
				}
			}
			
			time += Math.abs(current_cylinder - seek.getCylinder());
			seek.finishRequest(time);
			current_cylinder = seek.getCylinder();
			queue.remove(seek);
			
		}while(!queue.isEmpty());
		
		return time;
	}

}
