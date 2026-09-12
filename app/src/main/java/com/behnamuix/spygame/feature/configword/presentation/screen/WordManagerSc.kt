package com.behnamuix.spygame.feature.configword.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.behnamuix.spygame.R
import com.behnamuix.spygame.core.theme.AppDimens
import com.behnamuix.spygame.feature.configword.domain.model.KeyWord
import com.behnamuix.spygame.feature.configword.presentation.contract.UiAction
import com.behnamuix.spygame.feature.configword.presentation.viewmodel.KeywordViewmodel

@Composable
fun WordManagerSc(
    modifier: Modifier = Modifier,
    keyWordVm: KeywordViewmodel = hiltViewModel()
) {
    val contex = LocalContext.current
    val uiState by keyWordVm.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        keyWordVm.onAction(UiAction.GetKeyword)
    }
    Scaffold() {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFFBE9A73))
        ) {
            Column(Modifier.padding(top = 32.dp)) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .border(
                            width = AppDimens.borderWidth,
                            color = Color.Black,
                            shape = MaterialTheme.shapes.large
                        )
                ) {
                    Column(
                        Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            "WORD MANAGER",
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        HorizontalDivider(thickness = 1.dp)
                        Text(
                            "Add your own words to the game and let the game use them in future rounds for a more personalized and exciting experience.",
                            color = MaterialTheme.colorScheme.primary,

                            style = MaterialTheme.typography.bodySmall
                        )
                        OutlinedTextField(
                            value = uiState.word,
                            onValueChange = { keyWordVm.onAction(UiAction.SetText(it)) },
                            placeholder = {
                                Text(
                                    text = "example word",
                                    textAlign = TextAlign.End,
                                    modifier=Modifier.fillMaxWidth(),
                                    style = MaterialTheme.typography.labelMedium,
                                    color = Color.Gray
                                )
                            },
                            leadingIcon = {
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
                                        .clickable {
                                            if (uiState.word.isEmpty()) {
                                                Toast.makeText(
                                                    contex,
                                                    "empty word!",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            } else {

                                                keyWordVm.onAction(
                                                    UiAction.AddKeyword(KeyWord(word = uiState.word))
                                                )
                                            }

                                        },
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
                                .height(60.dp),
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
                var list = listOf<String>(
                    "Table",
                    "Chair",
                    "Book",
                    "Pen",
                    "Pencil",
                    "Notebook",
                    "Phone",
                    "Laptop",
                    "Clock",
                    "Bag",
                    "Glass",
                    "Plate",
                    "Spoon",
                    "Fork",
                    "Lamp",
                    "Mirror",
                    "Television",
                    "Wardrobe",
                    "Bed",
                    "Window"
                )
                LazyVerticalGrid(
                    modifier = Modifier.padding(bottom = 60.dp),
                    columns = GridCells.Fixed(2)
                ) {
                    items(uiState.keyWords) {
                        Card(
                            elevation = CardDefaults.elevatedCardElevation(6.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            modifier = Modifier
                                .padding(horizontal = 16.dp, vertical = 6.dp)
                                .fillMaxWidth()
                                .height(64.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = it.word,
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.bodyLarge
                                )

                                Spacer(Modifier.weight(1f))

                                Icon(
                                    tint = MaterialTheme.colorScheme.error,
                                    painter = painterResource(R.drawable.icon_trash),
                                    contentDescription = "Delete",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                }
            }


        }
    }


}