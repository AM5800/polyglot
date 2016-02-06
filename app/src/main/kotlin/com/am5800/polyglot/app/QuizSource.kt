package com.am5800.polyglot.app

import com.am5800.polyglot.app.sentenceGeneration.content.LessonGrammar
import com.am5800.polyglot.app.sentenceGeneration.content.WordPair
import com.am5800.polyglot.app.sentenceGeneration.english.EnglishSentenceGenerator
import com.am5800.polyglot.app.sentenceGeneration.english.Pronoun
import com.am5800.polyglot.app.sentenceGeneration.russian.RussianSentenceGenerator
import com.am5800.polyglot.app.sentenceGeneration.russian.RussianVerb
import java.util.*

class Quiz(val question: String, val answer: String)

class QuizSource(private val words: List<WordPair>, private val grammars: List<LessonGrammar>) {

  private val random = Random()
  private val pronouns = words.filter { it.russian is Pronoun }.toList()
  private val verbs = words.filter { it.russian is RussianVerb }.toList()
  private val russianGenerator = RussianSentenceGenerator()
  private val englishGenerator = EnglishSentenceGenerator()

  fun next(): Quiz {
    val grammar = grammars[random.nextInt(grammars.size)]
    val pronoun = pronouns[random.nextInt(pronouns.size)]
    val verb = verbs[random.nextInt(verbs.size)]

    val russian = russianGenerator.generate(grammar.russian, listOf(pronoun.russian, verb.russian))
    val english = englishGenerator.generate(grammar.english, listOf(pronoun.english, verb.english))

    return Quiz(russian, english)
  }
}