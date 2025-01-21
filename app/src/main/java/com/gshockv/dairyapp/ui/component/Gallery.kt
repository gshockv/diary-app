package com.gshockv.dairyapp.ui.component

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.model.GalleryImage
import com.gshockv.dairyapp.model.GalleryState
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import com.gshockv.dairyapp.ui.theme.Elevation
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
        AsyncImage(
          modifier = Modifier
            .size(imageSize)
            .clip(imageShape),
          model = ImageRequest.Builder(LocalContext.current)
            .data(it)
            .crossfade(true)
            .build(),
          contentScale = ContentScale.Crop,
          contentDescription = "Gallery Image"
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
fun GalleryUploader(
  galleryState: GalleryState,
  modifier: Modifier = Modifier,
  imageSize: Dp = 60.dp,
  imageShape: CornerBasedShape = Shapes().medium,
  spaceBetween: Dp = 12.dp,
  onAddClicked: () -> Unit,
  onImageSelect: (Uri) -> Unit,
  onImageClick: (GalleryImage) -> Unit
) {
  val multiplePhotoPicker = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 4)
  ) { images ->
    images.forEach {
      onImageSelect(it)
    }
  }

  BoxWithConstraints(
    modifier = modifier
  ) {
    val numberOfVisibleImages = remember {
      derivedStateOf {
        max(
          a = 0,
          b = this.maxWidth.div(spaceBetween + imageSize).toInt().minus(2)
        )
      }
    }
    val remainingImages = remember {
      derivedStateOf {
        galleryState.images.size - numberOfVisibleImages.value
      }
    }

    Row() {
      AddImageButton(
        imageSize = imageSize,
        imageShape = imageShape,
        onClick = {
          onAddClicked()
          multiplePhotoPicker.launch(
            PickVisualMediaRequest(
              ActivityResultContracts.PickVisualMedia.ImageOnly
            )
          )
        }
      )

      Spacer(modifier = Modifier.width(spaceBetween))

      galleryState.images.take(numberOfVisibleImages.value).forEach { galleryImage ->
        AsyncImage(
          modifier = Modifier
            .clip(imageShape)
            .size(imageSize)
            .clickable { onImageClick(galleryImage) },
          model = ImageRequest.Builder(LocalContext.current)
            .data(galleryImage.image)
            .crossfade(true)
            .build(),
          contentScale = ContentScale.Crop,
          contentDescription = "Gallery Image"
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
private fun AddImageButton(
  imageSize: Dp,
  imageShape: CornerBasedShape,
  onClick: () -> Unit
) {
  Surface(
    modifier = Modifier
      .size(imageSize)
      .clip(imageShape),
    onClick = onClick,
    tonalElevation = Elevation.Level1,
    color = MaterialTheme.colorScheme.primaryContainer
  ) {
    Box(
      modifier = Modifier.fillMaxSize(),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = Icons.Default.Add,
        contentDescription = "Add icon"
      )
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
