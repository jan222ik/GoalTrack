package com.github.jan222ik.goaltrack

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Providers
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import com.github.jan222ik.goaltrack.ui.GoalTrackTheme
import com.github.jan222ik.goaltrack.ui.screens.DetailScreen
import com.github.jan222ik.goaltrack.ui.screens.EditScreen
import com.github.jan222ik.goaltrack.ui.screens.MenuScreen
import com.github.jan222ik.goaltrack.ui.screens.Routing
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.router.Router


class MainActivity : AppCompatActivity() {
    private val backPressHandler = BackPressHandler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val defaultRouting: Routing = Routing.Menu
            GoalTrackTheme {
                Providers(
                    AmbientBackPressHandler provides backPressHandler
                ) {
                    Surface(Modifier.fillMaxSize()) {
                        Router(defaultRouting = defaultRouting) { backStack ->
                            when (val current = backStack.last()) {
                                is Routing.Menu -> MenuScreen(backStack)
                                is Routing.DetailView -> DetailScreen(goal = current.goal)
                                is Routing.EditGoal -> EditScreen(goal = current.goal)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }

}