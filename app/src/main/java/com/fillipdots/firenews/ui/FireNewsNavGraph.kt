package com.fillipdots.firenews.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.fillipdots.firenews.ui.account.AccountScreen
import com.fillipdots.firenews.ui.article.ArticleDetail
import com.fillipdots.firenews.ui.article.ArticleScreen
import com.fillipdots.firenews.ui.login.LoginScreen
import com.fillipdots.firenews.ui.search.SearchScreen
import com.fillipdots.firenews.ui.sign_up.SignUpScreen
import com.fillipdots.firenews.ui.topic_news.TopicNewsScreen
import com.fillipdots.firenews.ui.topics.TopicScreen

@Composable
fun FireNewsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
) {
    val uri = "com.fillipdots.firenews"

    NavHost(
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        composable(Route.ArticleList.routeString) { ArticleScreen(navController = navController) }
        composable(Route.ArticleDetail.routeString) { ArticleDetail() }
        composable(Route.Search.routeString) { SearchScreen(navController = navController) }
        composable(Route.Topics.routeString) { TopicScreen(navController = navController) }
        composable(
            Route.TopicNews.routeString,
            deepLinks = listOf(navDeepLink { uriPattern = "$uri/${Route.TopicNews.routeString}" })
        ) {
            TopicNewsScreen(navController = navController)
        }
        composable(Route.Account.routeString) { AccountScreen(navController = navController) }
        composable(Route.SignUp.routeString) { SignUpScreen(navController = navController) }
        composable(Route.Login.routeString) { LoginScreen(navController = navController) }
    }
}