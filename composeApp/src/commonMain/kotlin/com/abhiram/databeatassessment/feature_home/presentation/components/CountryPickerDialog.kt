package com.abhiram.databeatassessment.feature_home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.abhiram.databeatassessment.core.util.CountryData
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import databeatassessment.composeapp.generated.resources.Res
import databeatassessment.composeapp.generated.resources.ic_search
import databeatassessment.composeapp.generated.resources.search_countries
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CountryPickerDialog(
    modifier: Modifier = Modifier,
    onSelect: (CountryData) -> Unit,
    onDismiss: () -> Unit,
) {
    var query by remember {
        mutableStateOf("")
    }

    val countries = remember(key1 = query) {
        mutableStateListOf<CountryData>().apply {
            if (query.isBlank()) {
                this.addAll(CountryData.entries)
            } else {
                this.addAll(CountryData.entries.filter { country ->
                    country.displayText.contains(other = query, ignoreCase = true)
                })
            }
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5))
                .background(MaterialTheme.colors.surface)
                .padding(15.dp)
        ) {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = {
                    Text(
                        text = stringResource(Res.string.search_countries),
                        style = MaterialTheme.typography.body1,
                    )
                },
                trailingIcon = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_search),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                singleLine = true,
                shape = CircleShape,
                modifier = Modifier.fillMaxWidth()
            )


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    items = countries,
                    key = {
                        it.countryFlag
                    }
                ) {
                    TextButton(
                        onClick = { onSelect(it); onDismiss() },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${it.countryFlag}  ${it.displayText}",
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}