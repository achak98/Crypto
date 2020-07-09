package Cryptography;

import java.util.Arrays;
import java.util.List;

public class HillCipher {

    private final static int KEY_ROW = 3;
    private final static int KEY_COLUMN = 3;
    private final static String LETTERS = "0123456789abcdefghijklmnopqrstuvwxyz.";
    private final static int N = LETTERS.length();

    private int key[][];
    private int keyInv[][];

    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int[] row : mat)

            // converting each row as string
            // and then printing in a separate line
            System.out.println(Arrays.toString(row));
        System.out.println();
    }

    void multiply(int textArr[][], int key[][], int result[][]) {
       for (int i = 0; i < textArr.length; i++) {
            for (int j = 0; j < textArr[0].length; j++) {
                result[i][j] = 0;
                for (int k = 0; k < KEY_COLUMN; k++) {
                    result[i][j] += textArr[i][k] * key[k][j];
                }
                result[i][j] = result[i][j] % N;
            }
        }
    }

    public HillCipher(int[][] key, int[][] keyInv) {
        this.key = key;
        this.keyInv = keyInv;
    }

    String encrypt(String plainText) {
        if(plainText.length()%3 == 1) {
            plainText += "zz";
        } else if(plainText.length()%3 == 2) {
            plainText += "z";
        }
        int plainTextArr[][] = new int[plainText.length()/KEY_COLUMN][KEY_COLUMN];
        int index = 0;
        for(int i = 0; i<plainTextArr.length; i++) {
            for(int j = 0; j<plainTextArr[0].length; j++) {
                plainTextArr[i][j] = LETTERS.indexOf(plainText.charAt(index));
                index++;
            }
        }

        print2D(plainTextArr);

        int cipherTextArr[][] = new int[plainText.length()/KEY_COLUMN][KEY_COLUMN];
        multiply(plainTextArr,key,cipherTextArr);

        print2D(cipherTextArr);

        StringBuilder encrypted = new StringBuilder();
        for(int i = 0; i<cipherTextArr.length; i++) {
            for(int j = 0; j<cipherTextArr[0].length; j++) {
                encrypted.append(LETTERS.charAt(cipherTextArr[i][j]));
            }
        }

        return encrypted.toString();
    }

    String decrypt(String cipherText) {
        int cipherTextArr[][] = new int[cipherText.length()/KEY_COLUMN][KEY_COLUMN];
        int index = 0;
        for(int i = 0; i<cipherTextArr.length; i++) {
            for(int j = 0; j<cipherTextArr[0].length; j++) {
                cipherTextArr[i][j] = LETTERS.indexOf(cipherText.charAt(index));
                index++;
            }
        }

        int plainTextArr[][] = new int[cipherText.length()/KEY_COLUMN][KEY_COLUMN];
        multiply(cipherTextArr,keyInv,plainTextArr);

        StringBuilder decrypted = new StringBuilder();
        for(int i = 0; i<plainTextArr.length; i++) {
            for(int j = 0; j<plainTextArr[0].length; j++) {
                decrypted.append(LETTERS.charAt(plainTextArr[i][j]));
            }
        }

        return decrypted.toString();
    }

    public static void main (String [] args) {

        int key[][] = {
                {1,2,3},
                {4,5,35},
                {1,35,1}
        };


        int keyInv[][] = {

                {24,11,1},
                {24,16,35},
                {1,35,1}

        };



        HillCipher o1 = new HillCipher(key,keyInv);

        String plainText = "1234";
        String cipherText = o1.encrypt(plainText);
        System.out.println(cipherText);

        System.out.println(o1.decrypt(cipherText));

    }
}
