package com.gshockv.dairyapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import kotlin.math.max

@Composable
fun Gallery(
  images: List<Int>,
  modifier: Modifier = Modifier,
  imageSize: Dp = 40.dp,
  spaceBetween: Dp = 10.dp,
  imageShape: CornerBasedShape = Shapes().small
) {
  BoxWithConstraints(
    modifier = modifier
  ) {
    val numberOfVisibleImages = remember {
      derivedStateOf {
        max(
          a = 0,
          b = this.maxWidth.div(spaceBetween + imageSize).toInt().minus(1)
        )
      }
    }
    val remainingImages = remember {
      derivedStateOf {
        images.size - numberOfVisibleImages.value
      }
    }

    Row() {
      images.take(numberOfVisibleImages.value).forEach {
        Image(
          painter = painterResource(it),
          contentDescription = null,
          modifier = Modifier
            .clip(imageShape)
            .size(imageSize),
          contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(spaceBetween))
      }
      if (remainingImages.value > 0) {
        LastImageOverlay(
          imageSize = imageSize,
          imageShape = imageShape,
          remainingImages = remainingImages.value
        )
      }
    }
  }
}

@Composable
private fun LastImageOverlay(
  imageSize: Dp,
  remainingImages: Int,
  imageShape: CornerBasedShape
) {
  Box(
    contentAlignment = Alignment.Center
  ) {
    Surface(
      modifier = Modifier
        .clip(imageShape)
        .size(imageSize),
      color = MaterialTheme.colorScheme.primaryContainer
    ) { }
    Text(
      text = "+$remainingImages",
      style = TextStyle(
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontWeight = FontWeight.Medium
      ),
      color = MaterialTheme.colorScheme.onPrimaryContainer
    )
  }
}

@Composable
@Preview(showBackground = true)
private fun PreviewGallery() {
  DiaryAppTheme {
    Surface {
      Gallery(
        images = galleryTestImages
      )
    }
  }
}

val galleryTestImages = listOf(
  R.drawable.img_test_bg_1,
  R.drawable.img_test_bg_2,
  R.drawable.img_test_bg_3,
  R.drawable.img_test_bg_4,
  R.drawable.img_test_bg_5,
  R.drawable.img_test_bg_6,
  R.drawable.img_test_bg_7,
  R.drawable.img_test_bg_8,
  R.drawable.img_test_bg_9,
  R.drawable.img_test_bg_10,
)
