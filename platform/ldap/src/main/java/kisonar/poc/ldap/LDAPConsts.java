package kisonar.poc.ldap;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;

public final class LDAPConsts {

    static final String OBJECT_CLASS = "objectclass";
    static final String INETORGPERSON = "inetorgperson";
    static final Attribute PERSON = new BasicAttribute(OBJECT_CLASS, "person");
    static final Attribute ORGANIZATIONAL_PERSON = new BasicAttribute(OBJECT_CLASS, "organizationalPerson");
    static final Attribute INET_ORG_PERSON = new BasicAttribute(OBJECT_CLASS, INETORGPERSON); // requires cn
    static final Attribute POSSIX_ACCOUNT = new BasicAttribute(OBJECT_CLASS, "posixAccount");
    static final Attribute SHADOW_ACCOUNT = new BasicAttribute(OBJECT_CLASS, "shadowAccount");

    static final String UID = "uid";
    static final String UID_ALL = "(" + UID + "=*)";
    static final String OU = "ou";
    static final String OU_ALL = "(" + OU + "=*)";

    static final String SN = "sn";
    static final String CN = "cn";
    static final String HOME_DIRECTORY = "homeDirectory";
    static final String EMAIL = "e-mail";

    private LDAPConsts() {

    }
}
