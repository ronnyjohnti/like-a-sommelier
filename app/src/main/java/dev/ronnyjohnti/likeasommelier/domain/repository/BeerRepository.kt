package dev.ronnyjohnti.likeasommelier.domain.repository

import dev.ronnyjohnti.likeasommelier.data.local.BeerDatabaseHelper
import dev.ronnyjohnti.likeasommelier.data.model.Beer

class BeerRepository(private val databaseHelper: BeerDatabaseHelper) {

    fun addBeer(beer: Beer) {
        databaseHelper.saveBeer(beer)
    }

    fun getAllBeers(): List<Beer> {
        return databaseHelper.getBeers()
    }
}
