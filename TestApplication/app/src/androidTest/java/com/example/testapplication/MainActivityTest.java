package com.example.testapplication;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private String USER_TO_BE_TYPED = "pepe", PASS_TO_BE_TYPED = "7o7o!";

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void View_elements_are_displayed(){

        onView(withId(R.id.main_activity_titel))
                .check(matches(isDisplayed()));
        onView(withId(R.id.main_activity_button))
                .check(matches(isDisplayed()));
    }

    @Test
    public void view_elements_text_are_correct() {
        onView(withId(R.id.main_activity_titel))
                .check(matches(withText(R.string.main_activity_titel_text)));
        onView(withId(R.id.main_activity_button))
                .check(matches(withText(R.string.main_activity_button_next)));
    }

    @Test
    public void text_changes_when_nextButton_clicked(){

        onView(withId(R.id.main_activity_button))
                .check(matches(isClickable()));

        onView(withId(R.id.main_activity_button))
                .perform(click())
                .check(matches(withText(R.string.button_back)));
    }

    @Test
    public void login_form_behaviour() {
        onView(withId(R.id.username_editText))
                .perform(typeText(USER_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.password_editText))
                .perform(typeText(PASS_TO_BE_TYPED), closeSoftKeyboard());

        onView(withId(R.id.main_activity_button))
                .perform(click())
                .check(matches(withText(R.string.main_activity_button_logged)));
    }

    @Test
    public void checkCheangeActivity() {
        onView(withId(R.id.main_activity_button))
                .perform(click());
        onView(withId(R.id.second_activity)).check(matches(isEnabled()));
    }

    @Test
    public void CheckBackActivity() {
        onView(withId(R.id.main_activity_button))
                .perform(click());

        onView(withId(R.id.second_activity_button))
                .perform(click());
        onView(withId(R.id.main_activity)).check(matches(isEnabled()));
    }

    @Test
    public void checkFullFuncionality() {
        onView(withId(R.id.username_editText))
                .perform(typeText(USER_TO_BE_TYPED), closeSoftKeyboard());
        onView(withId(R.id.password_editText))
                .perform(typeText(PASS_TO_BE_TYPED), closeSoftKeyboard());

        checkCheangeActivity();

        onView(withId(R.id.second_activity_titel)).check(matches(withText("Welcome back "+USER_TO_BE_TYPED)));

        onView(withId(R.id.second_activity_button))
                .perform(click());
        onView(withId(R.id.main_activity)).check(matches(isEnabled()));

        onView(withId(R.id.username_editText))
                .check(matches(withText("")));
        onView(withId(R.id.password_editText))
                .check(matches(withText("")));
    }
}

