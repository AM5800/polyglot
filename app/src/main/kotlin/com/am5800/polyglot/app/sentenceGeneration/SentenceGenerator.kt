package com.am5800.polyglot.app.sentenceGeneration


interface SentenceGenerator {
  fun generate(rules: GeneratorRuleSet, words: List<Word>): String
}