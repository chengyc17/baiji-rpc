package com.baiji.spi;

public class DeployInfo {
    private String groupId;
    private String artifactId;
    private String version;

    private boolean isSnapshot;

    public DeployInfo() {
    }

    public DeployInfo(String groupId, String artifactId, String version, boolean isSnapshot) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.isSnapshot = isSnapshot;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isSnapshot() {
        return isSnapshot;
    }

    public void setSnapshot(boolean snapshot) {
        isSnapshot = snapshot;
    }
}
