package com.multibrand.helper;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.multibrand.util.Constants;
import com.multibrand.util.EnvMessageReader;
import com.multibrand.util.JNDILDAPConnectionManager;

@Test(singleThreaded = true)
public class LDAPHelperTest implements Constants {

	@Mock
	protected EnvMessageReader envMessageReader;

	@InjectMocks
	private LDAPHelper ldapHelper;

	@Spy
	JNDILDAPConnectionManager authenticatorSpy;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetLdapUserinfo() {

		try {

			when(envMessageReader.getMessage(LDAP_PROVIDER_URL)).thenReturn("ldap://txaixdocud03:21391");

			when(envMessageReader.getMessage(LDAP_SECURITY_AUTHENTICATION)).thenReturn("simple");

			when(envMessageReader.getMessage(LDAP_ADMIN_UID)).thenReturn("uid=ocsadmin,ou=cagroup,o=admin");

			when(envMessageReader.getMessage(LDAP_SECURITY_CREDENTIALS)).thenReturn("tester");

			DirContext dirContext = Mockito.mock(DirContext.class);

			doReturn(dirContext).when(authenticatorSpy).getLDAPDirContext();

			Attributes attributes = Mockito.mock(Attributes.class);

			doReturn(attributes).when(dirContext).getAttributes(Mockito.anyString());

			// ArgumentCaptor<SearchControls> argumentCaptor =
			// ArgumentCaptor.forClass(SearchControls.class);
			// NamingEnumeration answer = mock(NamingEnumeration.class);
			// doReturn(answer).when(dirContext).search(anyString(), anyString(),
			// argumentCaptor.capture());

			assertNotNull(ldapHelper.getLdapUserinfo("khouser06"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
