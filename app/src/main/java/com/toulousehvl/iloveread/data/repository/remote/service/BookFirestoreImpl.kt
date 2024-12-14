package com.toulousehvl.iloveread.data.repository.remote.service

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.model.Book
import com.toulousehvl.iloveread.domain.repository.BookFirestoreRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

//TODO
class BookFirestoreImpl @Inject constructor() : BookFirestoreRepository {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    private val firestoreInstance = FirebaseFirestore.getInstance()
    private val booksCollection = firestoreInstance.collection("users")

    //TODO connection user firebase
    override suspend fun getListBooks(): APIResult<List<Book>> = withContext(ioDispatcher) {
        try {
            val querySnapshot = booksCollection.document("lgfDT5icHtOSvuE8lUMdBSmLU6J2").collection("books").get().await()
            //val querySnapshot = booksCollection.get().await()
            val books = querySnapshot.toObjects(Book::class.java)
            APIResult.Success(books)
        } catch (e: Exception) {
            APIResult.Error("Error fetching books: ${e.message}")
        }
    }

    override suspend fun addBook(book: Book): APIResult<Boolean> = withContext(ioDispatcher) {
        try {
            booksCollection.add(book).await()
            APIResult.Success(true)
        } catch (e: Exception) {
            APIResult.Error("Error adding book: ${e.message}")
        }
    }

    //TODO
    override suspend fun deleteBook(book: Book): APIResult<Boolean> = withContext(ioDispatcher) {
        var querySnapshot: QuerySnapshot? = null
        try {
            for (item in book.items!!) {
               querySnapshot = booksCollection.whereEqualTo("id", item.id).get().await()

                Log.d("TAG", "deleteBook: $querySnapshot")
            }

            if (querySnapshot != null) {
                val documentId = querySnapshot.documents[0].id
                booksCollection.document(documentId).delete().await()
                APIResult.Success(true)
            } else {
                APIResult.Error("Book not found")
            }
        } catch (e: Exception) {
            APIResult.Error("Error deleting book: ${e.message}")
        }

    }

}