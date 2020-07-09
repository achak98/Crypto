package Cryptography;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CaesarCipherAttack {

    private static final int ASCII_A = 'a';
    private static final int N = 26;

    private static final String FREQUENCIES_SORTED  = "ETAOINSRHDLUCMFYWGPBVKXQJZ";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private static final double[] FREQUENCIES = {
            8.167, 1.492, 2.782, 4.253, 12.702, 2.228,
            2.015, 6.094, 6.966, 0.153, 0.772, 4.025, 2.406, 6.749, 7.507,
            1.929, 0.095, 5.987, 6.327, 9.056, 2.758, 0.978, 2.360, 0.150,
            1.974, 0.074
    };
    //frequencies of a-z in english

    String decrypt(String cipherText, int k) {

        int cipherTextArr[] = new int[cipherText.length()];
        for (int i = 0; i < cipherText.length(); i++) {
            cipherTextArr[i] = (cipherText.charAt(i)-ASCII_A);
        }

        for (int i = 0; i < cipherText.length(); i++) {
            cipherTextArr[i] = (cipherTextArr[i]-k) % N;
            if(cipherTextArr[i] < 0) {
                cipherTextArr[i] += N;
            }
        }

        String decrypted = "";
        for (int i = 0; i < cipherText.length(); i++) {
            decrypted = decrypted + Character.toString((char)(cipherTextArr[i] + ASCII_A));
        }
        return decrypted;
    }

    static List<String> readFile(String path) throws IOException {

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        List<String> wordsList = new ArrayList<>();

        String word;
        while((word = br.readLine()) != null) {
            wordsList.add(word);
        }
        return wordsList;

    }

    List<String> bruteForce(String cipherText) {

        List<String> plainTextList = new ArrayList<>();

        for(int i = 0; i<N; i++) {
            plainTextList.add(decrypt(cipherText,i));
        }
        return plainTextList;
    }

    static List <String> findValidText(List<String> wordsList, List<String> plainTextList) {

        List<String> sortedWordsList = new ArrayList<>();

        for(int i = 0; i< plainTextList.size(); i++) {
            String plainText = plainTextList.get(i);
            for(int j = 0; j<plainText.length(); j++) {
                for(int k = plainText.length(); k>j+3; k--) {
                    String subPlainText = plainText.substring(j,k);
                    int index = Collections.binarySearch(wordsList, subPlainText);
                    if(index > 0) {
                        sortedWordsList.add(plainText);
                    }
                }
            }
        }
        return sortedWordsList;
    }


    public static void main(String [] args) throws IOException {
        CaesarCipherAttack  caesarCipherAttack = new   CaesarCipherAttack();

        List<String> plainTextList =  caesarCipherAttack.bruteForce("PohclruvduhkcluabylzzlluwshjlzfvbwlvwsldpssulclyzllPclilluVmmdvyskhukihjrmyvuaplyzPclzavvkvuaolihjrkljrvmhispurlyivbukmvyaolWsbapapvuJhtwzdpaozdlhaputflflzdhajopunaolzahyzmpnoavuaolzovbsklyvmVypvuPclmlsadpukputfohpyypkpunalzaivhazvmmaolishjrnhsheplzhukzlluhuhaahjrmsllaibyusprlhthajohukkpzhwwlhyPclzllupamlsapa".toLowerCase());
        System.out.println(plainTextList);

        List<String> wordsList =  caesarCipherAttack.readFile("C:\\Users\\rahul\\Documents\\JavaProjects\\JavaPrograms\\src\\Cryptography\\words_alpha.txt");
        System.out.println(wordsList.size());

        List<String> validWordsList = findValidText(wordsList,plainTextList);
        for(String i :validWordsList) {
            System.out.println(i);
        }
    }

}
