package com.maasbodev.auth.data

import android.util.Patterns
import com.maasbodev.auth.core.PatternValidator

object EmailPatternValidator : PatternValidator {
	override fun matches(value: String): Boolean {
		return Patterns.EMAIL_ADDRESS.matcher(value).matches()
	}
}