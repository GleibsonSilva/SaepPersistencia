package DAO;

import br.ufg.inf.es.saep.sandbox.dominio.*;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class DaoConection {
    private Gson gson = new Gson();
    private DaoMongo mongo = new DaoMongo();

    public boolean inserirParecer(Parecer parecer) {
        if (mongo.inserirDoc(gson.toJson(parecer), "parecer")) {
            return true;
        }
        return false;
    }

    public boolean inserirRadoc(Radoc radoc) {
        if (mongo.inserirDoc(gson.toJson(radoc), "radoc")){
            return true;
        }
        return false;
    }

    public boolean inserirResolucao(Resolucao resolucao) {
        if (mongo.inserirDoc(gson.toJson(resolucao), "resolucao")) {
            return true;
        }
        return false;
    }

    public boolean inserirTipo(Tipo tipo) {
        if (mongo.inserirDoc(gson.toJson(tipo), "tipo")) {
            return true;
        }
        return false;
    }

    public Parecer buscarParecerPorId(String id) {
        Parecer parecer = gson.fromJson(mongo.buscarDoc("parecer", "id", id), Parecer.class);
        return parecer;
    }

    public Radoc buscarRadocPorId(String id) {
        Radoc radoc = gson.fromJson(mongo.buscarDoc("radoc", "id", id), Radoc.class);
        return radoc;
    }

    public Resolucao buscarResolucaoPorId(String id) {
        Resolucao resolucao = gson.fromJson(mongo.buscarDoc("resolucao", "id", id), Resolucao.class);
        return resolucao;
    }

    public Tipo buscarTipoPorId(String id) {
        Tipo tipo = gson.fromJson(mongo.buscarDoc("tipo", "id", id), Tipo.class);
        return tipo;
    }

    public Tipo buscarTipoPorCodigo(String codigo) {
        Tipo tipo = gson.fromJson(mongo.buscarDoc("tipo", "codigo", codigo), Tipo.class);
        return tipo;
    }

    public List<String> buscarIdsResolucao() {
        List<String> idsResolucao = mongo.listarCampos("resolucao", "id");
        return idsResolucao;
    }

    public List<Tipo> buscarVariosTipos(String nome) {
        List<Tipo> listaTipos = new ArrayList<Tipo>();
        for (String tipoJSON : mongo.listarDocs("tipo", "nome", nome)) {
            Tipo tipo = gson.fromJson(tipoJSON, Tipo.class);
            listaTipos.add(tipo);
        }
        return listaTipos;
    }
    
    public boolean adicionarNotaParecer(String id, Nota nota) {
        String notaJson = gson.toJson(nota);
        if (mongo.atualizarDoc("parecer", "id", id, "notas", notaJson)){
            return true;
        }
        return false;
    }

    public boolean editarNotaParecerPorId(String id, Avaliavel valor) {
        String avaliavelJson = gson.toJson(valor);
        if (mongo.excluirCampoDoc("parecer", "id", id, "notas", "avaliavel", avaliavelJson, "avaliavel", "")) {
            return true;
        }
        return false;
    }

    public boolean editarParecerPorId(String id, String campo, String valor) {
        if (mongo.atualizarDoc("parecer", "id", id, campo, valor) {
            return true;
        }
        return false;
    }

    public boolean editarResolucaoPorId(String id, String campo, String valor) {
        if (mongo.atualizarDoc("resolucao", "id", id, campo, valor) {
            return true;
        }
        return false;
    }

    public boolean excluirParecerPorId(String id) {
        if (mongo.excluirDoc("parecer", "id", id)) {
            return true;
        }
        return false;
    }

    public boolean excluirRadocPorId(String id) {
        if (mongo.excluirDoc("radoc", "id", id)) {
            return true;
        }
        return false;
    }

    public boolean excluirResolucaoPorId(String id) {
        if (mongo.excluirDoc("resolucao", "id", id)) {
            return true;
        }
        return false;
    }

    public boolean excluirTipoPorCodigo(String codigo) {
        if (mongo.excluirDoc("tipo", "codigo", codigo)) {
            return true;
        }
        return false;
    }
}
