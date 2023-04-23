package com.example.cal


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cal.ui.theme.CalTheme
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat
import java.math.RoundingMode

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Calculator()
                }
            }

        }
    }
}

@Composable
fun Calculator(){

    val df = DecimalFormat("#.####")
    df.roundingMode = RoundingMode.DOWN


    val answer = remember {
        mutableStateOf(0.0)
    }


    val operation = remember {
        mutableStateOf("")
    }


    val btnModifier = Modifier
        .padding(6.dp)
        .size(80.dp)
        .clip(CircleShape)



    // Button background colors

    val numBtnColor = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
    val opBtnColor = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
    val equalBtnColor = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
    
    Column(
        modifier = Modifier
        .fillMaxSize()
    ) {

        Text(text = operation.value, style = TextStyle(
            fontSize = 24.sp,
            textAlign = TextAlign.End
        ),
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
        )

        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.5f)
                .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)

        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 0.dp)
            ) {
                Text(text = df.format(answer.value), style = TextStyle(
                    fontSize = 32.sp,
                ))
                Button(onClick = { operation.value = ""; answer.value = 0.0 }, modifier = btnModifier, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)) {
                    Text(text = "AC", color = Color.White, fontSize = 20.sp)
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1.5f)
                .fillMaxSize()
            ) {
            Button(onClick = { operation.value = operation.value.plus("7") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "7", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus("8") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "8", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus("9") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "9", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus(" * ") }, modifier = btnModifier, colors = opBtnColor) {
                Text(text = "X", color = Color.White, fontSize = 20.sp)
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1.5f)
                .fillMaxSize()
        ) {
            Button(onClick = { operation.value = operation.value.plus("4") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "4", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus("5") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "5", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus("6") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "6", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus(" / ")
            }, modifier = btnModifier, colors = opBtnColor) {
                Text(text = "/", color = Color.White, fontSize = 20.sp)
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1.5f)
                .fillMaxSize()
        ) {
            Button(onClick = { operation.value = operation.value.plus("1") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "1", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus("2") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "2", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus("3") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "3", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus(" + ") }, modifier = btnModifier, colors = opBtnColor) {
                Text(text = "+", color = Color.White, fontSize = 20.sp)
            }
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .weight(1.5f)
                .fillMaxSize()
        ) {
            Button(onClick = { operation.value = operation.value.plus("0") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = "0", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus(".") }, modifier = btnModifier, colors = numBtnColor) {
                Text(text = ".", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { answer.value =
                ExpressionBuilder(operation.value).build().evaluate()
            }, modifier = btnModifier, colors = equalBtnColor) {
                Text(text = "=", color = Color.White, fontSize = 20.sp)
            }
            Button(onClick = { operation.value = operation.value.plus("-") }, modifier = btnModifier, colors = opBtnColor) {
                Text(text = "-", color = Color.White, fontSize = 20.sp)
            }
        }

    }
}



@Preview
@Composable
fun CalculatorPreview(){
    Calculator()
}

