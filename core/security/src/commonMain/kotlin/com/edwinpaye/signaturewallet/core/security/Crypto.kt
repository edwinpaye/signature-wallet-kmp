package com.edwinpaye.signaturewallet.core.security

import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

interface CryptoProvider {
    fun encrypt(plaintext: String, key: SecretKey): String
    fun decrypt(ciphertext: String, key: SecretKey): String
    fun generateKey(size: Int = 256): SecretKey
    fun generateIV(): ByteArray
}

@OptIn(ExperimentalEncodingApi::class)
class AES256CryptoProvider : CryptoProvider {
    private val algorithm = "AES"
    private val transformation = "AES/CBC/PKCS5Padding"

    override fun encrypt(plaintext: String, key: SecretKey): String {
        val cipher = Cipher.getInstance(transformation)
        val iv = generateIV()
        cipher.init(Cipher.ENCRYPT_MODE, key, IvParameterSpec(iv))
        val encrypted = cipher.doFinal(plaintext.toByteArray())
        val combined = iv + encrypted
        return Base64.encode(combined)
    }

    override fun decrypt(ciphertext: String, key: SecretKey): String {
        val cipher = Cipher.getInstance(transformation)
        val combined = Base64.decode(ciphertext)
        val iv = combined.sliceArray(0 until 16)
        val encrypted = combined.sliceArray(16 until combined.size)
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))
        val decrypted = cipher.doFinal(encrypted)
        return String(decrypted)
    }

    override fun generateKey(size: Int): SecretKey {
        val keyGenerator = KeyGenerator.getInstance(algorithm)
        keyGenerator.init(size)
        return keyGenerator.generateKey()
    }

    override fun generateIV(): ByteArray {
        return ByteArray(16) { (0..255).random().toByte() }
    }
}
