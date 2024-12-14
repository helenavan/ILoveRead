package com.toulousehvl.iloveread.data.repository.remote.service

import com.toulousehvl.iloveread.data.APIResult
import com.toulousehvl.iloveread.data.core.response.BookResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.Mockito.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class BookImplTest {

    private lateinit var apiService: BookApiService
    private lateinit var bookRepository: BookImpl

    @Before
    fun setup() {
        apiService = mock(BookApiService::class.java)
        bookRepository = BookImpl(apiService)
    }

    @Test
    fun `getISBNBooks returns success when API call is successful`() = runTest {
        // Arrange
        val mockResponse = BookResponse(listOf())
        `when`(apiService.getBooksDetails("test"))
            .thenReturn(Response.success(mockResponse))

        // Act
        val result = bookRepository.getBookDetails("test")

        // Assert
        assert(result is APIResult.Success)
        assertEquals((result as APIResult.Success).data, mockResponse)
    }

    @Test
    fun `getISBNBooks returns error when API call is unsuccessful`() = runTest {
        // Arrange
        `when`(apiService.getBooksDetails("test"))
            .thenReturn(Response.error(404, okhttp3.ResponseBody.create(null, "Not Found")))

        // Act
        val result = bookRepository.getBookDetails("test")

        // Assert
        assert(result is APIResult.Error)
       // assert((result as APIResult.Error).message.contains("Not Found"))
    }

    @Test
    fun `getISBNBooks returns error when API throws exception`() = runTest {
        // Arrange
        `when`(apiService.getBooksDetails("test")).thenThrow(RuntimeException("Network error"))

        // Act
        val result = bookRepository.getBookDetails("test")

        // Assert
        assert(result is APIResult.Error)
       // assert((result as APIResult.Error).message.contains("Network error"))
    }
}