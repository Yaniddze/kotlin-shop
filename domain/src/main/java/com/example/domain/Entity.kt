package com.example.domain

open class Entity(
    open val id: Int
) {
    override fun equals(other: Any?): Boolean {
        if(other is Entity){

            if(other.id < 1 || id < 1)
                return false

            return other.id == id
        }
        return false
    }

    override fun hashCode(): Int {
        return "Entity$id".hashCode()
    }
}