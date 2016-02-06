package com.am5800.polyglot.app.sentenceGeneration


class EnglishSentenceGenerator() {

  private class GeneratorVisitor(private val words: List<Word>) : GeneratorRuleSetVisitor<String> {
    private val auxDo = Verb("do", "did", "done", "doing", "does", Transitivity.Transitive)

    override fun visitLeaf(leafNode: GeneratorRuleSet.LeafRuleNode): String {
      if (leafNode.tag is AuxDoTag) return presentVerb(leafNode.tag, auxDo)
      else if (leafNode.tag is VerbTag) return findAndPresentVerb(leafNode.tag)
      else if (leafNode.tag is PronounTag) return findAndPresentPronoun(leafNode.tag)

      throw UnsupportedOperationException()
    }

    private fun findAndPresentPronoun(tag: PronounTag): String {
      val pronoun = words.single { it is Pronoun && tag.person.contains(it.person) } as Pronoun
      return pronoun.value
    }

    private fun presentVerb(tag: VerbTag, verb: Verb): String {
      val flags = tag.verbTagFlags
      if (flags.contains(VerbTagFlag.Present) && flags.contains(VerbTagFlag.ThirdPerson)) return verb.thirdPerson
      else if (flags.contains(VerbTagFlag.Past)) return verb.past

      return verb.infinitive
    }

    private fun findAndPresentVerb(tag: VerbTag): String {
      val verb = words.single { it is Verb } as Verb
      return presentVerb(tag, verb)
    }

    override fun visitBinaryNode(binaryNode: GeneratorRuleSet.BinaryNode): String {
      val left = binaryNode.left.visit(this)
      val right = binaryNode.right.visit(this)

      return binaryNode.format.replace("%0", left).replace("%1", right)
    }
  }

  fun generate(rules: GeneratorRuleSet, words: List<Word>): String {
    return rules.visit(GeneratorVisitor(words))
  }
}
