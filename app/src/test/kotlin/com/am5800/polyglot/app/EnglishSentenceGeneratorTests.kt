package com.am5800.polyglot.app

import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Gender
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Number
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Person
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Transitivity
import com.am5800.polyglot.app.sentenceGeneration.content.Lesson1Grammars
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
  val grammars = Lesson1Grammars()

  @Test
  fun testPresentFirstPersonDeclaration() {
    val sentence = generator.generate(grammars.presentDeclaration.english, listOf(me, love))
    Assert.assertEquals("i love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonDeclaration() {
    val sentence = generator.generate(grammars.presentDeclaration.english, listOf(he, love))
    Assert.assertEquals("he loves", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonQuestion() {
    val sentence = generator.generate(grammars.presentQuestion.english, listOf(he, love))
    Assert.assertEquals("does he love?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonQuestion() {
    val sentence = generator.generate(grammars.presentQuestion.english, listOf(me, love))
    Assert.assertEquals("do i love?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonNegation() {
    val sentence = generator.generate(grammars.presentNegation.english, listOf(me, love))
    Assert.assertEquals("i do not love", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonNegation() {
    val sentence = generator.generate(grammars.presentNegation.english, listOf(he, love))
    Assert.assertEquals("he does not love", sentence.toLowerCase())
  }


  @Test
  fun testFutureQuestion() {
    val sentence = generator.generate(grammars.futureQuestion.english, listOf(he, love))
    Assert.assertEquals("will he love?", sentence.toLowerCase())
  }

  @Test
  fun testFutureDeclaration() {
    val sentence = generator.generate(grammars.futureDeclaration.english, listOf(he, love))
    Assert.assertEquals("he will love", sentence.toLowerCase())
  }

  @Test
  fun testFutureNegation() {
    val sentence = generator.generate(grammars.futureNegation.english, listOf(he, love))
    Assert.assertEquals("he will not love", sentence.toLowerCase())
  }

  @Test
  fun testPastQuestion() {
    val sentence = generator.generate(grammars.pastQuestion.english, listOf(he, love))
    Assert.assertEquals("did he love?", sentence.toLowerCase())
  }

  @Test
  fun testPastDeclaration() {
    val sentence = generator.generate(grammars.pastDeclaration.english, listOf(he, love))
    Assert.assertEquals("he loved", sentence.toLowerCase())
  }

  @Test
  fun testPastNegation() {
    val sentence = generator.generate(grammars.pastNegation.english, listOf(he, love))
    Assert.assertEquals("he did not love", sentence.toLowerCase())
  }
}