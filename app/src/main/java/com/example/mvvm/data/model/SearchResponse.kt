package com.example.mvvm.data.model

data class SearchResponse(
	val totalCount: Int? = null,
	val incompleteResults: Boolean? = null,
	val items: List<Users>? = null
)



