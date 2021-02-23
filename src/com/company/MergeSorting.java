package com.company;

public class MergeSorting {

    public int[] sort(int[] mass) {

        // проверяем не нулевой ли он?
        if (mass == null) {
            System.out.print("Массив пуст");
            return null;
        }
        // проверяем не 1 ли элемент в массиве?
        if (mass.length < 2) {
            return mass;
        }
        int[] leftArray = new int[mass.length / 2];
        int[] rightArray = new int[mass.length - mass.length / 2];
        System.arraycopy
            (mass, 0, leftArray, 0, mass.length / 2);
        System.arraycopy
            (mass, mass.length / 2, rightArray, 0, mass.length - (mass.length / 2));

        return merge(sort(leftArray), sort(rightArray));
    }

    public String[] sort(String[] array) {

        // проверяем не нулевой ли он?
        if (array == null) {
            System.out.print("Массив пуст");
            return null;
        }
        // проверяем не 1 ли элемент в массиве?
        if (array.length < 2) {
            return array;
        }
        String[] leftArray = new String[array.length / 2];
        String[] rightArray = new String[array.length - array.length / 2];
        System.arraycopy
            (array, 0, leftArray, 0, array.length / 2);
        System.arraycopy
            (array, array.length / 2, rightArray, 0, array.length - (array.length / 2));

        return merge(sort(leftArray), sort(rightArray));
    }

    private int[] merge(int[] leftArray, int[] rightArray) {

        int[] res = new int[leftArray.length + rightArray.length];

        int n = leftArray.length;
        int m = rightArray.length;

        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (leftArray[i] <= rightArray[j]) {
                res[k] = leftArray[i];
                i++;
            } else {
                res[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n) {
            res[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < m) {
            res[k] = rightArray[j];
            j++;
            k++;
        }
        return res;
    }

    public String[] merge(String[] leftArray, String[] rightArray) {

        String[] res = new String[leftArray.length + rightArray.length];

        int n = leftArray.length;
        int m = rightArray.length;

        int i = 0, j = 0, k = 0;

        while (i < n && j < m) {
            if (leftArray[i].compareTo(rightArray[j]) <= 0) {
                res[k] = leftArray[i];
                i++;
            } else {
                res[k] = rightArray[j];
                j++;
            }
            k++;
        }
        while (i < n) {
            res[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < m) {
            res[k] = rightArray[j];
            j++;
            k++;
        }
        return res;
    }


}
