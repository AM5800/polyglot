package com.am5800.polyglot.app

import com.am5800.polyglot.app.sentenceGeneration.*
import org.junit.Assert
import org.junit.Test
import java.util.*


class SimpleEnglishSentenceGeneratorTests {
  val generator = EnglishSentenceGenerator()
  val love = Verb("love", "loved", "loved", "loving", "loves", Transitivity.Intransitive)
  val me = Pronoun("I", Person.First)
  val he = Pronoun("he", Person.Third)

  fun presentDeclaration(thirdPerson: Boolean): GeneratorRuleSet {
    val verbFlags =
            if (thirdPerson) EnumSet.of(VerbTagFlag.Present, VerbTagFlag.ThirdPerson)
            else EnumSet.of(VerbTagFlag.Present)

    val pronounTag =
            if (thirdPerson) ThirdPersonPronounTag()
            else FirstOrSecondPersonPronounTag()

    return GeneratorRuleSet.create {
      rootRule("%0 %1", pronounTag, VerbTag(verbFlags))
    }
  }


  fun presentQuestion(thirdPerson: Boolean): GeneratorRuleSet {
    val verbFlags =
            if (thirdPerson) EnumSet.of(VerbTagFlag.Present, VerbTagFlag.ThirdPerson)
            else EnumSet.of(VerbTagFlag.Present)

    val pronounTag =
            if (thirdPerson) ThirdPersonPronounTag()
            else FirstOrSecondPersonPronounTag()

    return GeneratorRuleSet.create {
      rootRule("%0 %1?", AuxDoTag(verbFlags), "VP")
      rule("VP", "%0 %1", pronounTag, VerbTag(VerbTagFlag.Infinitive))
    }
  }

  fun presentNegation(thirdPerson: Boolean): GeneratorRuleSet {
    val verbFlags =
            if (thirdPerson) EnumSet.of(VerbTagFlag.Present, VerbTagFlag.ThirdPerson)
            else EnumSet.of(VerbTagFlag.Present)

    val pronounTag =
            if (thirdPerson) ThirdPersonPronounTag()
            else FirstOrSecondPersonPronounTag()

    return GeneratorRuleSet.create {
      rootRule("%0 %1", pronounTag, "VP")
      rule("VP", "%0 not %1", AuxDoTag(verbFlags), VerbTag(VerbTagFlag.Infinitive))
    }
  }

  @Test
  fun testPresentFirstPersonDeclaration() {
    val sentence = generator.generate(presentDeclaration(false), listOf(me, love))
    Assert.assertEquals("i love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonDeclaration() {
    val sentence = generator.generate(presentDeclaration(true), listOf(he, love))
    Assert.assertEquals("he loves", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonQuestion() {
    val sentence = generator.generate(presentQuestion(true), listOf(he, love))
    Assert.assertEquals("does he love?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonQuestion() {
    val sentence = generator.generate(presentQuestion(false), listOf(me, love))
    Assert.assertEquals("do i love?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonNegation() {
    val sentence = generator.generate(presentNegation(false), listOf(me, love))
    Assert.assertEquals("i do not love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonNegation() {
    val sentence = generator.generate(presentNegation(true), listOf(he, love))
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