package com.gshockv.dairyapp.ui.component

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme
import java.time.LocalDate

@SuppressLint("DefaultLocale")
@Composable
fun DateListHeader(
  localDate: LocalDate,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .padding(vertical = 14.dp)
      .background(MaterialTheme.colorScheme.surface)
  ) {
    Column(
      horizontalAlignment = Alignment.End
    ) {
      Text(
        text = String.format("%02d", localDate.dayOfMonth),
        style = TextStyle(
          fontSize = MaterialTheme.typography.titleLarge.fontSize,
          fontWeight = FontWeight.Light
        )
      )
      Text(
        text = localDate.dayOfWeek.toString().take(3),
        style = TextStyle(
          fontSize = MaterialTheme.typography.bodySmall.fontSize,
          fontWeight = FontWeight.Light
        )
      )
    }

    Spacer(modifier = Modifier.width(14.dp))

    Column(
      horizontalAlignment = Alignment.Start
    ) {
      Text(
        text = localDate.month.toString().lowercase().replaceFirstChar { it.titlecase() },
        style = TextStyle(
          fontSize = MaterialTheme.typography.titleLarge.fontSize,
          fontWeight = FontWeight.Light
        )
      )
      Text(
        text = "${localDate.year}",
        style = TextStyle(
          fontSize = MaterialTheme.typography.bodySmall.fontSize,
          fontWeight = FontWeight.Light
        ),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
      )
    }
  }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun PreviewDateListHeader_LightTheme() {
  DiaryAppTheme {
    Surface {
      DateListHeader(
        localDate = LocalDate.now()
      )
    }
  }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun PreviewDateListHeader_DarkTheme() {
  DiaryAppTheme {
    Surface {
      DateListHeader(
        localDate = LocalDate.now()
      )
    }
  }
}
