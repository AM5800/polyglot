package com.am5800.polyglot.app.sentenceGeneration


class SimpleEnglishSentenceGenerator() {
  private val auxDo = Verb("do", "did", "done", "doing", "does")

  fun generate(rule: GeneratorSimpleRule, words: List<Word>): String {

    val result = StringBuilder()

    val pronoun = words.single { it is Pronoun } as Pronoun


    for (tag in rule.tags) {
      if (tag is LiteralTag) {
        result.append(tag.value)
      } else if (tag is PronounTag) {
        result.append(pronoun.value)
      } else if (tag is VerbTag) {
        val verb =
                if (tag is AuxDoTag) auxDo
                else words.single { it is Verb } as Verb
        if (tag.verbTagFlag == VerbTagFlag.Present) {
          result.append(fromPronoun(verb, pronoun))
        } else {
          result.append(fromFlag(verb, tag.verbTagFlag))
        }
      }
    }

    return result.toString()
  }

  private fun fromFlag(verb: Verb, verbTagFlag: VerbTagFlag): String {
    return when (verbTagFlag) {
      VerbTagFlag.Past -> verb.past
      else -> verb.infinitive
    }
  }

  private fun fromPronoun(verb: Verb, pronoun: Pronoun): String {
    return when (pronoun.value) {
      "he", "she", "it" -> verb.thirdPerson
      else -> verb.infinitive
    }
  }
}
