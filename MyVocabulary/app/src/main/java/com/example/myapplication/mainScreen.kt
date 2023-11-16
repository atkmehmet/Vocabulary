package com.example.myapplication

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapplication.domain.model.Vocabulary
import com.example.myapplication.representation.newVocabulary.VocabularyAddState


@Composable
fun mainScreen(){

    val navController = rememberNavController()
    Scaffold(topBar = { TopAppBar() { Text(text = "Save your Vocabulary") } },
           bottomBar = { BottomNavigationBar(items =  listOf(
               BottomNavItem(
                   name = "Home",
                   route = "home",
                   icon = Icons.Default.Home
               ),
               BottomNavItem(
                   name = "MyLearns",
                   route = "learn",
                   icon = Icons.Default.Check
               ),
               BottomNavItem(
                   name = "Search",
                   route = "search",
                   icon = Icons.Default.Search
               ),
           ),
               navController =navController ,
               onItemClick = {navController.navigate(it.route)})})
    {
        Navigation(navController = navController)
    }
}

@Composable
fun AddVocabularyScreen(state: VocabularyAddState,
                        onEvent:(VocabularyEvent)->Unit) {
    AlertDialog(
        onDismissRequest = { onEvent(VocabularyEvent.HideDialog) },
        title = { Text(text = state.vocabularyError)},
        text = {  Column(modifier = Modifier.padding(10.dp))
        {
            TextField(value =state.vocabulary,
                onValueChange = {onEvent(VocabularyEvent.vocabulary(it))},
                placeholder ={ Text(text = "Vocabulary of not known")}
            )
            TextField(value = state.vocabularyMeans,
                onValueChange ={onEvent(VocabularyEvent.vocabularyMeans(it))},
                placeholder = { Text(text = "Vocabulary Means")}
            )
            TextField(value = state.vocabularySentences,
                onValueChange ={onEvent(VocabularyEvent.vocabularySentences(it))},
                placeholder = { Text(text = "Examples of Sentences ")}
            )
            if(state.vocabularyError!="") {
                Text(text = state.vocabularyError)
            }


        }
        },
        buttons = { Row(modifier = Modifier.padding(8.dp)) {
            Button(onClick = { onEvent(VocabularyEvent.vocabularySubmit) }) {
                Text(text = "Record")
            }
            Spacer(modifier= Modifier.width(10.dp))
            Button(onClick = {onEvent(VocabularyEvent.HideDialog) }) {
                Text(text = "Close Dialog")
            }
        }
        })
}
@Composable
fun learnDialog(onEvent: (VocabularyEvent) -> Unit){
    AlertDialog(onDismissRequest = { onEvent(VocabularyEvent.LearnDialogHide)},
        title = { Text(text = "Change Vocabulary Page")},
        text = { Text(text = "If You think that You learn this vocabulary and that take a learn page")},
        buttons = {
            Button(onClick = { onEvent(VocabularyEvent.changeVocabularyPage)}) {
                Text(text = "Take the Learn Page")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = { onEvent(VocabularyEvent.LearnDialogHide) }) {
                Text(text = "Close Dialog")
            }
        })
}


@Composable
fun Navigation(navController: NavHostController){
    val view:VocabularyView = hiltViewModel() //viewModel()
    val viewLearn:learnView = hiltViewModel() //viewModel()
    val viewSearch:SearchView= viewModel()
    NavHost(navController = navController, startDestination ="home"  ){
        composable("home") {
            HomeScreen(view)
        }
        composable("learn") {
            LearnScreen(viewLearn)
        }
        composable("search") {
            Search(viewSearch)
        }
    }

}
@Composable
fun Search(view:SearchView){
    Column {

            TextField(
                value = view.vocabulary.value,
                placeholder={ Text(text = "Search Vocabulary Which You don't means")},
                modifier = Modifier.padding(20.dp),
                onValueChange ={newText->view.vocabulary.value=newText} )

               Button(onClick = { view.getWords(view.vocabulary.value) }, modifier = Modifier.padding(20.dp)) {
                   Text(text = "Search Means")
               }

        Text(text = view.means.value, modifier = Modifier.verticalScroll(rememberScrollState()))


    }
}



@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
){
    val backStackEntry=navController.currentBackStackEntryAsState()
    BottomNavigation(modifier=modifier, backgroundColor = Color.DarkGray, elevation = 5.dp)
    {
        items.forEach {
            item->
            val selected=item.route==backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Icon(imageVector = item.icon, contentDescription =item.name )
                //    if(selected) {
                  //      Text(
                   //         text = item.name,
                    //        textAlign = TextAlign.Center,
                     //       fontSize = 10.sp
                     //   )
                   // }
                })

        }
    }
}



//mainScreen(viewModel.vocabularyState,viewModel.allVocabulary,onEvent=viewModel::onEvent)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(view: VocabularyView ){

    HomePage(view.vocabularyState,view.allVocabulary.value.allVocabulary.collectAsState(initial = emptyList()).value,view::onEvent)

}

@Composable
fun HomePage(
    state: VocabularyAddState,
    vocabulary:List<Vocabulary>,
    onEvent: (VocabularyEvent) -> Unit
){
    if (state.dialogShowHide){
        AddVocabularyScreen(state,onEvent)
    }
    if (state.dialogLearn)(
        learnDialog(onEvent = onEvent)
    )


    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onEvent(VocabularyEvent.ShowDialog) }) {
            Icon(imageVector = Icons.Default.Add,contentDescription ="Add Vocabulary" )

        }
         })
    {

        Column(modifier = Modifier.padding(20.dp)) {

            Row() {
                TextField(value = state.findVocabulary, modifier = Modifier
                    .padding(33.dp),
                    placeholder = { Text(text = "Search Vocabulary in Your Records")},
                    onValueChange ={onEvent(VocabularyEvent.vocabularySearch(it))}
                )
            }
            LazyColumn( contentPadding = PaddingValues(30.dp)){
                items(vocabulary){
                        vocabulary->
                    Row() {

                        Card(border = BorderStroke(2.dp,Color.Black),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.padding(vertical = 10.dp)) {

                            Column() {


                                Row() {
                                    Text(text = vocabulary.vocabulary,
                                        fontSize = 24.sp, fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(5.dp))
                                    Button(onClick = { onEvent(VocabularyEvent.vocabularyLearn(vocabulary.id)) }, modifier = Modifier.weight(0.25f)) {
                                        Icon(imageVector = Icons.Default.Check, contentDescription = "I learned")
                                    }

                                    Button(onClick = { onEvent(VocabularyEvent.vocabularyEdit(vocabulary.id)) }, modifier = Modifier.weight(0.25f)) {
                                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Edit")
                                    }
                                }

                                Text(text = vocabulary.vocabularyMeans,fontSize = 24.sp,
                                    textAlign = TextAlign.Left, modifier = Modifier.padding(5.dp))
                                Text(text = vocabulary.vocabularySentences,fontSize = 24.sp,
                                    textAlign = TextAlign.Left, modifier = Modifier.padding(5.dp))

                            }
                            Spacer(modifier = Modifier.height(100.dp))
                        }




                    }
                }
            }
        }


    }
}
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LearnScreen(view: learnView){
    Column(){
             Row() {
                 TextField(
                     value = view.state.search,
                     placeholder = { Text(text ="Search Vocabulary")},
                     onValueChange ={view.onEvent(learnEvent.findVocabulary(it))},
                     modifier = Modifier.padding(20.dp) )
             }
             val vocabularyAll=view._learnVocabulary.value.allLearnVocabulary.collectAsState(initial = emptyList()).value
             LazyColumn( )
             {
                 items(vocabularyAll){
                         vocabulary->
                     Row() {
                         Card(border = BorderStroke(2.dp,Color.Black),
                             modifier = Modifier.padding(20.dp)) {

                             Column() {
                                 Row() {
                                     Text(text = vocabulary.vocabulary,
                                         fontSize = 24.sp, fontWeight = FontWeight.Bold,
                                         modifier = Modifier
                                             .weight(1f)
                                             .padding(5.dp))
                                 }
                                 Text(text = vocabulary.vocabularyMeans,fontSize = 24.sp,
                                     textAlign = TextAlign.Left, modifier = Modifier.padding(5.dp))
                                 Text(text = vocabulary.vocabularySentences,fontSize = 24.sp,
                                     textAlign = TextAlign.Left, modifier = Modifier.padding(5.dp))
                             }
                         }
                     }

                 }
             }
         }

}
