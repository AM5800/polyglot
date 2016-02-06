package com.am5800.polyglot.app.sentenceGeneration

interface GeneratorTag

class SentenceTag : GeneratorTag

class PronounTag : GeneratorTag


enum class VerbTagFlag {
  Infinitive,
  Past,
  Present,
  Future
}

open class VerbTag(val transitivity: Transitivity, val verbTagFlag: VerbTagFlag = VerbTagFlag.Infinitive) : GeneratorTag

open class LiteralTag(val value: String) : GeneratorTag

class SpaceTag : LiteralTag(" ")

class AuxDoTag(verbTagFlag: VerbTagFlag = VerbTagFlag.Infinitive) : VerbTag(Transitivity.Transitive, verbTagFlag)
