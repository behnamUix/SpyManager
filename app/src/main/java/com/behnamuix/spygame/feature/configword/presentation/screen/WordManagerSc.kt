package com.behnamuix.spygame.feature.configword.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.behnamuix.spygame.feature.configword.presentation.viewmodel.KeywordViewmodel

@Composable
fun WordManagerSc(modifier: Modifier = Modifier, keyWordVm: KeywordViewmodel = hiltViewModel()) {
    var list = listOf<String>(
        "میز",
        "صندلی",
        "کتاب",
        "خودکار",
        "مداد",
        "دفتر",
        "گوشی",
        "لپ‌تاپ",
        "ساعت",
        "کیف",
        "لیوان",
        "بشقاب",
        "قاشق",
        "چنگال",
        "چراغ",
        "آینه",
        "تلویزیون",
        "کمد",
        "تخت",
        "پنجره"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBE9A73))
    ) {
        Column() {
            Card() {
                Column() {
                    Text("WORD MANAGER", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "You can add your own words in this section",
                        style = MaterialTheme.typography.bodySmall
                    )
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        placeholder = {
                            Text(
                                text = "example word",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.Gray
                            )
                        },
                        trailingIcon = {
                            Box(
                                modifier = Modifier
                                    .padding(6.dp)
                                    .size(32.dp)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(7.dp)
                                    )
                                    .border(
                                        width = 1.dp,
                                        color = Color.Black,
                                        shape = RoundedCornerShape(7.dp)
                                    )
                                    .clickable {},
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add",
                                    tint = Color.Black,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(46.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,

                            focusedPlaceholderColor = Color.Gray,
                            unfocusedPlaceholderColor = Color.Gray,

                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,

                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,

                            cursorColor = Color.Black,

                            focusedTrailingIconColor = Color.Black,
                            unfocusedTrailingIconColor = Color.Black
                        ),
                        singleLine = true
                    )
                }
            }
            LazyHorizontalGrid(rows = GridCells.Fixed(2)) {
                items(list) {
                    Card() {
                        Row() {
                            Text(it)
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = ""
                            )
                        }
                    }
                }
            }
        }


    }


}