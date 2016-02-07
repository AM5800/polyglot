package com.am5800.polyglot.app.sentenceGeneration.content

import com.am5800.polyglot.app.sentenceGeneration.Word
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Gender
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Number
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Person
import com.am5800.polyglot.app.sentenceGeneration.english.EnglishVerb
import com.am5800.polyglot.app.sentenceGeneration.english.EnglishWeakVerb
import com.am5800.polyglot.app.sentenceGeneration.english.Pronoun
import com.am5800.polyglot.app.sentenceGeneration.russian.RussianVerb

data class WordPair(val russian: Word, val english: Word)

class KnownWords {
  val words = mutableListOf<WordPair>()

  init {
    addPronouns()
    addVerbs()
  }

  private fun addVerbs() {
    val giveRussian = RussianVerb.define {
      infinitive("давать")
      present("даю, даешь, даем, даете, дает, дают")
      past("давал, давали, давала, давало")
    }
    val giveEnglish = EnglishVerb("give", "gave", "gives")

    val takeRussian = RussianVerb.define {
      infinitive("брать")
      present("беру, берешь, берем, даете, дает, дают")
      past("брал, брали, брала, брало")
    }
    val takeEnglish = EnglishVerb("take", "took", "takes")

    val seeRussian = RussianVerb.define {
      infinitive("видеть")
      present("вижу, видишь, видим, видите, видит, видят")
      past("видел, видели, видела, видело")
    }
    val seeEnglish = EnglishVerb("see", "saw", "sees")

    val comeRussian = RussianVerb.define {
      infinitive("приходить")
      present("прихожу, приходишь, приходим, приходите, приходит, приходят")
      past("приходил, приходили, приходила, приходило")
    }
    val comeEnglish = EnglishVerb("come", "came", "comes")

    val goRussian = RussianVerb.define {
      infinitive("идти")
      present("иду, идешь, идем, идете, идет, идут")
      past("шел, шли, шла, шло")
    }
    val goEnglish = EnglishVerb("go", "went", "goes")

    val knowRussian = RussianVerb.define {
      infinitive("знать")
      present("знаю, знаешь, знаем, знаете, знает, знают")
      past("знал, знали, знала, знало")
    }
    val knowEnglish = EnglishVerb("know", "knew", "knows")

    val speakRussian = RussianVerb.define {
      infinitive("говорить")
      present("говорю, говоришь, говорим, говорите, говорит, говорят")
      past("говорил, говорили, говорила, говорило")
    }
    val speakEnglish = EnglishVerb("speak", "spoke", "speaks")

    val eatRussian = RussianVerb.define {
      infinitive("есть")
      present("ем, ешь, едим, едите, ест, едят")
      past("ел, ели, ела, ело")
    }
    val eatEnglish = EnglishVerb("eat", "ate", "eats")

    val workRussian = RussianVerb.define {
      infinitive("работать")
      present("работаю, работаешь, работаем, работаете, работает, работают")
      past("работал, работали, работала, работало")
    }
    val workEnglish = EnglishWeakVerb("work")

    val openRussian = RussianVerb.define {
      infinitive("открывать")
      present("открываю, открываешь, открываем, открываете, открывает, открывают")
      past("открывал, открывали, открывала, открывало")
    }
    val openEnglish = EnglishWeakVerb("open")

    val closeRussian = RussianVerb.define {
      infinitive("закрывать")
      present("закрываю, закрываешь, закрываем, закрываете, закрывает, закрывают")
      past("закрывал, закрывали, закрывала, закрывало")
    }
    val closeEnglish = EnglishVerb("close", "closed", "closes")

    val finishRussian = RussianVerb.define {
      infinitive("заканчивать")
      present("заканчиваю, заканчиваешь, заканчиваем, заканчиваете, заканчивает, заканчивают")
      past("заканчивал, заканчивали, заканчивала, заканчивало")
    }
    val finishEnglish = EnglishVerb("finish", "finished", "finishes")

    val travelRussian = RussianVerb.define {
      infinitive("путешествовать")
      present("путешествую, путешествуешь, путешествуем, путешествуете, путешестует, путешествуют")
      past("путешествовал, путешествовали, путешествовала, путешествовало")
    }
    val travelEnglish = EnglishWeakVerb("travel")

    val askRussian = RussianVerb.define {
      infinitive("спрашивать")
      present("спрашиваю, спрашиваешь, спрашиваем, спрашиваете, спрашивает, спрашивают")
      past("спрашивал, спрашивали, спрашивала, спрашивало")
    }
    val askEnglish = EnglishWeakVerb("ask")

    val answerRussian = RussianVerb.define {
      infinitive("отвечать")
      present("отвечаю, отвечаешь, отвечаем, отвечаете, отвечает, отвечают")
      past("отвечал, отвечали, отвечала, отвечало")
    }
    val answerEnglish = EnglishWeakVerb("answer")

    val helpRussian = RussianVerb.define {
      infinitive("помогать")
      present("помогаю, помогаешь, помогаем, помогаете, помогает, помогают")
      past("помогал, помогали, помогала, помогало")
    }
    val helpEnglish = EnglishWeakVerb("help")

    val loveRussian = RussianVerb.define {
      infinitive("любить")
      present("люблю, любишь, любим, любите, любит, любят")
      past("любил, любили, любила, любило")
    }
    val loveEnglish = EnglishWeakVerb("love")

    words.add(WordPair(giveRussian, giveEnglish))
    words.add(WordPair(takeRussian, takeEnglish))
    words.add(WordPair(seeRussian, seeEnglish))
    words.add(WordPair(comeRussian, comeEnglish))
    words.add(WordPair(goRussian, goEnglish))
    words.add(WordPair(knowRussian, knowEnglish))
    words.add(WordPair(speakRussian, speakEnglish))
    words.add(WordPair(eatRussian, eatEnglish))
    words.add(WordPair(workRussian, workEnglish))
    words.add(WordPair(openRussian, openEnglish))
    words.add(WordPair(closeRussian, closeEnglish))
    words.add(WordPair(finishRussian, finishEnglish))
    words.add(WordPair(travelRussian, travelEnglish))
    words.add(WordPair(askRussian, askEnglish))
    words.add(WordPair(answerRussian, answerEnglish))
    words.add(WordPair(helpRussian, helpEnglish))
    words.add(WordPair(loveRussian, loveEnglish))
  }

  private fun addPronouns() {
    val iRussian = Pronoun("я", Person.First, Number.Singular, null)
    val iEnglish = Pronoun("I", Person.First, Number.Singular, null)

    val thouRussian = Pronoun("ты", Person.Second, Number.Singular, null)
    val thouEnglish = Pronoun("you", Person.Second, Number.Singular, null)

    val weRussian = Pronoun("мы", Person.First, Number.Plural, null)
    val weEnglish = Pronoun("we", Person.First, Number.Plural, null)

    val youRussian = Pronoun("вы", Person.Second, Number.Plural, null)
    val youEnglish = Pronoun("you", Person.Second, Number.Plural, null)

    val heRussian = Pronoun("он", Person.Third, Number.Singular, Gender.Masculine)
    val heEnglish = Pronoun("he", Person.Third, Number.Singular, Gender.Masculine)

    val sheRussian = Pronoun("она", Person.Third, Number.Singular, Gender.Feminine)
    val sheEnglish = Pronoun("she", Person.Third, Number.Singular, Gender.Feminine)

    val itRussian = Pronoun("оно", Person.Third, Number.Singular, Gender.Neuter)
    val itEnglish = Pronoun("it", Person.Third, Number.Singular, Gender.Neuter)

    val theyRussian = Pronoun("они", Person.Third, Number.Plural, null)
    val theyEnglish = Pronoun("they", Person.Third, Number.Plural, null)

    words.add(WordPair(iRussian, iEnglish))
    words.add(WordPair(thouRussian, thouEnglish))
    words.add(WordPair(weRussian, weEnglish))
    words.add(WordPair(youRussian, youEnglish))
    words.add(WordPair(heRussian, heEnglish))
    words.add(WordPair(sheRussian, sheEnglish))
    words.add(WordPair(itRussian, itEnglish))
    words.add(WordPair(theyRussian, theyEnglish))
  }

}