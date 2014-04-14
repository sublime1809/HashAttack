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
public class PreImage extends Attack {
    
    public static void preImage(int n) {
        Random generator = new Random();
        byte[] hash = new byte[n/8];
        preImage(hash, n);
    }
    
    private static void preImage(byte[] hash, int n) {
        boolean found = false;
        Random generator = new Random();
        try {
            int i = 0;
            while(!found) {
                byte[] input = new byte[10];
                generator.nextBytes(input); 
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                digest.reset();
                byte[] buffer = truncate(digest.digest(input), n);
                if(equal(hash, buffer)) {
                    found = true;
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
    
    public static boolean equal(byte[] a, byte[] b) {
        for(int i = 0; i < b.length; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
