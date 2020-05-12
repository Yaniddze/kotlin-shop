package com.example.kotlin_shop.domain.usecases.validators

class ValidateSecondNameUseCase {
    operator fun invoke(secondName: String) = secondName.length > 2
}