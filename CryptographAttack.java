package Cryptography;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CryptographAttack {

    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
                                   //key = AZERTYUIOPQSDFGHJKLMWXCVBN

    private static String cipherText = "OIAXTQFGCFARXTFMWKTLLTTFHSAETLBGWHTGHSTCOSSFTXTKLTTOXTZTTFGYYCGKSRAFRZAEQYKGFMOTKLOXTLMGGRGFMITZAEQRTEQGYAZSOFQTKZGWFRYGKMITHSWMOMOGFEADHLCOMILCTAMOFDBTBTLCAMEIOFUMITLMAKLYOUIMGFMITLIGWSRTKGYGKOGFOXTYTSMCOFROFDBIAOKKOROFUMTLMZGAMLGYYMITZSAEQUASAVOTLAFRLTTFAFAMMAEQYSTTMZWKFSOQTADAMEIAFRROLAHHTAKOXTLTTFOMYTSMOM".toLowerCase();

    private static List<String> wordsList = new ArrayList<>();

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0)  {
            if(decrypt(prefix)) {
                return;
            }
        }
        else {
            for (int i = 0; i < n; i++)
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
        }
    }

    //key = AZERTYUIOPQSDFGHJKLMWXCVBN
    private static Boolean decrypt(String key) {
        String decrypted = "";
        for(int i = 0; i<cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            int index = key.indexOf(ch);
            decrypted += LETTERS.charAt(index);
        }
        boolean isValid = isValidText(decrypted);
        if(isValid) {
            System.out.println(decrypted);
        }
        return isValid;
    }


    static void readFile(String path) throws IOException {

        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);

        String word;
        while((word = br.readLine()) != null) {
            wordsList.add(word);
        }
    }

    static boolean isValidText(String plainText) {
        for(int j = 0; j<plainText.length(); j++) {
            for(int k = plainText.length(); k>j+3; k--) {
                String subPlainText = plainText.substring(j,k);
                int index = Collections.binarySearch(wordsList, subPlainText);
                if(index > 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String [] args) throws IOException {



        readFile("C:\\Users\\rahul\\Documents\\JavaProjects\\JavaPrograms\\src\\Cryptography\\words_alpha.txt");
        permutation("",LETTERS);
    }
}
