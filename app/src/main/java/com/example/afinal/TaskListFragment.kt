package com.example.afinal

import android.app.Application
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.platform.ComposeView

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import java.util.*


class TaskListFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        private var _binding: FragmentTaskListBinding? = null
//        private val binding get() = _binding!!
//            _binding = FragmentTaskListBinding.inflate(inflater, container, false)
//            val view = binding.root
//            binding.composeView.apply {
//                setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner))
//                setContent {
//                    val owner = LocalViewModelStoreOwner.current
//                        owner?.let {
//                        val viewModel: CustomViewModel = viewModel(
//                                it,
//                                "CustomViewModel",
//                                CustomViewModelFactory(
//                                    LocalContext.current.applicationContext as Application
//                                )
//                            )
//
//                            TaskList(viewModel)
//                        }
//                    }
//                }
//            }
//            return view
//        }


    return inflater.inflate(R.layout.fragment_list, container, false)
        .apply { findViewById<ComposeView>(R.id.composer).setContent {
            val owner = LocalViewModelStoreOwner.current
            owner?.let {
                val viewModel: CustomViewModel = viewModel(
                    it,
                    "CustomViewModel",
                    CustomViewModelFactory(
                        LocalContext.current.applicationContext as Application
                    )
                )

                TaskList(viewModel)
            }
        }
        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Routine(entity: rEntity, viewModel: CustomViewModel, name: String) {

        var expanded by remember {
            mutableStateOf(false)
        }
        var editing by remember {
            mutableStateOf(false)
        }

        //variable for the week of year
        var week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)



        var color = Color(0xFFE0E0FF)


        Surface(
            color = color,
            shadowElevation = 8.dp,
            modifier = Modifier
                .padding(24.dp)
                .clip(RoundedCornerShape(8.dp))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy
                    )
                )
        ) {
            Column() {

                Row(
                    Modifier.padding(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(editing){
                        BasicTextField(value = name, onValueChange = {}, modifier = Modifier
                            .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                            .weight(0.5f))}
                    else{Text(text = name, Modifier.weight(0.5f))}

                    CheckboxSpace()

                    Surface(
                        modifier = Modifier.pointerInput(Unit){
                            detectTapGestures(
                                onTap = {
                                    expanded = !expanded;
                                    if(!expanded){
                                        editing = false;
                                    }
                                },

                                onDoubleTap = {
                                    if(editing){
                                        viewModel.delete(entity)
                                    }
                                }
                            )
                        },
                        color = Color.Transparent,
                    )
                    {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.Close else Icons.Filled.ArrowDropDown,
                            contentDescription = "faminf"
                        )
                    }
                }

                if (expanded) {
                    Row(
                        modifier = Modifier
                            .padding(6.dp)
                            .fillMaxWidth()
                            .height(110.dp)
                    ) {
                        Box(modifier = Modifier
                            .weight(0.85f)
                            .fillMaxHeight()) {
                            var text by remember { mutableStateOf("entity.rD") }

                            if(editing){
                                BasicTextField(value = entity.rN, onValueChange = { text = it},
                                    modifier = Modifier
                                        .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
                                        .fillMaxHeight()
                                        .fillMaxWidth())
                            }else{Text(text = entity.rN)}


                        }

                        Box(modifier = Modifier
                            .weight(0.15f)
                            ) {
                            Column() {
                                Box(modifier = Modifier
                                    .weight(0.3f)
                                    .padding(6.dp)) {
                                    var goals by remember { mutableStateOf("rND") }

                                    if (editing) {
                                        BasicTextField(
                                            value = goals, onValueChange = { goals = it },
                                            modifier = Modifier
                                                .border(
                                                    1.dp,
                                                    Color.Black,
                                                    RoundedCornerShape(4.dp)
                                                )
                                                .fillMaxWidth()
                                                .fillMaxHeight()
                                        )
                                    } else {
                                        Text(text = "G")
                                    }
                                }

                                Box(modifier = Modifier
                                    .weight(0.3f)
                                    .padding(6.dp)) {

                                    var importance by remember { mutableStateOf("rI") }

                                    if (editing) {
                                        BasicTextField(
                                            value = importance, onValueChange = { importance = it },
                                            modifier = Modifier
                                                .border(
                                                    1.dp,
                                                    Color.Black,
                                                    RoundedCornerShape(4.dp)
                                                )
                                                .fillMaxWidth()
                                                .fillMaxHeight()
                                        )
                                    } else {
                                        Text(text = week.toString())
                                    }
                                }

                                IconButton(onClick = { editing = !editing; if(!editing){ viewModel.update(entity) }}) {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = "edit"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ButtonCheck(
        isTrue : Boolean = false
    ){
        var isEnabled by remember {
            mutableStateOf(isTrue)
        }

        Row(
            modifier = Modifier.padding(6.dp)

        ) {


            Image(modifier = Modifier.pointerInput(Unit){
                detectTapGestures(
                    onTap = {
                        isEnabled = !isEnabled
                    }
                )
            }

            , painter = painterResource(
                when {
                    isEnabled -> androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl
                    else -> androidx.appcompat.R.drawable.btn_checkbox_unchecked_mtrl

                }
            ), contentDescription = "gaming")

        }
    }

    @Composable
    fun weekCheckList(bl : List<Boolean>){

        val list = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
        var calendar = Calendar.getInstance();
        var dayofWeek = calendar.get(Calendar.DAY_OF_WEEK);

        LazyRow(modifier = Modifier.width(210.dp)){
            items(bl.size){
                if(dayofWeek-1 == it){
                        Box(modifier = Modifier
                            .border(3.dp, Color.Blue, RoundedCornerShape(8.dp)).width(40.dp), contentAlignment = Alignment.Center){
                            Column() {
                            Text(text = list[it], textAlign = TextAlign.Center, modifier = Modifier.fillParentMaxWidth(
                                0.2F
                            ))
                            ButtonCheck(bl[it])
                            }
                        }

                }
                else{
                        Box(modifier = Modifier) {
                            Column() {
                                Text(
                                    text = list[it],
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillParentMaxWidth(0.2F)
                                )
                                ButtonCheck(bl[it])
                            }
                        }
                }

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CheckboxSpace(){
        weekCheckList(bl = listOf(true, false, true, false, true, false, true))
    }

    @Composable
    fun TaskList(
        viewModel : CustomViewModel,
        modifier: Modifier = Modifier,
    ){

            val Entities by viewModel.show().observeAsState(listOf())

            LazyColumn(modifier = modifier.padding(vertical = 4.dp)){
                items(items = Entities,
                    key = { entity -> entity.id }
                        )
                { entity ->
                    Routine(entity, viewModel, entity.rN)
                }
            }
    }





}

class CustomViewModelFactory(val application : Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CustomViewModel(application) as T
    }
}