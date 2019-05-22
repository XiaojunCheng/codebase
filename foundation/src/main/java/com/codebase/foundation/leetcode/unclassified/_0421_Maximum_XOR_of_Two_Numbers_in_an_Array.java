package com.codebase.foundation.leetcode.unclassified;

import java.util.*;

public class _0421_Maximum_XOR_of_Two_Numbers_in_an_Array {

    public int findMaximumXOR(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        if (nums.length == 2) {
            return nums[0] ^ nums[1];
        }

        int xorValue = 0;
        for (int i = 0; i < nums.length; i++) {//O(n)
            xorValue |= nums[i];
        }

        boolean[] flag = new boolean[32];
        int length = 0;
        for (int value = xorValue; value > 0; ) {
            flag[length] = value % 2 == 1 ? true : false;
            value >>= 1;
            length++;
        }

        int maxXorValue = 0;
        int mask = 0;
        for (int byteIndex = length - 1; byteIndex >= 0; byteIndex--) {//O(n)
            if (flag[byteIndex] == false) {
                continue;
            }

            int powerValue = 1 << byteIndex;
            mask = mask | powerValue;

            Set<Integer> candidates = new HashSet<>();
            for (int i = 0; i < nums.length; i++) {//O(n)
                candidates.add(nums[i] & mask);
            }

            int localMaxXorValue = maxXorValue | powerValue;
            for (int candidate : candidates) {
                if (candidates.contains(localMaxXorValue ^ candidate)) {
                    maxXorValue = localMaxXorValue;
                    break;
                }
            }
        }

        return maxXorValue;
    }

    public static void main(String[] args) {
        _0421_Maximum_XOR_of_Two_Numbers_in_an_Array main = new _0421_Maximum_XOR_of_Two_Numbers_in_an_Array();
        System.out.println(main.findMaximumXOR(new int[]{5}));//5
        System.out.println(main.findMaximumXOR(new int[]{1, 1}));//0
        System.out.println(main.findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));//28
        System.out.println(main.findMaximumXOR(new int[]{96401, 56140, 27138, 31551,//
                46701, 7610, 35232, 57981, 5146, 21516, 7365, 86253, 6055, 13283, 47873, 14977,//
                20021, 73153, 98685, 60032, 2031, 66300, 20805, 62505, 88328, 92910, 5270, 54199,//
                10064, 91081, 52580, 22752, 54162, 95303, 38216, 21528, 81901, 52447, 94246, 87859,//
                77453, 64212, 97335, 92424, 89157, 65159, 59415, 80273, 23596, 79981, 79948, 16270,//
                88113, 23740, 67217, 7849, 51484, 78558, 62655, 68660, 29570, 56584, 89303, 17230,//
                31204, 14949, 21326, 25381, 82757, 36619, 56053, 60822, 47814, 91855, 18724, 91184,//
                35264, 6228, 21251, 50646, 70525, 50174, 15637, 69731, 39668, 40293, 69953, 16039,//
                25695, 15525, 98142, 91504, 23898, 23186, 90522, 38020, 60059, 66121, 98887, 3914,//
                2376, 88706, 11235, 54633, 61049, 66690, 59970, 62440, 92043, 78156, 47194, 59573,//
                90558, 29047, 7659, 43873, 37228, 65787, 49033, 15656, 49991, 6117, 43943, 5678,//
                4909, 9034, 93634, 23077, 31114, 30872, 49511, 6348, 5419, 79528, 69794, 66993, 33434,//
                86288, 66756, 566, 24187, 42325, 48815, 80849, 59778, 87773, 42690, 18734, 68078,//
                94754, 27900, 84656, 63814, 80803, 63278, 88180, 50959, 19317, 38210, 12470, 55399,//
                55126, 97730, 53965, 57281, 51010, 64870, 16273, 47997, 61182, 93630, 95325, 57347,//
                61746, 93336, 88271, 7324, 91124, 10222, 87021, 3534, 44543, 88079, 76130, 16658,//
                12099, 44424, 43109, 11219, 22198, 70703, 74394, 90628, 84871, 70348, 73298, 5118,//
                28051, 82789, 91278}));//131036
    }
}
