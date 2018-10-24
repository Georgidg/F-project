
import java.util.Arrays;

public class Knapsack01{

	

	public static void TheOriginalKnapsack(int[] v, int[] w, int LimitOfWeight) {
		int Mat[][]=new int [v.length+1][LimitOfWeight+1];

		for(int i=1;i<Mat.length;i++) {
			for (int j = 1; j < Mat[0].length; j++) {
				if(j<w[i-1]) {
					Mat[i][j]=Mat[i-1][j];
				}
				else {
					Mat[i][j]=Math.max(Mat[i-1][j],Mat[i-1][j-w[i-1]]+v[i-1]);

				}

			}
		}
		for (int i = 0; i < Mat.length; i++) {
			System.out.println(Arrays.toString(Mat[i]));
		}
	}

	public  static void SymmetricQuadraticKnapsackProblem(int[] alfa, int[] beta, int[] u, int[] v, int r, int A){
		long Mat[][]= new long[alfa.length+1][A+1];
		for (int i = 0; i < Mat.length; i++) {
			for (int j = 0; j < Mat[0].length; j++) {
				Mat[i][j] = -1;
			}
		}
		Mat[0][0] = r;
		long Ak=0;//The sum of all the weights we have passed
		for(int i=0;i<Mat.length - 1;i++) {
			for (int Yk = 0; Yk < Mat[0].length; Yk++) {
				if(Mat[i][Yk] != -1){
					/////////don't add 
					long dontAdd = Mat[i][Yk] + beta[i + 1 -1]*(Ak - Yk) + v[i + 1];
					Mat[i + 1][Yk] = (Mat[i + 1][Yk] == -1) ? dontAdd : Math.min(dontAdd, Mat[i + 1][Yk]);

					/////////add
					if(Yk + alfa[i + 1 -1] <= A){
						long add = Mat[i][Yk] + beta[i + 1 -1]*Yk + u[i + 1];
						Mat[i + 1][Yk + alfa[i + 1 -1]] = (Mat[i + 1][Yk + alfa[i + 1 -1]] == -1) ? add : Math.min(add, Mat[i + 1][Yk]);
					}
				}

			}
			Ak+=alfa[i -1];
		}
		for (int i = 0; i < Mat.length; i++) {
			System.out.println(Arrays.toString(Mat[i]));
		}
	}

	public  static void N_RESProblem(int[] p, int[] w, int s, int t){
		long Mat[][]= new long[p.length+1][s+1];
		for (int i = 0; i < Mat.length; i++) {
			for (int j = 0; j < Mat[0].length; j++) {
				Mat[i][j] = -1;
			}
		}
		Mat[0][0] = 0;
		for (int i = 0; i < Mat.length - 1; i++) Mat[0][0] += p[i]*w[i];
		long Ak=0;//The sum of all the weights we have passed
		for(int i=0;i<Mat.length - 1;i++) {
			for (int Yk = 0; Yk < Mat[0].length; Yk++) {
				if(Mat[i][Yk] != -1){
					/////////don't add 
					long dontAdd = Mat[i][Yk] + w[i+1 -1]*(Ak - Yk) + w[i + 1 -1]*t;
					Mat[i + 1][Yk] = (Mat[i + 1][Yk] == -1) ? dontAdd : Math.min(dontAdd, Mat[i + 1][Yk]);
					if(Yk == 3) System.out.println(Mat[i + 1][Yk]);
					/////////add
					if(Yk + p[i+1 -1] <= s){
						long add = Mat[i][Yk] + w[i+1 -1]*Yk;
						Mat[i + 1][Yk + p[i+1 -1]] = (Mat[i + 1][Yk + p[i+1 -1]] == -1) ? add : Math.min(add, Mat[i + 1][Yk]);
					}
				}

			}
			if(i > 0) Ak+=p[i -1];
		}
		for (int i = 0; i < Mat.length; i++) {
			System.out.println(Arrays.toString(Mat[i]));
		}
	}
}




