/*
 * Java - Algorithms
 * 
 * https://github.com/egalli64/jaal
 */
package com.example.jaal.m1.s09;

import java.util.Arrays;

/**
 * Merging two sorted arrays in a new sorted one
 */
public class Merge {
    /**
     * Iterative implementation
     * 
     * @param left  a sorted array
     * @param right another sorted array
     * @return the merge of the input arrays
     * @throws NullPointerException if left or right is null
     */
    public static int[] iterative(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        // indexes for the current element in left, right, result
        int i = 0;
        int j = 0;
        int k = 0;
        for (; i < left.length && j < right.length; k++) {
            if (left[i] < right[j]) {
                result[k] = left[i];
                i += 1;
            } else {
                result[k] = right[j];
                j += 1;
            }
        }

        // the (eventual) left tail
        for (; i < left.length; i++, k++) {
            result[k] = left[i];
        }
        // the (eventual) right tail
        for (; j < right.length; j++, k++) {
            result[k] = right[j];
        }

        return result;
    }
    
    public static int[] mymergeiterative(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        
        int i = 0;
        int j = 0;
        
        for (int k = 0; k<result.length;k++) {
        	if (i<left.length && j<right.length) {
            	if(left[i]<right[j]) {
            		result[k]=left[i];
            		i++;
            	} else {
            		result[k]=right[j];
            		j++;
            	}        		
        	}       	
        }        
        if (i==left.length) {
        	result[result.length-1] = right[right.length-1];       	
        } else {
        	result[result.length-1] = left[left.length-1];         	
        }        
        return result;
    }

    /**
     * Recursive implementation
     * 
     * @param left  a sorted array
     * @param right another sorted array
     * @return the merge of the input arrays
     * @throws NullPointerException if left or right is null
     */
    public static int[] recursive(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        return recursive(left, 0, right, 0, result, 0);
    }

    /**
     * Append the elements of the first array from index i in the second one from
     * index j
     * <p>
     * Precondition: compatible sizes and indexes
     * 
     * @param giver       the source array
     * @param i           the index in the source array
     * @param accumulator the destination array
     * @param j           the index in the destination array
     */
    private static void append(int[] giver, int i, int[] accumulator, int j) {
        if (i < giver.length) {
            accumulator[j++] = giver[i++];
            append(giver, i, accumulator, j);
        }
    }

    /**
     * Internal tail-recursive implementation for merge
     * 
     * @param left   a source array
     * @param i      current position in the left source
     * @param right  another source array
     * @param j      current position in the right source
     * @param result the destination
     * @param k      the current position in the destination
     * @return the destination
     */
    private static int[] recursive(int[] left, int i, int[] right, int j, int[] result, int k) {
        if (i == 0 || j == 0) {
            append(left, i, result, k);
            append(right, j, result, k);
            return result;
        }

        if (left[i] < right[j]) {
            result[k] = left[i++];
        } else {
            result[k] = right[j++];
        }

        return recursive(left, i, right, j, result, k);
    }
    
    public static void main(String[] args) {
        int[] a = { -55, -44, -21, -8, 0, 12, 27, 51, 93, 115, 500 };
        int[] b = { -57, -40, -20, 1, 13, 28, 55, 99, 119, 200 };
        
        int[] c = iterative(a,b);
        int[] d = mymergeiterative(a,b);
        System.out.println("Merge prof");
        System.out.println(Arrays.toString(c));
        
        System.out.println("Merge mio");
        System.out.println(Arrays.toString(d));

   
    }
}
