package com.am5800.polyglot.app


import com.am5800.polyglot.app.sentenceGeneration.content.KnownWords
import com.am5800.polyglot.app.sentenceGeneration.content.Lesson1Grammars
import com.am5800.polyglot.app.sentenceGeneration.english.Pronoun
import com.am5800.polyglot.app.sentenceGeneration.russian.RussianSentenceGenerator
import com.am5800.polyglot.app.sentenceGeneration.russian.RussianVerb
import org.junit.Assert
import org.junit.Test


class RussianSentenceGeneratorTests {
  val generator = RussianSentenceGenerator()
  val words = KnownWords()
  val love = getVerb("любить")
  val me = getPronoun("я")
  val he = getPronoun("он")
  val grammars = Lesson1Grammars()

  fun getVerb(inf: String): RussianVerb {
    return words.words.map { it.russian as? RussianVerb }.filterNotNull().single { it.infinitive == inf }
  }

  fun getPronoun(value: String): Pronoun {
    return words.words.map { it.russian as? Pronoun }.filterNotNull().single { it.value == value }
  }

  @Test
  fun testPresentFirstPersonDeclaration() {
    val sentence = generator.generate(grammars.presentDeclaration.russian, listOf(me, love))
    Assert.assertEquals("я люблю", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonDeclaration() {
    val sentence = generator.generate(grammars.presentDeclaration.russian, listOf(he, love))
    Assert.assertEquals("он любит", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonQuestion() {
    val sentence = generator.generate(grammars.presentQuestion.russian, listOf(he, love))
    Assert.assertEquals("он любит?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonQuestion() {
    val sentence = generator.generate(grammars.presentQuestion.russian, listOf(me, love))
    Assert.assertEquals("я люблю?", sentence.toLowerCase())
  }

  @Test
  fun testPresentFirstPersonNegation() {
    val sentence = generator.generate(grammars.presentNegation.russian, listOf(me, love))
    Assert.assertEquals("я не люблю", sentence.toLowerCase())
  }

  @Test
  fun testPresentThirdPersonNegation() {
    val sentence = generator.generate(grammars.presentNegation.russian, listOf(he, love))
    Assert.assertEquals("он не любит", sentence.toLowerCase())
  }

  @Test
  fun testHeDoesNotEat() {
    val sentence = generator.generate(grammars.presentNegation.russian, listOf(he, getVerb("есть")))
    Assert.assertEquals("он не ест", sentence.toLowerCase())
  }

  @Test
  fun testFutureQuestion() {
    val sentence = generator.generate(grammars.futureQuestion.russian, listOf(he, love))
    Assert.assertEquals("он будет любить?", sentence.toLowerCase())
  }

  @Test
  fun testFutureDeclaration() {
    val sentence = generator.generate(grammars.futureDeclaration.russian, listOf(he, love))
    Assert.assertEquals("он будет любить", sentence.toLowerCase())
  }

  @Test
  fun testFutureNegation() {
    val sentence = generator.generate(grammars.futureNegation.russian, listOf(he, love))
    Assert.assertEquals("он не будет любить", sentence.toLowerCase())
  }

  @Test
  fun testPastQuestion() {
    val sentence = generator.generate(grammars.pastQuestion.russian, listOf(he, love))
    Assert.assertEquals("он любил?", sentence.toLowerCase())
  }

  @Test
  fun testPastDeclaration() {
    val sentence = generator.generate(grammars.pastDeclaration.russian, listOf(he, love))
    Assert.assertEquals("он любил", sentence.toLowerCase())
  }

  @Test
  fun testPastNegation() {
    val sentence = generator.generate(grammars.pastNegation.russian, listOf(he, love))
    Assert.assertEquals("он не любил", sentence.toLowerCase())
  }
}

