/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.plantdetail

import android.content.res.Configuration
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.google.android.material.composethemeadapter.MdcTheme
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel

@Composable
fun PlantDetailDescription(plantDetailViewModel: PlantDetailViewModel) {
    val plant by plantDetailViewModel.plant.observeAsState()

    plant?.let {
        PlantDetailContent(it)
    }
}

@Composable
fun PlantDetailContent(plant: Plant, modifier: Modifier = Modifier) {
    Surface {
        Column(modifier = modifier.padding(dimensionResource(id = R.dimen.margin_normal))) {
            PlantName(plantName = plant.name)
            PlantWatering(wateringInterval = plant.wateringInterval)
            PlantDescription(description = plant.description)
        }
    }
}

@Composable
fun PlantName(plantName: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = plantName,
            style = MaterialTheme.typography.h5,
            modifier = modifier
                .padding(horizontal = dimensionResource(R.dimen.margin_small))
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun PlantWatering(wateringInterval: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val centerWithHorizontalMargin =
            Modifier
                .padding(horizontal = dimensionResource(id = R.dimen.margin_small))
                .align(Alignment.CenterHorizontally)

        Text(
            text = stringResource(id = R.string.watering_needs_prefix),
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold,
            modifier = centerWithHorizontalMargin.padding(
                top = dimensionResource(id = R.dimen.margin_normal)
            )
        )

        val wateringIntervalText = LocalContext.current.resources.getQuantityString(
            R.plurals.watering_needs_suffix, wateringInterval, wateringInterval
        )
        Text(
            text = wateringIntervalText,
            modifier = centerWithHorizontalMargin.padding(
                bottom = dimensionResource(id = R.dimen.margin_normal)
            )
        )
    }
}

@Composable
fun PlantDescription(description: String) {
    val htmlDescription = remember(description) {
        HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    AndroidView(
        factory = { context ->
            TextView(context).apply {
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        update = {
            it.text = htmlDescription
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPlantDetailContent() {
    val plant = Plant("id", "Apple", "HTML<br><br>description", 3, 30, "")

    MdcTheme {
        PlantDetailContent(plant = plant)
    }
}

@Preview
@Composable
fun PreviewPlantName() {
    MdcTheme {
        PlantName(plantName = "SunFlower")
    }
}

@Preview
@Composable
private fun PlantDescriptionPreview() {
    MdcTheme {
        PlantDescription("HTML<br><br>description")
    }
}