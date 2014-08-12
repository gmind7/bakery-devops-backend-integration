package io.gmind7.devops.jpa.membership;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QMembershipIDs is a Querydsl query type for MembershipIDs
 */
@Generated("com.mysema.query.codegen.EmbeddableSerializer")
public class QMembershipIDs extends BeanPath<MembershipIDs> {

    private static final long serialVersionUID = -1163202202;

    public static final QMembershipIDs membershipIDs = new QMembershipIDs("membershipIDs");

    public final NumberPath<Long> groupId = createNumber("groupId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QMembershipIDs(String variable) {
        super(MembershipIDs.class, forVariable(variable));
    }

    public QMembershipIDs(Path<? extends MembershipIDs> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMembershipIDs(PathMetadata<?> metadata) {
        super(MembershipIDs.class, metadata);
    }

}

