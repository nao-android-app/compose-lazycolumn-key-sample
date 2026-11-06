package com.nao.lazycolumnkeysample

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nao.lazycolumnkeysample.model.Fruit
import com.nao.lazycolumnkeysample.ui.theme.LazyColumnKeySampleTheme

private val initialFruits = listOf(
    Fruit(1, "Apple"),
    Fruit(2, "Banana"),
    Fruit(3, "Orange")
)

private val grape = Fruit(
    id = 4,
    name = "Grape"
)

private const val TAG = "LazyColumnKey"

@Composable
fun LazyColumnKeyScreen(
    modifier: Modifier = Modifier
) {
    var fruits by remember { mutableStateOf(initialFruits) }
    var useKey by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text("Use key")

            Switch(
                checked = useKey,
                onCheckedChange = {
                    useKey = it
                }
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = {
                    fruits = initialFruits
                }
            ) {
                Text("Reset")
            }

            Button(
                onClick = {
                    if (fruits.none { it.id == 4 }) {
                        fruits = listOf(grape) + fruits
                    }
                }
            ) {
                Text("Add Grape to Top")
            }
        }

        LazyColumn {
            // keyの有無を切り替えて挙動を比較
            if (useKey) {
                items(
                    items = fruits,
                    key = { it.id }
                ) { fruit ->
                    FruitRow(fruit)
                }
            } else {
                items(fruits) { fruit ->
                    FruitRow(fruit)
                }
            }
        }
    }
}

@Composable
private fun FruitRow(
    fruit: Fruit
) {
    val rememberId = remember {
        (1000..9999).random()
    }

    var isFavorite by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconToggleButton(
            checked = isFavorite,
            onCheckedChange = {
                isFavorite = it
            }
        ) {
            Icon(
                imageVector = if (isFavorite) {
                    Icons.Filled.Favorite
                } else {
                    Icons.Outlined.FavoriteBorder
                },
                contentDescription = null
            )
        }

        Column {
            Text(fruit.name)
            Text(
                text = "rememberId=$rememberId",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
    Log.d(
        TAG,
        "fruit=${fruit.name}, rememberId=$rememberId, isFavorite=$isFavorite"
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewLazyColumnKeyScreen() {
    LazyColumnKeySampleTheme {
        LazyColumnKeyScreen()
    }
}
