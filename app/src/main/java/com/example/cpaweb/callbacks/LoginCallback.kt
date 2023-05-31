import com.example.cpaweb.models.auth.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.KFunction1

class LoginCallback(
    private val saveToken: KFunction1<String, Unit>,
    private val saveUserInfo: KFunction1<Long, Unit>,
    private val handleError: (String) -> Unit,
    private val launchActivity: () -> Unit,
        ): Callback<LoginResponse> {
    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        if(response.isSuccessful){
            val token: String? = response.body()?.jwtToken
            val userInfoId: Long? = response.body()?.id
            if(token == null || userInfoId == null){
                handleError("Erro ao obter informações do usuário")
               return
            }
            saveToken(token)
            saveUserInfo(userInfoId)
            launchActivity()
        }else if(response.code() === 401){
            handleError("Credenciais inválidas")
        }
    }

    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        handleError("Ops! houve um erro na conexão")
    }
}