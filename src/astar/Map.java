package astar;

import java.util.LinkedList;

public class Map {

	private int column = 10, row = 10;
	private int startX = 0, startY = 0;
	private int goalX = 9, goalY = 9;

	private LinkedList<Node> path = new LinkedList<Node>();
	private LinkedList<Node> openList = new LinkedList<Node>();
	private LinkedList<Node> closedList = new LinkedList<Node>();

	private Node[][] nodes = new Node[column][row];
	private Node current, start, goal;

	public Map() {
		init();

		while (!openList.isEmpty()) {
			findLowestF();
			if (current.equals(goal)) {
				createPath();
			}

			openList.remove(current);
			closedList.add(current);
			current.setNeighbors(getNeighbors(current));

			for (Node neighbor : current.getNeighbors()) {
				checkNeighbor(neighbor);
			}
		}

	}

	private void init() {
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				nodes[i][j] = new Node(i, j);
				nodes[i][j].setH(heuristic(i, j, goalX, goalY));
			}
		}
		start = nodes[startX][startY];
		goal = nodes[goalX][goalY];
		start.setG(0);
		start.setF(start.getH());

		openList.add(start);
	}

	private int heuristic(int x1, int y1, int x2, int y2) {
		int distance = (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		return distance * 10;
	}

	private void findLowestF() {
		current = openList.getFirst();
		for (Node node : openList) {
			if (node.getF() < current.getF())
				current = node;
		}
	}

	private LinkedList<Node> getNeighbors(Node node) {
		LinkedList<Node> neighbors = new LinkedList<Node>();
		int[] offSets = { -1, 0, 1 };

		for (int i = 0; i < offSets.length; i++) {
			for (int j = 0; j < offSets.length; j++) {
				int x = offSets[i] + node.getX();
				int y = offSets[j] + node.getY();
				if ((x < 0 || y < 0) || (x > nodes.length - 1 || y > nodes[0].length - 1)
						|| (offSets[i] == 0 && offSets[j] == 0))
					continue;
				Node neighbor = nodes[x][y];
				neighbors.add(neighbor);
			}
		}
		return neighbors;
	}

	private void checkNeighbor(Node neighbor) {
		if (!closedList.contains(neighbor)) {
			int tempG = current.getG() + heuristic(current.getX(), current.getY(), neighbor.getX(), neighbor.getY());
			if (!openList.contains(neighbor)) {
				openList.add(neighbor);
			} else if (tempG >= neighbor.getG()) {
				return;
			}
			neighbor.setG(tempG);
			neighbor.setF(neighbor.getG() + neighbor.getH());
			neighbor.setParent(current);
		}
	}

	private void createPath() {
		Node tempNode = current;
		path.add(tempNode);
		while (tempNode.getParent() != null) {
			Node parent = tempNode.getParent();
			path.add(parent);
			tempNode = parent;
		}
		for (Node node : path) {
			System.out.println(node.getX() + " " + node.getY());
		}
		return;
	}
}
