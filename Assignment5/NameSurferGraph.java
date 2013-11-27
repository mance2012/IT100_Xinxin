/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		drawGraphic();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		names.clear();
		update();
	}
	
	/**
	 * Draws the graph's grid.
	 */
	private void drawGraphic() {
		int width = this.getWidth();
		int height = this.getHeight();
		double vx = (double) width / NDECADES;
		
		add(new GLine(0, 0, width, 0)); //top
		add(new GLine(width - 1, 0, width - 1, height)); //left
		add(new GLine(0, height - 1, width, height - 1)); //bottom
		add(new GLine(0, 0, 0, height)); //right
		
		add(new GLine(0, GRAPH_MARGIN_SIZE, width, GRAPH_MARGIN_SIZE));
		add(new GLine(0, height - GRAPH_MARGIN_SIZE, width, height - GRAPH_MARGIN_SIZE));
		
		double x = 0;
		int decade = START_DECADE;
		for(int i = 0; i < NDECADES; i++) {
			GLabel label = new GLabel(Integer.toString(decade)); // label of decade
			label.setLocation(x + vx / 2 - label.getWidth() / 2, height - label.getAscent() / 2);
			add(label);
			x += vx;
			decade += 10;
			add(new GLine(x, 0, x, height)); // vertical columns indicating the decade.
		}
		
	}
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		names.add(entry);
		update();
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		currentColor = 0;
		removeAll();
		drawGraphic();
		drawEntries();
	}
	
	/**
	 * Organize the drawing of all entrys.
	 * Defer to drawEntry() for the actual work.
	 */
	private void drawEntries() {
		Iterator<NameSurferEntry> it = names.iterator();
		while (it.hasNext()) {
			drawEntry(it.next(), nextColor());
		}
	}
	
	/**
	 * Draws one NameSurferentry on the graph.
	 */
	private void drawEntry(NameSurferEntry next, Color nextColor) {
		int decade = START_DECADE;
		int width = this.getWidth();
		int height = this.getHeight();
		double vx = (double) width / NDECADES;
		double x = 0;
		double range = height - GRAPH_MARGIN_SIZE - GRAPH_MARGIN_SIZE;
		double DyRank = range / MAX_RANK; // the (difference in y coordinate) per rank
		
		GPoint lastpoint = null;
		for (int i = 0; i < NDECADES; i++) {
			int rank = next.getRank(decade);
			double y; // find the vertical coordinate.
			if (rank == 0)
				y = height - GRAPH_MARGIN_SIZE;
			else
				y = GRAPH_MARGIN_SIZE + (rank * DyRank);

			String str; // prepare the label's string.
			if (rank == 0)
				str = next.getName() + " *";
			else
				str = next.getName() + " " + next.getRank(decade);

			GLabel label = new GLabel(str, x, y);
			label.setColor(nextColor);
			add(label);
			if (lastpoint != null) {
				//draw the line connecting the two graph points.
				GLine line = new GLine(lastpoint.getX(), lastpoint.getY(), x, y);
				line.setColor(nextColor);
				add(line);
			}
			lastpoint = new GPoint(x, y);
			//increment decade and horizontal coordinate, for next iteration.
			decade += 10;
			x += vx;
		}
		
	}

	/**
	 * gets a 'next' color in a consistent manner.
	 */
	private Color nextColor() {
		currentColor %= colors.length;
		return colors[currentColor++];
	}
	
	
	private Color[] colors = {Color.BLACK, Color.RED, Color.MAGENTA, Color.GRAY};
	private int currentColor; 
	private ArrayList<NameSurferEntry> names = new ArrayList<NameSurferEntry>();
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
