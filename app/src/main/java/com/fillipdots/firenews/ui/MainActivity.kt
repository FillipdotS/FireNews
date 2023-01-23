package com.fillipdots.firenews.ui

import android.app.NotificationManager
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.hilt.work.HiltWorkerFactory
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.fillipdots.firenews.R
import com.fillipdots.firenews.data.repository.AccountRepository
import com.fillipdots.firenews.ui.account.AccountViewModel
import com.fillipdots.firenews.ui.article.ArticleViewModel
import com.fillipdots.firenews.ui.theme.FireNewsTheme
import com.fillipdots.firenews.util.TOPIC_NOTIFICATION_CHANNEL_ID
import com.fillipdots.firenews.util.TOPIC_WORKER_UNIQUE_NAME
import com.fillipdots.firenews.util.createNotificationChannel
import com.fillipdots.firenews.worker.TopicNotificationWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel(
            name = getString(R.string.topic_channel),
            descriptionText = getString(R.string.topic_channel_description),
            importance = NotificationManager.IMPORTANCE_DEFAULT,
            channelId = TOPIC_NOTIFICATION_CHANNEL_ID,
            context = applicationContext
        )

        // Setup work manager to check for new articles in topics
        val workManager = WorkManager.getInstance(applicationContext)
        val myWork = PeriodicWorkRequestBuilder<TopicNotificationWorker>(
            15, TimeUnit.MINUTES
        ).build()
        workManager.enqueueUniquePeriodicWork(
            TOPIC_WORKER_UNIQUE_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            myWork
        )

        setContent {
            FireNewsTheme {
                FireNewsApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun FireNewsApp(
    accountViewModel: AccountViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentScreenIsNavItem = screenIsNavItem(currentDestination?.route)

    LaunchedEffect(Unit) {
        if (accountViewModel.uiState.value.accountUid.isEmpty()) {
            accountViewModel.anonSignIn()
        }
    }

    // Google recommends to do it like this? Wacky
    Scaffold(
        topBar = {
            Surface(tonalElevation = 1.dp) {
                CenterAlignedTopAppBar(
                    title = { Text("\uD83D\uDD25 FireNews") },
                    navigationIcon = {
                        if (!currentScreenIsNavItem) {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.go_back))
                            }
                        }
                    },
//                    actions = {
//                        if (currentDestination?.route == Route.ArticleList.routeString) {
//                            IconButton(onClick = {
//                                articleViewModel.refreshPosts()
//                            }) {
//                                Icon(Icons.Filled.Refresh, contentDescription = stringResource(R.string.refresh))
//                            }
//                        }
//                    }
                )
            }
        },
        bottomBar = {
            AnimatedContent(
                targetState = currentScreenIsNavItem,
                transitionSpec = {
                    slideInVertically { fullHeight -> fullHeight } with slideOutVertically { fullHeight -> fullHeight }
                }
            ) { targetState ->
                if (targetState) {
                    NavigationBar {
                        navItems.forEach { screen ->
                            NavigationBarItem(
                                selected = currentDestination?.hierarchy?.any { it.route == screen.route.toString() } == true,
                                icon = { Icon(screen.icon, contentDescription = screen.route.routeString) },
                                label = { Text(stringResource(screen.resourceId)) },
                                onClick = {
                                    navController.navigate(screen.route.toString()) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        FireNewsNavGraph(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = Screen.Articles.route.toString()
        )
    }
}

private fun screenIsNavItem(route: String?): Boolean {
    if (route == null) return false

    return navItems.find { it.route.toString() == route } != null
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MainActivityPreview() {
    FireNewsTheme {
        FireNewsApp()
    }
}