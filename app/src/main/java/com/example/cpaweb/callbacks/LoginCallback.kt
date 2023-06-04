import com.example.cpaweb.models.auth.LoginResponse
import com.example.cpaweb.models.users.UserBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction1

class LoginCallback(
    private val saveToken: KFunction1<String, Unit>,
    private val saveUserInfo: KFunction1<LoginResponse, Unit>,
    private val handleError: (String) -> Unit,
    private val launchActivity: () -> Unit,
    private val finish: () -> Unit,
        ): Callback<LoginResponse> {
    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        if(response.isSuccessful){
            val token: String? = response.body()?.jwtToken
            val userInfo: LoginResponse? = response.body()!!
            if(token == null || userInfo?.id == null){
                handleError("Erro ao obter informações do usuário")
               return
            }
            saveToken(token)
            saveUserInfo(LoginResponse(
                id = userInfo.id,
                username = userInfo.username.toString(),
                email = userInfo.email.toString(),
                profileUrl = userInfo.profileUrl.toString(),
                phone = userInfo.phone.toString(),
                userType = userInfo.userType.toString(),
            ))
            launchActivity()
            finish()
        }else if(response.code() === 401){
            handleError("Credenciais inválidas")
        }
    }

    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        handleError("Ops! houve um erro na conexão")
    }
}