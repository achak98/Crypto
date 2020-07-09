package Cryptography;

import java.util.Scanner;

public class AffineCipher {
    int k1, k2,kInverse;
    int[] arr;
    String str;

    final int ASCII_A = 'a';

    void readInput() {
        Scanner sn = new Scanner(System.in);
        System.out.println("Enter K1 value:");
        k1 = sn.nextInt();

        System.out.println("Enter enter K2 value: ");
        k2 = sn.nextInt();

        System.out.println("Enter the string");
        str = sn.next();

        arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i)-ASCII_A;
        }
    }

    String encrypt() {

        for (int i = 0; i < str.length(); i++) {
            arr[i] = ((arr[i]+k2) * k1) % 26;
        }
        String encrypted = "";
        for (int i = 0; i < str.length(); i++) {
            encrypted = encrypted + Character.toString((char)(arr[i]+ASCII_A));
        }

        return encrypted;
    }

    String decrypt() {
        for (int i = 0; i < str.length(); i++) {
            arr[i] = ((arr[i]-k2) * kInverse) % 26;
        }
        String decrypted = "";
        for (int i = 0; i < str.length(); i++) {
            decrypted = decrypted + Character.toString((char)(arr[i]+ASCII_A));
        }

        return decrypted;
    }

    void modInverse() {
        int a = k1 % 26;
        for (int i = 1; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                kInverse = i;
                return;
            }
        }
        kInverse = -1;
        System.out.println("Inverse does not exist, cannot decrypt.");

    }

    public static void main(String[] args) {
        AffineCipher o1 = new AffineCipher();
        o1.readInput();
        System.out.println("Encrypted String: " + o1.encrypt());
        o1.modInverse();
        if(o1.kInverse != -1)
            System.out.println("Decrypted String: " + o1.decrypt());

    }
}
    
    

