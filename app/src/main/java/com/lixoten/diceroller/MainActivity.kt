package com.lixoten.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lixoten.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colors.background
                    color = Color.LightGray
                ) {
                    DiceRollerScreen()
                }
            }
        }
    }
}

@Composable
fun DiceRollerScreen() {
    var resultDiceRoll1 by remember { mutableStateOf(1) }
    var resultDiceRoll2 by remember { mutableStateOf(1) }

    val diceImageReference1 = getRollRusult(resultDiceRoll1)
    val diceImageReference2 = getRollRusult(resultDiceRoll2)

    val totalResult = resultDiceRoll1 + resultDiceRoll2

    Column(
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Row {
            Image(
                painter = painterResource(id = diceImageReference1),
                contentDescription = resultDiceRoll1.toString(),
            )

            Image(
                painter = painterResource(id = diceImageReference2),
                contentDescription = resultDiceRoll2.toString()
            )
        }

        Text(
            text = stringResource(R.string.text_message, totalResult),
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            resultDiceRoll1 = (1..6).random()
            resultDiceRoll2 = (1..6).random()
        }) {
            Text(text = stringResource(R.string.btn_dice_text))
        }
    }
}

fun getRollRusult(result: Int): Int {
    return when (result) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    DiceRollerTheme {
        DiceRollerScreen()
    }
}