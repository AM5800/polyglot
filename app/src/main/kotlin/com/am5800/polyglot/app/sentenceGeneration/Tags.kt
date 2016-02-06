package com.am5800.polyglot.app.sentenceGeneration

import java.util.*

interface GeneratorTag

open class PronounTag() : GeneratorTag

enum class VerbTagFlag {
  Infinitive,
  Past,
  Present,
  Future,
  Transitive,
  Intransitive,
  Ditransitive,
  ThirdPerson
}

open class VerbTag(val verbTagFlags: EnumSet<VerbTagFlag> = EnumSet.of(VerbTagFlag.Infinitive)) : GeneratorTag {
  constructor(flag: VerbTagFlag) : this(EnumSet.of(flag))

  constructor(flag1: VerbTagFlag, flag2: VerbTagFlag) : this(EnumSet.of(flag1, flag2))
}

class AuxDoTag(verbTagFlags: EnumSet<VerbTagFlag>) : VerbTag(verbTagFlags) {
  constructor(flag: VerbTagFlag) : this(EnumSet.of(flag))
}

