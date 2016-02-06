package com.am5800.polyglot.app.sentenceGeneration.english

import com.am5800.polyglot.app.sentenceGeneration.Person
import com.am5800.polyglot.app.sentenceGeneration.Transitivity
import com.am5800.polyglot.app.sentenceGeneration.Word

class EnglishVerb(val infinitive: String,
                  val past: String,
                  val perfect: String,
                  val continuous: String,
                  val thirdPerson: String,
                  val transitivity: Transitivity) : Word

class Pronoun(val value: String, val person: Person) : Word
