package com.pdmpa.stockmarketapp.domain.repository

import android.content.ContentValues
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class AuthenticationRepository @Inject constructor(
    private val auth: FirebaseAuth,
) {

    fun getUserName(): String? {
        return if (auth.currentUser != null) {
            auth.currentUser?.displayName
        } else {
            "Undefined"
        }
    }

    fun logInWithEmail(
        email: String,
        password: String
    ): Flow<AuthStatus> {
        return callbackFlow {
            auth.signOut()
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    trySend(AuthStatus.Success)
                }
                .addOnFailureListener {
                    trySend(AuthStatus.Failure(it.message.toString()))
                }
            awaitClose()
        }
    }

    fun signUpWithEmail(email: String, password: String, name: String): Flow<AuthStatus> {
        return callbackFlow {
            auth.signOut()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    val profileUpdates = userProfileChangeRequest {
                        displayName = name
                    }
                    auth.currentUser?.updateProfile(profileUpdates)
                    trySend(AuthStatus.Success)
                }
                .addOnFailureListener {
                    trySend(AuthStatus.Failure(it.message.toString()))
                }
            awaitClose()
        }
    }

    fun resetPasswordWithEmail(email: String): Flow<AuthStatus> {
        return callbackFlow {
            auth.signOut()
            auth.sendPasswordResetEmail(email)
                .addOnSuccessListener {
                    trySend(AuthStatus.Success)
                }
                .addOnFailureListener {
                    trySend(AuthStatus.Failure(it.message.toString()))
                }
            awaitClose()
        }
    }

    fun logOut(): Boolean {
        return try {
            auth.signOut()
            true
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "log out from Firebase: log out failed: ${e.message}", e)
            false
        }
    }
}

sealed class AuthStatus {
    object Success : AuthStatus()
    data class Failure(val message: String?) : AuthStatus()
}