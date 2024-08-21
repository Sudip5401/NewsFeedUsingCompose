package com.example.newsfeedusingcompose.utils

class Utils {
    companion object {
        fun removeSpecialCharacters(value: String): String {
            val re = Regex("[^A-Za-z0-9 ]")
            val correctedValue = re.replace(value, "") // works
            return correctedValue
        }
    }
}