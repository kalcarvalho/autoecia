package br.com.www.application.util;

public interface RequestConstants {
		
	public static final String REQUEST_PARAMETER_SUCCESS_MESSAGE = "sucessMessage";
	public static final String REQUEST_PARAMETER_ERROR_MESSAGE = "errorMessage";
	public static final String REQUEST_PARAMETER_FATAL_ERROR_EXCEPTION = "fatalErrorException";
	public static final String REQUEST_CALLBACK_ERROR_PROPERTIES_FILE_NAME = "callbackpage.properties";
	
	public static final String REQUEST_PARAMETER_ACTION = "action";
	public static final String REQUEST_ACTION_PERFORM = "performAction";
	public static final String REQUEST_ACTION_SHOW_FORM = "showFormAction";
	public static final String REQUEST_ACTION_BACK = "backAction";

	public static final String REQUEST_PARAMETER_COMMAND = "command";
	public static final String REQUEST_COMMAND_PROPERTIES_FILE_NAME = "command.properties";
	public static final String REQUEST_COMMAND_EFETUAR_LOGIN = "efetuarLogin";
	public static final String REQUEST_COMMAND_LISTAR_USUARIOS = "listarUsuarios";
	public static final String REQUEST_COMMAND_ADICIONAR_USUARIO = "incluirUsuario";
	public static final String REQUEST_COMMAND_REMOVER_USUARIO = "excluirUsuario";
	public static final String REQUEST_COMMAND_VISUALIZAR_USUARIO = "visualizarUsuario";
	public static final String REQUEST_COMMAND_EDITAR_USUARIO = "editarUsuario";
        
        public static final String REQUEST_COMMAND_LISTAR_NOTICIAS = "listarNoticias";
        public static final String REQUEST_COMMAND_VISUALIZAR_NOTICIA = "visualizarNoticia";
        public static final String REQUEST_COMMAND_EXIBIR_AREA_RESTRITA = "exibirAreaRestrita";
        
        public static final String REQUEST_COMMAND_VISUALIZAR_CARRO = "visualizarCarro";
	
	public static final String REQUEST_PARAMETER_PAGE_NAME = "pageName";
	public static final String REQUEST_PARAMETER_PAGE_TITLE = "pageTitle";
        public static final String REQUEST_PARAMETER_MENU = "menu";
        public static final String REQUEST_PARAMETER_MENU_RESTRITA = "restrita";
        
        public static final String REQUEST_CARRO_CODIGO = "codigo";
        public static final String REQUEST_CARRO_MARCA = "marca";
        public static final String REQUEST_CARRO_MODELO = "modelo";
	public static final String REQUEST_CARRO_ANO = "ano";
        public static final String REQUEST_CARRO_KM = "km";
        public static final String REQUEST_CARRO_TIPOESTADO = "tipoestado";
        public static final String REQUEST_CARRO_PRECO = "preco";
        public static final String REQUEST_CARRO_LOCALIZACAO = "localizacao";
        public static final String REQUEST_CARRO_PROPOSTAS = "propostas";
        public static final String REQUEST_CARRO_VISITAS = "visitas";
        public static final String REQUEST_CARRO_EMAIL = "email";
        public static final String REQUEST_CARRO_TELEFONE = "telefone";
        public static final String REQUEST_CARRO_VERSAO = "versao";
        public static final String REQUEST_CARRO_COMBUSTIVEL = "combustivel";
        public static final String REQUEST_CARRO_PORTAS = "portas";
        public static final String REQUEST_CARRO_UNICODONO = "unicodono";
        public static final String REQUEST_CARRO_COR = "cor";
        public static final String REQUEST_CARRO_CAMBIO = "cambio";
        public static final String REQUEST_CARRO_PLACA = "placa";
        public static final String REQUEST_CARRO_OBSERVACOES = "observacoes";
        public static final String REQUEST_CARRO_FOTOS = "fotos";
        public static final String REQUEST_CARRO_OPCIONAIS = "opcionais";
        
        public static final String REQUEST_USUARIO_CHAVE = "chave";
	public static final String REQUEST_USUARIO_SENHA = "senha";
	public static final String REQUEST_USUARIO_NOME = "nome";
	public static final String REQUEST_USUARIO_CONFIRMAR_SENHA = "confirmarSenha";
	public static final String REQUEST_USUARIO_EMAIL = "email";
	public static final String REQUEST_USUARIO_DDD = "ddd";
	public static final String REQUEST_USUARIO_TELEFONE = "telefone";
	public static final String REQUEST_USUARIO_RAMAL = "ramal";
	public static final String REQUEST_USUARIO_CODIGO_PERFIL = "codigoPerfil";
	public static final String REQUEST_USUARIO_PERFIL = "descricaoPerfil";
	public static final String REQUEST_USUARIO_STATUS = "status";
	
	public static final String REQUEST_PARAMETER_TEXT_TO_FIND = "textToFind";
	public static final String REQUEST_PARAMETER_SEARCH_RESULT = "searchResult";
	
	
}
