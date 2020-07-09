package Cryptography;

import java.util.*;

public class MonoalphabeticSubAttack {

    private static final String FREQUENCIES_SORTED  = "etaoinsrhdlucmfywgpbvkxqjz";
                                                     //hlzbdopstvyacefgijkmnqruwx
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";

    private static final int N = 26;


    private  String key;

    private List<String> keyList = new ArrayList<>();

    int countFrequency(String str, char ch) {
        int frequency = 0;

        for(int i = 0; i < str.length(); i++) {
            if(ch == str.charAt(i)) {
                ++frequency;
            }
        }
        return frequency;
    }

    List<Integer> count(String str) {

        List<Integer> plainTextLetterFrequency = new ArrayList<>();

        for(int i = 0; i<LETTERS.length(); i++) {
            int frequency = countFrequency(str, LETTERS.charAt(i));
            plainTextLetterFrequency.add(frequency);
        }

        System.out.println(plainTextLetterFrequency);
        return plainTextLetterFrequency;
    }

    List<Integer> sort(List<Integer> list, String str) {
        char sortedPlainTextArr[] = LETTERS.toCharArray();

        for(int i = 0; i<list.size()-1; i++) {
            for(int j = 0; j<list.size()-1-i; j++) {
                if(list.get(j) < list.get(j+1)) {
                    int temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);

                    char tempChar = sortedPlainTextArr[j];
                    sortedPlainTextArr[j] = sortedPlainTextArr[j+1];
                    sortedPlainTextArr[j+1] = tempChar;

                }
            }
        }
        key = new String(sortedPlainTextArr);
        return list;
    }

    String decrypt(String cipherText, int shift) {

        String decrypted = "";
        for(int i = 0; i<cipherText.length(); i++) {
            char ch = cipherText.charAt(i);
            int index = (key.indexOf(ch)+shift) % 26;
            decrypted += LETTERS.charAt(index);
        }
        return decrypted;
    }

    List<String> findPossibilities (String str) {
        List<String> possibilities = new ArrayList<>();
        for(int i = 0; i<26; i++) {
            possibilities.add(decrypt(str,i));
        }
        return possibilities;
    }





    public static void main (String [] args) {
        MonoalphabeticSubAttack monoalphabeticSubAttack = new MonoalphabeticSubAttack();

        String str = "RLEYNFBKXBEIYNBGMWNUUNNBPHEANUQKMPNKPHNXRHHBNYNWUNNRYNTNNBKOOXKWHIEBITEAFOWKBGRNWURYNUGKKIKBGLNTEAFINAFKOETHRBFNWTKMBIOKWGLNPHMGRGRKBAECPUXRGLUXNEGRBCQNQNUXEGALRBSGLNUGEWUORSLGKBGLNULKMHINWKOKWRKBRYNONHGXRBIRBCQLERWWRIRBSGNUGTKEGUKOOGLNTHEAFSEHEJRNUEBIUNNBEBEGGEAFOHNNGTMWBHRFNECEGALEBIIRUEPPNEWRYNUNNBRGONHGRG".toLowerCase();

        List<Integer> plainTextLetterFrequency = monoalphabeticSubAttack.count(str);

        System.out.println(monoalphabeticSubAttack.sort(plainTextLetterFrequency, str));
        System.out.println(monoalphabeticSubAttack.key);

        List<String> possibilities = monoalphabeticSubAttack.findPossibilities(str);
        for (String possibility : possibilities) {
            System.out.println(possibility);
        }
    }

}
