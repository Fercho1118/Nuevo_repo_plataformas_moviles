package com.uvg.rueda.lab08.data

data class UiState <T>(
    val isLoading: Boolean = false,
    val data: T? = null,
    val hasError: Boolean = false
)