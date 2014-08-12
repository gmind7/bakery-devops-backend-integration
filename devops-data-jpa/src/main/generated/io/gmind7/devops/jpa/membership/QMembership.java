package io.gmind7.devops.jpa.membership;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QMembership is a Querydsl query type for Membership
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QMembership extends EntityPathBase<Membership> {

    private static final long serialVersionUID = 1493129234;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMembership membership = new QMembership("membership");

    public final QMembershipIDs ids;

    public QMembership(String variable) {
        this(Membership.class, forVariable(variable), INITS);
    }

    public QMembership(Path<? extends Membership> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembership(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QMembership(PathMetadata<?> metadata, PathInits inits) {
        this(Membership.class, metadata, inits);
    }

    public QMembership(Class<? extends Membership> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.ids = inits.isInitialized("ids") ? new QMembershipIDs(forProperty("ids")) : null;
    }

}

