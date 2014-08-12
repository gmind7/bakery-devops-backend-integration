package io.gmind7.devops.jpa.project;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QProject is a Querydsl query type for Project
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QProject extends EntityPathBase<Project> {

    private static final long serialVersionUID = 1639917352;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProject project = new QProject("project");

    public final org.springframework.data.jpa.domain.QAbstractPersistable _super = new org.springframework.data.jpa.domain.QAbstractPersistable(this);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath displayName = createString("displayName");

    public final io.gmind7.devops.jpa.group.QGroup group;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath language = createString("language");

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final StringPath lowerName = createString("lowerName");

    public final StringPath name = createString("name");

    public final StringPath projectKey = createString("projectKey");

    public final StringPath sonarId = createString("sonarId");

    public final StringPath version = createString("version");

    public QProject(String variable) {
        this(Project.class, forVariable(variable), INITS);
    }

    public QProject(Path<? extends Project> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProject(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QProject(PathMetadata<?> metadata, PathInits inits) {
        this(Project.class, metadata, inits);
    }

    public QProject(Class<? extends Project> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.group = inits.isInitialized("group") ? new io.gmind7.devops.jpa.group.QGroup(forProperty("group")) : null;
    }

}

