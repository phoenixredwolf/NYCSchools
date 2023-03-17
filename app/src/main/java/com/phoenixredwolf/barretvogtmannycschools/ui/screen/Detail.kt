package com.phoenixredwolf.barretvogtmannycschools.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.phoenixredwolf.barretvogtmannycschools.data.model.School
import com.phoenixredwolf.barretvogtmannycschools.utility.MockData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, scrollState: ScrollState, school: School) {
    Scaffold(
        topBar = {
            DetailTopAppBar(school, onBackPressed = {navController.popBackStack()})
        }
//        modifier = Modifier.padding(8.dp)
    ) {
       Column(
           modifier = Modifier
               .fillMaxWidth()
               .padding(8.dp,)
               .verticalScroll(scrollState)
       ) {
           ConstraintLayout(
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(8.dp)
           ) {
               val (address,phone,city,state,zip,fax,neighborhood, boro) = createRefs()
               Text(
                   text = school.primary_address_line_1!!,
                   fontSize = 12.sp,
                   modifier = Modifier.constrainAs(address) {
                       start.linkTo(parent.start)
                       top.linkTo(parent.top)
                   }
               )
               Text(
                   text = "Phone: ${school.phone_number!!}",
                   fontSize = 12.sp,
                   modifier = Modifier.constrainAs(phone) {
                   top.linkTo(parent.top)
                   end.linkTo(parent.end)
               })
               Text(
                   text = "${school.city!!}, ",
                   fontSize = 12.sp,
                   modifier = Modifier.constrainAs(city) {
                       start.linkTo(parent.start)
                       top.linkTo(address.bottom)
                   }
               )
               Text(
                   text = "${school.state_code}  ",
                   fontSize = 12.sp,
                   modifier = Modifier.constrainAs(state) {
                       start.linkTo(city.end)
                       top.linkTo(address.bottom)
                   }
               )
               Text(
                   text = school.zip!!,
                   fontSize = 12.sp,
                   modifier = Modifier.constrainAs(zip) {
                       start.linkTo(state.end)
                       top.linkTo(address.bottom)
                   }
               )
               Text(
                   text = "Fax: ${school.fax_number}",
                   fontSize = 12.sp,
                   modifier = Modifier.constrainAs(fax) {
                       end.linkTo(parent.end)
                       top.linkTo(phone.bottom)
                   }
               )
               Text(
                   text = "Neighborhood: ${school.neighborhood}",
                   fontSize = 12.sp,
                   modifier = Modifier.constrainAs(neighborhood) {
                       start.linkTo(parent.start)
                       top.linkTo(city.bottom)
                   }
               )
               Text(
                   text = "Boro: ${school.boro}",
                   fontSize = 12.sp,
                   modifier = Modifier.constrainAs(boro) {
                       end.linkTo(parent.end)
                       top.linkTo(fax.bottom)
                   }
               )
           }
           Row(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp),
               horizontalArrangement = Arrangement.SpaceBetween
           ) {
                StringHyperlink(url = school.website!!)
           }
           school.overview_paragraph?.let {
               Text(text = "Overview", textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.overview_paragraph, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.addtl_info1?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Additional Info", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.addtl_info1, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.extracurricular_activities?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Extraurricular Activities", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.extracurricular_activities, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.language_classes?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Available Language Classes", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.language_classes, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.ell_programs?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "English Programs", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.ell_programs, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.school_sports?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "School Sports", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.school_sports, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.psal_sports_girls?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Girls Sports Teams", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.psal_sports_girls, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.psal_sports_boys?.let {
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Boys Sports Teams", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
                Text(text = school.psal_sports_boys, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.psal_sports_coed?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Coed Sports", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.psal_sports_coed, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.diplomaendorsements?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Additional Info", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.diplomaendorsements, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.school_accessibility_description?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "School Accessibility", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.school_accessibility_description, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.subway?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Nearest Subway Lines", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = if (school.subway == "N/A") "None" else school.subway, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
           school.bus?.let {
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Nearest Bus Lines", fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(start = 8.dp))
               Text(text = school.bus, modifier = Modifier.padding(start = 8.dp, end = 8.dp))
           }
       }
    }
}

@Composable
fun DetailTopAppBar(school: School, onBackPressed: () -> Unit = {}) {
    TopAppBar(
        title = { Text(
            text = school.school_name!!,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        ) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow")
            }
        }
    )
}

@Composable
fun StringHyperlink(url: String) {
    val annotatedLinkString = buildAnnotatedString {

        val link = "https://$url"
        val startIndex = 0
        val endIndex = link.length

        append(link)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ), start = startIndex, end = endIndex
        )
        addStringAnnotation(
            tag = "URL",
            annotation = "https://$url",
            start = startIndex,
            end = endIndex
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        text = annotatedLinkString, 
        onClick = {
            annotatedLinkString.getStringAnnotations("URL", it, it).firstOrNull()
                ?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        })
}

@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    DetailScreen(navController = rememberNavController(), scrollState = rememberScrollState(), school = MockData.schoolList[1])
}