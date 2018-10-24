
import java.util.Arrays;

public class Knapsack01{




	public static void Knapsack(int[] p, int[] w,int s,int t) {
		long Mat[][]= new long[p.length][s];

		long notAdded=0;
		long Ak=0;//The sum of all the weights we have passed
		long Yk=0;//The sum of all the weights we have entered 
		long Added=0;
		for(int i=1;i<Mat.length;i++) {
			for (int j = 1; j < Mat[0].length; j++) {
				if(j<w[i-1]) {

					notAdded=Mat[i-1][j]+Mat[i][j-1]+w[j]*(Ak-Yk)+w[j]*t ;
					Mat[i][j]=notAdded;
					Ak+=w[j-1];
				}

				else {
					notAdded=Mat[i-1][j]+Mat[i][j-1]+w[j]*(Ak-Yk)+w[j]*t ;
					Added=Mat[i-1][j-w[i-1]]+p[i-1] +Mat[i][j-1]+w[j]*Yk;//Mew is Equivalent to 0

					Mat[i][j]=Math.min(Added,notAdded);
					Ak+=w[j-1];
					if(Mat[i-1][j]<(Mat[i-1][j-w[i-1]])+p[i-1])
						Yk+=w[j-1];

				}

			}
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
		int Mat[][]=new int [v.length][LimitOfWeight];

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
}




