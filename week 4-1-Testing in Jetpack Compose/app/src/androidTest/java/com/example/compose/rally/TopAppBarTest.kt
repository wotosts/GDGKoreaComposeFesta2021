package com.example.compose.rally

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.components.RallyTopAppBar
import com.example.compose.rally.ui.theme.RallyTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*

class TopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        val allScreens = RallyScreen.values().toList()      // fake
        var currentScreen by  mutableStateOf(RallyScreen.Accounts)
        composeTestRule.setContent {
            RallyTheme {
                RallyTopAppBar(
                    allScreens = allScreens,
                    onTabSelected = { currentScreen = it },
                    currentScreen = currentScreen
                )
            }
        }
    }

    @Test
    fun rallyTopAppBarTest() {
        Thread.sleep(5000)
    }

    @Test
    fun rallyTopAppBarTest_currentTabSelected() {
        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertIsSelected()
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExist() {
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase(Locale.getDefault())) and
                        hasParent(
                            hasContentDescription(RallyScreen.Accounts.name)
                        ),
                useUnmergedTree = true
            )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_selectTab() {
        composeTestRule.onNodeWithContentDescription(label = RallyScreen.Overview.name)
            .performClick()
        composeTestRule.onNodeWithContentDescription(RallyScreen.Overview.name).assertIsSelected()

        composeTestRule.onNodeWithContentDescription(label = RallyScreen.Accounts.name)
            .performClick()
        composeTestRule.onNodeWithContentDescription(RallyScreen.Accounts.name).assertIsSelected()

        composeTestRule.onNodeWithContentDescription(label = RallyScreen.Bills.name)
            .performClick()
        composeTestRule.onNodeWithContentDescription(RallyScreen.Bills.name).assertIsSelected()
    }
}