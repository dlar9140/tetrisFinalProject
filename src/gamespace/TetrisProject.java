package gamespace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TetrisProject extends JPanel
{
	private final Point[][][] myPoint = { {
			// I
			{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
			{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) }, { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) } },
			{
					// J
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) }, { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) }, { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) } },
			{
					// L
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) }, { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
					{ new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) }, { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) } },
			{
					// 0
					{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
					{ new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }, { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) } } };

	private final Color[] myColor = { Color.CYAN, Color.magenta, Color.orange, Color.yellow, Color.black, Color.red };

	private Point pt;
	private int currentPiece;
	private int rotation;
	private ArrayList<Integer> nextPiece = new ArrayList<Integer>();
	private long score;
	private Color[][] well;

	private void init()
	{
		well = new Color[12][24];
		for (int i = 0; i < 12; i++)
		{
			for (int j = 0; j < 23; j++)
			{
				if (i == 0 || i == 11 || i == 22)
				{
					well[i][j] = Color.PINK;
				}
				else
				{
					well[i][j] = Color.BLACK;
				}
			}
		}
		newPiece();
	}

	public void newPiece()
	{
		pt = new Point(5, 2);
		rotation = 0;
		if (nextPiece.isEmpty())
		{
			Collections.addAll(nextPiece, 0, 1, 2, 3);
			Collections.shuffle(nextPiece);
		}
	}

	private boolean collidesAt(int x, int y, int rotation)
	{
		for (Point p : myPoint[currentPiece][rotation])
		{
			if (well[p.x + x][p.y + y] != Color.BLACK)
			{
				return true;
			}
		}
		return false;
	}

	private void rotate(int i)
	{
		int newRotation = (rotation + i) % 4;
		if (newRotation < 0)
		{
			newRotation = 3;
		}

		if (!collidesAt(pt.x, pt.y, newRotation))
		{
			rotation = newRotation;
		}
		repaint();
	}

	public void move(int i)
	{
		if (collidesAt(pt.x, pt.y, rotation))
		{
			pt.x += i;
		}

		repaint();
	}

	public void drop()
	{
		if (collidesAt(pt.x, pt.y, rotation))
		{
			int i = 0;
			pt.y += i;
		}
		else
		{
			fixTowell();
		}
		repaint();
	}

	public void fixTowell()
	{
		for (Point p : myPoint[currentPiece][rotation])
		{
			well[pt.x + p.x][pt.y + p.y] = myColor[currentPiece];
		}
		clearrows();
		newPiece();
	}

	public void deleteRow(int row)
	{
		for (int j = row - 1; j > 0; j--)
		{
			for (int i = 1; i < 11; i++)
			{
				well[i][j + 1] = well[i][j];
			}
		}
	}

	public void clearrows()
	{
		boolean gap = false;
		int numClear = 0;
		for (int j = 21; j > 0; j--)
		{
			gap = false;
			for (int i = 1; i < 11; i++)
			{
				if (well[i][j] == Color.BLACK)
				{
					gap = true;
					break;
				}
			}

			if (!gap)
			{
				deleteRow(numClear);
				j += 1;
				numClear += 1;

			}

			switch (numClear)
			{
			case 1:
				score += 100;
				break;
			case 2:
				score += 300;
				break;
			case 3:
				score += 500;
				break;
			case 4:
				score += 800;
				break;
			}
		}
	}

	public void paintComponent(Graphics g)
	{
		g.fillRect(0, 0, 26 * 12, 26 * 23);
		{
			for (int j = 0; j < 23; j++)
			{
				int i = 0;
				g.setColor(well[i][j]);
				g.fillRect(26 * i, 26 * j, 25, 25);
			}
		}

		g.setColor(Color.WHITE);
		g.drawString("Score is :" + score, 19 * 12, 25);
		drawPiece(g);
	}

	public void drawPiece(Graphics g)
	{
		g.setColor(myColor[currentPiece]);
		for (Point p : myPoint[currentPiece][rotation])
		{
			g.fillRect((p.x + pt.x) * 26, (pt.y + p.y) * 26, 25, 25);
		}

	}

	public static void main(String[] args)
	{
		JFrame f = new JFrame("TetrisProject");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(12 * 26 + 10, 26 * 23 + 25);
		f.setVisible(true);

		final TetrisProject game = new TetrisProject();
		game.init();
		f.add(game);

		f.addKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				switch (e.getKeyCode())
				{
				case KeyEvent.VK_UP:
					game.rotate(-1);
					break;

				case KeyEvent.VK_DOWN:
					game.rotate(+1);
					break;

				case KeyEvent.VK_LEFT:
					game.move(-1);
					break;

				case KeyEvent.VK_RIGHT:
					game.move(+1);
					break;

				case KeyEvent.VK_SPACE:
					game.drop();
					game.score += 1;
					break;

				}
			}

		});
		
		new Thread()
		{
			public void run()
			{
				while(true)
				{
					try
					{
						Thread.sleep(1000);
						
						
					}
					catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					game.drop();
				}
			}
		}.start();

	}

}
