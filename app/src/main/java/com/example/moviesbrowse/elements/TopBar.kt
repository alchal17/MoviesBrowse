package com.example.moviesbrowse.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviesbrowse.R
import com.example.moviesbrowse.ui.theme.LightGray

@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Featured", style = TextStyle(
                color = LightGray,
                fontSize = 35.sp,
                fontFamily = FontFamily(Font(R.font.oswald_semi_bold, FontWeight.Normal))
            ), modifier = Modifier.align(Alignment.Center)
        )
    }
}