package com.example.afinal

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


class CustomViewModel(app: Application?) : ViewModel() {
    private val myRepo: Repo
    private val entityList: LiveData<List<rEntity>>
    private val entityList2 : LiveData<List<rEntity>>
    private val currentCategory = MutableLiveData<String>()

    fun show(): LiveData<List<rEntity>> {
        return entityList
    }

//    fun showThisWeek(): LiveData<List<rEntity>> {
//        val w = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
//        val newList : LiveData<List<rEntity>>
//
//        for (i in entityList.value!!) {
//            if (i.rW == w) {
//                newList.value?.plus(i)
//            }
//        }
//
//        return newList
//    }

    fun insert(entity: rEntity?) {
        myRepo.add(entity)
    }

    fun showByWeek(): LiveData<List<rEntity>> {
        return entityList2
    }

    fun delete(entity: rEntity?) {
        myRepo.delete(entity)
    }

    fun update(entity: rEntity?) {
        myRepo.update(entity)
    }

    fun setCategory(category: String) {
        currentCategory.value = category
    }

    init {
        myRepo = Repo(app)
        entityList = myRepo.show(currentCategory.value)
        entityList2 =  myRepo.showByWeek()
    }
}