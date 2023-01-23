package com.fillipdots.firenews.data.api

import android.util.Log
import com.fillipdots.firenews.data.api.dto.ArticleDto
import com.fillipdots.firenews.data.api.dto.ApiArticlesDto
import com.google.gson.Gson
import kotlinx.coroutines.delay

class FakeNewsApiImpl(
    private val apiKey: String
) {
    suspend fun fetchLatestNews(): List<ArticleDto> {
        Log.w("FakeNewsApiImpl", "Fetching latest (fake) news")

        delay(800)

        val gson = Gson()

        val response = gson.fromJson(topHeadlinesJSON, ApiArticlesDto::class.java)

        return response.articles
    }
}

const val topHeadlinesJSON =
    "{\"status\":\"ok\",\"totalResults\":35,\"articles\":[{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"https://www.facebook.com/bbcnews\",\"title\":\"King Charles greets crowds at Sandringham after Christmas Day service - BBC\",\"description\":\"Members of the Royal Family are attending a service at St Mary Magdalene Church.\",\"url\":\"https://www.bbc.co.uk/news/uk-64089661\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/1790/production/_128123060_kingcharlescamillasandringham.png\",\"publishedAt\":\"2022-12-25T12:00:57Z\",\"content\":\"King Charles is attending a Christmas Day church service at Sandringham for the first time as monarch.\\r\\nThe King and Camilla, the Queen Consort are joined by the Prince and Princess of Wales, their c… [+1542 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"https://www.facebook.com/bbcnews\",\"title\":\"Archbishop of Canterbury remembers Queen's example in Christmas message - BBC\",\"description\":\"The archbishop remembered the late monarch's ability to put her own interest after those she served.\",\"url\":\"https://www.bbc.co.uk/news/uk-64087775\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/13043/production/_128119877_976cce113c266177406fdeeb2cf9f0c1ebb7a591.jpg\",\"publishedAt\":\"2022-12-25T12:00:07Z\",\"content\":\"In his first Christmas sermon since the death of Queen Elizabeth II, the Archbishop of Canterbury praised the late monarch's life of service.\\r\\nThe Queen \\\"put her interest after those of the people sh… [+2476 chars]\"},{\"source\":{\"id\":null,\"name\":\"Sky.com\"},\"author\":\"Sky\",\"title\":\"Rail strikes to hit Boxing Day travel - with no trains running at all - Sky News\",\"description\":\"\",\"url\":\"https://news.sky.com/story/rail-strikes-to-hit-boxing-day-travel-with-no-trains-running-at-all-12774334\",\"urlToImage\":\"https://e3.365dm.com/22/12/1600x900/skynews-euston-rmt-strike_5995945.jpg?20221214074501\",\"publishedAt\":\"2022-12-25T10:52:29Z\",\"content\":\"There will be no trains running in the UK on Boxing Day due to fresh industrial action by rail workers.\\r\\nHundreds of trains usually run on the 26 December, but now thousands of people will be forced … [+1603 chars]\"},{\"source\":{\"id\":null,\"name\":\"Daily Mail\"},\"author\":\"Adam Shergold\",\"title\":\"What happens when Cristiano Ronaldo leaves a club after Man United exit - Daily Mail\",\"description\":\"His acrimonious Man United exit is the fifth time in his illustrious career that Ronaldo has left a club. So what happens to their fortunes when he walks through the door?\",\"url\":\"https://www.dailymail.co.uk/sport/football/article-11538269/What-happens-Cristiano-Ronaldo-leaves-club-Man-United-exit.html\",\"urlToImage\":\"https://i.dailymail.co.uk/1s/2022/12/25/10/65623567-0-image-a-16_1671965070837.jpg\",\"publishedAt\":\"2022-12-25T10:40:50Z\",\"content\":\"Manchester United are currently adjusting to with life after Cristiano Ronaldo for the second time.\\r\\nThe Portuguese star's spectacular separation from the Old Trafford club in recent weeks has certai… [+14696 chars]\"},{\"source\":{\"id\":null,\"name\":\"City A.M.\"},\"author\":\"City A.M. Reporter\",\"title\":\"Red-faced Putin: Russia now ready to 'negotiate' over Ukraine war - City A.M.\",\"description\":\"President Vladimir Putin said Russia was ready to negotiate with all parties involved in the war in Ukraine but that Kyiv and its\",\"url\":\"https://www.cityam.com/red-faced-putin-russia-now-ready-to-negotiate-over-ukraine-war/\",\"urlToImage\":\"https://www.cityam.com/wp-content/uploads/2022/05/Russia-Putin.jpg\",\"publishedAt\":\"2022-12-25T10:20:35Z\",\"content\":\"Sunday 25 December 2022 10:20 am\\r\\nVladimir Putin’s war in Ukraine has left him with few friends – and seeking negotiations\\r\\nPresident Vladimir Putin said Russia was ready to negotiate with all partie… [+2350 chars]\"},{\"source\":{\"id\":null,\"name\":\"The Guardian\"},\"author\":\"Peter Beaumont\",\"title\":\"‘It won’t be like before’: Christmas in Ukraine transformed by war - The Guardian\",\"description\":\"With many of the usual traditions cancelled, Ukrainians are doing their best to keep the festive spirit alive\",\"url\":\"https://www.theguardian.com/world/2022/dec/25/it-wont-be-like-before-christmas-in-ukraine-transformed-by-war\",\"urlToImage\":\"https://i.guim.co.uk/img/media/8e69f9c1db505c9547b22ed565be90319cbdbb53/0_19_3500_2100/master/3500.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctZGVmYXVsdC5wbmc&enable=upscale&s=0b04b754cb7e2c5eddac9281dd6d5d9b\",\"publishedAt\":\"2022-12-25T10:00:00Z\",\"content\":\"On Christmas Eve, as Ukrainian Catholics gathered to celebrate in the Latin Cathedral in the western city of Lviv, the electricity was off, the consequence of the recent waves of Russian missile stri… [+6773 chars]\"},{\"source\":{\"id\":null,\"name\":\"BBC News\"},\"author\":\"https://www.facebook.com/bbcnews\",\"title\":\"Police hunting gunman as woman dies after Wallasey pub shooting - BBC\",\"description\":\"Merseyside Police have released details of the vehicle the gunman may have used to flee the scene.\",\"url\":\"https://www.bbc.com/news/uk-england-merseyside-64088175\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/0408/production/_128123010_capture.png\",\"publishedAt\":\"2022-12-25T09:54:18Z\",\"content\":\"A murder investigation has been launched after a woman was fatally shot at a pub near Liverpool on Christmas Eve.\\r\\nMerseyside Police officers were called to the Lighthouse Inn in Wallasey Village at … [+3238 chars]\"},{\"source\":{\"id\":null,\"name\":\"Manchester Evening News\"},\"author\":\"Liam Wood, Jack Flintham\",\"title\":\"Manchester United transfer news LIVE with Goncalo Ramos and Cody Gakpo latest - Manchester Evening News\",\"description\":\"Keep tabs on the latest news, views and Man Utd transfer rumours with our dedicated blog updates.\",\"url\":\"https://www.manchestereveningnews.co.uk/sport/football/transfer-news/manchester-united-transfer-news-live-25834214\",\"urlToImage\":\"https://i2-prod.football.london/incoming/article25834278.ece/ALTERNATES/s1200/0_Collage-Maker-24-Dec-2022-0659-PM.jpg\",\"publishedAt\":\"2022-12-25T09:45:00Z\",\"content\":\"Merry Christmas! As Manchester United fans open their presents today, they will be wondering what tree-mendous gift Erik ten Hag will serve up in the January transfer window.\\r\\nThe winter market opens… [+1101 chars]\"},{\"source\":{\"id\":null,\"name\":\"The Guardian\"},\"author\":\"Stuart Heritage\",\"title\":\"‘Best workplace drama since Mad Men’: the sandwich-makers who shook the world - The Guardian\",\"description\":\"How did The Bear – a small, indie-feeling series about a top chef who returns home to save the family business – become such a TV sensation? We unravel a story of chaos, obsession and thinly-sliced beef\",\"url\":\"https://www.theguardian.com/tv-and-radio/2022/dec/25/the-bear-jeremy-allen-white-best-workplace-drama-since-mad-men\",\"urlToImage\":\"https://i.guim.co.uk/img/media/e41446bd559eaae87b670f22209f62f2c3aa174f/0_0_3442_2066/master/3442.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctZGVmYXVsdC5wbmc&enable=upscale&s=fb852ff03ce5d9dd0c2fad857f47e94e\",\"publishedAt\":\"2022-12-25T09:00:00Z\",\"content\":\"When The Bear appeared on screens in the US this year, it arrived with few expectations. A small, indie-feeling drama about an ailing Chicago sandwich shop, it came with a semi-recognisable cast and … [+9946 chars]\"},{\"source\":{\"id\":null,\"name\":\"Mirror Online\"},\"author\":\"Milica Cosic\",\"title\":\"Kate and William release adorable handpainted Christmas card made by Prince George - The Mirror\",\"description\":\"Kate Middleton and Prince William have shared an \\\"adorable\\\" painting by Prince George on Christmas Day, as royal fans have taken to compliment the nine-year-old's \\\"brilliant\\\" talent\",\"url\":\"https://www.mirror.co.uk/news/royals/breaking-kate-william-release-adorable-28812701\",\"urlToImage\":\"https://i2-prod.ok.co.uk/incoming/article28795314.ece/ALTERNATES/s1200/3_GettyImages-1449479330.jpg\",\"publishedAt\":\"2022-12-25T08:36:24Z\",\"content\":\"The Prince and Princess of Wales have shared a picture of a Christmas card designed by Prince George.\\r\\nThe young royal painted a reindeer in the adorable image, which has been shared by William and K… [+2387 chars]\"},{\"source\":{\"id\":null,\"name\":\"Manchester Evening News\"},\"author\":\"Gary Stewart, Nicole Wootton-Cane\",\"title\":\"Tragedy as woman dies after being hit by police car on Christmas Eve - Manchester Evening News\",\"description\":\"Formal identification is still taking place\",\"url\":\"https://www.manchestereveningnews.co.uk/news/uk-news/tragedy-woman-dies-after-being-25834762\",\"urlToImage\":\"https://i2-prod.manchestereveningnews.co.uk/incoming/article25834779.ece/ALTERNATES/s1200/0_Police-cordon-off-Sheil-RoadKensingtonLiverpool.jpg\",\"publishedAt\":\"2022-12-25T08:34:48Z\",\"content\":\"A woman has died after being hit by a police car on Christmas Eve.\\r\\nEmergency services were scrambled to Sheil Road in Kensington, Merseyside, at around 8.10pm on Saturday to reports of a collision i… [+1228 chars]\"},{\"source\":{\"id\":\"independent\",\"name\":\"Independent\"},\"author\":\"Joe Sommerlad\",\"title\":\"What time is King Charles’ Christmas speech and where can I watch it? - The Independent\",\"description\":\"Monarch expected to reflect on death of his late mother in landmark broadcast\",\"url\":\"https://www.independent.co.uk/arts-entertainment/tv/news/kings-speech-christmas-speech-day-time-live-b2251251.html\",\"urlToImage\":\"https://static.independent.co.uk/2022/12/23/10/newFile-11.jpg?quality=75&width=1200&auto=webp\",\"publishedAt\":\"2022-12-25T08:14:20Z\",\"content\":\"Sign up to our free IndyArts newsletter for all the latest entertainment news and reviews\\r\\nSign up to our free IndyArts newsletter\\r\\nKing Charles III will give his first Christmas Day address to the n… [+1789 chars]\"},{\"source\":{\"id\":\"google-news\",\"name\":\"Google News\"},\"author\":null,\"title\":\"Daring midfield choice, new Leon Bailey role - Aston Villa predicted XI vs Liverpool - BirminghamWorld\",\"description\":null,\"url\":\"https://news.google.com/__i/rss/rd/articles/CBMimwFodHRwczovL3d3dy5iaXJtaW5naGFtd29ybGQudWsvc3BvcnQvZm9vdGJhbGwvYXN0b24tdmlsbGEvZGFyaW5nLW1pZGZpZWxkLWNob2ljZS1uZXctbGVvbi1iYWlsZXktcm9sZS1hc3Rvbi12aWxsYS1wcmVkaWN0ZWQteGktZ2FsbGVyeS12cy1saXZlcnBvb2wtMzk2MzE0MNIBAA?oc=5\",\"urlToImage\":null,\"publishedAt\":\"2022-12-25T08:09:53Z\",\"content\":null},{\"source\":{\"id\":null,\"name\":\"Sky Sports\"},\"author\":\"Gail Davis\",\"title\":\"Leah Williamson: England Women captain talks Lionesses legacy and goals for 2023 - Sky Sports\",\"description\":\"Sky Sports reporter Gail Davis catches up with England Women captain Leah Williamson to reflect on an incredible 2022 and her goals for the new year.\",\"url\":\"https://www.skysports.com/football/news/11095/12770789/leah-williamson-england-women-captain-talks-lionesses-legacy-and-goals-for-2023\",\"urlToImage\":\"https://e0.365dm.com/22/11/1600x900/skysports-leah-williamson-england_5976225.jpg?20221123204022\",\"publishedAt\":\"2022-12-25T08:03:51Z\",\"content\":\"Before I sat down with Leah Williamson to reflect on what has been an historic year I read back through the notes from the last time we spoke at length, a month out from the Euros.\\r\\nA time when she c… [+10239 chars]\"},{\"source\":{\"id\":null,\"name\":\"Daily Mail\"},\"author\":\"Aneeta Bhole\",\"title\":\"San Francisco unveils taxpayer-funded open-air Christmas market that's become dystopian hellhole - Daily Mail\",\"description\":\"San Francisco's Christmas market, hoped to be a wholesome holiday outing, is in fact a dystopian hellhole besieged by the city's famed drug addicts, a video posted on social media reveals.\",\"url\":\"https://www.dailymail.co.uk/news/article-11572629/San-Francisco-unveils-taxpayer-funded-open-air-Christmas-market-thats-dystopian-hellhole.html\",\"urlToImage\":\"https://i.dailymail.co.uk/1s/2022/12/25/06/65920145-0-image-a-98_1671949600866.jpg\",\"publishedAt\":\"2022-12-25T06:44:01Z\",\"content\":\"San Francisco's taxpayer-funded open air Christmas market, hoped to be a wholesome holiday outing for families, is in fact a dystopian hellhole besieged by the city's famed drug addicts.\\r\\nAt least th… [+7311 chars]\"},{\"source\":{\"id\":null,\"name\":\"The Guardian\"},\"author\":\"Sophie Zeldin-O'Neill\",\"title\":\"‘Losing sense of self’: Kirsty Young opens up about illness on Desert Island Discs - The Guardian\",\"description\":\"Former presenter tells current host Lauren Laverne of pain of stepping back from career due to chronic conditions\",\"url\":\"https://www.theguardian.com/tv-and-radio/2022/dec/25/losing-sense-of-self-kirsty-young-opens-up-about-illness-on-desert-island-discs\",\"urlToImage\":\"https://i.guim.co.uk/img/media/be00ac1ddd69537dc688b0f6e327fc0ca213ebdf/0_88_3000_1800/master/3000.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctZGVmYXVsdC5wbmc&enable=upscale&s=51cea35ddfe13be3f0565e15e08f82ff\",\"publishedAt\":\"2022-12-25T06:00:00Z\",\"content\":\"Kirsty Young has recalled how having to step away from broadcasting for a few years due to her chronic pain condition caused her to question her own identity, saying you lose your sense of self.\\r\\nApp… [+3545 chars]\"},{\"source\":{\"id\":null,\"name\":\"The Guardian\"},\"author\":\"Guardian staff reporter\",\"title\":\"China stops publishing daily Covid figures amid reports of explosion in cases - The Guardian\",\"description\":\"The National Health Commission’s decision comes as the country’s health system faces enormous strain after the sudden end to the zero-Covid policy\",\"url\":\"https://www.theguardian.com/world/2022/dec/25/china-stops-publishing-daily-covid-figures-amid-reports-of-explosion-in-cases\",\"urlToImage\":\"https://i.guim.co.uk/img/media/4ebee85188b8fc51e677200b90159c3024a2ab4e/0_295_8256_4954/master/8256.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctZGVmYXVsdC5wbmc&enable=upscale&s=c24c1bb4d93baccdc1f2b8bc7bc5bc88\",\"publishedAt\":\"2022-12-25T05:24:00Z\",\"content\":\"Chinas National Health Commission (NHC) has stopped publishing daily Covid-19 data, amid concerns about the reliability of the figures after infections exploded in the wake of an abrupt easing of tou… [+2226 chars]\"},{\"source\":{\"id\":null,\"name\":\"Daily Mail\"},\"author\":\"Owen Tonks, Sean O'grady\",\"title\":\"Rebekah Vardy and husband Jamie don matching festive pyjamas - Daily Mail\",\"description\":\"Rebekah Vardy, 40, and her husband Jamie, 35, put on a cosy display as they posed in matching pyjamas on Christmas Eve.\",\"url\":\"https://www.dailymail.co.uk/tvshowbiz/article-11572517/Rebekah-Vardy-husband-Jamie-don-matching-festive-pyjamas.html\",\"urlToImage\":\"https://i.dailymail.co.uk/1s/2022/12/25/01/65918475-0-image-a-197_1671932846194.jpg\",\"publishedAt\":\"2022-12-25T01:51:00Z\",\"content\":\"Rebekah Vardy and her husband Jamie put on a cosy display as they posed in matching pyjamas on Christmas Eve.\\r\\nTelevision personality Rebekah, 40, and her footballer beau Jamie, 35, were in good spir… [+6285 chars]\"},{\"source\":{\"id\":null,\"name\":\"The Guardian\"},\"author\":\"Guardian staff reporter\",\"title\":\"Pope condemns power hungry and alludes to Ukraine conflict in Christmas Eve mass - The Guardian\",\"description\":\"7,000 people fill St. Peter’s Basilica after several years of Covid imposed restrictions on attendance\",\"url\":\"https://www.theguardian.com/world/2022/dec/25/pope-condemns-power-hungry-and-alludes-to-ukraine-conflict-in-christmas-eve-mass\",\"urlToImage\":\"https://i.guim.co.uk/img/media/9be9a44b39b78eecaa331cc792a4af6c49b34409/0_232_7087_4252/master/7087.jpg?width=1200&height=630&quality=85&auto=format&fit=crop&overlay-align=bottom%2Cleft&overlay-width=100p&overlay-base64=L2ltZy9zdGF0aWMvb3ZlcmxheXMvdGctZGVmYXVsdC5wbmc&enable=upscale&s=a87d88b17cdab169f890e3b53f9808f5\",\"publishedAt\":\"2022-12-25T01:39:00Z\",\"content\":\"Pope Francis warned in a solemn Christmas Eve Mass that the level of greed and hunger for power was such that some wanted to consume even their neighbours, in an apparent reference to the war in Ukra… [+2218 chars]\"},{\"source\":{\"id\":\"bbc-news\",\"name\":\"BBC News\"},\"author\":\"https://www.facebook.com/bbcnews\",\"title\":\"James Webb telescope: Amazing images show the Universe as never before - BBC\",\"description\":\"It's a year since the James Webb telescope launched, and we've marvelled at its pictures ever since.\",\"url\":\"https://www.bbc.co.uk/news/science-environment-64051171\",\"urlToImage\":\"https://ichef.bbci.co.uk/news/1024/branded_news/1F72/production/_128105080_newindex.jpg\",\"publishedAt\":\"2022-12-25T00:22:41Z\",\"content\":\"It was the \$10bn gift to the world. A machine that would show us our place in the Universe.\\r\\nThe James Webb Space Telescope was launched exactly a year ago, on Christmas Day. It had taken three decad… [+4310 chars]\"}]}"