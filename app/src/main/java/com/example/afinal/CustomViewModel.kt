package com.example.afinal

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class CustomViewModel(app: Application?) : ViewModel() {
    private val myRepo: Repo
    private val entityList: LiveData<List<rEntity>>
    fun show(): LiveData<List<rEntity>> {
        return entityList
    }

    fun insert(entity: rEntity?) {
        myRepo.add(entity)
    }

    fun delete(entity: rEntity?) {
        myRepo.delete(entity)
    }

    fun update(entity: rEntity?) {
        myRepo.update(entity)
    }

    init {
        myRepo = Repo(app)
        entityList = myRepo.show()
    }
}