package com.monsen.cards_against_christmas_backend.util;

import java.util.Random;

public class GameIdGenerator {

    private final Random random;

    private final int lenght = 5;
    private final int leftLim = 65;
    private final int rightLim = 90;

    public GameIdGenerator() {
        this.random = new Random();
    }

    public String generateId() {
        return random.ints(leftLim, rightLim)
                .limit(lenght)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
