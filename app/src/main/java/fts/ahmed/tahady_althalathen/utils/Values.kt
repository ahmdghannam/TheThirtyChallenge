package fts.ahmed.tahady_althalathen.utils

import androidx.lifecycle.MutableLiveData
import fts.ahmed.tahady_althalathen.models.Player
import kotlin.math.max
import kotlin.math.min

object Values {
    lateinit var firstName:String
    lateinit var secondName: String
    lateinit var firstScore: MutableLiveData<Int>
    lateinit var secondScore: MutableLiveData<Int>
    lateinit var playersList: MutableLiveData<MutableList<Player>>
    lateinit var copyPlayersList:MutableList<Player>
    var playersID=0

    public fun changeTheTitleScore(): String {
        val concatName = getWinner()
        val title = score(concatName)
        return title
    }

    public fun score(concatName: String): String {
        val title = "$concatName ${max(firstScore.value!!, secondScore.value!!)} : ${
            min(
                firstScore.value!!,
                secondScore.value!!
            )
        } "
        return title
    }

    fun increaseFirstScore(){
       firstScore.value= firstScore.value?.plus(1)
    }
    fun increaseSecondScore(){
        secondScore.value= secondScore.value?.plus(1)
    }

    fun getWinner(): String {
        val winner= if (firstScore.value!! > secondScore.value!!) firstName
        else if (firstScore.value!! < secondScore.value!!) secondName
        else "Draw"
        return winner
    }

    fun totalMatches(): String {
        return (secondScore.value?.let { firstScore.value?.plus(it) }) .toString()
    }
    fun totalMatchesInt(): Int {
        return (secondScore.value!!.let { firstScore.value!!.plus(it)})
    }


}