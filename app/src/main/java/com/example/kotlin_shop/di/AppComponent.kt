package com.example.kotlin_shop.di

import android.content.Context
import com.example.kotlin_shop.di.modules.*
import com.example.kotlin_shop.ui.fragments.*
import com.example.kotlin_shop.ui.fragments.catalog.CatalogFragment
import com.example.kotlin_shop.ui.fragments.catalog.MainCategoryFragment
import com.example.kotlin_shop.ui.fragments.catalog.SubCategoryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        FactoriesModule::class,
        DbModule::class,
        RetrofitModule::class,
        RepositoriesModule::class,
        DetailedPresenterUseCasesModule::class,
        CatalogPresenterUseCasesModule::class,
        CartPresenterUseCasesModule::class,
        GeneralUseCases::class,
        MakeOrderPresenterUseCasesModule::class,
        ValidatorsModule::class,
        OrderPresenterUseCaseModule::class,
        MainCategoryPresenterUseCasesModule::class,
        SubCategoryUseCasesModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(base: DetailedFragment)
    fun inject(base: CatalogFragment)
    fun inject(base: CartFragment)
    fun inject(base: MakeOrderFragment)
    fun inject(base: FavoriteFragment)
    fun inject(base: OrdersFragment)
    fun inject(base: MainCategoryFragment)
    fun inject(base: SubCategoryFragment)

}