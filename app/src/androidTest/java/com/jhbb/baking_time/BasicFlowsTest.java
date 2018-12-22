package com.jhbb.baking_time;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jhbb.baking_time.view.ui.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
public class BasicFlowsTest {

    @Rule
    public final ActivityTestRule<MainActivity> mMainActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivity_List_IsDisplayed() {

        onView(withId(R.id.recipes_recycler_view))
                .check(matches(hasDescendant(withText("Nutella Pie"))));
    }

    @Test
    public void ingredients_IsDisplayed() {

        onView(withId(R.id.recipes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.ingredients_list_text_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void stepDescription_IsDisplayed() {

        onView(withId(R.id.recipes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.step_description_text_view))
                .check(matches(isDisplayed()));

        onView(withId(R.id.step_description_text_view))
                .check(matches(withText("Recipe Introduction")));
    }

    @Test
    public void stepInstructions_IsDisplayed() {

        onView(withId(R.id.recipes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.step_instruction_text_view))
                .check(matches(isDisplayed()));

        onView(withId(R.id.step_instruction_text_view))
                .check(matches(withText("Recipe Introduction")));
    }

    @Test
    public void navigation_ToNextStep() {

        onView(withId(R.id.recipes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.next_button))
                .perform(click());

        onView(withId(R.id.next_button))
                .perform(click());

        onView(withId(R.id.step_instruction_text_view))
                .check(matches(not(withText("Recipe Introduction"))));
    }

    @Test
    public void navigation_BackToFirstStep() {

        onView(withId(R.id.recipes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.next_button))
                .perform(click());

        onView(withId(R.id.previous_button))
                .perform(click());

        onView(withId(R.id.step_instruction_text_view))
                .check(matches(withText("Recipe Introduction")));
    }

    @Test
    public void navigation_Back_StepDetails() {

        onView(withId(R.id.recipes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.steps_recycler_view))
                .check(matches(isDisplayed()));
    }

    @Test
    public void navigation_Back_MainActivity() {

        onView(withId(R.id.recipes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.steps_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withContentDescription("Navigate up")).perform(click());

        onView(withContentDescription("Navigate up")).perform(click());

        onView(withId(R.id.recipes_recycler_view))
                .check(matches(isDisplayed()));
    }
}
