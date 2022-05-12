import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class PathFinder {

    public PathFinder(){}

    public static boolean isMovable(String[][] map, Position position) { //function to check whether there are obstacles and is it possible to move.

        // setting up boundaries for y axis
        if (position.y < 0 || position.y > map.length - 1)
            return false;

        // setting up boundaries for x axis
        if (position.x < 0 || position.x > map[0].length - 1)
            return false;

        //check if the value is ".", "@", "F", "S"
        return map[position.y][position.x].equals(".") || map[position.y][position.x].equals("@") || map[position.y][position.x].equals("F")|| map[position.y][position.x].equals("S");

    }

    public static List<Position> FindNeighbors(String[][] map, Position position) {
        List<Position> neighbors = new ArrayList<>();           //new array
        Position up = position.offset(0,  1);
        Position down = position.offset(0,  -1);
        Position left = position.offset(-1, 0);
        Position right = position.offset(1, 0);

        //if it ia movable up
        if (isMovable(map, up))
            neighbors.add(up);

        //if it is movable down
        if (isMovable(map, down))
            neighbors.add(down);

        //if it is movable left
        if (isMovable(map, left))
            neighbors.add(left);

        //if it is movable right
        if (isMovable(map, right))
            neighbors.add(right);

        return neighbors;
    }

    public static List<Position> FindPath(String[][] map, Position start, Position end) {
        boolean finished = false;
        List<Position> used = new ArrayList<>();
        used.add(start);
        while (!finished) {
            List<Position> newOpen = new ArrayList<>();

            for(int i = 0; i < used.size(); ++i){
                Position position = used.get(i);
                for (Position neighbor : FindNeighbors(map, position)) {
                    if (!used.contains(neighbor) && !newOpen.contains(neighbor)) {
                        newOpen.add(neighbor);
                    }
                }
            }

            for(Position position : newOpen) {
                used.add(position);
                // checks whether it has reached "F"
                if (end.equals(position)) {
                    finished = true;
                    break;
                }
            }

            if (!finished && newOpen.isEmpty())
                return null;
        }

        List<Position> path = new ArrayList<>();
        Position position = used.get(used.size() - 1);
        while(position.previous != null) {
            path.add(0, position);
            position = position.previous;
        }
        return path;
    }


    static int currentRowNumber ;
    static int currentColumnNumber;
    public static void findSpecificElement(String [] [] map, String element) {
        currentColumnNumber = 0;
        currentRowNumber = 0 ;
        for (int i = 0; i < sizeOfArray; i++) {  //number of rows
            for (int j = 0; j < sizeOfArray; j++) { //number of columns
                if (map[i][j].equals(element)) {
                    System.out.println("Found the position of "+element);
                    currentRowNumber = i;
                    currentColumnNumber = j;
                    break;
                }
            }
        }

    }

    public static int sizeOfArray = 0;

    public static String [] [] readMapFromFile(String FileName) throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(FileName)));

        // getting the size of the array
        File driverInfo= new File(FileName);
        Scanner myReader= new Scanner(driverInfo);
        int count = 0;
        while(myReader.hasNextLine()) {
            myReader.nextLine();
            count++;

        }
        sizeOfArray = count;
        int rows = count;
        int columns = count;
        String [][] myArray = new String[rows][columns];
        while(sc.hasNextLine()) {
            for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split("");
                for (int j=0; j<line.length; j++) {
                    myArray[i][j] = line[j];
                }
            }
        }

        return myArray;
    }
}