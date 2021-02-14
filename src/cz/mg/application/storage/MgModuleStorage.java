package cz.mg.application.storage;

import cz.mg.application.entities.architecture.MgModule;
import cz.mg.sql.Sql;
import cz.mg.sql.connection.SqlConnection;
import cz.mg.sql.connection.SqlResult;
import cz.mg.sql.entities.SqlColumn;
import cz.mg.sql.entities.SqlTable;
import cz.mg.sql.entities.types.SqliteTypes;

import static cz.mg.application.entities.MgEntity.NULL_ID;


public class MgModuleStorage {
    private final SqlTable table = new SqlTable(
        "Module",
        new SqlColumn("id", SqliteTypes.INTEGER)
    );

    private final SqlConnection connection;

    public MgModuleStorage(SqlConnection connection) {
        this.connection = connection;
    }

    public void createTable(){
        connection.executeDdl(table.getCreateSql());
    }

    public void deleteTable(){
        connection.executeDdl(table.getDeleteSql());
    }

    public boolean exists(MgModule module){
        Sql sql = table.getReadRowSql();
        sql.setBind("id", module.getId());
        return connection.executeQuery(sql).getResults().count() != 0;
    }

    public void save(MgModule module){
        if(module.getId() == NULL_ID){
            long id = module.getSequence().next();
            module.setId(id);
        }

        if(exists(module)){
            update(module);
        } else {
            create(module);
        }
    }

    public void create(MgModule module){
        Sql sql = table.getUpdateRowSql();
        sql.setBind("id", module.getId());
        // todo
        connection.executeDml(sql);
    }

    public MgModule read(long id){
        Sql sql = table.getReadRowSql();
        sql.setBind("id", id);
        SqlResult result = connection.executeQuery(sql).getSingleResult();
        MgModule module = new MgModule();
        module.setId(id);
        // todo
        return module;
    }

    public void update(MgModule module){
        Sql sql = table.getCreateRowSql();
        sql.setBind("id", module.getId());
        // todo
        connection.executeDml(sql);
    }

    public void delete(MgModule module){
        Sql sql = table.getDeleteSql();
        sql.setBind("id", module.getId());
    }
}
