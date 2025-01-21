package com.gshockv.dairyapp.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import com.gshockv.dairyapp.R
import com.gshockv.dairyapp.ui.theme.AngryColor
import com.gshockv.dairyapp.ui.theme.AwfulColor
import com.gshockv.dairyapp.ui.theme.BoredColor
import com.gshockv.dairyapp.ui.theme.CalmColor
import com.gshockv.dairyapp.ui.theme.DepressedColor
import com.gshockv.dairyapp.ui.theme.DisappointedColor
import com.gshockv.dairyapp.ui.theme.HappyColor
import com.gshockv.dairyapp.ui.theme.HumorousColor
import com.gshockv.dairyapp.ui.theme.LonelyColor
import com.gshockv.dairyapp.ui.theme.MysteriousColor
import com.gshockv.dairyapp.ui.theme.NeutralColor
import com.gshockv.dairyapp.ui.theme.RomanticColor
import com.gshockv.dairyapp.ui.theme.ShamefulColor
import com.gshockv.dairyapp.ui.theme.SurprisedColor
import com.gshockv.dairyapp.ui.theme.SuspiciousColor
import com.gshockv.dairyapp.ui.theme.TenseColor

enum class Mood(
  @DrawableRes val icon: Int,
  val contentColor: Color,
  val containerColor: Color
) {
  Neutral(
    icon = R.drawable.ic_mood_neutral,
    contentColor = Color.Black,
    containerColor = NeutralColor
  ),
  Happy(
    icon = R.drawable.ic_mood_happy,
    contentColor = Color.Black,
    containerColor = HappyColor
  ),
  Angry(
    icon = R.drawable.ic_mood_angry,
    contentColor = Color.White,
    containerColor = AngryColor
  ),
  Bored(
    icon = R.drawable.ic_mood_bored,
    contentColor = Color.Black,
    containerColor = BoredColor
  ),
  Calm(
    icon = R.drawable.ic_mood_calm,
    contentColor = Color.Black,
    containerColor = CalmColor
  ),
  Depressed(
    icon = R.drawable.ic_mood_depressed,
    contentColor = Color.Black,
    containerColor = DepressedColor
  ),
  Disappointed(
    icon = R.drawable.ic_mood_disappointed,
    contentColor = Color.White,
    containerColor = DisappointedColor
  ),
  Humorous(
    icon = R.drawable.ic_mood_humorous,
    contentColor = Color.Black,
    containerColor = HumorousColor
  ),
  Lonely(
    icon = R.drawable.ic_mood_lonely,
    contentColor = Color.White,
    containerColor = LonelyColor
  ),
  Mysterious(
    icon = R.drawable.ic_mood_mysterious,
    contentColor = Color.Black,
    containerColor = MysteriousColor
  ),
  Romantic(
    icon = R.drawable.ic_mood_romantic,
    contentColor = Color.White,
    containerColor = RomanticColor
  ),
  Shameful(
    icon = R.drawable.ic_mood_shameful,
    contentColor = Color.White,
    containerColor = ShamefulColor
  ),
  Awful(
    icon = R.drawable.ic_mood_awful,
    contentColor = Color.Black,
    containerColor = AwfulColor
  ),
  Surprised(
    icon = R.drawable.ic_mood_surprised,
    contentColor = Color.Black,
    containerColor = SurprisedColor
  ),
  Suspicious(
    icon = R.drawable.ic_mood_suspicious,
    contentColor = Color.Black,
    containerColor = SuspiciousColor
  ),
  Tense(
    icon = R.drawable.ic_mood_tense,
    contentColor = Color.Black,
    containerColor = TenseColor
  )
}
