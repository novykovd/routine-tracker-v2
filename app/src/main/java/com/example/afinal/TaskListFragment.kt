package com.example.afinal

import android.app.Application
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.ui.platform.ComposeView

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory


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


    @Composable
    fun Routine(entity: rEntity) {

        var expanded by remember {
            mutableStateOf(false)
        }
        var editing by remember {
            mutableStateOf(false)
        }

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
                        var text by remember { mutableStateOf(entity.rN) }
                        BasicTextField(value = text, onValueChange = { text = it}, modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(4.dp)).weight(0.5f))}
                    else{Text(text = entity.rN, Modifier.weight(0.5f))}

                    ButtonCheck()

                    IconButton(onClick = { expanded = !expanded }) {
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
                                BasicTextField(value = text, onValueChange = { text = it},
                                    modifier = Modifier.border(1.dp, Color.Black, RoundedCornerShape(4.dp)).fillMaxHeight().fillMaxWidth())
                            }else{Text(text = "text")}
                        }

                        Box(modifier = Modifier
                            .weight(0.15f)
                            ) {
                            Column() {
                                Box(modifier = Modifier.weight(0.3f).padding(6.dp)) {
                                    var goals by remember { mutableStateOf("rND") }

                                    if (editing) {
                                        BasicTextField(
                                            value = goals, onValueChange = { goals = it },
                                            modifier = Modifier.border(
                                                1.dp,
                                                Color.Black,
                                                RoundedCornerShape(4.dp)
                                            ).fillMaxWidth().fillMaxHeight()
                                        )
                                    } else {
                                        Text(text = "G")
                                    }
                                }

                                Box(modifier = Modifier.weight(0.3f).padding(6.dp)) {

                                    var importance by remember { mutableStateOf("rI") }

                                    if (editing) {
                                        BasicTextField(
                                            value = importance, onValueChange = { importance = it },
                                            modifier = Modifier.border(
                                                1.dp,
                                                Color.Black,
                                                RoundedCornerShape(4.dp)
                                            ).fillMaxWidth().fillMaxHeight()
                                        )
                                    } else {
                                        Text(text = "I")
                                    }
                                }

                                IconButton(onClick = { editing = !editing }) {
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

    ){
        var isEnabled = remember {
            mutableStateOf(false)
        }

        Row(
            modifier = Modifier.padding(6.dp)

        ) {


            Image(modifier = Modifier.clickable(
                role = Role.RadioButton,
                onClickLabel = null,
                enabled = isEnabled.value,
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = {
                    isEnabled.value = !(isEnabled.value)
                }

            ), painter = painterResource(
                when {
                    isEnabled.value -> androidx.constraintlayout.widget.R.drawable.btn_checkbox_checked_mtrl
                    else -> androidx.appcompat.R.drawable.btn_checkbox_unchecked_mtrl

                }
            ), contentDescription = "gaming")

        }
    }



    @Composable
    fun TaskList(
        viewModel : CustomViewModel,
        modifier: Modifier = Modifier,
    ){


        val Entities by viewModel.show().observeAsState(listOf())


        LazyColumn(modifier = modifier.padding(vertical = 4.dp)){
            items(items = Entities) {
                    name -> Routine(entity = name)
            }
        }
    }




}

class CustomViewModelFactory(val application : Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CustomViewModel(application) as T
    }
}