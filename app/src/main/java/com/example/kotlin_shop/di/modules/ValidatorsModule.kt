package com.example.kotlin_shop.di.modules

import com.example.kotlin_shop.domain.usecases.validators.ValidateNameUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidatePhoneUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidateSecondNameUseCase
import com.example.kotlin_shop.domain.usecases.validators.ValidateSerNameUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ValidatorsModule {

    @Provides
    @Singleton
    fun provideNameValidator(): ValidateNameUseCase = ValidateNameUseCase()


    @Provides
    @Singleton
    fun provideSerNameValidator(): ValidateSerNameUseCase = ValidateSerNameUseCase()


    @Provides
    @Singleton
    fun provideSecondNameValidator(): ValidateSecondNameUseCase = ValidateSecondNameUseCase()


    @Provides
    @Singleton
    fun providePhoneValidator(): ValidatePhoneUseCase = ValidatePhoneUseCase()

}