package com.maasbodev.auth.domain

interface PatternValidator {
	fun matches(value: String): Boolean
}