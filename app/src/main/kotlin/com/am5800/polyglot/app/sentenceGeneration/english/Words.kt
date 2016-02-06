package com.am5800.polyglot.app.sentenceGeneration.english

import com.am5800.polyglot.app.sentenceGeneration.*
import com.am5800.polyglot.app.sentenceGeneration.Number

class EnglishVerb(val infinitive: String,
                  val past: String,
                  val perfect: String,
                  val continuous: String,
                  val thirdPerson: String,
                  val transitivity: Transitivity) : Word

class Pronoun(val value: String, val person: Person, val number: Number, val gender: Gender?) : Word
