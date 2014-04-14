/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hashattack;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author chrystal
 */
public class HashAttack extends Attack {

    
    private static int numOfTestCases = 30;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for( int i = 0; i < numOfTestCases; i++ ) {
            PreImage.preImage(16);
        }
        for( int i = 0; i < numOfTestCases; i++ ) {
            PreImage.preImage(24);
        }
        for( int i = 0; i < numOfTestCases; i++ ) {
            PreImage.preImage(8);
        }
//        for( int i = 0; i < numOfTestCases; i++ ) {
//            PreImage.preImage(32);
//        }
    }
    
    public static void collisionAttack(int n) {
        ArrayList digests = new ArrayList<byte[]>();
        boolean collision = false;
        Random generator = new Random();
        try {
            int i = 0;
            while(!collision) {
                byte[] input = new byte[10];
                generator.nextBytes(input); 
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.reset();
                byte[] buffer = truncate(digest.digest(input), n);
                if(contains(digests, buffer)) {
                    collision = true;
                } else {
                    digests.add(buffer);
                }
                i++;
            }
            System.out.print(n);
            System.out.print('\t');
            System.out.println(Math.pow(2, calcN(i)));
        } catch(NoSuchAlgorithmException e) {
            System.out.println("Attack Failed with Exception.");
            e.printStackTrace();
        }
    }
    
    private static boolean contains(ArrayList<byte[]> list, byte[] test) {
        for(byte[] b : list) {
            boolean equal = true;
            for(int i = 0; i < b.length; i++) {
                if(test[i] != b[i]) {
                    equal = false;
                    break;
                }
            }
            if(equal) {
                return true;
            }
        }
        return false;
    }
}
