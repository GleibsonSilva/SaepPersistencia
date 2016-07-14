package com.ufg.saep.conexao;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by gleibson on 22/06/16.
 */
public class ParecerDBTest {
    private ObjectCreator criador = new ObjectCreator();
    private ParecerDB parecerDB = new ParecerDB();

    public ParecerDBTest() throws IOException {
    }

    @Test
    public void testAdicionaNota() throws Exception {
        Nota nota = criador.criaNota(criador.criaPontuacao(), criador.criaPontuacao());
        Parecer parecer = criador.criaParecer();
        parecerDB.persisteParecer(parecer);
        parecerDB.adicionaNota("12345", nota);
        assertEquals(parecer, parecerDB.byId(parecer.getId()));
    }

    @Test
    public void testRemoveNota() throws Exception {
        parecerDB.removeNota("12345", criador.criaPontuacao());
        assertEquals(criador.criaParecer(), parecerDB.byId(criador.criaParecer().getId()));
    }

    @Test
    public void testAtualizaFundamentacao() throws Exception {
        Parecer parecer = criador.criaParecer();
        parecerDB.atualizaFundamentacao(parecer.getId(), "asldjgkshkls");
        assertEquals(parecerDB.byId(criador.criaParecer().getId()), parecer);
    }

    @Test
    public void testRemoveParecer() throws Exception {
        Parecer parecer = criador.criaParecer();
        parecerDB.persisteParecer(parecer);
        parecerDB.removeParecer(parecer.getId());
        assertEquals(false, parecerDB.byId(parecer.getId()));
    }

    @Test
    public void testRadocById() throws Exception {

    }
    @Test
    public void testPersisteRadoc() throws Exception {

    }
    @Test
    public void testRemoveRadoc() throws Exception {

    }

}