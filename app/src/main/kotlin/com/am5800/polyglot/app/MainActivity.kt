package com.am5800.polyglot.app

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.support.v7.internal.widget.ContentFrameLayout
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.am5800.polyglot.app.sentenceGeneration.content.KnownWords
import com.am5800.polyglot.app.sentenceGeneration.content.Lesson1Grammars

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val rootView = findViewById(android.R.id.content) as ContentFrameLayout

    val tts = TextToSpeech(applicationContext, {})

    val question = findViewById(R.id.question) as TextView
    val answer = findViewById(R.id.answer) as TextView

    val quizSource = QuizSource(KnownWords().words, Lesson1Grammars().grammars)

    initTextViews(answer, quizSource.next(), question)

    rootView.setOnClickListener {
      if (answer.visibility == View.INVISIBLE) {
        answer.visibility = View.VISIBLE
        tts.speak(answer.text.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
      } else {
        val quiz = quizSource.next()
        initTextViews(answer, quiz, question)
      }
    }
  }

  private fun initTextViews(answer: TextView, currentQuiz: Quiz, question: TextView) {
    question.text = currentQuiz.question
    question.visibility = View.VISIBLE

    answer.text = currentQuiz.answer
    answer.visibility = View.INVISIBLE
  }


  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    val id = item.itemId

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true
    }

    return super.onOptionsItemSelected(item)
  }
}
