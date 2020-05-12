package com.example.kotlin_shop.domain.usecases.validators

class ValidateSerNameUseCase {
    operator fun invoke(serName: String): Boolean = serName.length > 2
}