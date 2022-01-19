package com.yavin.busdriver.model

class Price(
    private var singleJourneyTicket: Int = 110,
    private var dayTicket: Int = 250,
    private var weekTicket: Int = 1200
) {

    constructor(price: Price) : this(
        price.getSingleJourneyTicket(),
        price.getDayTicket(),
        price.getWeekTicket()
    )

    fun getSingleJourneyTicketString(): String {

        return "${(singleJourneyTicket / 100)}.${(singleJourneyTicket % 100)}"
    }

    fun getDayTicketString(): String {

        return "${(dayTicket / 100)}.${(dayTicket % 100)}"
    }

    fun getWeekTicketString(): String {

        return "${(weekTicket / 100)}.${(weekTicket % 100)}"
    }

    fun setSingleJourneyTicketString(value: String) {

        val integer: String = value.split(".")[0]
        val decimal: String = value.split(".")[1]
        var price = 0

        try {
            price = integer.toInt() * 100
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }

        if (decimal.isEmpty()) {

            singleJourneyTicket = price
            return
        }

        try {
            price += decimal.toInt()
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }

        singleJourneyTicket = price
    }

    fun setDayTicketString(value: String) {

        val integer: String = value.split(".")[0]
        val decimal: String = value.split(".")[1]
        var price = 0

        try {
            price = integer.toInt() * 100
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }

        if (decimal.isEmpty()) {

            dayTicket = price
            return
        }

        try {
            price += decimal.toInt()
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }

        dayTicket = price
    }

    fun setWeekTicketString(value: String) {

        val integer: String = value.split(".")[0]
        val decimal: String = value.split(".")[1]
        var price = 0

        try {
            price = integer.toInt() * 100
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }

        if (decimal.isEmpty()) {

            weekTicket = price
            return
        }

        try {
            price += decimal.toInt()
        } catch (nfe: NumberFormatException) {
            // not a valid int
        }

        weekTicket = price
    }

    fun getSingleJourneyTicket(): Int {

        return singleJourneyTicket
    }

    fun getDayTicket(): Int {

        return dayTicket
    }

    fun getWeekTicket(): Int {

        return weekTicket
    }

    fun setSingleJourneyTicket(value: Int) {

        singleJourneyTicket = value
    }

    fun setDayTicket(value: Int) {

        dayTicket = value
    }

    fun setWeekTicket(value: Int) {

        weekTicket = value
    }
}
