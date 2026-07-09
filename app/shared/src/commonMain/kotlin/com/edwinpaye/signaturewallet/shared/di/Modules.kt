package com.edwinpaye.signaturewallet.shared.di

import com.edwinpaye.signaturewallet.core.security.AES256CryptoProvider
import com.edwinpaye.signaturewallet.core.security.CryptoProvider
import com.edwinpaye.signaturewallet.feature.auth.domain.GetCurrentUserUseCase
import com.edwinpaye.signaturewallet.feature.auth.domain.LoginUseCase
import com.edwinpaye.signaturewallet.feature.auth.domain.LogoutUseCase
import com.edwinpaye.signaturewallet.feature.auth.domain.SignupUseCase
import com.edwinpaye.signaturewallet.feature.signature.domain.SignatureValidatorImpl
import com.edwinpaye.signaturewallet.feature.wallet.domain.GetTransactionHistoryUseCase
import com.edwinpaye.signaturewallet.feature.wallet.domain.GetWalletUseCase
import com.edwinpaye.signaturewallet.feature.wallet.domain.ObserveWalletBalanceUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val securityModule = module {
    singleOf<CryptoProvider> { AES256CryptoProvider() }
}

val authModule = module {
    singleOf { LoginUseCase(get()) }
    singleOf { SignupUseCase(get()) }
    singleOf { LogoutUseCase(get()) }
    singleOf { GetCurrentUserUseCase(get()) }
}

val walletModule = module {
    singleOf { GetWalletUseCase(get()) }
    singleOf { ObserveWalletBalanceUseCase(get()) }
    singleOf { GetTransactionHistoryUseCase(get()) }
}

val signatureModule = module {
    singleOf { SignatureValidatorImpl() }
}

val appModule = listOf(
    securityModule,
    authModule,
    walletModule,
    signatureModule
)
