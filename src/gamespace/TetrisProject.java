package gamespace;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class TetrisProject
{
	private final Point [][][] myPoint = {
			{
				//I
				{new Point(0,1),new Point(1,1),new Point(2,1),new Point(3,1)},
				{new Point(1,0),new Point(1,1),new Point(1,2),new Point(1,3)},
				{new Point(0,1),new Point(1,1),new Point(2,1),new Point(3,1)},
				{new Point(1,0),new Point(1,1),new Point(1,2),new Point(1,3)}
			},
			{
				//J
				{new Point(0,1),new Point(1,1),new Point(2,1),new Point(2,0)},
				{new Point(1,0),new Point(1,1),new Point(1,2),new Point(2,2)},
				{new Point(0,1),new Point(1,1),new Point(2,1),new Point(0,2)},
				{new Point(1,0),new Point(1,1),new Point(1,2),new Point(0,0)}
			},
			{
				//L
				{new Point(0,1),new Point(1,1),new Point(2,1),new Point(2,0)},
				{new Point(1,0),new Point(1,1),new Point(1,2),new Point(2,2)},
				{new Point(0,1),new Point(1,1),new Point(2,1),new Point(0,0)},
				{new Point(1,0),new Point(1,1),new Point(1,2),new Point(2,0)}
			},
			{
				//0
				{new Point(0,0),new Point(0,1),new Point(1,0),new Point(1,1)},
				{new Point(0,0),new Point(0,1),new Point(1,0),new Point(1,1)},
				{new Point(0,0),new Point(0,1),new Point(1,0),new Point(1,1)},
				{new Point(0,0),new Point(0,1),new Point(1,0),new Point(1,1)}
			}
	};
	
	private final Color[] myColor = {Color.CYAN,Color.magenta,Color.orange,Color.yellow,Color.black,Color.red}; 
	
	private Point pt;
	private int currentPiece;
	private int rotation;
	private ArrayList<Integer> nextPiece = new ArrayList<Integer>();
	private long score;
	private Color [][] well;

	
	
	
	


public static void main(String [] args)
{
	
}

}
	

