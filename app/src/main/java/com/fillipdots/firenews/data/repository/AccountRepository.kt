package com.fillipdots.firenews.data.repository

import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
) {
    val currentUserId: String
        get() = auth.currentUser?.uid.orEmpty()

    val currentUser: Flow<FirebaseUser>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    auth.currentUser?.let { this.trySend(it) }
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }

    suspend fun signInAnonymously() {
        auth.signInAnonymously().await()
    }

    suspend fun authenticate(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun linkAccount(email: String, password: String) {
        val creds = EmailAuthProvider.getCredential(email, password)

        auth.currentUser!!.linkWithCredential(creds).await()
    }

    suspend fun signOut() {
        auth.signOut()

        signInAnonymously()
    }

    suspend fun updateUserDocument(fieldMap: Map<String, Any>) {
        currentUserDocument().set(fieldMap).await()
    }

    fun currentUserDocument(): DocumentReference {
        return firestore.collection("users").document(currentUserId)
    }
}