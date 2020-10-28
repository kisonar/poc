package kisonar.poc.ldap;

import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;

public final class LDAPConsts {

    private static final String OBJECT_CLASS = "objectclass";
    public static final Attribute PERSON = new BasicAttribute(OBJECT_CLASS, "person");
    public static final Attribute ORGANIZATIONAL_PERSON = new BasicAttribute(OBJECT_CLASS, "organizationalPerson");
    public static final Attribute INET_ORG_PERSON = new BasicAttribute(OBJECT_CLASS, "inetorgperson"); // requires cn
    public static final Attribute POSSIX_ACCOUNT = new BasicAttribute(OBJECT_CLASS, "posixAccount");
    public static final Attribute SHADOW_ACCOUNT = new BasicAttribute(OBJECT_CLASS, "shadowAccount");

    private LDAPConsts() {

    }
}
