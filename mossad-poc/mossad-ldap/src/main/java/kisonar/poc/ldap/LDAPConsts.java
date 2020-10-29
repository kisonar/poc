package kisonar.poc.ldap;

import javax.naming.directory.Attribute;
import javax.naming.directory.BasicAttribute;

public final class LDAPConsts {

    private static final String OBJECT_CLASS = "objectclass";
    public static final String INETORGPERSON = "inetorgperson";
    public static final Attribute PERSON = new BasicAttribute(OBJECT_CLASS, "person");
    public static final Attribute ORGANIZATIONAL_PERSON = new BasicAttribute(OBJECT_CLASS, "organizationalPerson");
    public static final Attribute INET_ORG_PERSON = new BasicAttribute(OBJECT_CLASS, INETORGPERSON); // requires cn
    public static final Attribute POSSIX_ACCOUNT = new BasicAttribute(OBJECT_CLASS, "posixAccount");
    public static final Attribute SHADOW_ACCOUNT = new BasicAttribute(OBJECT_CLASS, "shadowAccount");

    public static final String UID_ALL = "(uid=*)";
    public static final String OU_ALL = "(ou=*)";
    public static final String UID = "uid";
    public static final String SN = "sn";
    public static final String CN = "cn";
    public static final String OU = "ou";
    public static final String HOME_DIRECTORY = "homeDirectory";
    public static final String EMAIL = "e-mail";

    private LDAPConsts() {

    }
}
