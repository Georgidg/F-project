
import java.util.Arrays;

public class Knapsack01{




	public static void Knapsack(int[] p, int[] w,int s,int t) {
		long Mat[][]= new long[p.length+1][s+1];

		long notAdded=0;
		long Ak=0;//The sum of all the weights we have passed
		long Yk=0;//The sum of all the weights we have entered 
		long Added=0;
		for(int i=1;i<Mat.length;i++) {
			for (int j = 1; j < Mat[0].length; j++) {
				if(j<p[i-1]) {

					notAdded=Mat[i-1][j]+w[i]*(Ak-Yk)+w[j]*t ;
					Mat[i][j]=notAdded;

				}

				else {
					notAdded=Mat[i-1][j]+Mat[i][j-1]+w[j]*(Ak-Yk)+w[j]*t ;
					Added=Mat[i-1][j-w[i-1]]+p[i-1] +Mat[i][j-1]+w[j]*Yk;//Mew is Equivalent to 0

					Mat[i][j]=Math.min(Added,notAdded);
					if(Mat[i-1][j]<(Mat[i-1][j-w[i-1]])+p[i-1])
						Yk+=w[j-1];
				}

			}
			Ak+=w[i];
		}
		for (int i = 0; i < Mat.length; i++) {
			System.out.println(Arrays.toString(Mat[i]));
		}
	}






	public static void main(String args[]){
		int v []={4,8,7,1,2,10};
		int w []={9,6,3,4,5,2};
		int a=15;
		TheOriginalKnapsack(v,w,a);

	}

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
			for (int Yk = 0; Yk < Mat[0].length - 1; Yk++) {
				if(Mat[i][Yk] != -1){
					/////////don't add 
					long dontAdd = Mat[i][Yk] + beta[i+1]*(Ak - Yk) + v[i + 1];
					Mat[i + 1][Yk] = (Mat[i + 1][Yk] == -1) ? dontAdd : Math.min(dontAdd, Mat[i + 1][Yk]);
					
					/////////add
					long add = Mat[i][Yk] + beta[i+1]*Yk + u[i + 1];
					Mat[i + 1][Yk] = (Mat[i + 1][Yk + alfa[i+1]] == -1) ? dontAdd : Math.min(dontAdd, Mat[i + 1][Yk]);

				}

			}
			Ak+=alfa[i];
		}
		for (int i = 0; i < Mat.length; i++) {
			System.out.println(Arrays.toString(Mat[i]));
		}
	}
}




