package com.example.newsapp.news

import com.example.newsapp.models.News

class NewsRepository {
    private var newsList = listOf<News>()

    fun getNew():News {
        newsList= listOf(
            News("1", "Украйна нападната...", "Lorem ipsum..ala bala"),
            News("2", "Русия атакува", "Lorem ipsum..ala bala2"),
            News("3", "България се готви за отбрана", "Lorem ipsum..ala bala"),
            News("4", "Новина номер 4", "Lorem ipsum..ala bala"),
            News("5", "Новина номер 5", "Lorem ipsum..ala bala"),
            News("6", "Украйна нападната...", "Lorem ipsum..ala bala"),
            News("7", "Русия атакува", "Lorem ipsum..ala bala2"),
            News("8", "България се готви за отбрана", "Lorem ipsum..ala bala"),
            News("9", "Новина номер 4", "Lorem ipsum..ala bala"),
            News("10", "Новина номер 5", "Lorem ipsum..ala bala"),
        )
        return newsList.random()
    }
}