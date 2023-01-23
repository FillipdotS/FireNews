package com.fillipdots.firenews.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Topic
import androidx.compose.ui.graphics.vector.ImageVector
import com.fillipdots.firenews.R

sealed class Route(val routeString: String) {
    object ArticleList : Route("article")
    object ArticleDetail : Route("article/{uuid}")
    object Search : Route("search")
    object Topics : Route("topic")
    object TopicNews : Route("topic_news")
    object Account : Route("account")
    object SignUp : Route("sign_up")
    object Login : Route("login")

    override fun toString(): String {
        return routeString
    }
}

sealed class Screen(val route: Route, val icon: ImageVector, @StringRes val resourceId: Int) {
    object Articles : Screen(Route.ArticleList, Icons.Filled.Newspaper, R.string.articles)
    object Search : Screen(Route.Search, Icons.Filled.Search, R.string.search)
    object Topics : Screen(Route.Topics, Icons.Filled.Flag, R.string.topics)
    object Account : Screen(Route.Account, Icons.Filled.Person, R.string.account)
}

val navItems = listOf(
    Screen.Articles,
    Screen.Search,
    Screen.Topics,
    Screen.Account
)