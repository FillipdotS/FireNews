package com.fillipdots.firenews.domain.model

import com.google.firebase.firestore.DocumentId

data class Topic(
    @DocumentId val id: String? = null,
    val name: String = "",
    val notificationsOn: Boolean = true
) {
    override fun toString(): String {
        return name
    }
}
