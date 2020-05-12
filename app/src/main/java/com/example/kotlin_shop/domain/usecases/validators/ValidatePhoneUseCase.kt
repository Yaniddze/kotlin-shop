package com.example.kotlin_shop.domain.usecases.validators

class ValidatePhoneUseCase {
    operator fun invoke(phone: String) = if (phone.startsWith("8"))
        phone.length == 11
    else
        phone.startsWith("+7") && phone.length == 12
}