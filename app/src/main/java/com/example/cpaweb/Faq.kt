package com.example.cpaweb

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cpaweb.adapters.VersionAdapter
import com.example.cpaweb.databinding.ActivityCommunityHomeBinding
import com.example.cpaweb.databinding.ActivityFaqBinding

class Faq : AppCompatActivity() {
    private lateinit var binding: ActivityFaqBinding
    val versionList = ArrayList<Versions>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        binding.btnBack.setOnClickListener{
            finish()
        }

        initData()
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val versionAdapter = VersionAdapter(versionList)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = versionAdapter
        recyclerView.setHasFixedSize(true)
    }
    private fun initData() {
        versionList.add(Versions(
            "Como criar um perfil na rede social e configurar as informações de forma adequada?",
            "Version 10",
            "api level 10",
            "Para criar um perfil na nossa rede social, basta seguir alguns passos simples. Clique em \"Criar conta\" e preencha as informações solicitadas, como nome, e-mail e senha. Em seguida, você poderá adicionar informações adicionais ao seu perfil, como foto, interesses e uma breve descrição. Certifique-se de revisar e configurar suas informações de privacidade de acordo com suas preferências"
        ))
        versionList.add(Versions(
            "Quais são as opções de privacidade e segurança disponíveis para proteger as informações dos usuários?",
            "Version 10",
            "api level 10",
            "A nossa rede social valoriza a privacidade e a segurança dos usuários. Oferecemos opções de configuração de privacidade para controlar quem pode ver suas postagens, fotos e informações pessoais. Além disso, utilizamos medidas de segurança para proteger os dados dos usuários contra acesso não autorizado. É importante revisar e ajustar suas configurações de privacidade de acordo com suas necessidades e preferências."
        ))
        versionList.add(Versions(
            "Como encontrar e se conectar com outras pessoas que compartilham interesses semelhantes?",
            "Version 10",
            "api level 10",
            "Para encontrar pessoas com interesses semelhantes, você pode explorar as comunidades e grupos da nossa rede social. Pesquise por palavras-chave relacionadas aos seus interesses e participe de discussões relevantes. Também é possível seguir perfis de outros usuários que compartilham seus interesses e enviar solicitações de amizade. Isso facilitará a conexão com pessoas que possuem afinidades com você."
        ))
        versionList.add(Versions(
            "Quais recursos estão disponíveis para promover a interação e a troca de informações na rede social?",
            "Version 10",
            "api level 10",
            "Nossa rede social oferece diversos recursos para promover a interação e a troca de informações entre os usuários. Você pode compartilhar atualizações, fotos e vídeos, além de comentar e curtir as postagens de outros usuários. Além disso, temos fóruns de discussão, onde você pode participar de conversas sobre tópicos de interesse. Explore os recursos disponíveis e aproveite a experiência de interação social."
        ))
        versionList.add(Versions(
            "Como posso participar de grupos ou comunidades específicas relacionadas a temas de interesse?",
            "Version 10",
            "api level 10",
            "Participar de grupos ou comunidades específicas é fácil na nossa rede social. Navegue pela seção de \"Grupos\" ou \"Comunidades\" e explore as opções disponíveis. Você pode pesquisar por grupos relacionados aos seus interesses ou criar um grupo próprio. Ao ingressar em um grupo, você poderá interagir com outros membros, compartilhar informações e participar de discussões relevantes ao tema escolhido."
        ))
        versionList.add(Versions(
            "Existe algum recurso para acompanhar e registrar o progresso do desenvolvimento de uma pessoa autista?",
            "Version 10",
            "api level 10",
            "Sim, nossa rede social oferece um recurso para acompanhar e registrar o progresso do desenvolvimento de pessoas autistas. Você pode criar um perfil personalizado para a pessoa, registrar marcos importantes, compartilhar conquistas e fazer anotações sobre o desenvolvimento. Essa funcionalidade permite que familiares e profissionais acompanhem o progresso de forma organizada e colaborativa."
        ))
        versionList.add(Versions(
            "Quais são as opções de compartilhamento de fotos e vídeos na plataforma?",
            "Version 10",
            "api level 10",
            "Nossa rede social oferece opções fáceis e seguras de compartilhamento de fotos e vídeos. Ao criar uma postagem, você pode anexar fotos ou vídeos diretamente do seu dispositivo. Também fornecemos recursos de privacidade para controlar quem pode visualizar e interagir com o conteúdo compartilhado. Dessa forma, você pode compartilhar momentos especiais com a comunidade de forma segura e controlada."
        ))
        versionList.add(Versions(
            "Como posso encontrar profissionais especializados e obter informações sobre serviços e terapias disponíveis?",
            "Version 10",
            "api level 10",
            "Na nossa rede social, você pode encontrar profissionais especializados através da seção de \"Serviços e Terapias\". Aqui você terá acesso a uma lista de profissionais qualificados e poderá obter informações detalhadas sobre os serviços oferecidos. Além disso, é possível entrar em contato diretamente com os profissionais para tirar dúvidas ou agendar consultas."
        ))
        versionList.add(Versions(
            "Existe um sistema de mensagens privadas para facilitar a comunicação entre os usuários?",
            "Version 10",
            "api level 10",
            "Sim, nossa rede social possui um sistema de mensagens privadas que facilita a comunicação entre os usuários. Você pode enviar mensagens para outros usuários de forma privada, possibilitando trocas de informações mais diretas e pessoais. Essa funcionalidade é útil para estabelecer conexões individuais, compartilhar ideias ou obter suporte de outros membros da comunidade."
        ))
        versionList.add(Versions(
            "Como posso relatar ou lidar com comportamentos inadequados ou abusivos na rede social?",
            "Version 10",
            "api level 10",
            "Valorizamos um ambiente seguro e acolhedor na nossa rede social. Caso você encontre comportamentos inadequados ou abusivos, oferecemos um sistema de denúncia para relatar tais ocorrências. Você pode entrar em contato com nossa equipe de suporte ou utilizar a opção de denúncia presente nas postagens e mensagens. Tomaremos as medidas necessárias para investigar e lidar com a situação adequadamente, visando preservar a segurança e o bem-estar de todos os usuários."
        ))
        versionList.add(Versions(
            "Inclusão no Ambiente de Trabalho: Capacitando Profissionais Autistas",
            "Version 10",
            "api level 10",
            "Desenvolvendo Habilidades Sociais em Indivíduos com Autismo\"\n" +
                    "As habilidades sociais são essenciais para a interação e o relacionamento saudáveis. Para indivíduos autistas, o desenvolvimento dessas habilidades pode exigir intervenções específicas. Programas de treinamento em habilidades sociais, jogos de role-playing e atividades estruturadas podem ajudar a ensinar e praticar interações sociais, promovendo o desenvolvimento de relacionamentos significativos e a inclusão social."
        ))
    }
}