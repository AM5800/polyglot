package com.am5800.polyglot.app.sentenceGeneration

interface Word

enum class Transitivity {
  Intransitive,
  Transitive,
  Ditransitive
}

class Verb(val infinitive: String,
           val past: String,
           val perfect: String,
           val continuous: String,
           val thirdPerson: String,
           val transitivity: Transitivity) : Word

class Pronoun(val value: String) : Word
