package com.ufg.saep.conexao;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import com.ufg.saep.dao.DaoConection;

import java.io.IOException;

public class ParecerDB implements ParecerRepository{
    private DaoConection banco = new DaoConection();

    protected ParecerDB() throws IOException {}

    public void adicionaNota(String id, Nota nota) {
        banco.adicionarNotaParecer(id, nota);
    }

    public void removeNota(String id, Avaliavel original) {
        banco.editarNotaParecerPorId(id, Avaliavel.class.getName(), original);
    }

    public void persisteParecer(Parecer parecer) {
        Parecer p = banco.buscarParecerPorId(parecer.getId());
        try {
            if (p == null) {
                banco.inserirParecer(parecer);}
        } catch (Exception e) {
            e.equals("Identificador Existente");
        }
    }

    public void atualizaFundamentacao(String parecer, String fundamentacao) {
        banco.editarParecerPorId(parecer, "fundamentacao", fundamentacao);
    }

    public Parecer byId(String id) {
        return banco.buscarParecerPorId(id);
    }

    public void removeParecer(String id) {
        banco.excluirParecerPorId(id);
    }

    public Radoc radocById(String identificador) {
        return banco.buscarRadocPorId(identificador);
    }

    public String persisteRadoc(Radoc radoc) {
        Radoc r = banco.buscarRadocPorId(radoc.getId());
        try {
            if (r == null) {
                banco.inserirRadoc(radoc);
                return radoc.getId();}
        } catch (Exception e) {
            e.equals("Identificador Existente");
        }
        return null;
    }

    public void removeRadoc(String identificador) {
        banco.excluirRadocPorId(identificador);
    }
}