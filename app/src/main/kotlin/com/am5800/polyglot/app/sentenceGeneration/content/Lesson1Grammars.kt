package com.am5800.polyglot.app.sentenceGeneration.content

import com.am5800.polyglot.app.sentenceGeneration.*
import com.am5800.polyglot.app.sentenceGeneration.russian.AuxWillTag

data class LessonGrammar(val russian: GeneratorRuleSet, val english: GeneratorRuleSet)

class Lesson1Grammars {
  val presentNegation: LessonGrammar
    get() = _presentNegation

  private val _presentNegation = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 не %1", PronounTag(), AuxDoTag(VerbTagFlag.Present), Head.IsLeft)
          },
          GeneratorRuleSet.create {
            rootRule("%0 %1", PronounTag(), "VP", Head.IsLeft)
            rule("VP", "%0 not %1", AuxDoTag(VerbTagFlag.Present), VerbTag(), Head.IsRight)
          })

  val presentDeclaration: LessonGrammar
    get() = _presentDeclaration

  private val _presentDeclaration = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 %1", PronounTag(), VerbTag(VerbTagFlag.Present), Head.IsLeft)
          },
          GeneratorRuleSet.create {
            rootRule("%0 %1", PronounTag(), VerbTag(VerbTagFlag.Present), Head.IsLeft)
          }
  )

  val presentQuestion: LessonGrammar
    get() = _presentQuestion

  private val _presentQuestion = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 %1?", PronounTag(), VerbTag(VerbTagFlag.Present), Head.IsLeft)
          },
          GeneratorRuleSet.create {
            rootRule("%0 %1?", AuxDoTag(VerbTagFlag.Present), "VP", Head.IsRight)
            rule("VP", "%0 %1", PronounTag(), VerbTag(), Head.IsLeft)
          }
  )

  //
  val futureDeclaration: LessonGrammar
    get() = _futureDeclaration

  private val _futureDeclaration = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 %1", PronounTag(), "VP", Head.IsLeft)
            rule("VP", "%0 %1", AuxWillTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
          },
          GeneratorRuleSet.create {
            rootRule("%0 will %1", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
          }
  )

  val futureQuestion: LessonGrammar
    get() = _futureQuestion

  private val _futureQuestion = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 %1?", PronounTag(), "VP", Head.IsLeft)
            rule("VP", "%0 %1", AuxWillTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
          },
          GeneratorRuleSet.create {
            rootRule("Will %0 %1?", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
          }
  )

  val futureNegation: LessonGrammar
    get() = _futureNegation

  private val _futureNegation = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 не %1", PronounTag(), "VP", Head.IsLeft)
            rule("VP", "%0 %1", AuxWillTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
          },
          GeneratorRuleSet.create {
            rootRule("%0 will not %1", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
          }
  )

  val pastDeclaration: LessonGrammar
    get() = _pastDeclaration

  private val _pastDeclaration = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 %1", PronounTag(), VerbTag(VerbTagFlag.Past), Head.IsLeft)
          },
          GeneratorRuleSet.create {
            rootRule("%0 %1", PronounTag(), VerbTag(VerbTagFlag.Past), Head.IsRight)
          }
  )

  val pastNegation: LessonGrammar
    get() = _pastNegation

  private val _pastNegation = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 не %1", PronounTag(), VerbTag(VerbTagFlag.Past), Head.IsLeft)
          },
          GeneratorRuleSet.create {
            rootRule("%0 did not %1", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
          }
  )

  val pastQuestion: LessonGrammar
    get() = _pastQuestion

  private val _pastQuestion = LessonGrammar(
          GeneratorRuleSet.create {
            rootRule("%0 %1?", PronounTag(), VerbTag(VerbTagFlag.Past), Head.IsLeft)
          },
          GeneratorRuleSet.create {
            rootRule("Did %0 %1?", PronounTag(), VerbTag(VerbTagFlag.Infinitive), Head.IsRight)
          }
  )


}