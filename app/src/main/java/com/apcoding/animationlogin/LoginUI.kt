package com.apcoding.animationlogin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.apcoding.animationlogin.ui.theme.P2PBackground
import com.apcoding.animationlogin.ui.theme.TextColor
import com.apcoding.animationlogin.ui.theme.monteSB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginUI() {
    var password by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val passwordVisible = rememberSaveable { mutableStateOf(false) }
    var isChecking by remember { mutableStateOf(false) }
    var isCheckingJob: Job? = null // Initialize isCheckingJob
    var trigSuccess by remember { mutableStateOf(false) }
    var trigFail by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(P2PBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(P2PBackground),
            contentAlignment = Alignment.Center
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    Color.Transparent  // Set the card background color to transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(0.8f)
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    ComposableRiveAnimationView( // Add the RiveAnimationView
                        animation = R.raw.login,
                        modifier = Modifier
                            .size(400.dp)
                            .padding(bottom = 180.dp)
                    ) { view -> // This is a lambda block that receives a view parameter, which represents the RiveAnimationView. Inside this lambda block, you interact with the Rive animation view to control its behavior based on various states and triggers.
                        view.setBooleanState( // Set the boolean state of the Rive animation view
                            "Login Machine",
                            "isHandsUp",
                            passwordVisible.value
                        )
                        view.setBooleanState(
                            "Login Machine",
                            "isChecking",
                            isChecking
                        )
                        if (trigFail) //his code checks the trigFail variable. If it is true, it triggers a specific state in the Rive animation. The fireState function is used to transition the state machine in the Rive animation to the state specified. In this case, if trigFail is true, it fires the "trigFail" state in the "Login Machine."
                            view.fireState("Login Machine", "trigFail")
                        if (trigSuccess)
                            view.fireState("Login Machine", "trigSuccess")
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(0.6f)
                    .padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
                    .align(Alignment.BottomCenter),
                colors = CardDefaults.cardColors(Color(0xFFFFFFFF)),
                shape = RoundedCornerShape(15.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 35.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontFamily = monteSB,
                            )
                        ) {
                            append("Welcome")
                        }
                        append(" ")
                        withStyle(
                            SpanStyle(
                                color = Color.Black.copy(0.89f),
                                fontFamily = monteSB
                            )
                        ) {
                            append("to")
                        }
                    }, fontSize = 25.sp)
                    Text(text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontFamily = monteSB,
                            )
                        ) {
                            append("Rive")
                        }
                    }, fontSize = 25.sp)


                    Spacer(modifier = Modifier.height(30.dp))
                    TextFieldWithIcons(
                        textValue = "Email ID",
                        placeholder = "Enter Your Email",
                        icon = Icons.Filled.Email,
                        mutableText = email,
                        onValueChanged = {
                            email = it
                        },
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next, //The imeAction parameter, set to ImeAction.Next, is used in Jetpack Compose TextField to specify the action that should be taken when the user presses the "Next" button on the software keyboard (IME stands for Input Method Editor, which is the software keyboard).
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    TextFieldWithIcons(
                        textValue = "Password",
                        placeholder = "Enter Your Password",
                        icon = Icons.Filled.Lock,
                        mutableText = password,
                        onValueChanged = { //This is a lambda function that is called whenever the text value of the field changes. It updates the password variable with the new value. Additionally, it checks if isCheckingJob (a coroutine job) is currently active and cancels it. Then, it starts a new coroutine that sets isChecking to false after a 100-millisecond delay. This is typically used to provide feedback or check the password in real-time as the user types.
                            password = it
                            if (isCheckingJob?.isActive == true) {
                                isCheckingJob?.cancel()
                            }
                            isCheckingJob = CoroutineScope(Dispatchers.Main).launch {
                                delay(100) // Adjust the delay time as needed
                                isChecking = false
                            }
                            isChecking = true
                        },
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next,
                        passwordVisible = passwordVisible // Pass the passwordVisible variable to the TextFieldWithIcons composable function
                    )

                    Spacer(
                        modifier = Modifier.height(30.dp),
                    )
                    Button(
                        onClick = {
                            if (password.text == "123456" && email.text == "abc@gmail.com") {
                                trigSuccess = true
                                trigFail = false
                            } else {
                                trigSuccess = false
                                trigFail = true
                            }

                        } ,
                        colors = ButtonDefaults.buttonColors(Color(0xFF6297F1) ),
                    ) {
                        Text(
                            text = "Login",
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

            }

        }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp, vertical = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.rive),
                    tint = Color.Unspecified,
                    contentDescription = "Icon",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    Icons.Filled.MoreHoriz,
                    tint = Color(0xFF6297F1),
                    contentDescription = "Icon",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.compose),
                    tint = Color.Unspecified,
                    contentDescription = "Icon",
                    modifier = Modifier.size(60.dp)
                )

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcons(
    textValue: String,
    placeholder: String,
    icon: ImageVector,
    mutableText: TextFieldValue,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    passwordVisible: MutableState<Boolean> = mutableStateOf(false),
    onValueChanged: (TextFieldValue) -> Unit,
) {
    if (keyboardType == KeyboardType.Password) {
        TextField(
            value = mutableText,
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    tint = Color(0xFF4483D1),
                    contentDescription = "Icon"
                )
            },
            trailingIcon = {
                val image = if (passwordVisible.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff

                // Please provide localized description for accessibility services
                val description = if (passwordVisible.value) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(imageVector = image, description, tint = Color(0xFF4483D1))
                }
            },
            visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            onValueChange = onValueChanged,
            label = { Text(text = textValue, color = TextColor) },
            placeholder = { Text(text = placeholder, color = TextColor) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            modifier = Modifier
                .padding(start = 15.dp, top = 5.dp, bottom = 5.dp, end = 15.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = P2PBackground,
                textColor = TextColor
            )
        )
    } else {
        TextField(
            value = mutableText,
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    tint = Color(0xFF4483D1),
                    contentDescription = "Icon"
                )
            },
            onValueChange = onValueChanged,
            label = { Text(text = textValue, color = TextColor) },
            placeholder = { Text(text = placeholder, color = TextColor) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            modifier = Modifier
                .padding(start = 15.dp, top = 5.dp, bottom = 5.dp, end = 15.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = P2PBackground,
                textColor = TextColor
            ),

            )
    }
}
