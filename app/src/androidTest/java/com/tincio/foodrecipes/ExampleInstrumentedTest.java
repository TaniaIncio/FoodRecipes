package com.tincio.foodrecipes;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tincio.foodrecipes.presentation.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {

        /**Start flow */

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recycler_recipe),
                        withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE),
                        withParent(withId(R.id.fragment_base)),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**Check redirection to ingredient*/
        onView(withId(R.id.option_ingredient)).check(matches(isDisplayed()));
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.option_ingredient), isDisplayed()));
        linearLayout.perform(click());

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();

        /**Check redirection to steps*/
        onView(withId(R.id.recycler_step)).check(matches(isDisplayed()));
        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_step), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**Check the  buttons next**/
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.btn_next), withText("Next"),
                        withParent(withId(R.id.linear_buttons))));
        appCompatButton.perform(ViewActions.scrollTo(), click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_next), withText("Next"),
                        withParent(withId(R.id.linear_buttons))));
        appCompatButton2.perform(ViewActions.scrollTo(), click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_next), withText("Next"),
                        withParent(withId(R.id.linear_buttons))));
        appCompatButton3.perform(ViewActions.scrollTo(), click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_next), withText("Next"),
                        withParent(withId(R.id.linear_buttons))));
        appCompatButton4.perform(ViewActions.scrollTo(), click());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        pressBack();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**Finish flow*/
        pressBack();
    }
}
