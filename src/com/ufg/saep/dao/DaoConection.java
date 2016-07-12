package com.ufg.saep.dao;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DaoConection {
    /**
     * Variáveis de inicialização do serializador JSON e Conexao com o banco (com.ufg.saep.dao.DaoMongo).
     */
    private Gson gson = new Gson();
    private DaoMongo mongo = new DaoMongo();

    public DaoConection() throws IOException { }

    /**
     * Inserir um parecer.
     * @param parecer Instancia de Parecer.
     * @return True para inserido e false para não inserido.
     */
    public boolean inserirParecer(Parecer parecer) {
        if (mongo.inserirDoc(gson.toJson(parecer), "parecer")) {
            return true;
        }
        return false;
    }

    /**
     * Insere um radoc.
     * @param radoc Instancia de radoc.
     * @return True para inserido e false para não inserido.
     */
    public boolean inserirRadoc(Radoc radoc) {
        if (mongo.inserirDoc(gson.toJson(radoc), "radoc")){
            return true;
        }
        return false;
    }

    /**
     * Insere uma resolução.
     * @param resolucao Instancia de Resolução.
     * @return True para inserido e false para não inserido.
     */
    public boolean inserirResolucao(Resolucao resolucao) {
        if (mongo.inserirDoc(gson.toJson(resolucao), "resolucao")) {
            return true;
        }
        return false;
    }

    /**
     * Insere um tipo.
     * @param tipo Instancia de Tipo.
     * @return True para inserido e false para não inserido.
     */
    public boolean inserirTipo(Tipo tipo) {
        if (mongo.inserirDoc(gson.toJson(tipo), "tipo")) {
            return true;
        }
        return false;
    }

    /**
     * Busca um Parecer pelo id.
     * @param id Valor do id do documento a ser buscado.
     * @return Uma instancia de Parecer.
     */
    public Parecer buscarParecerPorId(String id) {
        Parecer parecer = gson.fromJson(mongo.buscarDoc("parecer", "id", id), Parecer.class);
        return parecer;
    }

    /**
     * Busca um Radoc pelo id.
     * @param id Valor do id do documento a ser buscado.
     * @return Uma instancia de Radoc.
     */
    public Radoc buscarRadocPorId(String id) {
        Radoc radoc = gson.fromJson(mongo.buscarDoc("radoc", "id", id), Radoc.class);
        return radoc;
    }

    /**
     * Busca uma Resolução pelo id.
     * @param id Valor do id do documento a ser buscado.
     * @return Uma instancia de Resolução.
     */
    public Resolucao buscarResolucaoPorId(String id) {
        Resolucao resolucao = gson.fromJson(mongo.buscarDoc("resolucao", "id", id), Resolucao.class);
        return resolucao;
    }

    /**
     * Busca um Tipo pelo id.
     * @param id Valor do id do documento a ser buscado.
     * @return Uma instancia de Tipo.
     */
    public Tipo buscarTipoPorId(String id) {
        Tipo tipo = gson.fromJson(mongo.buscarDoc("tipo", "id", id), Tipo.class);
        return tipo;
    }

    /**
     * Busca Tipo pelo Código.
     * @param codigo Valor do Código do documento a ser buscado.
     * @return Uma instancia de Tipo.
     */
    public Tipo buscarTipoPorCodigo(String codigo) {
        Tipo tipo = gson.fromJson(mongo.buscarDoc("tipo", "codigo", codigo), Tipo.class);
        return tipo;
    }

    /**
     * Busca todos os ids na collection resolução.
     * @return Uma lista de ids.
     */
    public List<String> buscarIdsResolucao() {
        List<String> idsResolucao = mongo.listarCampos("resolucao", "id");
        return idsResolucao;
    }

    /**
     * Busca os Tipos que contém o nome similar ao informado.
     * @param nome Valor do campo nome a ser buscado.
     * @return Uma lista com os Tipos encontrados.
     */
    public List<Tipo> buscarVariosTipos(String nome) {
        List<Tipo> listaTipos = new ArrayList<Tipo>();
        for (String tipoJSON : mongo.listarDocs("tipo", "nome", nome)) {
            Tipo tipo = gson.fromJson(tipoJSON, Tipo.class);
            listaTipos.add(tipo);
        }
        return listaTipos;
    }

    /**
     * Adiciona uma Nota a um parecer.
     * @param id Valor do id a ser buscado para inserir nota.
     * @param nota Valor do campo nota a ser inserido.
     * @return Valor booleano para verificar se foi realizado com sucesso.
     */
    public boolean adicionarNotaParecer(String id, Nota nota) {
        String notaJson = gson.toJson(nota);
        if (mongo.atualizarDoc("parecer", "id", id, "notas", notaJson)){
            return true;
        }
        return false;
    }

    /**
     * Edita uma Nota inserida em um documento Parecer.
     * @param id Valor do id do documento a ser atualizado.
     * @param nomeClasse Nome do campo ao qual se deseja atualizar o valor, no cado
     *                   um Relato ou uma Pontuação. Como ele pega o nome da classe
     *                   ele descobre o nome do campo.
     * @param valor Campo para encontrar o campo a ser alterado.
     * @return Valor booleano para verificar se foi realizado ou não.
     */
    public boolean editarNotaParecerPorId(String id, String nomeClasse, Avaliavel valor) {
        String avaliavelJson = gson.toJson(valor);
        if (mongo.excluirCampoDoc("parecer", "id", id, "notas", nomeClasse, avaliavelJson, nomeClasse, "")) {
            return true;
        }
        return false;
    }

    /**
     * Edita um parecer pelo id.
     * @param id Valor do id contido no documento.
     * @param campo Nome do campo a ser alterado.
     * @param valor Valor do campo a ser alterado.
     * @return Valor booleano para verificar se foi realizado ou não.
     */
    public boolean editarParecerPorId(String id, String campo, String valor) {
        if (mongo.atualizarDoc("parecer", "id", id, campo, valor)) {
            return true;
        }
        return false;
    }

    /**
     * Edita um Resolução pelo id.
     * @param id Valor do id contido no documento a ser fornecido.
     * @param campo Nome do campo a ser alterado.
     * @param valor Valor do campo a ser alterado.
     * @return Valor booleano para verificar se foi realizado ou não.
     */
    public boolean editarResolucaoPorId(String id, String campo, String valor) {
        if (mongo.atualizarDoc("resolucao", "id", id, campo, valor)) {
            return true;
        }
        return false;
    }

    /**
     * Exclui um Parecer.
     * @param id Valor do id contido no documento a ser excluido.
     * @return Valor booleano para verificar se foi realizado ou não.
     */
    public boolean excluirParecerPorId(String id) {
        if (mongo.excluirDoc("parecer", "id", id)) {
            return true;
        }
        return false;
    }

    /**
     * Exclui um Radoc.
     * @param id Valor do id contido no documento a ser excluido.
     * @return Valor booleano para verificar se foi realizado ou não.
     */
    public boolean excluirRadocPorId(String id) {
        if (mongo.excluirDoc("radoc", "id", id)) {
            return true;
        }
        return false;
    }

    /**
     * Exclui uma Resolução.
     * @param id Valor do id contido no documento a ser excluido.
     * @return Valor booleano para verificar se foi realizado ou não.
     */
    public boolean excluirResolucaoPorId(String id) {
        if (mongo.excluirDoc("resolucao", "id", id)) {
            return true;
        }
        return false;
    }

    /**
     * Exclui um Tipo pelo código.
     * @param codigo Valor do código do documento a ser excluido.
     * @return Valor booleano para verificar se foi realizado ou não.
     */
    public boolean excluirTipoPorCodigo(String codigo) {
        if (mongo.excluirDoc("tipo", "codigo", codigo)) {
            return true;
        }
        return false;
    }
}
