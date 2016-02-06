package com.am5800.polyglot.app

import com.am5800.polyglot.app.sentenceGeneration.*
import org.junit.Assert
import org.junit.Test


class SimpleEnglishSentenceGeneratorTests {
  val generator = EnglishSentenceGenerator()
  val love = Verb("love", "loved", "loved", "loving", "loves", Transitivity.Transitive)
  val me = Pronoun("I", Person.First)
  val he = Pronoun("he", Person.Third)

  @Test
  fun testPresentFirstPersonDeclaration() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 %1", FirstOrSecondPersonPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(me, love))
    Assert.assertEquals("i love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonDeclaration() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 %1", ThirdPersonPronounTag(), VerbTag(VerbTagFlag.Present, VerbTagFlag.ThirdPerson))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he loves", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonQuestion() {
    val rules = GeneratorRuleSet.create {
      rootRule("does %0 %1?", ThirdPersonPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("does he love?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonQuestion() {
    val rules = GeneratorRuleSet.create {
      rootRule("do %0 %1?", FirstOrSecondPersonPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(me, love))
    Assert.assertEquals("do i love?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonNegation() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 do not %1", FirstOrSecondPersonPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(me, love))
    Assert.assertEquals("i do not love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonNegation() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 does not %1", ThirdPersonPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he does not love", sentence.toLowerCase())
  }


  @Test
  fun testFutureQuestion() {
    val rules = GeneratorRuleSet.create {
      rootRule("Will %0 %1?", AnyPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("will he love?", sentence.toLowerCase())
  }

  @Test
  fun testFutureDeclaration() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 will %1", AnyPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he will love", sentence.toLowerCase())
  }

  @Test
  fun testFutureNegation() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 will not %1", AnyPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he will not love", sentence.toLowerCase())
  }

  @Test
  fun testPastQuestion() {
    val rules = GeneratorRuleSet.create {
      rootRule("Did %0 %1?", AnyPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("did he love?", sentence.toLowerCase())
  }

  @Test
  fun testPastDeclaration() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 %1", AnyPronounTag(), VerbTag(VerbTagFlag.Past))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he loved", sentence.toLowerCase())
  }

  @Test
  fun testPastNegation() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 did not %1", AnyPronounTag(), VerbTag(VerbTagFlag.Infinitive))
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("he did not love", sentence.toLowerCase())
  }
}