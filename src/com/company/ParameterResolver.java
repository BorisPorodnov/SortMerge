package com.company;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParameterResolver {

    private final String[] parameters;

    public ParameterResolver(String[] args) {
        parameters = args;
    }

    public String getDataType() {
        List<String> flags = Arrays.stream(parameters).filter(it -> it.charAt(0) == '-')
            .collect(Collectors.toList());

        return flags.get(0);
    }

    public List<String> getInputFiles() {
        return Arrays.stream(parameters).filter(it -> it.charAt(0) != '-')
            .skip(1)
            .collect(Collectors.toList());
    }
}
