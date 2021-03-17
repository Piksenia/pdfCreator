package com.piksenia.thymeleaf.pdfCreator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Evaluation {
    BAD(1, "Heard about it, but...", "newbie"),
    OK(2, "Improvement needed, but I'm motivated!", "cowboy"),
    GOOD(3, "Already done some things with it, excited to learn more!", "sheriff"),
    EXCELLENT(4, "I find solutions for every problem, leave it to me!", "hero");

    private final int level;
    private final String levelDescription;
    private final String levelLabel;
}
