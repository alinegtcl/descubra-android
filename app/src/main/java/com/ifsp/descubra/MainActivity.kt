package com.ifsp.descubra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AutoAwesome
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.ifsp.descubra.ui.theme.DescubraTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

class MainActivity : ComponentActivity() {

    private val wikipediaApi = Retrofit.Builder()
        .baseUrl("https://pt.wikipedia.org/api/rest_v1/")
        .client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                        .newBuilder()
                        .header(
                            "User-Agent",
                            "DescubraAndroid/1.0 (https://github.com/alinegtcl/descubra-android)"
                        )
                        .build()

                    chain.proceed(request)
                }
                .build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WikipediaApi::class.java)

    private var articleTitle by mutableStateOf<String?>(null)
    private var articleDescription by mutableStateOf<String?>(null)
    private var isLoading by mutableStateOf(false)
    private var errorMessage by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DescubraTheme {
                DescubraScreen(
                    title = articleTitle,
                    description = articleDescription,
                    isLoading = isLoading,
                    errorMessage = errorMessage,
                    onDiscover = ::discoverArticle
                )
            }
        }
    }

    private fun discoverArticle() {
        lifecycleScope.launch {
            isLoading = true
            errorMessage = null
            articleTitle = null
            articleDescription = null

            try {
                val response = wikipediaApi.getRandomArticle()

                articleTitle = response.title
                articleDescription = response.extract

            } catch (exception: Exception) {
                errorMessage = "Não foi possível descobrir algo novo. Tente novamente."
            } finally {
                isLoading = false
            }
        }
    }
}

data class WikipediaResponse(
    val title: String,
    val extract: String
)

@androidx.compose.runtime.Composable
fun DescubraScreen(
    title: String?,
    description: String?,
    isLoading: Boolean,
    errorMessage: String?,
    onDiscover: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Outlined.AutoAwesome,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.height(48.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Descubra",
                    style = MaterialTheme.typography.displaySmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Clique no botão e descubra algo novo.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                when {
                    isLoading -> {
                        CircularProgressIndicator()

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Buscando algo para você...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    title != null && description != null -> {
                        ArticleCard(
                            title = title,
                            description = description
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }

                    errorMessage != null -> {
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }

                Button(
                    onClick = onDiscover,
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(18.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = if (title == null) {
                            Icons.Outlined.AutoAwesome
                        } else {
                            Icons.Outlined.Refresh
                        },
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                    Text(
                        text = if (title == null) {
                            "Descobrir"
                        } else {
                            "Descobrir outra coisa"
                        }
                    )
                }
            }
        }
    }
}

@androidx.compose.runtime.Composable
private fun ArticleCard(
    title: String,
    description: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@androidx.compose.runtime.Composable
private fun DescubraScreenPreview() {
    DescubraTheme {
        DescubraScreen(
            title = "Fortaleza do Morro de São Paulo",
            description = "A Fortaleza do Morro de São Paulo localiza-se na ponta noroeste da ilha de Tinharé, atual distrito de Cairu, no litoral do estado brasileiro da Bahia.",
            isLoading = false,
            errorMessage = null,
            onDiscover = {}
        )
    }
}