package com.gshockv.dairyapp.ui.screen.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.ui.component.GoogleButton
import com.gshockv.dairyapp.ui.theme.DiaryAppTheme

@Composable
fun AuthContent(
  loadingState: Boolean,
  onButtonClicked: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(
      modifier = Modifier
        .weight(weight = 1f)
        .fillMaxWidth()
        .padding(all = 40.dp)
    ) {
      Column(
        modifier = Modifier
          .weight(weight = 10f)
          .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Image(
          modifier = Modifier.size(120.dp),
          painter = painterResource(id = R.drawable.google_logo),
          contentDescription = "Google Logo"
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
          text = stringResource(id = R.string.auth_title),
          fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
        Text(
          text = stringResource(id = R.string.auth_subtitle),
          color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f),
          style = MaterialTheme.typography.bodyMedium
        )
      }

      Column(
        modifier = Modifier.weight(weight = 2f),
        verticalArrangement = Arrangement.Bottom
      ) {
        GoogleButton(
          loadingState = loadingState,
          onClick = onButtonClicked
        )
      }
    }
  }
}

@Composable
@Preview(showSystemUi = true)
private fun PreviewAuthContent() {
  DiaryAppTheme {
    AuthContent(
      loadingState = false,
      onButtonClicked = {}
    )
  }
}

