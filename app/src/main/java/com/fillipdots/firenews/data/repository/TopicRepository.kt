package com.fillipdots.firenews.data.repository

import com.fillipdots.firenews.domain.model.Topic
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TopicRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val accountRepository: AccountRepository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    val topics: Flow<List<Topic>>
        get() =
            accountRepository.currentUser.flatMapLatest { user ->
                currentCollection(user.uid).snapshots().map { snapshot -> snapshot.toObjects() }
            }

    suspend fun save(topic: Topic): String {
        return currentCollection(accountRepository.currentUserId).add(topic).await().id
    }

    suspend fun update(topic: Topic) {
        currentCollection(accountRepository.currentUserId).document(topic.id!!).set(topic).await()
    }

    suspend fun delete(topic: Topic) {
        currentCollection(accountRepository.currentUserId).document(topic.id!!).delete().await()
    }

    private fun currentCollection(uid: String): CollectionReference {
        return firestore.collection("users").document(uid).collection("topics")
    }
}