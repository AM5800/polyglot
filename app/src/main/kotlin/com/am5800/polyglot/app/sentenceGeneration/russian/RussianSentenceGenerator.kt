package com.am5800.polyglot.app.sentenceGeneration.russian

import com.am5800.polyglot.app.sentenceGeneration.*
import com.am5800.polyglot.app.sentenceGeneration.english.Pronoun


class RussianSentenceGenerator : SentenceGenerator {
  private class VisitResult(val str: String, val propagatedWord: Word?)

  private class GeneratorVisitor(private val words: List<Word>) : GeneratorRuleSetVisitor<VisitResult, Word?> {
    private val auxWill = RussianVerb.define {
      infinitive("быть")
      future("буду", "будешь", "будем", "будете", "будет", "будет", "будет", "будут")
    }

    override fun visitLeaf(leafNode: LeafRuleNode, data: Word?): VisitResult {
      val tag = leafNode.tag
      if (tag is PronounTag) {
        val pronoun = words.single { it is Pronoun } as Pronoun
        return VisitResult(pronoun.value, pronoun)
      }
      if (tag is VerbTag) {
        val verbFlags = tag.verbTagFlags
        if (verbFlags.contains(VerbTagFlag.Infinitive)) {
          val verb = words.single { it is RussianVerb } as RussianVerb
          return VisitResult(verb.infinitive, verb)
        }
        if (verbFlags.contains(VerbTagFlag.Present) && data is Pronoun) {
          val verb = words.single { it is RussianVerb } as RussianVerb
          return VisitResult(verbFormFromPronoun(verb, data, RussianTime.Present), verb)
        }
        if (verbFlags.contains(VerbTagFlag.Future) && data is Pronoun) {
          return VisitResult(verbFormFromPronoun(auxWill, data, RussianTime.Future), auxWill)
        }
        if (verbFlags.contains(VerbTagFlag.Past) && data is Pronoun) {
          val verb = words.single { it is RussianVerb } as RussianVerb
          return VisitResult(verbFormFromPronoun(verb, data, RussianTime.Past), verb)
        }
      }

      throw UnsupportedOperationException()
    }

    private fun verbFormFromPronoun(verb: RussianVerb, pronoun: Pronoun, time: RussianTime): String {
      return verb.by(pronoun.number, pronoun.person, time, pronoun.gender)
    }

    override fun visitBinaryNode(binaryNode: BinaryNode, data: Word?): VisitResult {
      val left = binaryNode.left
      val right = binaryNode.right

      var leftResult: String? = null
      var rightResult: String? = null

      val headResult =
              if (binaryNode.head == Head.IsLeft) {
                val result = left.visit(this, null)
                leftResult = result.str
                result
              } else {
                val result = right.visit(this, null)
                rightResult = result.str
                result
              }

      val propagated = data ?: headResult.propagatedWord

      if (binaryNode.head == Head.IsLeft) rightResult = right.visit(this, propagated).str
      else leftResult = left.visit(this, propagated).str

      val str = binaryNode.format.replace("%0", leftResult!!).replace("%1", rightResult!!)
      return VisitResult(str, headResult.propagatedWord)
    }
  }

  override fun generate(rules: GeneratorRuleSet, words: List<Word>): String {
    return rules.visit(GeneratorVisitor(words), null).str
  }
}