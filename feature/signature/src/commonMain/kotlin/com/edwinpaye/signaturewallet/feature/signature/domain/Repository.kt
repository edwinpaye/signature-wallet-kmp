package com.edwinpaye.signaturewallet.feature.signature.domain

interface SignatureRepository {
    suspend fun saveSignature(signature: Signature): Result<String>
    suspend fun getSignature(signatureId: String): Result<Signature>
    suspend fun getBaselineSignature(userId: String): Result<Signature?>
    suspend fun setBaselineSignature(signature: Signature): Result<Unit>
    suspend fun getAllSignatures(userId: String): Result<List<Signature>>
}
