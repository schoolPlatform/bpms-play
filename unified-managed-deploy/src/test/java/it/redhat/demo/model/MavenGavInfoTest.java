package it.redhat.demo.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import it.redhat.demo.model.MavenGavInfo.Affinity;

@RunWith(JUnit4.class)
public class MavenGavInfoTest {
	
	@Test
	public void verifyCreation_no_snapshot_valid_version() {
		MavenGavInfo gav = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21");
		
		assertEquals("it.redhat.demo", gav.getGroupId());
		assertEquals("mykjar", gav.getArtifactId());
		assertEquals(1, gav.getMajorVersion());
		assertEquals(0, gav.getMinorVersion());
		assertEquals(21, gav.getMiniVersion());
		assertFalse(gav.isSnapshot());
		
	}
	
	@Test
	public void verifyCreation_snapshot_valid_version() {
		MavenGavInfo gav = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21-SNAPSHOT");
		
		assertEquals("it.redhat.demo", gav.getGroupId());
		assertEquals("mykjar", gav.getArtifactId());
		assertEquals(1, gav.getMajorVersion());
		assertEquals(0, gav.getMinorVersion());
		assertEquals(21, gav.getMiniVersion());
		assertTrue(gav.isSnapshot());
	}
	
	@Test(expected = RuntimeException.class)
	public void verifyCreation_invalid_version() {
		new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21-SN");
	}
	
	@Test
	public void affinity_diff_artifact() {
		
		MavenGavInfo gavA = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21-SNAPSHOT");
		MavenGavInfo gavB = new MavenGavInfo("it.redhat.demo", "mykjarb", "1.0.21-SNAPSHOT");
		
		Affinity affinityAB = gavA.affinity(gavB);
		Affinity affinityBA = gavB.affinity(gavA);
		
		assertEquals(Affinity.DIFFERENT_ARTIFACT, affinityAB);
		assertEquals(Affinity.DIFFERENT_ARTIFACT, affinityBA);
		
	}
	
	@Test
	public void affinity_diff_major() {
		
		MavenGavInfo gavA = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21-SNAPSHOT");
		MavenGavInfo gavB = new MavenGavInfo("it.redhat.demo", "mykjar", "2.0.21-SNAPSHOT");
		
		Affinity affinityAB = gavA.affinity(gavB);
		Affinity affinityBA = gavB.affinity(gavA);
		
		assertEquals(Affinity.DIFFERENT_MAJOR, affinityAB);
		assertEquals(Affinity.DIFFERENT_MAJOR, affinityBA);
		
	}
	
	@Test
	public void affinity_diff_minor() {
		
		MavenGavInfo gavA = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21");
		MavenGavInfo gavB = new MavenGavInfo("it.redhat.demo", "mykjar", "1.2.21");
		
		Affinity affinityAB = gavA.affinity(gavB);
		Affinity affinityBA = gavB.affinity(gavA);
		
		assertEquals(Affinity.DIFFERENT_MINOR, affinityAB);
		assertEquals(Affinity.DIFFERENT_MINOR, affinityBA);
		
	}
	
	@Test
	public void affinity_diff_mini() {
		
		MavenGavInfo gavA = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21");
		MavenGavInfo gavB = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.22");
		
		Affinity affinityAB = gavA.affinity(gavB);
		Affinity affinityBA = gavB.affinity(gavA);
		
		assertEquals(Affinity.DIFFERNT_MINI, affinityAB);
		assertEquals(Affinity.DIFFERNT_MINI, affinityBA);
		
	}
	
	@Test
	public void affinity_diff_snap() {
		
		MavenGavInfo gavA = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21");
		MavenGavInfo gavB = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21-SNAPSHOT");
		
		Affinity affinityAB = gavA.affinity(gavB);
		Affinity affinityBA = gavB.affinity(gavA);
		
		assertEquals(Affinity.DIFFERNT_SNAPSHOT, affinityAB);
		assertEquals(Affinity.DIFFERNT_SNAPSHOT, affinityBA);
		
	}
	
	@Test
	public void equals() {
		
		MavenGavInfo gavA = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21");
		MavenGavInfo gavB = new MavenGavInfo("it.redhat.demo", "mykjar", "1.0.21");
		
		Affinity affinityAB = gavA.affinity(gavB);
		Affinity affinityBA = gavB.affinity(gavA);
		
		assertEquals(Affinity.EQUALS, affinityAB);
		assertEquals(Affinity.EQUALS, affinityBA);
		
	}

}
