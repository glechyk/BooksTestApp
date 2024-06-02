package com.books.app.data.repository

import android.app.Activity
import com.books.app.data.mapper.mapToDomain
import com.books.app.data.model.BooksData
import com.books.app.data.model.DetailsData
import com.books.app.domain.model.BooksDomain
import com.books.app.domain.model.DetailsDomain
import com.books.app.domain.repository.BooksRepository
import com.books.app.utils.FIREBASE_BOOKS_KEY
import com.books.app.utils.FIREBASE_DETAILS_KEY
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.serialization.json.Json
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig
) : BooksRepository {

    override suspend fun getBooks(activity: Activity): Flow<BooksDomain> =
        callbackFlow {
            firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        val data = firebaseRemoteConfig.getString(FIREBASE_BOOKS_KEY)
                        try {
                            val obj = Json.decodeFromString<BooksData>(data)
                            trySend(obj.mapToDomain())
                        } catch (e: Exception) {
                            close(e)
                        }
                    } else {
                        close(task.exception ?: Exception("Fetch failed"))
                    }
                }
            awaitClose()
        }

    override suspend fun getDetails(activity: Activity): Flow<DetailsDomain> =
        callbackFlow {
            firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        val data = firebaseRemoteConfig.getString(FIREBASE_DETAILS_KEY)
                        try {
                            val obj = Json.decodeFromString<DetailsData>(data)
                            trySend(obj.mapToDomain())
                        } catch (e: Exception) {
                            close(e)
                        }
                    } else {
                        close(task.exception ?: Exception("Fetch failed"))
                    }
                }
            awaitClose()
        }
}