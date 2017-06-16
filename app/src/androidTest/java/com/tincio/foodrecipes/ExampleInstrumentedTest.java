package com.tincio.foodrecipes;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.tincio.foodrecipes.presentation.MainActivity;
import com.tincio.foodrecipes.presentation.StepActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<StepActivity> mActivityTestRule = new ActivityTestRule<>(StepActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        onView(
                (withId(R.id.option_ingredient))).perform(click());
      //  linearLayout.perform(click());
       // Context appContext = InstrumentationRegistry.getTargetContext();

        //assertEquals("com.tincio.foodrecipes", appContext.getPackageName());
    }
}
