package com.am5800.polyglot.app

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
  private val quizSource = QuizSource()
  /**
   * The [android.support.v4.view.PagerAdapter] that will provide
   * fragments for each of the sections. We use a
   * [FragmentPagerAdapter] derivative, which will keep every
   * loaded fragment in memory. If this becomes too memory intensive, it
   * may be best to switch to a
   * [android.support.v4.app.FragmentStatePagerAdapter].
   */
  private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

  /**
   * The [ViewPager] that will host the section contents.
   */
  private var mViewPager: ViewPager? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val toolbar = findViewById(R.id.toolbar) as Toolbar
    setSupportActionBar(toolbar)
    // Create the adapter that will return a fragment for each of the three
    // primary sections of the activity.
    mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

    // Set up the ViewPager with the sections adapter.
    mViewPager = findViewById(R.id.container) as ViewPager
    mViewPager!!.adapter = mSectionsPagerAdapter


    val fab = findViewById(R.id.fab) as FloatingActionButton
    fab.setOnClickListener { view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show() }
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


  /**
   * A [FragmentPagerAdapter] that returns a fragment corresponding to
   * one of the sections/tabs/pages.
   */
  inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
      // getItem is called to instantiate the fragment for the given page.
      // Return a PlaceholderFragment (defined as a static inner class below).
      return PlaceholderFragment.newInstance()
    }

    override fun getCount(): Int {
      // Show 3 total pages.
      return 1
    }

    override fun getPageTitle(position: Int): CharSequence? {
      when (position) {
        0 -> return "Урок 1"
        1 -> return "SECTION 2"
        2 -> return "SECTION 3"
      }
      return null
    }
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  class PlaceholderFragment : Fragment() {

    private var mainActivity: MainActivity? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
      val rootView = inflater!!.inflate(R.layout.fragment_main, container, false) as LinearLayout
      val question = rootView.findViewById(R.id.question) as TextView
      val answer = rootView.findViewById(R.id.answer) as TextView

      val activity = mainActivity!!
      val currentQuiz = activity.nextQuiz()

      initTextViews(answer, currentQuiz, question)

      rootView.setOnClickListener {
        if (answer.visibility == View.INVISIBLE) {
          answer.visibility = View.VISIBLE
        } else {
          val quiz = activity.nextQuiz()
          initTextViews(answer, quiz, question)
        }
      }

      return rootView
    }

    private fun initTextViews(answer: TextView, currentQuiz: Quiz, question: TextView) {
      question.text = currentQuiz.question
      question.visibility = View.VISIBLE

      answer.text = currentQuiz.answer
      answer.visibility = View.INVISIBLE
    }

    override fun onAttach(activity: Activity?) {
      super.onAttach(activity)

      mainActivity = activity as MainActivity
    }

    companion object {
      fun newInstance(): PlaceholderFragment {
        val fragment = PlaceholderFragment()
        val args = Bundle()
        fragment.arguments = args
        return fragment
      }
    }
  }

  private fun nextQuiz(): Quiz {
    return quizSource.next()
  }
}
