package com.am5800.polyglot.app

import com.am5800.polyglot.app.sentenceGeneration.*
import com.am5800.polyglot.app.sentenceGeneration.Number
import com.am5800.polyglot.app.sentenceGeneration.english.EnglishSentenceGenerator
import com.am5800.polyglot.app.sentenceGeneration.english.EnglishVerb
import com.am5800.polyglot.app.sentenceGeneration.english.Pronoun
import org.junit.Assert
import org.junit.Test

class EnglishSentenceGeneratorTests {
  val generator = EnglishSentenceGenerator()
  val love = EnglishVerb("love", "loved", "loved", "loving", "loves", Transitivity.Transitive)
  val me = Pronoun("I", Person.First, Number.Singular, null)
  val he = Pronoun("he", Person.Third, Number.Singular, Gender.Masculine)

  val presentNegation = GeneratorRuleSet.create {
    rootRule("%0 %1", PronounTag(), "VP", Head.IsLeft)
    rule("VP", "%0 not %1", AuxDoTag(VerbTagFlag.Present), VerbTag(), Head.IsRight)
  }

  val presentDelcaration = GeneratorRuleSet.create {
    rootRule("%0 %1", PronounTag(), VerbTag(VerbTagFlag.Present), Head.IsLeft)
  }

  val presentQuestion = GeneratorRuleSet.create {
    rootRule("%0 %1?", AuxDoTag(VerbTagFlag.Present), "VP", Head.IsRight)
    rule("VP", "%0 %1", PronounTag(), VerbTag(), Head.IsLeft)
  }

  @Test
  fun testPresentFirstPersonDeclaration() {
    val sentence = generator.generate(presentDelcaration, listOf(me, love))
    Assert.assertEquals("i love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonDeclaration() {
    val sentence = generator.generate(presentDelcaration, listOf(he, love))
    Assert.assertEquals("he loves", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonQuestion() {
    val sentence = generator.generate(presentQuestion, listOf(he, love))
    Assert.assertEquals("does he love?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonQuestion() {
    val sentence = generator.generate(presentQuestion, listOf(me, love))
    Assert.assertEquals("do i love?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonNegation() {
    val sentence = generator.generate(presentNegation, listOf(me, love))
    Assert.assertEquals("i do not love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonNegation() {
    val sentence = generator.generate(presentNegation, listOf(he, love))
    Assert.assertEquals("he does not love", sentence.toLowerCase())
  }


  @Test
  fun testFutureQuestion() {
    val rules = GeneratorRuleSet.create {
      rootRule("Will %0 %1?", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("will he love?", sentence.toLowerCase())
  }

  @Test
  fun testFutureDeclaration() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 will %1", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he will love", sentence.toLowerCase())
  }

  @Test
  fun testFutureNegation() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 will not %1", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he will not love", sentence.toLowerCase())
  }

  @Test
  fun testPastQuestion() {
    val rules = GeneratorRuleSet.create {
      rootRule("Did %0 %1?", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("did he love?", sentence.toLowerCase())
  }

  @Test
  fun testPastDeclaration() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 %1", PronounTag(), VerbTag(VerbTagFlag.Past), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he loved", sentence.toLowerCase())
  }

  @Test
  fun testPastNegation() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 did not %1", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he did not love", sentence.toLowerCase())
  }
}