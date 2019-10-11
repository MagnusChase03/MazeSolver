package MazeSolver;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class MazeSolver {

    private MazeSolver(int[][] maze) {

        Point solution = solve(maze);
        System.out.println(solution);
        while ((solution = solution.getParent()) != null) {

            System.out.println(solution);

        }

    }

    private Point solve(int[][] maze) {

        Queue<Point> points = new LinkedList<Point>();
        ArrayList<Point> searched = new ArrayList<>();

        Point root = new Point(0, 0, null);
        points.add(root);
        searched.add(root);

        while (!points.isEmpty()) {

            Point p = points.remove();
            if (p.getX() == 0 && p.getY() == 2) {

                return p;

            }

            Point right = new Point(p.getX() + 1, p.getY(), p);
            if (!isWall(maze, right) && notSearched(searched, right)) {

                points.add(right);

            }

            Point left = new Point(p.getX() - 1, p.getY(), p);
            if (!isWall(maze, left) && notSearched(searched, left)) {

                points.add(left);

            }

            Point up = new Point(p.getX(), p.getY() - 1, p);
            if (!isWall(maze, up) && notSearched(searched, up)) {

                points.add(up);

            }

            Point down = new Point(p.getX(), p.getY() + 1, p);
            if (!isWall(maze, down) && notSearched(searched, down)) {

                points.add(down);

            }

        }

        return null;

    }

    private boolean isWall(int[][] maze, Point point) {

        try {

            return maze[point.getY()][point.getX()] == 1;

        } catch (IndexOutOfBoundsException e) {

            return true;

        }

    }

    private boolean notSearched(ArrayList<Point> searched, Point point) {

        for (Point p : searched) {

            if (p.getX() == point.getX() && p.getY() == point.getY()) {

                return false;

            }

        }

        return true;

    }

    public static void main(String[] args) {

        int[][] maze = {{0, 0, 0}, {1, 1, 0}, {0, 0, 0}};
        new MazeSolver(maze);

    }

}

class Point {

    private int x;
    private int y;
    private Point parent;

    Point(int x, int y, Point parent) {

        this.x = x;
        this.y = y;
        this.parent = parent;

    }

    int getX() {

        return x;

    }

    int getY() {

        return y;

    }

    Point getParent() {

        return parent;

    }

    public String toString() {

        return getX() + " " + getY();

    }

}