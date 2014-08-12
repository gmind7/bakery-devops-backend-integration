package io.gmind7.devops.jpa.user;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1310669106;

    public static final QUser user = new QUser("user");

    public final org.springframework.data.jpa.domain.QAbstractPersistable _super = new org.springframework.data.jpa.domain.QAbstractPersistable(this);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath displayName = createString("displayName");

    public final ListPath<io.gmind7.devops.jpa.group.Group, io.gmind7.devops.jpa.group.QGroup> groups = this.<io.gmind7.devops.jpa.group.Group, io.gmind7.devops.jpa.group.QGroup>createList("groups", io.gmind7.devops.jpa.group.Group.class, io.gmind7.devops.jpa.group.QGroup.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final StringPath lowerName = createString("lowerName");

    public final StringPath mail = createString("mail");

    public final StringPath name = createString("name");

    public final StringPath userId = createString("userId");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata<?> metadata) {
        super(User.class, metadata);
    }

}

