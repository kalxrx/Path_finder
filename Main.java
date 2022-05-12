

import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter the file name: ");

                String fileName = input.nextLine();
                System.out.println();

                String[][] map = PathFinder.readMapFromFile(fileName + ".txt");


                Stopwatch timer = new Stopwatch();

                System.out.println("____THE MAP____");

                // getting the starting coordinates
                PathFinder.findSpecificElement(map, "S");
                int startPoint_X = PathFinder.currentColumnNumber;
                int startPoint_Y = PathFinder.currentRowNumber;
                System.out.println("Initial Position = (X = " +startPoint_Y+","+"Y= " + startPoint_X+")");

                // getting the ending coordinates
                PathFinder.findSpecificElement(map, "F");
                int endPoint_X = PathFinder.currentColumnNumber;
                int endPoint_Y = PathFinder.currentRowNumber;
                System.out.println("Final Position = (X = " +endPoint_Y+","+"Y= " + endPoint_X+")");

                Position start = new Position(startPoint_X, startPoint_Y, null);
                Position end = new Position(endPoint_X, endPoint_Y, null);
                List<Position> path = PathFinder.FindPath(map, start, end);

                // Displaying the paths as the coordinates
                if (path != null) {
                    System.out.println();
                    System.out.println("THE PATH");
                    for (Position position : path) {
                        if (position.previous.x > position.x) {
                            System.out.print("Move left to  : ");
                        }
                        if (position.previous.x < position.x) {
                            System.out.print("Move Right to : ");
                        }
                        if (position.previous.y > position.y) {
                            System.out.print("Move Up to    : ");
                        }
                        if (position.previous.y < position.y) {
                            System.out.print("Move Down to  : ");
                        }

                        System.out.println("("+position.y+","+position.x+")");
                        map[position.y][position.x] = "*";

                    }
                    System.out.println();

                } else
                    System.out.println("No path found");


                StdOut.println("elapsed time = " + timer.elapsedTime());
        }
        catch (Exception e){
            System.out.println("No file detected");
        }

    }
}
