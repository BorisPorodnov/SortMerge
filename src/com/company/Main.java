package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> flags = Arrays.stream(args).filter(it -> it.charAt(0) == '-')
            .collect(Collectors.toList());

        List<String> files = Arrays.stream(args).filter(it -> it.charAt(0) != '-')
            .collect(Collectors.toList());

        String outFile = files.get(0);

        List<String> inputFiles = new ArrayList<>();

        for (int i = 1; i < files.size(); i++) {
            inputFiles.add(files.get(i));
        }
        Test test = new Test();
        int[] readText = test.read(inputFiles);
        int[] sortMasRes = test.sort(readText);
        test.write(sortMasRes);
    }
}