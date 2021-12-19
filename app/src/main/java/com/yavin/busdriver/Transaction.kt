package com.yavin.busdriver

object Transaction {

    private var ids: MutableList<Int> = mutableListOf<Int>()
    private var amounts: MutableList<Int> = mutableListOf<Int>()

    fun add(id: Int, amount: Int) {

        ids.add(id)
        amounts.add(amount)
    }

    fun getAll() : List<Pair<Int, Int>> {

        return ids zip amounts
    }
}