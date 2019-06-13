package com.example.letrack.support

interface Constants {

    companion object{

        //app log
        val APP_LOG  = "LETRACK_LOG"


        val FLAG  = "flag"
        val FLAG_UPDATE  = "flag_update"

        //sqlite database parameter
        var DATABASE_NAME = "LeTrack.db"
        var DATABASE_VERSION = 1


        var PAYMENT_ADVANCE = "advance"
        var PAYMENT_PAYMENT = "payment"
        var RETURN_ADVANCE = "return_advance"
    }
}