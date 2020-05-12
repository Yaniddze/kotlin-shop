package com.example.kotlin_shop.domain.usecases.validators

class ValidateNameUseCase {
    operator fun invoke(name: String): Boolean = name.length > 2
}