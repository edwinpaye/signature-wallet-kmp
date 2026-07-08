package com.edwinpaye.signaturewallet.core.common

import io.github.aakira.napier.Napier

object Logger {
    fun debug(tag: String, message: String) {
        Napier.d(message, tag = tag)
    }

    fun info(tag: String, message: String) {
        Napier.i(message, tag = tag)
    }

    fun warning(tag: String, message: String) {
        Napier.w(message, tag = tag)
    }

    fun error(tag: String, message: String, throwable: Throwable? = null) {
        Napier.e(message, tag = tag, throwable = throwable)
    }
}
