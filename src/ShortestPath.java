
/**
 * 
 * @author Christopher Marek
 * Student Number: 251034808
 * Course: Comp Sci 1027b
 * Assignment 3
 * 
 *  This class traverses through a map
 *  and finds and displays the shortest path
 *  from the power station to the customer
 *  implementing the DLList.java
 * 
 */


import java.io.IOException;
import java.io.FileNotFoundException;

public class ShortestPath {
	
	/**
	* Instance variable of parsed map file.
	* Holds an object of Map.java
	*/
	Map cityMap;
	

	/**
	 * Recives the name of the map file and
	 * creates an object of the class Map and stores
	 * it into the cityMap instance variable
	 * @param filename
	 */
	public ShortestPath(String filename) throws FileNotFoundException {
		try {
			this.cityMap = new Map(filename);
		}catch(InvalidMapException | IOException e){
			System.out.println(e);
		}
	}
	
	/**
	 * Main method of program. 
	 * Creates doubly linked list for path
	 * Traverses map using loop to find best path to customer
	 * 
	 * @param args
	 */
	public static void main(String args[]) throws FileNotFoundException {
		
		if(args.length < 1) {
			System.out.println("You must provide the name of the input file");
			System.exit(0);
		}

		ShortestPath connection = new ShortestPath(args[0]);
		
		DLList<MapCell> mapList = new DLList<MapCell>();
		
		Map map = connection.cityMap;
		MapCell start = map.getStart();
		
		if(!start.isPowerStation()) {
			System.out.println("Invalid");
		}
		
		mapList.insert(start, 0);
		start.markInList();
		
		boolean reachedEnd = false;
		int distance = 0;
		
		while(!mapList.isEmpty() && !reachedEnd) {

			start = mapList.getSmallest();
			start.markOutList();
			
			if(start.isCustomer()) {
				reachedEnd = true;
			}else {
				
				while(connection.nextCell(start) != null) {
					
					MapCell neighbourCell = connection.nextCell(start);

					distance = 1 + start.getDistanceToStart();
					int neighbourDistance = neighbourCell.getDistanceToStart();
					
					if(neighbourDistance > distance) {
						neighbourCell.setDistanceToStart(distance);
						neighbourCell.setPredecessor(start);
					}
					
					neighbourDistance = neighbourCell.getDistanceToStart();
					if(neighbourCell.isMarkedInList() && neighbourDistance < mapList.getDataValue(neighbourCell)) {
						mapList.changeValue(neighbourCell, neighbourDistance);
					}
					if(!neighbourCell.isMarkedInList()) {
						mapList.insert(neighbourCell, neighbourDistance);
						neighbourCell.markInList();
					}
					
				}
					
				
			}
			
		}
		if(reachedEnd) {
			System.out.println("The customer has been found, the shortest path is " + (start.getDistanceToStart() + 1) + " cells");
		}else {
			System.out.println("No path found");
		}
	}
	
	/**
	 * Find the best possible cell for the program
	 * to move onto in the path given the restraints
	 * in the assignment
	 * @param cell
	 * @return
	 */
	private MapCell nextCell(MapCell cell) {
		
		if(cell.isPowerStation() || cell.isOmniSwitch()) {
			
			for(int i = 0; i < 4; i++) {
				try {
					MapCell neighbourCell = cell.getNeighbour(i);
					
					if(neighbourCell != null && neighbourCell.isBlock()) {
						continue;
					}
					
					if(neighbourCell != null && !neighbourCell.isMarked()) {
						
						if(neighbourCell.isHorizontalSwitch() && ((i == 1) || i == 3)) {
							return neighbourCell;
						}
						if(neighbourCell.isVerticalSwitch() && ((i == 0 ) || i == 2)) {
							return neighbourCell;
						}
						
						if(neighbourCell.isOmniSwitch() || neighbourCell.isCustomer()) {
							return neighbourCell;
						}
					}
				} catch (InvalidNeighbourIndexException e) {
				}
					
			}
		}
		
		if(cell.isVerticalSwitch()) {
			for(int i = 0; i < 4; i++) {
				try {
					MapCell neighbourCell = cell.getNeighbour(i);
					
					if(neighbourCell != null && neighbourCell.isBlock()) {
						continue;
					}
					
					if(i == 0 || i == 2) {

						if(neighbourCell != null && !neighbourCell.isMarked()) {
							
							if(neighbourCell.isHorizontalSwitch() && ((i == 1) || i == 3)) {
								return neighbourCell;
							}
							if(neighbourCell.isVerticalSwitch() && ((i == 0 ) || i == 2)) {
								return neighbourCell;
							}
							
							if(neighbourCell.isOmniSwitch() || neighbourCell.isCustomer()) {
								return neighbourCell;
							}
						}
					}
				} catch (InvalidNeighbourIndexException e) {
				}
			}
			
		}
		
		if(cell.isHorizontalSwitch()) {
			for(int i = 0; i < 4; i++) {
				try {
					MapCell neighbourCell = cell.getNeighbour(i);
					
					if(neighbourCell != null && neighbourCell.isBlock()) {
						continue;
					}
					
					if(i == 1 || i == 3) {

						if(neighbourCell != null && !neighbourCell.isMarked()) {
							
							if(neighbourCell.isHorizontalSwitch() && ((i == 1) || i == 3)) {
								return neighbourCell;
							}
							if(neighbourCell.isVerticalSwitch() && ((i == 0 ) || i == 2)) {
								return neighbourCell;
							}
							
							if(neighbourCell.isOmniSwitch() || neighbourCell.isCustomer()) {
								return neighbourCell;
							}
						}
					}
				} catch (InvalidNeighbourIndexException e) {
					
				}
			}
			
		}
		return null;
	}
}
