package com.example.kotlin_shop.di.components

import com.example.kotlin_shop.di.modules.*
import com.example.kotlin_shop.ui.fragments.CartFragment
import com.example.kotlin_shop.ui.fragments.catalog.CatalogFragment
import com.example.kotlin_shop.ui.fragments.DetailedFragment
import com.example.kotlin_shop.ui.fragments.PurchaseFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules=[
        FactoriesModule::class,
        DbModule::class,
        RetrofitModule::class,
        RepositoriesModule::class,
        DetailedPresenterUseCasesModule::class,
        CatalogPresenterUseCasesModule::class,
        CartPresenterUseCasesModule::class
    ]
)
@Singleton
interface AppComponent {

//    @Component.Builder
//    interface Builder{
//        @BindsInstance
//        fun context(context: Context): Builder
//        fun build(): AppComponent
//    }

    fun inject(base: DetailedFragment)
    fun inject(base: CatalogFragment)
    fun inject(base: CartFragment)
    fun inject(base: PurchaseFragment)

}