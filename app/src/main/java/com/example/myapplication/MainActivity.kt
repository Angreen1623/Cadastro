package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.db.DBHandler
import com.example.myapplication.ui.theme.DarkBlueMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    MenuTopBar()
}

@Preview(showBackground = true, widthDp = 200, heightDp = 500)
@Composable
fun AppPreview() {
    App()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopBar() {

    Scaffold (

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = DarkBlueMenu
                ),
                title = {
                    Column() {
                        Text(text = "Cadastro", color = Color.White)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ArrowBack, tint = Color.White, contentDescription = "")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.sede), contentDescription = "")
                    }
                }
            )
        }

    ) {

        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(it)){

            FormLayoutFilled(LocalContext.current)
        }
    }
}

@Preview(showBackground = true, widthDp = 230, heightDp = 300)
@Composable
fun MenuTopBarPreview(){
    MenuTopBar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormLayoutFilled(context: Context
) {

    val activity = context as Activity

    val options = listOf("Telefone Fixo", "Celular", "Whatsapp")

    val userName = remember {
        mutableStateOf(TextFieldValue())
    }
    val userPhone = remember {
        mutableStateOf(TextFieldValue())
    }
    val userOrigin = remember {
        mutableStateOf(options[0])
    }
    val userObservation = remember {
        mutableStateOf(TextFieldValue())
    }

    var dbHandler: DBHandler = DBHandler(context)

    val focusManager = LocalFocusManager.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier.widthIn(max = 480.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            item {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = { Text("Nome") },
                    value = userName.value,
                    onValueChange = { userName.value = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )

            }
            item {
                var text by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    label = { Text("Telefone") },
                    value = userPhone.value,
                    onValueChange = { userPhone.value = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
            }
            item{

                var expanded by remember { mutableStateOf(false) }

                // We want to react on tap/press on TextField to show menu
                ExposedDropdownMenuBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                ) {
                    TextField(
                        // The `menuAnchor` modifier must be passed to the text field for correctness.
                        modifier = Modifier.menuAnchor(),
                        readOnly = true,
                        value = userOrigin.value,
                        onValueChange = {},
                        label = { Text("Origem:") },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        options.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    userOrigin.value = selectionOption
                                    expanded = false
                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }

            item {
                var text by remember { mutableStateOf("") }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 16.dp),
                    label = { Text("Observação") },
                    value = userObservation.value,
                    onValueChange = { userObservation.value = it },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next,
                        capitalization = KeyboardCapitalization.Words
                    ),
                    keyboardActions = KeyboardActions {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                )
            }

            item {
                Box(modifier = Modifier
                    .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter) {
                    Row(

                    ) {

                        Button(onClick = {
                            dbHandler.addNewUser(
                                userName.value.text,
                                userPhone.value.text,
                                userOrigin.value,
                                userObservation.value.text
                            )
                            Toast.makeText(context, "Usuário adicionado ao banco de dados", Toast.LENGTH_SHORT).show()

                        }) {
                            Text("Enviar")
                        }
                        Surface(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                        ) {

                        }
                        ButtonW("Cancelar")
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonW(text: String) {
    Button(onClick = { /* Do something! */ }) {
        Text(text)
    }
}