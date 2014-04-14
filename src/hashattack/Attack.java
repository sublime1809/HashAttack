/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hashattack;

/**
 *
 * @author chrystal
 */
public class Attack {
    protected static byte[] truncate(byte[] input, int n) {
        byte[] output = new byte[n/8];
        for ( int i = 0; i < n/8; i++ ) {
            output[i] = input[i];
        }
        return output;
    }
    
    protected static double calcN(int i) {
        return (Math.log10(i) / Math.log10(2));
    }
}
