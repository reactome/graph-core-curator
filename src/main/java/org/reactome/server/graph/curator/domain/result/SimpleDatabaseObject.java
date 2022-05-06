package org.reactome.server.graph.curator.domain.result;

import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.reactome.server.graph.curator.service.util.DatabaseObjectUtils;

import java.util.Collection;
import java.util.Objects;

@SuppressWarnings("unused")
public class SimpleDatabaseObject implements DatabaseObjectLike {

    private Long DB_ID;
    private String stId;
    private String _displayName;
    private String schemaClass;

    public SimpleDatabaseObject() {}

    public Long getDB_ID() {
        return DB_ID;
    }

    public void setDB_ID(Long dbId) {
        this.DB_ID = DB_ID;
    }

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String get_displayName() {
        return _displayName;
    }

    public void set_displayName(String displayName) {
        this._displayName = displayName;
    }

    public String getSchemaClass() {
        return schemaClass;
    }

    public void setLabels(Collection<String> labels) {
        this.schemaClass = DatabaseObjectUtils.getSchemaClass(labels);
    }

    public static SimpleDatabaseObject build(Record record) {
        SimpleDatabaseObject simpleDatabaseObject = new SimpleDatabaseObject();
        simpleDatabaseObject.setDB_ID(record.get("DB_ID").asLong());
        simpleDatabaseObject.setStId(record.get("stId").asString(null));
        simpleDatabaseObject.set_displayName(record.get("_displayName").asString());
        simpleDatabaseObject.setLabels(record.get("labels").asList(Value::asString));
        return simpleDatabaseObject;
    }

    public static SimpleDatabaseObject build(Value value) {
        SimpleDatabaseObject simpleDatabaseObject = new SimpleDatabaseObject();
        simpleDatabaseObject.setDB_ID(value.get("DB_ID").asLong());
        simpleDatabaseObject.setStId(value.get("stId").asString(null));
        simpleDatabaseObject.set_displayName(value.get("_displayName").asString());
        if(!value.get("labels").isNull()) {
            simpleDatabaseObject.setLabels(value.get("labels").asList(Value::asString));
        } else {
            simpleDatabaseObject.setSchemaClass(value.get("schemaClass").asString());
        }
        return simpleDatabaseObject;
    }

    private void setSchemaClass(String schemaClass) {
        this.schemaClass = schemaClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleDatabaseObject that = (SimpleDatabaseObject) o;

        return Objects.equals(DB_ID, that.DB_ID);
    }

    @Override
    public int hashCode() {
        return DB_ID != null ? DB_ID.hashCode() : 0;
    }
}
