package com.samueljuma.ktorinaction.repository

import com.samueljuma.ktorinaction.models.Comment
import com.samueljuma.ktorinaction.network.CommentsAPIService
import com.samueljuma.ktorinaction.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

interface CommentsRepository {
    suspend fun getComments(): NetworkResult<List<Comment>>
}

class CommentsRepositoryImpl(
    private val apiService: CommentsAPIService,
    private val dispatcher: CoroutineDispatcher
): CommentsRepository {

    override suspend fun getComments(): NetworkResult<List<Comment>> {
        return withContext(dispatcher){
            try{
                val response = apiService.getComments()
                NetworkResult.Success(response)
            }catch (e: Exception){
                NetworkResult.Error(e.message ?: "Something went wrong")
            }
        }
    }
}