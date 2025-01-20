package com.gshockv.dairyapp.ui.screen.write

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.data.Mood
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

@Composable
fun WriteContent(
  title: String,
  onTitleChanged: (String) -> Unit,
  description: String,
  onDescriptionChanged: (String) -> Unit,
  onSaveClick: () -> Unit,
  moodPagerState: PagerState,
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val scrollState = rememberScrollState()

  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(bottom = 24.dp)
      .padding(horizontal = 24.dp),
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    Column(
      modifier = Modifier
        .weight(1f)
        .verticalScroll(scrollState)
    ) {
      Spacer(modifier = Modifier.height(30.dp))

      HorizontalPager(
        state = moodPagerState
      ) { page ->
        Box(
          modifier = Modifier.fillMaxWidth(),
          contentAlignment = Alignment.Center
        ) {
          Image(
            painter = painterResource(id = Mood.entries[page].icon),
            contentDescription = null,
            modifier = Modifier.size(120.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(30.dp))

      TextField(
        modifier = Modifier.fillMaxWidth(),
        value = title,
        onValueChange = onTitleChanged,
        placeholder = {
          Text(text = "Title")
        },
        colors = TextFieldDefaults.colors(
          unfocusedContainerColor = Color.Transparent,
          focusedContainerColor = Color.Transparent,
          focusedIndicatorColor = Color.Unspecified,
          disabledIndicatorColor = Color.Unspecified,
          unfocusedIndicatorColor = Color.Unspecified,
          focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
          unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ),
        keyboardOptions = KeyboardOptions(
          imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
          onNext = {}
        ),
        maxLines = 1,
        singleLine = true
      )

      TextField(
        modifier = Modifier.fillMaxWidth(),
        value = description,
        onValueChange = onDescriptionChanged,
        placeholder = {
          Text(text = "Tell something about it")
        },
        colors = TextFieldDefaults.colors(
          unfocusedContainerColor = Color.Transparent,
          focusedContainerColor = Color.Transparent,
          focusedIndicatorColor = Color.Unspecified,
          disabledIndicatorColor = Color.Unspecified,
          unfocusedIndicatorColor = Color.Unspecified,
          focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
          unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
        ),
        keyboardOptions = KeyboardOptions(
          imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
          onNext = {}
        )
      )
    }

    Column(
      verticalArrangement = Arrangement.Bottom
    ) {
      Spacer(modifier = Modifier.height(12.dp))
      Button(
        onClick = {
          if (title.isNotEmpty() && description.isNotEmpty()) {
            onSaveClick()
          } else {
            Toast.makeText(
              context,
              "Fields are empty",
              Toast.LENGTH_SHORT
            ).show()
          }
        },
        modifier = Modifier
          .fillMaxWidth()
          .height(54.dp),
        shape = Shapes().small
      ) {
        Text(
          text = "Save"
        )
      }
    }
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewWriteContent_LightTheme() {
  DiaryAppTheme {
    Surface {
      WriteContent(
        title = "",
        onTitleChanged = {},
        description = "",
        onDescriptionChanged = {},
        moodPagerState = rememberPagerState(pageCount = { Mood.entries.size }),
        onSaveClick = {}
      )
    }
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewWriteContent_DarkTheme() {
  DiaryAppTheme {
    Surface {
      WriteContent(
        title = "",
        onTitleChanged = {},
        description = "",
        onDescriptionChanged = {},
        moodPagerState = rememberPagerState(pageCount = { Mood.entries.size }),
        onSaveClick = {}
      )
    }
  }
}
