package com.alife.vegan.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alife.vegan.network.response.GetFoodByPriceResponseItem
import com.alife.vegan.ui.theme.Color_Alife_Cyan
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FoodItem(
    changeSelected: (GetFoodByPriceResponseItem) -> Unit,
    item: GetFoodByPriceResponseItem
) {
    var isSelected = remember {
        mutableStateOf(item.isSelected)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Card(
            modifier = Modifier.size(70.dp),
            shape = CircleShape,
            elevation = 2.dp
        ) {
            GlideImage(
                imageModel = item.product_image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.6f)) {
                Text(
                    maxLines = 1,
                    text = item.product_name,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                )
                Text(
                    text = "${item.calory}kcal", fontSize = 13.sp,
                )
            }
            Text(
                maxLines = 1,
                modifier = Modifier.weight(0.4f),
                fontSize = 14.sp,
                text = "${item.price}원",
                textAlign = TextAlign.End
            )
        }
        Checkbox(
            checked = item.isSelected,
            onCheckedChange = { changeSelected(item) }
//                isSelected.value = !isSelected.value
            ,
            colors = CheckboxDefaults.colors(
                Color_Alife_Cyan
            )
        )
    }
}