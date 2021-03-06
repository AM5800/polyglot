package com.am5800.polyglot.app.sentenceGeneration.russian

import com.am5800.polyglot.app.sentenceGeneration.Word
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Gender
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Number
import com.am5800.polyglot.app.sentenceGeneration.commonAttributes.Person


class RussianVerb(private val definition: RussianVerbDefinition) : Word {
  val infinitive: String = definition.inf

  class RussianVerbDefinition {

    private data class Key(val number: Number, val person: Person, val time: RussianTime, val gender: Gender?)

    private val table = mutableMapOf<Key, String>()
    var inf: String = ""

    fun infinitive(value: String) {
      inf = value
    }

    fun present(
            iForm: String,
            thouForm: String,
            weForm: String,
            youForm: String,
            heForm: String,
            theyForm: String) {
      put(RussianTime.Present, iForm, thouForm, weForm, youForm, heForm, heForm, heForm, theyForm)
    }

    fun present(str: String) {
      val parts = str.split(",").map { it.trim() }
      if (parts.size != 6) throw Exception("invalid format: " + str)
      present(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5])
    }

    private fun put(time: RussianTime,
                    iForm: String,
                    thouForm: String,
                    weForm: String,
                    youForm: String,
                    heForm: String,
                    sheForm: String,
                    itForm: String,
                    theyForm: String) {
      table.put(Key(Number.Singular, Person.First, time, null), iForm)
      table.put(Key(Number.Singular, Person.Second, time, null), thouForm)

      table.put(Key(Number.Singular, Person.Third, time, Gender.Masculine), heForm)
      table.put(Key(Number.Singular, Person.Third, time, Gender.Feminine), sheForm)
      table.put(Key(Number.Singular, Person.Third, time, Gender.Neuter), itForm)

      table.put(Key(Number.Plural, Person.First, time, null), weForm)
      table.put(Key(Number.Plural, Person.Second, time, null), youForm)
      table.put(Key(Number.Plural, Person.Third, time, null), theyForm)

    }

    fun past(
            iForm: String,
            weForm: String,
            sheForm: String,
            itForm: String) {
      put(RussianTime.Past, iForm, iForm, weForm, weForm, iForm, sheForm, itForm, weForm)
    }

    fun past(str: String) {
      val parts = str.split(",").map { it.trim() }
      if (parts.size != 4) throw Exception("invalid format: " + str)
      past(parts[0], parts[1], parts[2], parts[3])
    }

    fun future(
            iForm: String,
            thouForm: String,
            weForm: String,
            youForm: String,
            heForm: String,
            sheForm: String,
            itForm: String,
            theyForm: String) {
      put(RussianTime.Future, iForm, thouForm, weForm, youForm, heForm, sheForm, itForm, theyForm)
    }

    fun by(number: Number, person: Person, time: RussianTime, gender: Gender?): String {
      val result = table[Key(number, person, time, gender)] ?: return inf
      return result
    }
  }

  companion object {
    fun define(init: RussianVerbDefinition. () -> Unit): RussianVerb {
      val definition = RussianVerbDefinition()
      init(definition)
      return RussianVerb(definition)
    }

  }

  fun by(number: Number, person: Person, time: RussianTime, gender: Gender?): String {
    return definition.by(number, person, time, gender)
  }
}