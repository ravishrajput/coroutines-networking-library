package com.ravish.coroutines.networking

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class ApiResultTest {

    data class User(val id: String, val name: String)

    private val user = User("SS213G", "Joe")

    @Test
    fun `test Safe Api Success`() = runBlockingTest {
        val result = callCatching { user }
        result.onSuccess {
            assertEquals(user, it)
        }
    }

    @Test
    fun `test Safe Api failure`() = runBlockingTest {
        val result = callCatching { null }
        result.onFailure {
            assertEquals(204, it.code)
            assert(it.error is EmptyBodyException)
        }
    }
}