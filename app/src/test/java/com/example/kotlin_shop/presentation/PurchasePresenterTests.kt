package com.example.kotlin_shop.presentation

import com.example.kotlin_shop.ui.interfaces.PurchaseView
import org.junit.Assert.*
import org.junit.Test

class PurchasePresenterTests {
    class Tester: PurchaseView {
        var result: Boolean = false

        override fun showErrorForSecondName(visible: Boolean) {
            result = visible
        }

        override fun showErrorForFirstName(visible: Boolean) {
            result = visible
        }

        override fun showErrorForSerName(visible: Boolean) {
            result = visible
        }

        override fun showErrorForPhone(visible: Boolean) {
            result = visible
        }
    }

    @Test
    fun phone_validate_success(){
        val presenter = PurchasePresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.validatePhone("+77777599660").also {
            assert(tester.result)
        }
        presenter.validatePhone("87777599660").also {
            assert(tester.result)
        }
    }

    @Test
    fun phone_validate_failed(){
        val presenter = PurchasePresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.validatePhone("+7777759966").also {
            assertFalse(tester.result)
        }
        presenter.validatePhone("877775996601").also {
            assertFalse(tester.result)
        }
    }

    @Test
    fun name_validate_success(){
        val presenter = PurchasePresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.validateName("Yan").also {
            assert(tester.result)
        }
        presenter.validateName("SomeCoolName").also {
            assert(tester.result)
        }
    }

    @Test
    fun name_validate_failed(){
        val presenter = PurchasePresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.validateName("Ya").also {
            assertFalse(tester.result)
        }
        presenter.validateName("1").also {
            assertFalse(tester.result)
        }
    }

    @Test
    fun ser_name_validate_success(){
        val presenter = PurchasePresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.validateSerName("SerName").also {
            assert(tester.result)
        }
        presenter.validateSerName("SerElseName").also {
            assert(tester.result)
        }
    }

    @Test
    fun ser_name_validate_failed(){
        val presenter = PurchasePresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.validateSerName("Ya").also {
            assertFalse(tester.result)
        }
        presenter.validateSerName("1").also {
            assertFalse(tester.result)
        }
    }

    @Test
    fun second_name_validate_success(){
        val presenter = PurchasePresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.validateSecondName("SerName").also {
            assert(tester.result)
        }
        presenter.validateSecondName("SerElseName").also {
            assert(tester.result)
        }
    }

    @Test
    fun second_name_validate_failed(){
        val presenter = PurchasePresenter()

        val tester = Tester()

        presenter.attachView(tester)

        presenter.validateSecondName("ss").also {
            assertFalse(tester.result)
        }
        presenter.validateSecondName("ff").also {
            assertFalse(tester.result)
        }
    }
}