package cn.test;

public class Node {
		String bsite;//起点
		String esite;//终点
		int time;//间隔时间
		int checi;//所属车次
		public Node(){
			
		}
		public Node(String bsite, String esite, int time,int checi) {
			this.bsite = bsite;
			this.esite = esite;
			this.time = time;
			this.checi=checi;
		}

		public String getBsite() {
			return bsite;
		}

		public void setBsite(String bsite) {
			this.bsite = bsite;
		}

		public String getEsite() {
			return esite;
		}

		public void setEsite(String esite) {
			this.esite = esite;
		}

		public int getTime() {
			return time;
		}

		public void setTime(int time) {
			this.time = time;
		}

		public int getCheci() {
			return checi;
		}

		public void setCheci(int checi) {
			this.checi = checi;
		}
}
