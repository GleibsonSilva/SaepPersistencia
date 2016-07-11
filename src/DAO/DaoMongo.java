
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DaoMongo {
    private MongoClient mongo = new MongoClient();
    private MongoDatabase database = mongo.getDatabase("bd_saep");

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

    public String buscarDoc(String collection, String campo, String valor) {
        String objetoJson = database.getCollection(collection).find(new Document(campo, valor)).toString();
        if (objetoJson == null || objetoJson == "") {
            return null;
        } else {
            return objetoJson;
        }
    }

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

    public List<String> listarCampos(String collection, String campo){
        List<String> ids = new ArrayList<String>();
        for (Document doc : database.getCollection(collection).find()) {
            ids.add(doc.getString(campo));
        }
        return ids;
    }

    public List<String> listarDocs(String collection, String campo, String valor){
        List<String> objectsJson = new ArrayList<String>();
        for (Document doc : database.getCollection(collection).find(new Document(campo, valor))) {
            objectsJson.add(doc.toString());
        }
        return objectsJson;
    }

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