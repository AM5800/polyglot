package com.am5800.polyglot.app.sentenceGeneration.english

import com.am5800.polyglot.app.sentenceGeneration.Word
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Gender
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Number
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Person

open class EnglishVerb(val infinitive: String,
                       val past: String,
                       val thirdPerson: String) : Word

class Pronoun(val value: String, val person: Person, val number: Number, val gender: Gender?) : Word

class EnglishWeakVerb(val base: String) : EnglishVerb(base, base + "ed", base + "s")
