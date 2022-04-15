package org.reactome.server.graph.curator.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.reactome.server.graph.curator.domain.annotations.ReactomeProperty;
import org.reactome.server.graph.curator.domain.annotations.ReactomeSchemaIgnore;
import org.reactome.server.graph.curator.domain.annotations.ReactomeTransient;
import org.reactome.server.graph.curator.domain.result.DatabaseObjectLike;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

/**
 * DatabaseObject contains the minimum fields used to define an instance of an Reactome entry
 * <p>
 * <p>
 * For the JsonIdentityInfo, when assigning generator as ObjectIdGenerators.PropertyGenerator could
 * slow down the json serialisation due to a paging problem. Right now the @JsonIgnore annotations
 * have been added to avoid serialising the not necessary attributes, but in case those are removed
 * the best thing is to remove the mentioned property
 */
@SuppressWarnings("unused")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "dbId")
@Node
public abstract class DatabaseObject implements Serializable, Comparable<DatabaseObject>, DatabaseObjectLike {

    @Id
    protected Long dbId;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT);
    @ReactomeProperty
    private String displayName;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT);
    @Relationship(type = "created", direction = Relationship.Direction.INCOMING)
    private InstanceEdit created;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT);
    @Relationship(type = "modified", direction = Relationship.Direction.INCOMING)
    private InstanceEdit modified;

    @ReactomeConstraint(constraint = ReactomeConstraint.Constraint.NOMANUALEDIT);
    @Relationship(type = "stableIdentifier")
    private StableIdentifier stableIdentifier;

    public DatabaseObject() {
    }

    public DatabaseObject(Long dbId) {
        this.dbId = dbId;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStId() {
        return getStableIdentifier().getStId();
    }

    public InstanceEdit getCreated() {
        return created;
    }

    public void setCreated(InstanceEdit created) {
        this.created = created;
    }

    public InstanceEdit getModified() {
        return modified;
    }

    public void setModified(InstanceEdit modified) {
        this.modified = modified;
    }

    public StableIdentifier getStableIdentifier() {
        return stableIdentifier;
    }

    public void setStableIdentifier(StableIdentifier  stableIdentifier) {
        this.stableIdentifier = stableIdentifier;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                (stableIdentifier.getIdentifier() == null ? "dbId=" + dbId : "dbId=" + dbId + ", stId='" + stableIdentifier.getIdentifier() + '\'') +
                ", displayName='" + displayName + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DatabaseObject that = (DatabaseObject) o;
        return dbId != null ? dbId.equals(that.dbId) : that.dbId == null &&
                (stableIdentifier.getIdentifier() != null ?
                        stableIdentifier.getIdentifier().equals(that.stableIdentifier.getIdentifier()) :
                        that.stableIdentifier.getIdentifier() == null && Objects.equals(displayName, that.displayName));
    }

    @Override
    public int hashCode() {
        int result = dbId != null ? dbId.hashCode() : 0;
        result = 31 * result + (stableIdentifier.getIdentifier() != null ? stableIdentifier.getIdentifier().hashCode() : 0);
        result = 31 * result + (displayName != null ? displayName.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(@NonNull DatabaseObject o) {
        return this.dbId.compareTo(o.dbId);
    }

    public final String getSchemaClass() {
        return getClass().getSimpleName();
    }

    public static DatabaseObject emptyObject() {
        return new Pathway();
    }

    @ReactomeSchemaIgnore
    @JsonIgnore
    public String getExplanation() {
        return "Not available";
    }

    @ReactomeSchemaIgnore //In some classes it is overridden to provide an easier-to-understand name
    public String getClassName() {
        return getClass().getSimpleName();
    }

    public <T> T fetchSingleValue(String methodName) {
        try {
            Method method = getClass().getMethod(methodName);
            //noinspection unchecked
            return (T) method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    public <T> Collection<T> fetchMultiValue(String methodName) {
        try {
            Method method = this.getClass().getMethod(methodName);
            //noinspection unchecked
            return (Collection<T>) method.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            return new ArrayList<>();
        }
    }
}
