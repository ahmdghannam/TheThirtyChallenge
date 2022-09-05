package fts.ahmed.tahady_althalathen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.math.max
import kotlin.math.min

object Values {
    lateinit var firstName:String
    lateinit var secondName: String
    lateinit var firstScore: MutableLiveData<Int>
    lateinit var secondScore: MutableLiveData<Int>


    public fun changeTheTitleScore(): String {
        val name =
            if (Values.firstScore.value!! > Values.secondScore.value!!) Values.firstName
            else if (Values.firstScore.value!! < Values.secondScore.value!!) Values.secondName

            else "Draw"

        val title = "$name ${max(Values.firstScore.value!!, Values.secondScore.value!!)} : ${
            min(
                Values.firstScore.value!!,
                Values.secondScore.value!!
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



}