package com.gshockv.dairyapp.ui.component

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.data.Diary
import com.gshockv.dairyapp.data.Mood
import com.gshockv.dairyapp.data.testImages
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import com.gshockv.dairyapp.ui.theme.Elevation
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DiaryHolder(
  diary: Diary,
  onClick: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  val localDensity = LocalDensity.current
  var componentHeight by remember {
    mutableStateOf(0.dp)
  }
  var galleryOpened by remember {
    mutableStateOf(false)
  }

  Row(
    modifier = modifier
      .clickable(
        indication = null,
        interactionSource = remember {
          MutableInteractionSource()
        }
      ) { onClick(diary.id) }
  ) {
    Spacer(modifier = Modifier.width(14.dp))
    Surface(
      modifier = Modifier
        .width(2.dp)
        .height(componentHeight + 14.dp),
      tonalElevation = Elevation.Level1
    ) { }
    Spacer(modifier = Modifier.width(20.dp))

    Surface(
      modifier = Modifier
        .clip(
          shape = Shapes().medium
        )
        .onGloballyPositioned {
          componentHeight = with(localDensity) {
            it.size.height.toDp()
          }
        },
      tonalElevation = Elevation.Level1
    ) {
      Column(
        modifier = Modifier.fillMaxWidth()
      ) {
        DiaryHeader(
          mood = diary.mood,
          time = diary.date
        )
        Text(
          text = diary.description,
          modifier = Modifier.padding(all = 14.dp),
          style = MaterialTheme.typography.bodyLarge,
          maxLines = 4,
          overflow = TextOverflow.Ellipsis
        )

        if (diary.testImages.isNotEmpty()) {
          ShowGalleryButton(
            galleryOpened = galleryOpened,
            onClick = {
              galleryOpened = !galleryOpened
            }
          )
        }
        AnimatedVisibility(
          visible = galleryOpened,
          enter = fadeIn() + expandVertically(
            animationSpec = spring(
              dampingRatio = Spring.DampingRatioMediumBouncy,
              stiffness = Spring.StiffnessLow
            )
          )
        ) {
          Column(
            modifier = Modifier.padding(all = 14.dp)
          ) {
            Gallery(
              images = diary.testImages
            )
          }
        }
      }
    }
  }
}

@Composable
private fun DiaryHeader(
  mood: Mood,
  time: LocalDateTime,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .background(mood.containerColor)
      .padding(horizontal = 14.dp, vertical = 7.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(id = mood.icon),
        contentDescription = "Mood icon",
        modifier = Modifier.size(18.dp)
      )
      Spacer(modifier = Modifier.width(7.dp))
      Text(
        text = mood.name,
        color = mood.contentColor,
        style = MaterialTheme.typography.bodyMedium
      )
    }
    Text(
      text = time.format(DateTimeFormatter.ofPattern("HH:mm")),
      color = mood.contentColor,
      style = MaterialTheme.typography.bodyMedium
    )
  }
}

@Composable
private fun ShowGalleryButton(
  galleryOpened: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  TextButton(
    modifier = modifier,
    onClick = onClick
  ) {
    Text(
      text = if (galleryOpened) {
        stringResource(R.string.hide_gallery)
      } else {
        stringResource(R.string.show_gallery)
      }
    )
  }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewDiaryHolder_LightTheme() {
  DiaryAppTheme {
    Surface {
      DiaryHolder(
        diary = Diary(
          id = 1,
          title = "Test Diary",
          description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
              "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
              "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
          mood = Mood.Happy,
          images = listOf(),
          date = LocalDateTime.now()
        ),
        onClick = { }
      )
    }
  }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewDiaryHolder_DarkTheme() {
  DiaryAppTheme {
    Surface {
      DiaryHolder(
        diary = Diary(
          id = 1,
          title = "Test Diary",
          description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " +
              "tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis " +
              "nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis ",
          mood = Mood.Happy,
          images = listOf(),
          date = LocalDateTime.now()
        ),
        onClick = { }
      )
    }
  }
}
