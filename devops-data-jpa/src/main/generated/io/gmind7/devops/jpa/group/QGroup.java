package io.gmind7.devops.jpa.group;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QGroup is a Querydsl query type for Group
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QGroup extends EntityPathBase<Group> {

    private static final long serialVersionUID = -1960672716;

    public static final QGroup group = new QGroup("group1");

    public final org.springframework.data.jpa.domain.QAbstractPersistable _super = new org.springframework.data.jpa.domain.QAbstractPersistable(this);

    public final StringPath createdBy = createString("createdBy");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath displayName = createString("displayName");

    public final StringPath groupId = createString("groupId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lastModifiedBy = createString("lastModifiedBy");

    public final DateTimePath<java.util.Date> lastModifiedDate = createDateTime("lastModifiedDate", java.util.Date.class);

    public final StringPath lowerName = createString("lowerName");

    public final StringPath mail = createString("mail");

    public final StringPath name = createString("name");

    public final ListPath<io.gmind7.devops.jpa.user.User, io.gmind7.devops.jpa.user.QUser> users = this.<io.gmind7.devops.jpa.user.User, io.gmind7.devops.jpa.user.QUser>createList("users", io.gmind7.devops.jpa.user.User.class, io.gmind7.devops.jpa.user.QUser.class, PathInits.DIRECT2);

    public QGroup(String variable) {
        super(Group.class, forVariable(variable));
    }

    public QGroup(Path<? extends Group> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGroup(PathMetadata<?> metadata) {
        super(Group.class, metadata);
    }

}

