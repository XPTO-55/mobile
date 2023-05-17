import com.example.cpaweb.helpers.AuthManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val token = AuthManager.getAuthToken()
    val modifiedRequest = if (token != null) {
        originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
    } else {
        originalRequest
    }
    return chain.proceed(modifiedRequest)
  }
}