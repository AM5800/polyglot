package com.am5800.polyglot.app.sentenceGeneration.english

import com.am5800.polyglot.app.sentenceGeneration.*
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Person
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Transitivity


class EnglishSentenceGenerator : SentenceGenerator {
  private class VisitResult(val str: String, val propagatedWord: Word?)

  private class GeneratorVisitor(private val words: List<Word>) : GeneratorRuleSetVisitor<VisitResult, Word?> {
    private val auxDo = EnglishVerb("do", "did", "done", "doing", "does", Transitivity.Transitive)

    override fun visitLeaf(leafNode: LeafRuleNode, data: Word?): VisitResult {
      val tag = leafNode.tag
      if (tag is PronounTag) {
        val pronoun = words.single { it is Pronoun } as Pronoun
        return VisitResult(pronoun.value, pronoun)
      }
      if (tag is VerbTag) {
        val verbFlags = tag.verbTagFlags
        val verb = if (tag is AuxDoTag) auxDo else words.single { it is EnglishVerb } as EnglishVerb
        if (verbFlags.contains(VerbTagFlag.Infinitive)) {

          return VisitResult(verb.infinitive, verb)
        }
        if (verbFlags.contains(VerbTagFlag.Present) && data is Pronoun) {
          val form = if (data.person == Person.Third) verb.thirdPerson else verb.infinitive

          return VisitResult(form, verb)
        }
        if (verbFlags.contains(VerbTagFlag.Past)) {
          return VisitResult(verb.past, verb)
        }
      }

      throw UnsupportedOperationException()
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
