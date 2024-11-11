package cn.test;

import java.util.ArrayList;

public class Dijkstra {

	ArrayList<Node> collection = new ArrayList<Node>(); // 储存所有的站点，含站点名称及下一站名称，及到下一站的时间，还有车次
	ArrayList<Node> route = new ArrayList<Node>(); // 记录形成路径

	ArrayList<Node> waiting = new ArrayList<Node>();// 此轮候选的点

	String current = null;

	int length = 0;

	public boolean addNodes(ArrayList<Node> collection) {
		this.collection = collection;
		return true;
	}

	void setNext(String nt) {
		current = nt;
	}

	// 更新等待节点
	void update(ArrayList<Node> wl) {
		ArrayList<Node> update = new ArrayList<Node>();
		for (Node a : wl) {
			for (Node b : wl) {
				if (a.getEsite().equals(b.getEsite())
						&& a.getTime() < b.getTime()) {// 如果存在相同站点，但是时间更短，加入update
					update.add(b);
				}
			}
		}

		for (Node c : update) {
			wl.remove(c);
		}
	}

	void Remove(ArrayList<Node> coll, String nt) {
		ArrayList<Node> move = new ArrayList<Node>();
		for (Node a : coll) {
			if (nt.equals(a.getEsite()))
				move.add(a);
		}
		//
		for (Node a : move) {
			if (coll.contains(a))
				coll.remove(a);
		}
	}

	void linkroute(ArrayList<Node> rt) {
		ArrayList<Node> linkroute = new ArrayList<Node>();

		int last = rt.size() - 1;

		for (int i = rt.size() - 2; i > -1; i--) {
			//从后向前，如果最后一个节点的起点不等于前一个节点的终点，则不能形成路径，移除
			if (!rt.get(last).getBsite().equals(rt.get(i).getEsite())) {
				linkroute.add(rt.get(i));
			} else if (rt.get(last).getBsite().equals(rt.get(i).getEsite())) {
				last = i;
			}
		}

		for (Node a : linkroute) {
			rt.remove(a);
		}

	}

	void toWait(String nt) {//如果站点为起点，加入wait队列
		Remove(collection, current);//当前站点为节点的到站，移除当前站点
		for (Node a : collection) {
			if (nt.equals(a.getBsite())) {
				waiting.add(new Node(a.getBsite(), a.getEsite(), a.getTime()
						+ length, a.getCheci()));
			}
		}
//		for (Node b : waiting) {
//			if (collection.contains(b))
//				collection.remove(b);
//		}
		update(waiting);
	}

	boolean selectNext(ArrayList<Node> wl) {
		if (wl.size() == 0) {//无可用候选点
			System.out.println("done");
			return false;
		} else if (wl.size() == 1) {
			current = wl.get(0).getEsite();//取当前节点的下一节点名称
			length = wl.get(0).getTime();//得到他们之间的时间
			route.add(route.size(), wl.get(0));//当前节点加入到路径节点
			wl.remove(wl.get(0));//移除候选节点

		} else {
			Node s = wl.get(0);
			//候选节点比较多时，选择时间最少的节点
			for (Node a : wl) {
				if (a.getTime() < s.getTime()) {
					s = a;
				}
			}
			current = s.getEsite();
			length = s.getTime();
			route.add(route.size(), s);//将时间最少节点加入路径

			wl.remove(s);// 将waiting list中的这一点删去

		}
		return true;
	}

	// 发站，到站
	public ArrayList<Node> wrap(String start, String stop) {
		current = start;

		for (int i = 0;; i++) {
			if (stop.equals(current)) {// 如果发站等于到站，终止
				break;
			}
			toWait(current);
			if (selectNext(waiting))//如果无可用退出循环
				continue;
			else
				break;
		}
		//如果最后一个节点的后继节点名称等于我们要找的终点
		if (route.size() != 0
				&& route.get(route.size() - 1).getEsite().equals(stop)) {
			linkroute(route);

			System.out.print(route.get(0).getBsite() + " -> ");

			for (Node a : route) {
				System.out.print(a.getEsite() + " ");
			}
			System.out.print("distance: "
					+ route.get(route.size() - 1).getTime() + "\t"
					+ route.get(route.size() - 1).getCheci() + "\n");
			return route;
		} else {
			System.err.println("no way");
		}

		return new ArrayList<Node>();
	}

	public static void main(String[] args) {
		ArrayList<Node> test=new ArrayList<Node>();
		test.add(new Node("哈尔滨", "长春", 60,3));
		test.add(new Node("长春", "沈阳", 60,3));
		test.add(new Node("沈阳", "北京西", 60,3));
		test.add(new Node("北京西", "安阳", 60,2));
		test.add(new Node("安阳", "西安", 240,2));
		test.add(new Node("西安", "成都", 300,2));
		Dijkstra d=new Dijkstra();
		d.addNodes(test);
		ArrayList<Node> route = d.wrap("哈尔滨", "安阳");
		System.out.println(route.size());
	}

}
// 节点
