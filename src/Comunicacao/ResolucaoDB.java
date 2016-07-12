
import br.ufg.inf.es.saep.sandbox.dominio.*;

import java.io.IOException;
import java.util.List;

public class ResolucaoDB implements ResolucaoRepository{
    private DaoConection banco = new DaoConection();

    public ResolucaoDB() throws IOException {
    }

    public Resolucao byId(String id) {
        return banco.buscarResolucaoPorId(id);
    }

    public String persiste(Resolucao resolucao) {
        Resolucao resol = banco.buscarResolucaoPorId(resolucao.getId());
        if (resol != null) {
            return null;
        } else {
            banco.inserirResolucao(resolucao);
            return resolucao.getId();
        }
    }

    public boolean remove(String id) {
        return banco.excluirResolucaoPorId(id);
    }

    public List<String> resolucoes() {
        return banco.buscarIdsResolucao();
    }

    public void persisteTipo(Tipo tipo) {
        banco.inserirTipo(tipo);
    }

    public void removeTipo(String codigo) {
        banco.excluirTipoPorCodigo(codigo);
    }

    public Tipo tipoPeloCodigo(String codigo) {
        return banco.buscarTipoPorCodigo(codigo);
    }

    public List<Tipo> tiposPeloNome(String nome) {
        return banco.buscarVariosTipos(nome);
    }
}