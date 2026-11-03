package com.example.androidstoragecleaner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("home") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (currentScreen == "home") {
            Text(text = "تطبيق التنظيف الذكي", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(30.dp))
            
            // زر التنظيف
            Button(onClick = { currentScreen = "cleaner" }) {
                Text("أداة التنظيف 🧹")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // زر الساعة
            Button(onClick = { currentScreen = "clock" }) {
                Text("الساعة ⏰")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // زر الإضافات
            Button(onClick = { currentScreen = "notes" }) {
                Text("ملاحظات سريعة 📝")
            }
        } 
        else if (currentScreen == "cleaner") {
            CleanerScreen { currentScreen = "home" }
        } 
        else if (currentScreen == "clock") {
            ClockScreen { currentScreen = "home" }
        } 
        else if (currentScreen == "notes") {
            NotesScreen { currentScreen = "home" }
        }
    }
}

@Composable
fun CleanerScreen(onBack: () -> Unit) {
    var message by remember { mutableStateOf("جاهز للتنظيف") }
    var isCleaning by remember { mutableStateOf(false) }

    Text("أداة التنظيف")
    Spacer(modifier = Modifier.height(20.dp))
    Text(message)
    Spacer(modifier = Modifier.height(20.dp))
    
    Button(
        onClick = {
            isCleaning = true
            message = "جاري مسح الملفات المؤقتة..."
            // ملاحظة: هذا مجرد شكل، لتطبيق حقيقي يحتاج أذونات معينة
        },
        enabled = !isCleaning
    ) {
        Text("ابدأ المسح")
    }
    Spacer(modifier = Modifier.height(20.dp))
    TextButton(onClick = onBack) { Text("رجوع") }
}

@Composable
fun ClockScreen(onBack: () -> Unit) {
    var time by remember { mutableStateOf(0) }
    
    // مؤقت بسيط يزيد كل ثانية
    LaunchedEffect(Unit) {
        while(true) {
            delay(1000)
            time++
        }
    }
    
    Text("الساعة / العداد")
    Text("$time ثانية", style = MaterialTheme.typography.displayLarge)
    Spacer(modifier = Modifier.height(20.dp))
    TextButton(onClick = onBack) { Text("رجوع") }
}

@Composable
fun NotesScreen(onBack: () -> Unit) {
    var text by remember { mutableStateOf("") }
    Text("اكتب ملاحظتك هنا")
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("ملاحظة...") }
    )
    Spacer(modifier = Modifier.height(20.dp))
    TextButton(onClick = onBack) { Text("رجوع") }
}
