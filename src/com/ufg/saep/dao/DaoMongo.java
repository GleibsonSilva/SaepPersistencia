package com.ufg.saep.dao;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DaoMongo {
    public DaoMongo() throws IOException {}

    /**
     * Busca no arquivo de configuração (conexaobanco.properties) o nome do banco a ser instanciado.
     * @return Uma instancia de um banco mongo local
     * @throws IOException
     */
    private MongoDatabase banco() throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("./properties/conexaobanco.properties");
        prop.load(file);
        String banco = prop.getProperty("prop.banco.name");
        MongoClient mongo = new MongoClient();
        MongoDatabase database = mongo.getDatabase(banco);
        return database;
    }

    /**
     * Variável que pega o resultado do método banco() para ser usada na classe
     */
    MongoDatabase database = banco();

    /**
     * Insere um documento no banco.
     * @param objetoJson String JSON a ser inserida no documento.
     * @param collection Nome da collection a ser inserido.
     * @return Valor booleano para sucesso na inserção ou não.
     */
    public boolean inserirDoc(String objetoJson, String collection) {
        boolean finalizou = false;
        try {
            database.getCollection(collection).insertOne(Document.parse(objetoJson));
            finalizou = true;
        } catch (MongoException e) {
            e.getStackTrace();
        }
        return finalizou;
    }

    /**
     * Busca um documento JSON no banco.
     * @param collection Nome da collection a ser buscada.
     * @param campo Nome do Campo a ser buscado no documento (Ex.: "id").
     * @param valor Valor atribuido ao campo (Ex.: id).
     * @return A string JSON buscada.
     */
    public String buscarDoc(String collection, String campo, String valor) {
        String objetoJson = database.getCollection(collection).find(new Document(campo, valor)).toString();
        if (objetoJson == null || objetoJson == "") {
            return null;
        } else {
            return objetoJson;
        }
    }

    /**
     * Exclui um documento do banco.
     * @param collection Nome da Collection que contém o documento a ser excluido.
     * @param campo Nome do campo para encontrar o documento que será excluido.
     * @param valor Valor atribuido ao campo mencionado.
     * @return Valor booleano caso seja excluido com sucesso ou não.
     */
    public boolean excluirDoc(String collection, String campo, String valor) {
        boolean finalizou = false;
        try {
            database.getCollection(collection).findOneAndDelete(new Document(campo, valor));
            finalizou = true;
        } catch (MongoException e) {
            e.getStackTrace();
        }
        return finalizou;
    }

    /**
     * Exclui um campo específico dentro de um array dentro de um ojeto que estão
     * dentro de um documento em uma collection.
     * @param collection Collection que contém o documento.
     * @param campo Campo para encontrar o documento.
     * @param valor Valor do campo.
     * @param key Nome do array.
     * @param campo2 Campo para encontrar o atributo no array.
     * @param valor2 valor do campo2.
     * @param campoUpdate Campo para identificar o item a ser excluido dentro do array.
     * @param valorUpdate Valor do campoUpdate.
     * @return Valor booleano caso tenha sido efetuada com sucesso a modificação.
     */
    public boolean excluirCampoDoc(String collection, String campo, String valor, String key, String campo2,
                                   String valor2, String campoUpdate, String valorUpdate) {
        boolean finalizou = false;
        try {
            database.getCollection(collection).findOneAndUpdate(new Document(campo, valor)
                    .append(key, new Document(campo2, valor2)), new Document("$set",
                    new Document(campoUpdate, valorUpdate)));
            finalizou = true;
        } catch (MongoException e) {
            e.getStackTrace();
        }
        return finalizou;
    }

    /**
     * Atualiza um campo específico de um documento.
     * @param collection Nome da collection que contém o documento.
     * @param campo Nome do campo para identificar o documento.
     * @param valor Valor do campo.
     * @param campoUpdate Nome do campo a ser atualizado.
     * @param valorUpdate Valor desse campo.
     * @return Valor booleano para verificar se foi realizada a operação com sucesso.
     */
    public boolean atualizarDoc(String collection, String campo, String valor, String campoUpdate, String valorUpdate) {
		boolean finalizou = false;
        try {
            database.getCollection(collection).findOneAndUpdate(new Document(campo, valor),
                    new Document("$set", new Document(campoUpdate, valorUpdate)));
            finalizou = true;
        } catch (MongoException e) {
            e.getStackTrace();
        }
        return finalizou;
    }

    /**
     * Lista um determinado campo de todos os documentos contidos na collection.
     * @param collection Nome da collection a qual os documentos estão contidos.
     * @param campo Nome do campo a ser buscado.
     * @return Lista dos campos.
     */
    public List<String> listarCampos(String collection, String campo){
        List<String> ids = new ArrayList<String>();
        for (Document doc : database.getCollection(collection).find()) {
            ids.add(doc.getString(campo));
        }
        return ids;
    }

    /**
     * Lista os documentos na collection especificada.
     * @param collection Nome da collection a qual estão contidos os documentos.
     * @param campo Nome do campo utilizado para achar os documentos a serem listados.
     * @param valor Valor do campo.
     * @return A lista de documentos JSON encontrados.
     */
    public List<String> listarDocs(String collection, String campo, String valor){
        List<String> objectsJson = new ArrayList<String>();
        for (Document doc : database.getCollection(collection).find(new Document(campo, valor))) {
            objectsJson.add(doc.toString());
        }
        return objectsJson;
    }

    /**
     * Apaga uma collection inteira.
     * @param collection Nome da collection a ser excluida.
     * @return Valor booleano para verificar se foi excluido com sucesso.
     */
    public boolean apagarCollection(String collection) {
        boolean finalizou = false;
        try {
            database.getCollection(collection).drop();
            finalizou = true;
        } catch (MongoException e) {
            e.getStackTrace();
        }
        return finalizou;
    }
}