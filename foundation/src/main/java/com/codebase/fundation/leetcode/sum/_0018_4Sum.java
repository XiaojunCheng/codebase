package com.codebase.fundation.leetcode.sum;

import java.util.*;

public class _0018_4Sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> solutions = new ArrayList<>();
        if (nums.length <= 3) {
            return solutions;
        }

        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            numList.add(nums[i]);
        }
        Collections.sort(numList);

        Map<Integer/*value*/, Integer> valueCount = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            int value = numList.get(index);
            if (!valueCount.containsKey(value)) {
                valueCount.put(value, 0);
            }
            valueCount.put(value, valueCount.get(value) + 1);
        }

        Map<Integer/*sum*/, Set<Integer/*one*/>> sumIndex = new HashMap<>();
        for (int indexI = 0; indexI < nums.length - 1; indexI++) {
            for (int indexJ = indexI + 1; indexJ < nums.length; indexJ++) {
                int one = numList.get(indexI);
                int two = numList.get(indexJ);
                int sum = one + two;
                if (!sumIndex.containsKey(sum)) {
                    sumIndex.put(sum, new HashSet<Integer>());
                }
                sumIndex.get(sum).add(one);
            }
        }

        Set<Integer> sumSet = sumIndex.keySet();
        List<Integer> sumList = new ArrayList<>();
        sumList.addAll(sumSet);
        Collections.sort(sumList);
        Set<Integer> remainSumSet = new HashSet<>();
        remainSumSet.addAll(sumSet);

        for (int i = 0; i < sumList.size(); i++) {
            int leftSum = sumList.get(i);
            int rightSum = target - leftSum;
            if (!remainSumSet.contains(rightSum)) {
                remainSumSet.remove(leftSum);
                continue;
            }

            remainSumSet.remove(rightSum);
            if (leftSum == rightSum) {
                Set<Integer> candidateSet = sumIndex.get(leftSum);
                List<Integer> candidates = new ArrayList<>();
                candidates.addAll(candidateSet);
                Collections.sort(candidates);
                for (int i = 0; i < candidates.size(); i++) {
                    for (int j = i; j < candidates.size(); j++) {
                        int one = candidates.get(i);
                        int two = leftSum - one;
                        int three = candidates.get(j);
                        int four = leftSum - three;
                        if (two > three) {
                            continue;
                        }

                        int oneCount = 1 + (two == one ? 1 : 0) + (three == one ? 1 : 0) + (four == one ? 1 : 0);
                        int twoCount = 1 + (three == two ? 1 : 0) + (four == two ? 1 : 0);
                        int threeCount = 1 + (four == three ? 1 : 0);

                        if (valueCount.get(one) < oneCount || valueCount.get(two) < twoCount || valueCount.get(three) < threeCount) {
                            continue;
                        }

                        List<Integer> solution = new ArrayList<>();
                        solution.add(one);
                        solution.add(two);
                        solution.add(three);
                        solution.add(four);
                        solutions.add(solution);
                    }
                }
                remainSumSet.remove(leftSum);
                remainSumSet.remove(rightSum);
                continue;
            }


        }
        //

        for (int leftSum : sumList) {
            int sumTwo = target - leftSum;
            if (!remainSumSet.contains(sumTwo)) {
                continue;
            }

            remainSumSet.remove(leftSum);
            remainSumSet.remove(sumTwo);


            Set<Integer> onesInSumOne = sumIndex.get(leftSum);
            List<Integer> oneList = new ArrayList<>();
            oneList.addAll(onesInSumOne);
            Collections.sort(oneList);
            Set<Integer> threesInSumTwo = sumIndex.get(sumTwo);
            for (int one : oneList) {
                for (int three : threesInSumTwo) {
                    int two = leftSum - one;
                    int four = sumTwo - three;
                    if (two > three) {
                        continue;
                    }

                    int oneCount = 1 + (two == one ? 1 : 0) + (three == one ? 1 : 0) + (four == one ? 1 : 0);
                    int twoCount = 1 + (three == two ? 1 : 0) + (four == two ? 1 : 0);
                    int threeCount = 1 + (four == three ? 1 : 0);

                    if (valueCount.get(one) < oneCount || valueCount.get(two) < twoCount || valueCount.get(three) < threeCount) {
                        continue;
                    }

                    List<Integer> solution = new ArrayList<>();
                    solution.add(one);
                    solution.add(two);
                    solution.add(three);
                    solution.add(four);
                    solutions.add(solution);
                }
            }
        }

        return solutions;
    }

    public static void main(String[] args) {
        _0018_4Sum main = new _0018_4Sum();
        int[][] data = {
                {1, 0, -1, 0, -2, 2},//
                {-1, 0, 1, 2, -1, -4},//
                {0, 4, -5, 2, -2, 4, 2, -1, 4}//
        };
        int[] targets = {
                0, //
                -1,//
                12//
        };
        for (int i = 0; i < data.length; i++) {
            System.out.println("============");
            List<List<Integer>> solutions = main.fourSum(data[i], targets[i]);
            for (List<Integer> s : solutions) {
                System.out.println(s);
            }
        }

    }
}
