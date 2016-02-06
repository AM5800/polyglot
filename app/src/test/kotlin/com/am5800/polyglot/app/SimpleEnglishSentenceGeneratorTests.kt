package com.am5800.polyglot.app

import com.am5800.polyglot.app.sentenceGeneration.*
import org.junit.Assert
import org.junit.Test


class SimpleEnglishSentenceGeneratorTests {
  val generator = SimpleEnglishSentenceGenerator()
  val love = Verb("love", "loved", "loved", "loving", "loves")
  val me = Pronoun("I")
  val he = Pronoun("he")

  val presentDeclaration = GeneratorSimpleRule(PronounTag(), SpaceTag(), VerbTag(VerbTagFlag.Present))
  val presentQuestion = GeneratorSimpleRule(
          AuxDoTag(VerbTagFlag.Present),
          SpaceTag(),
          PronounTag(),
          SpaceTag(),
          VerbTag(),
          LiteralTag("?"))

  val presentNegation = GeneratorSimpleRule(
          PronounTag(),
          SpaceTag(),
          AuxDoTag(VerbTagFlag.Present),
          LiteralTag(" not "),
          VerbTag())

  @Test
  fun testPresentFirstPersonDeclaration() {
    val sentence = generator.generate(presentDeclaration, listOf(me, love))
    Assert.assertEquals("i love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonDeclaration() {
    val sentence = generator.generate(presentDeclaration, listOf(he, love))
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
    val rule = GeneratorSimpleRule(LiteralTag("will "), PronounTag(), SpaceTag(), VerbTag(), LiteralTag("?"))
    val sentence = generator.generate(rule, listOf(he, love))
    Assert.assertEquals("will he love?", sentence.toLowerCase())
  }

  @Test
  fun testFutureDeclaration() {
    val rule = GeneratorSimpleRule(PronounTag(), LiteralTag(" will "), VerbTag())
    val sentence = generator.generate(rule, listOf(he, love))
    Assert.assertEquals("he will love", sentence.toLowerCase())
  }

  @Test
  fun testFutureNegation() {
    val rule = GeneratorSimpleRule(PronounTag(), LiteralTag(" will not "), VerbTag())
    val sentence = generator.generate(rule, listOf(he, love))
    Assert.assertEquals("he will not love", sentence.toLowerCase())
  }

  @Test
  fun testPastQuestion() {
    val rule = GeneratorSimpleRule(LiteralTag("Did"), SpaceTag(), PronounTag(), SpaceTag(), VerbTag(), LiteralTag("?"))
    val sentence = generator.generate(rule, listOf(he, love))
    Assert.assertEquals("did he love?", sentence.toLowerCase())
  }

  @Test
  fun testPastDeclaration() {
    val rule = GeneratorSimpleRule(PronounTag(), SpaceTag(), VerbTag(VerbTagFlag.Past))
    val sentence = generator.generate(rule, listOf(he, love))
    Assert.assertEquals("he loved", sentence.toLowerCase())
  }

  @Test
  fun testPastNegation() {
    val rule = GeneratorSimpleRule(PronounTag(), LiteralTag(" did not "), VerbTag())
    val sentence = generator.generate(rule, listOf(he, love))
    Assert.assertEquals("he did not love", sentence.toLowerCase())
  }
}