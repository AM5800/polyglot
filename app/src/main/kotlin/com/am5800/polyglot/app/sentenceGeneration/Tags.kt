package com.am5800.polyglot.app.sentenceGeneration

import java.util.*

interface GeneratorTag

enum class Person {
  First,
  Second,
  Third,
}

open class PronounTag(val person: EnumSet<Person>) : GeneratorTag

class AnyPronounTag() : PronounTag(EnumSet.of(Person.First, Person.Second, Person.Third))
class ThirdPersonPronounTag() : PronounTag(EnumSet.of(Person.Third))
class FirstOrSecondPersonPronounTag() : PronounTag(EnumSet.of(Person.First, Person.Second))


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

class AuxDoTag(verbTagFlags: EnumSet<VerbTagFlag>) : VerbTag(verbTagFlags)

