package com.ufg.saep.conexao;

import br.ufg.inf.es.saep.sandbox.dominio.*;

import java.util.*;

/**
 * Created by gleibson on 13/07/16.
 */
public class ObjectCreator {
    public Parecer criaParecer() {
        return new Parecer();
    }

    public Resolucao criaResolucao(String id, String nome) {
        String descricao = "blablabla";
        Date dataApr = new Date(20121999);
        List<Regra> lista = new ArrayList<Regra>();
        return new Resolucao(id, nome, descricao, dataApr, lista);
    }

    public Radoc criaRadoc(String id) {
        int anobase = 1999;
        List<Relato> listaRelatos = new ArrayList<Relato>();
        return new Radoc(id, anobase, listaRelatos);
    }

    public Tipo criaTipo(String id) {
        String nome = "tipo1";
        String descricao = "barabarabara";
        Set<Atributo> atributos = new Set<Atributo>() {
            public int size() {
                return 0;
            }

            public boolean isEmpty() {
                return false;
            }

            public boolean contains(Object o) {
                return false;
            }

            public Iterator<Atributo> iterator() {
                return null;
            }

            public Object[] toArray() {
                return new Object[0];
            }

            public <T> T[] toArray(T[] a) {
                return null;
            }

            public boolean add(Atributo atributo) {
                return false;
            }

            public boolean remove(Object o) {
                return false;
            }

            public boolean containsAll(Collection<?> c) {
                return false;
            }

            public boolean addAll(Collection<? extends Atributo> c) {
                return false;
            }

            public boolean retainAll(Collection<?> c) {
                return false;
            }

            public boolean removeAll(Collection<?> c) {
                return false;
            }

            public void clear() {

            }

            public boolean equals(Object o) {
                return false;
            }

            public int hashCode() {
                return 0;
            }
        };
        return new Tipo(id, nome, descricao, atributos);
    }

    public Pontuacao criaPontuacao() {
        return new Pontuacao("pontos", new Valor("45325"));
    }

    public Nota criaNota(Avaliavel avaliavel, Avaliavel avaliavel2) {
        return new Nota(avaliavel, avaliavel2, "bereberebere");
    }
}
