package com.am5800.polyglot.app


import com.am5800.polyglot.app.sentenceGeneration.*
import com.am5800.polyglot.app.sentenceGeneration.Number
import com.am5800.polyglot.app.sentenceGeneration.english.Pronoun
import com.am5800.polyglot.app.sentenceGeneration.russian.AuxWillTag
import com.am5800.polyglot.app.sentenceGeneration.russian.RussianSentenceGenerator
import com.am5800.polyglot.app.sentenceGeneration.russian.RussianVerb
import org.junit.Assert
import org.junit.Test


class RussianSentenceGeneratorTests {
  val generator = RussianSentenceGenerator()
  val love = RussianVerb.define {
    infinitive("любить")
    present("люблю", "любишь", "любим", "любите", "любит", "любит", "любит", "любят")
    past("любил", "любил", "любили", "любили", "любил", "любила", "любило", "любили")
  }
  val me = Pronoun("я", Person.First, Number.Singular, null)
  val he = Pronoun("он", Person.Third, Number.Singular, Gender.Masculine)

  val presentNegation = GeneratorRuleSet.create {
    rootRule("%0 не %1", PronounTag(), AuxDoTag(VerbTagFlag.Present), Head.IsLeft)
  }

  val presentDelcaration = GeneratorRuleSet.create {
    rootRule("%0 %1", PronounTag(), VerbTag(VerbTagFlag.Present), Head.IsLeft)
  }

  val presentQuestion = GeneratorRuleSet.create {
    rootRule("%0 %1?", PronounTag(), VerbTag(VerbTagFlag.Present), Head.IsLeft)
  }

  @Test
  fun testPresentFirstPersonDeclaration() {
    val sentence = generator.generate(presentDelcaration, listOf(me, love))
    Assert.assertEquals("я люблю", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonDeclaration() {
    val sentence = generator.generate(presentDelcaration, listOf(he, love))
    Assert.assertEquals("он любит", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonQuestion() {
    val sentence = generator.generate(presentQuestion, listOf(he, love))
    Assert.assertEquals("он любит?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonQuestion() {
    val sentence = generator.generate(presentQuestion, listOf(me, love))
    Assert.assertEquals("я люблю?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonNegation() {
    val sentence = generator.generate(presentNegation, listOf(me, love))
    Assert.assertEquals("я не люблю", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonNegation() {
    val sentence = generator.generate(presentNegation, listOf(he, love))
    Assert.assertEquals("он не любит", sentence.toLowerCase())
  }


  @Test
  fun testFutureQuestion() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 %1?", PronounTag(), "VP", Head.IsLeft)
      rule("VP", "%0 %1", AuxWillTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("он будет любить?", sentence.toLowerCase())
  }

  @Test
  fun testFutureDeclaration() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 %1", PronounTag(), "VP", Head.IsLeft)
      rule("VP", "%0 %1", AuxWillTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("он будет любить", sentence.toLowerCase())
  }

  @Test
  fun testFutureNegation() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 не %1", PronounTag(), "VP", Head.IsLeft)
      rule("VP", "%0 %1", AuxWillTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("он не будет любить", sentence.toLowerCase())
  }

  @Test
  fun testPastQuestion() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 %1?", PronounTag(), VerbTag(VerbTagFlag.Past), Head.IsLeft)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("он любил?", sentence.toLowerCase())
  }

  @Test
  fun testPastDeclaration() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 %1", PronounTag(), VerbTag(VerbTagFlag.Past), Head.IsLeft)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("он любил", sentence.toLowerCase())
  }

  @Test
  fun testPastNegation() {
    val rules = GeneratorRuleSet.create {
      rootRule("%0 не %1", PronounTag(), VerbTag(VerbTagFlag.Past), Head.IsLeft)
    }
    val sentence = generator.generate(rules, listOf(he, love))
    Assert.assertEquals("он не любил", sentence.toLowerCase())
  }
}

