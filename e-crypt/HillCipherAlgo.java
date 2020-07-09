/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package e.crypt;

import static java.lang.Math.ceil;
import static java.lang.Math.sqrt;

/**
 *
 * @author abhic
 */
public class HillCipherAlgo{
        public int[][] getKeyMatrix(String key, int index) 
        { 
            int k = 0; 
            char [] keyA = key.toCharArray();
            int keyMatrix[][] = new int [index][index];
            for (int i = 0; i < index; i++)  
            { 
                for (int j = 0; j < index; j++)  
                { 
                    keyMatrix[i][j] = (keyA[k]); 
                    k++; 
                } 
            } 
            return keyMatrix;
        } 

        // Following function encrypts the message 
        public String encrypt(String p, String key) 
        { 
            // Get key matrix from the key String 
            int index = (int) sqrt((double)(key.length()));
            int n = (int)(ceil(((double)(p.length()))/((double)(index))));
            p=p.toUpperCase();
            char [] pA = p.toCharArray();
            String ct=""; 
            
            int keyM[][] = getKeyMatrix(key,index); 

            int pM[][]=new int[n][index]; 
            int k =0;
            
            for (int i = 0; i < n; i++){
                for(int j=0;j<index;j++){
                    while(k<pA.length){
                        pM[i][j] = pA[k]; 
                        k++;
                        System.out.println(pM[i][j]);
                    }
                }
            }
            

            int cM [][] = multiplyMatrices(pM,keyM,index,n);
            for(int i=0;i<n;i++){
                for(int j=0;j<index;j++){
                    System.out.println(cM[i][j]);
                }
            }
            
            for (int i = 0; i < n;i++){
                for(int j=0;j<index;j++){
                    while(cM[i][j]>90)
                        cM[i][j]-=26;
                    ct += (char)cM[i][j];
                }
            }
            return ct;
        } 
        
        public String decrypt (String c, String key)
        {
            int index = (int) sqrt((double)(key.length()));
            int n = (int)(ceil(((double)(c.length()))/((double)(index))));
            char [] cA = c.toCharArray();
            String pT=""; 
            
            int keyM[][] = getKeyMatrix(key,index); 
            int keyMinv[][] = invert (keyM);

            int cM[][]=new int[n][index]; 
            int k =0;
            
            for (int i = 0; i < n; i++){
                for(int j=0;j<index;j++){
                    while(k<cA.length){
                        cM[i][j] = cA[k]; 
                        k++;
                    }
                }
            }

            int pM [][] = multiplyMatrices(cM,keyMinv,index,n);
            
            for (int i = 0; i < n;i++){
                for(int j=0;j<index;j++){
                    while(pM[i][j]>90)
                        pM[i][j]-=26;
                    pT += (char)pM[i][j]; 
                }
            }
            pT=pT.toLowerCase();
            return pT;
        }

        public int [][] multiplyMatrices(int[][] fM, int [][] keyM,int index,int n){
            for(int i = 0; i < n; i++) {
            for (int j = 0; j < index; j++) {
            System.out.println(fM[i][j]);
            }}
            int [][] sM = new int [n][index];
            for(int i = 0; i < n; i++) {
            for (int j = 0; j < index; j++) {
                for (int k = 0; k < index; k++) {
                    sM[i][j] += fM[i][k] * (keyM[k][j]);
                    System.out.println("SM:"+sM[i][j]+"     "+"FM:"+fM[i][j]+"   "+"keyM:"+keyM[i][j]);
                    }
                System.out.println(sM[i][j]);
            }
        }
            return sM;
        }
        
        public static int[][] invert(int a[][]) {
		int n = a.length;
		int x[][] = new int[n][n];
		double b[][] = new double[n][n];
		int index[] = new int[n];
		for (int i = 0; i < n; ++i)
			b[i][i] = 1;

		// Transform the matrix into an upper triangle
		gaussian(a, index);

		// Update the matrix b[i][j] with the ratios stored
		for (int i = 0; i < n - 1; ++i)
			for (int j = i + 1; j < n; ++j)
				for (int k = 0; k < n; ++k)
					b[index[j]][k] -= (double)(a[index[j]])[i] * b[index[i]][k];

		// Perform backward substitutions
		for (int i = 0; i < n; ++i) {
			x[n - 1][i] = (int) ((double)(b[index[n - 1]][i]) / (double)(a[index[n - 1]][n - 1]));
			for (int j = n - 2; j >= 0; --j) {
				x[j][i] = (int)(b[index[j]][i]);
				for (int k = j + 1; k < n; ++k) {
					x[j][i] -= (double)(a[index[j]][k]) * x[k][i];
				}
				x[j][i] /= (double)(a[index[j]][j]);
			}
		}
		return x;
	}

	// Method to carry out the partial-pivoting Gaussian
	// elimination. Here index[] stores pivoting order.

	public static void gaussian(int a[][], int index[]) {
		int n = index.length;
		double c[] = new double[n];

		// Initialize the index
		for (int i = 0; i < n; ++i)
			index[i] = i;

		// Find the rescaling factors, one from each row
		for (int i = 0; i < n; ++i) {
			double c1 = 0;
			for (int j = 0; j < n; ++j) {
				double c0 = Math.abs((double)(a[i][j]));
				if (c0 > c1)
					c1 = c0;
			}
			c[i] = c1;
		}

		// Search the pivoting element from each column
		int k = 0;
		for (int j = 0; j < n - 1; ++j) {
			double pi1 = 0;
			for (int i = j; i < n; ++i) {
				double pi0 = Math.abs((double)(a[index[i]][j]));
				pi0 /= c[index[i]];
				if (pi0 > pi1) {
					pi1 = pi0;
					k = i;
				}
			}

			// Interchange rows according to the pivoting order
			int itmp = index[j];
			index[j] = index[k];
			index[k] = itmp;
			for (int i = j + 1; i < n; ++i) {
				double pj = (double)(a[index[i]][j]) / (double)(a[index[j]][j]);

				// Record pivoting ratios below the diagonal
				(a[index[i]][j]) = (int)(pj);

				// Modify other elements accordingly
				for (int l = j + 1; l < n; ++l)
					(a[index[i]][l]) -= pj * (double)(a[index[j]][l]);
			}
		}
	}
  
}