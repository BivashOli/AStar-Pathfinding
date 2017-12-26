package astar;
import java.util.LinkedList;

public class Node {

	private LinkedList<Node> neighbors = new LinkedList<Node>();
	private Node parent;
	private int x, y;
	private int g, h, f;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public LinkedList<Node> getNeighbors() {
		return neighbors;
	}

	public void setNeighbors(LinkedList<Node> neighbors) {
		this.neighbors = neighbors;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}
}
