package com.liner.example.common;

import lombok.AllArgsConstructor;


public class DefaultTheme {

    @AllArgsConstructor
    enum Light {
        YELLOW("#ffff8d"), AQUA("#a5f2e9"), CORAL("#ffd5c8");
        private final String hex;

    }

    @AllArgsConstructor
    enum Pastel {
        YELLOW("#f6f0aa"), GREEN("#d3edd1"), PEACH("#f9d6c1");

        private final String hex;
    }
}
