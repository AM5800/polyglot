package com.am5800.polyglot.app.sentenceGeneration

interface Word

class Verb(val infinitive: String,
           val past: String,
           val perfect: String,
           val continuous: String,
           val thirdPerson: String) : Word

class Pronoun(val value: String) : Word
