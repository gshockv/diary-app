package com.gshockv.dairyapp.ui.screen.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

@Composable
fun EmptyPage(
  title: String = "Empty Diary",
  subtitle: String = "Write Something",
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(all = 24.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = title,
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Medium
    )
    Text(
      text = subtitle,
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Normal
    )
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewEmptyPage_LightTheme() {
  DiaryAppTheme {
    Surface {
      EmptyPage()
    }
  }
}

@Composable
@Preview(showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewEmptyPage_DarkTheme() {
  DiaryAppTheme {
    Surface {
      EmptyPage()
    }
  }
}
