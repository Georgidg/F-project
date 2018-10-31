package project;
import java.util.Arrays;

public class DP_Algorithm {
	/**
	 * we have n jobs sorted with time/importance from big to small
	 * that we need to decide in 1|h(1),N - res|sum wjCj which to do before the blocking time interval and which after
	 * 
	 * @param p array size n of the time that each job need
	 * @param w array size n of the relative importance of each job
	 * @param s the begin of the time which the machine cannot perform the processing of any job
	 * @param t the end of the time which the machine cannot perform the processing of any job
	 * 
	 * @return array size n that present which job to do before s with 1 and which after t with 0
	 */
	public  static int[] N_RESProblem(int[] p, int[] w, int s, int t){
		long Mat[][]= new long[p.length+1][s+1];
		for (int i = 0; i < Mat.length; i++) {
			for (int j = 0; j < Mat[0].length; j++) {
				Mat[i][j] = -1;
			}
		}
		Mat[0][0] = 0;
		for (int i = 0; i < Mat.length - 1; i++) Mat[0][0] += p[i]*w[i];
		long Ak=0;//The sum of all the weights we have passed
		for(int i = 0;i < Mat.length - 1; i++) {
			for (int Yk = 0; Yk < Mat[0].length; Yk++) {
				if(Mat[i][Yk] != -1){
					/////////don't add 
					long dontAdd = Mat[i][Yk] + w[i]*(Ak - Yk) + w[i]*t;
					Mat[i + 1][Yk] = (Mat[i + 1][Yk] == -1) ? dontAdd : Math.min(dontAdd, Mat[i + 1][Yk]);

					/////////add
					if(Yk + p[i+1 -1] <= s){
						long add = Mat[i][Yk] + w[i + 1 -1]*Yk;
						Mat[i + 1][Yk + p[i]] = (Mat[i + 1][Yk + p[i]] == -1) ? add : Math.min(add, Mat[i + 1][Yk + p[i]]);
					}
				}
			} 
			Ak+=p[i];
		}
		int[] x = new int[p.length];
		long min = Mat[Mat.length - 1][0];
		int minIndex = 0;
		for (int i = 1; i < Mat[0].length; i++) {
			if(min > Mat[Mat.length - 1][i] && Mat[Mat.length - 1][i] != -1){
				min =  Mat[Mat.length - 1][i];
				minIndex = i;
			}
		}
		for(int i = Mat.length - 2; i >= 0; i--){
			Ak -= p[i + 1 -1];
			if(Mat[i][minIndex] + w[i]*(Ak - minIndex) + w[i]*t == Mat[i + 1][minIndex] && Mat[i][minIndex] != -1){
				x[i] = 0;
			}
			else{
				x[i] = 1;
				minIndex -= p[i];
			}
		}
		return x;
	}
}
