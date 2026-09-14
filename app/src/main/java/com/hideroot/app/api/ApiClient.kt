package com.hideroot.app.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

/**
 * API client for local_api service
 * Base URL: http://127.0.0.1:8080
 */
object ApiClient {
    private const val BASE_URL = "http://127.0.0.1:8080"
    private const val ENDPOINT = "$BASE_URL/api/v1/execute"
    
    private val client = OkHttpClient()
    private val JSON = "application/json".toMediaType()

    /**
     * Execute an action via POST /api/v1/execute
     * @param action The action name (e.g., "list_items", "create_item")
     * @return Response body as string, or error message
     */
    suspend fun execute(action: String): String = withContext(Dispatchers.IO) {
        try {
            val json = JSONObject().apply {
                put("action", action)
            }
            
            val body = json.toString().toRequestBody(JSON)
            val request = Request.Builder()
                .url(ENDPOINT)
                .post(body)
                .build()
            
            val response = client.newCall(request).execute()
            val responseBody = response.body?.string() ?: "Empty response"
            
            if (response.isSuccessful) {
                responseBody
            } else {
                "Error ${response.code}: $responseBody"
            }
        } catch (e: Exception) {
            "Exception: ${e.message}"
        }
    }

    /**
     * Check if service is running
     * @return true if service responds, false otherwise
     */
    suspend fun isServiceRunning(): Boolean = withContext(Dispatchers.IO) {
        try {
            val request = Request.Builder()
                .url("$BASE_URL/health")
                .get()
                .build()
            
            val response = client.newCall(request).execute()
            response.isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}
