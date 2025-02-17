package com.example.interviewcityinfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.interviewcityinfoapp.ui.theme.InterviewCityInfoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            InterviewCityInfoAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(color = MaterialTheme.colorScheme.background) {
                        MainActivityScreen(modifier = Modifier.padding(innerPadding))
                    }
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen(modifier: Modifier) {
    val mainActivityViewModel = viewModel<MainActivityViewModel>()

    CityListTopAppBar(mainActivityViewModel)
    CityList(modifier, mainActivityViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListTopAppBar(mainActivityViewModel: MainActivityViewModel, ) {
    val isOptionsExpanded by mainActivityViewModel.isOptionsExpanded.collectAsStateWithLifecycle()

    TopAppBar(title = { Text("City List") }, navigationIcon = {
        IconButton(onClick = {  }) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings")
        }
    }, actions = {
        IconButton(onClick = { mainActivityViewModel.updateIsOptionsExpandedState(true) }) {
            Icon(Icons.Filled.MoreVert, contentDescription = "Menu")
        }
        DropdownMenu(
            expanded = isOptionsExpanded,
            onDismissRequest = {mainActivityViewModel.updateIsOptionsExpandedState(false)}
        ) {
            DropdownMenuItem(
                onClick = { mainActivityViewModel.sortCityInfoListAscending() },
                text = {
                    Text("Sort by Ascending")
                },
            )
            DropdownMenuItem(
                onClick = { mainActivityViewModel.sortCityInfoListDescending() },
                text = {
                    Text("Sort by Descending")
                },
            )
        }
    })

}

@Composable
fun CityList(
    modifier: Modifier,
    mainActivityViewModel: MainActivityViewModel
) {
    val cityInfoList = mainActivityViewModel.cityListState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(5.dp)
    ) {
        items(cityInfoList.value.orEmpty()) { cityInfo ->
            Text(cityInfo.name)
            Text(cityInfo.state)
            Text(cityInfo.id.toString())
        }
    }
}