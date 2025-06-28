package com.notescollection.app.notes.data.api.notes

import com.notescollection.app.notes.data.request.NoteRequest
import com.notescollection.app.notes.data.request.NoteResponse
import com.notescollection.app.notes.data.request.NotesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.client.request.parameter
import io.ktor.client.request.put

class NotesApiImpl(
    private val client: HttpClient
) : NotesApi {

    override suspend fun createNote(request: NoteRequest): NoteResponse {
        return client.post(BASE_URL + CREATE_NOTE_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun updateNote(request: NoteRequest): NoteResponse {
        return client.put(BASE_URL + UPDATE_NOTE_PATH) {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    override suspend fun getNotes(page: Int, size: Int): NotesResponse {
        return client.get(BASE_URL + GET_NOTES_PATH) {
            parameter(PAGE, page)
            parameter(SIZE, size)
        }.body()
    }

    override suspend fun deleteNote(id: String): Boolean {
        val response = client.delete("$BASE_URL/api/notes/$id")
        return response.status == HttpStatusCode.OK
    }

    companion object {
        private const val BASE_URL = "https://notemark.pl-coding.com"
        private const val CREATE_NOTE_PATH = "/api/notes"
        private const val UPDATE_NOTE_PATH = "/api/notes"
        private const val GET_NOTES_PATH = "/api/notes"
        private const val PAGE = "page"
        private const val SIZE = "size"
    }

}