package project;
import java.util.Arrays;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(easyTest()));
		//System.out.println(Arrays.toString(difficultTest()));
//			int v []={4,8,7,1,2,10};
//			int w []={9,6,3,4,5,2};
//			int a=15;
//			TheOriginalKnapsack(v,w,a);

		
	}
	
	public static int[] easyTest(){
		int p[] = {2,1};
		int w[] = {2,1};
		int t=5;
		int s=2;
		
		return Knapsack01.N_RESProblem(p, w, s, t); 
	}
	public static int[] difficultTest(){
		int p[] = {4,4,8,7,1,10};
		int w[] = {9,5,6,3,2,2};
		int t=5;
		int s=5;

		return Knapsack01.N_RESProblem(p, w, s, t);
	}
}
