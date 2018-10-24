
public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		easyTest();
		difficultTest();
	}
	
	public static void easyTest(){
		int p[] = {2};
		int w[] = {2};
		int t=5;
		int s=2;

		Knapsack01.Knapsack(p,w,s,t); 
	}
	public static void difficultTest(){
		int p[] = {4,8,7,1,2,10,0};
		int w[] = {9,6,3,4,5,2,0};
		int t=5;
		int s=4;

		Knapsack01.Knapsack(p,w,s,t);
	}
}
