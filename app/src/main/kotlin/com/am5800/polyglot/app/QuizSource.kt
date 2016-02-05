package com.am5800.polyglot.app

import java.util.*

class Quiz(val question: String, val answer: String)

class QuizSource {

  private val hardcodedQuizzes = mutableListOf<Quiz>()
  private val random = Random()

  init {
    hardcodedQuizzes.add(Quiz("Я буду любить?", "Will I love?"))
    hardcodedQuizzes.add(Quiz("Я буду любить", "I will love"))
    hardcodedQuizzes.add(Quiz("Я не буду любить", "I will not love"))

    hardcodedQuizzes.add(Quiz("Ты любишь?", "Do you love?"))
    hardcodedQuizzes.add(Quiz("Ты любишь", "You love"))
    hardcodedQuizzes.add(Quiz("Ты не любишь", "You don't love"))

    hardcodedQuizzes.add(Quiz("Он любил?", "Did he love?"))
    hardcodedQuizzes.add(Quiz("Он любил", "He loved"))
    hardcodedQuizzes.add(Quiz("Он не любил", "He didn't love"))
  }

  fun next(): Quiz {
    val index = random.nextInt(hardcodedQuizzes.size)
    return hardcodedQuizzes[index]
  }
}