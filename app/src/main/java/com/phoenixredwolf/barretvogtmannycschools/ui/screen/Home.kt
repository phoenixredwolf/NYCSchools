package com.phoenixredwolf.barretvogtmannycschools.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.phoenixredwolf.barretvogtmannycschools.data.model.School
import com.phoenixredwolf.barretvogtmannycschools.ui.viewmodel.MainViewModel
import com.phoenixredwolf.barretvogtmannycschools.R
import com.phoenixredwolf.barretvogtmannycschools.components.ErrorUI
import com.phoenixredwolf.barretvogtmannycschools.components.LoadingUI
import com.phoenixredwolf.barretvogtmannycschools.ui.theme.BarretVogtmanNYCSchoolsTheme

@Composable
fun Home(
    navController: NavController,
    schools: List<School>,
    viewModel: MainViewModel,
    isLoading: MutableState<Boolean>,
    isError: MutableState<Boolean>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val resultList = mutableListOf<School>()
        resultList.addAll(schools)

        when {
            isLoading.value -> {
                LoadingUI()
            }
            isError.value -> {
                ErrorUI()
            } else -> {
                LazyColumn {
                    items(resultList.size) {
                        index ->
                        SchoolItem(school = resultList[index], onSchoolClick = {
                            navController.navigate("Detail/${index}")
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun SchoolItem(school: School, onSchoolClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onSchoolClick() },
        backgroundColor = colorResource(id = R.color.purple_700),
        elevation = 6.dp,
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, top = 8.dp, bottom = 8.dp )
        ) {
            school.school_name?.let {
                Text(
                    text = school.school_name,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
            school.primary_address_line_1?.let {
                Text(text = school.primary_address_line_1)
            }
            Row {
                school.city?.let {
                    Text(text = "${school.city}, ")
                }
                school.state_code?.let {
                    Text(text = "${school.state_code}  ")
                }
                school.zip?.let {
                    Text(text = school.zip)
                }
            }
            school.phone_number?.let {
                Text(text = school.phone_number)
            }
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultSchoolItemPreview() {
    val schoolItem = School(
        school_name = "Clinton School Writers & Artists, M.S. 260",
        primary_address_line_1 = "10 East 15th Street",
        city = "Manhattan",
        state_code = "NY",
        zip = "10003",
        website = "www.theclintonschool.net"
    )
    BarretVogtmanNYCSchoolsTheme {
        SchoolItem(school = schoolItem)
    }
}
