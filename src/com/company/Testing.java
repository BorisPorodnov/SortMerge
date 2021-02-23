package com.company;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Testing {

    public String[] readFile(List<String> inputFiles) throws IOException {

        List<String> words = new ArrayList<>();
        for (String inputFile : inputFiles) {
            words.addAll(Files.readAllLines(Paths.get(inputFile)));
        }
        return words.toArray(new String[0]);
    }

    public void write(String[] sortArray) throws FileNotFoundException {

        PrintWriter out = new PrintWriter("outFile.txt");

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < sortArray.length; i++) {
            builder.append(sortArray[i]);
            builder.append("\n");
        }
        out.print(builder.toString());
        out.close();
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
